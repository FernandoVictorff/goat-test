package br.com.test.goat.validator;

import br.com.test.goat.dto.CreateServidorDTO;
import br.com.test.goat.dto.UpdateServidorDTO;
import br.com.test.goat.enums.SexoServidor;
import br.com.test.goat.repository.ServidorRepository;
import br.com.test.goat.validator.interfaces.IServidorValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static br.com.test.goat.enums.SexoServidor.*;
import static br.com.test.goat.enums.TipoServidor.getTipoServidorByTipo;
import static br.com.test.goat.enums.TipoServidor.values;
import static br.com.test.goat.validator.ValidatorConstants.TASK_NOME;
import static br.com.test.goat.validator.ValidatorConstants.TASK_NOME_MAX_LENGTH;
import static br.com.test.goat.validator.ValidatorUtils.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class ServidorValidator implements IServidorValidator {

    private final ServidorRepository servidorRepository;

    public ServidorValidator(ServidorRepository servidorRepository) {
        this.servidorRepository = servidorRepository;
    }

    @Override
    public void validate(CreateServidorDTO createServidorDTO) {
        var validationErrors = new ValidationErrors();

        validateIsTipoValid(createServidorDTO.tipoServidor(), validationErrors);
        validateIsSexoValid(createServidorDTO.sexo(), validationErrors);
        validateIsCpfValid(createServidorDTO.cpf(), validationErrors);
        validateIsEmailValid(createServidorDTO.email(), validationErrors);
        validateName(createServidorDTO.nome(), validationErrors);
        validateIsLegalAge(createServidorDTO.dataNascimento(), validationErrors);

        throwOnError(validationErrors);
    }

    @Override
    public void validate(UpdateServidorDTO updateServidorDTO) {
        var validationErrors = new ValidationErrors();

        validateIsTipoValid(updateServidorDTO.tipoServidor(), validationErrors);
        validateIsSexoValid(updateServidorDTO.sexo(), validationErrors);
        validateIsCpfValid(updateServidorDTO.cpf(), validationErrors);
        validateIsEmailValid(updateServidorDTO.email(), validationErrors);
        validateName(updateServidorDTO.nome(), validationErrors);
        validateIsLegalAge(updateServidorDTO.dataNascimento(), validationErrors);

        for (var error : validationErrors) {
            if (error.getErrorCode().contains("null")) validationErrors.remove(error);
        }
        throwOnError(validationErrors);
    }

    private boolean validateIsTipoValid(Integer tipoServidor, ValidationErrors errors) {
        boolean isTipoValid = nonNull(tipoServidor) && Arrays.stream(values()).toList().contains(getTipoServidorByTipo(tipoServidor));
        if (isTipoValid) return true;
        if (nonNull(tipoServidor)) errors.add(String.valueOf(tipoServidor), tipoServidor + ".tipoIsInvalid");
        if (isNull(tipoServidor)) errors.add("tipoServidor", "tipoServidor" + ".tipoIsNull");
        return false;
    }

    private boolean validateIsSexoValid(Character sexo, ValidationErrors errors) {
        boolean isSexoValid = nonNull(sexo) && Arrays.stream(SexoServidor.values()).toList().contains(getSexoServidorByCharacter(sexo));
        if (isSexoValid) return true;
        if (nonNull(sexo)) errors.add(String.valueOf(sexo), sexo + ".sexoIsInvalid");
        if (isNull(sexo)) errors.add("sexo", "null" + ".sexoIsNull");
        return false;
    }

    private boolean validateIsLegalAge(LocalDate dataNascimento, ValidationErrors errors) {
        boolean isLegalAge = nonNull(dataNascimento) && LocalDate.now().minusYears(18).compareTo(dataNascimento) >= 0;
        if (isLegalAge) return true;
        if (nonNull(dataNascimento)) {
            String date = dataNascimento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            errors.add(date, date + ".isUnderAge");
        }
        if (isNull(dataNascimento)) errors.add("dataNascimento", "null" + ".dataNascimentoIsNull");
        return false;
    }

    private boolean validateName(String nome, ValidationErrors errors) {
        return (
            validateRequired(nome, TASK_NOME, errors) &&
            validateMaxLength(nome, TASK_NOME, TASK_NOME_MAX_LENGTH, errors)
        );
    }

    private boolean validateIsEmailValid(String email, ValidationErrors errors) {
        boolean isEmailValid = nonNull(email) && email.contains("@");
        if (isEmailValid) return true;
        if (nonNull(email)) errors.add(email, email + ".emailIsInvalid");
        if (isNull(email)) errors.add("email", "email" + ".emailIsNull");
        return false;
    }

    private boolean validateIsCpfValid(String cpf, ValidationErrors errors) {
        boolean isCpfValid =  nonNull(cpf) && cpf.length() == 11;
        if (isCpfValid) return true;
        if (nonNull(cpf)) errors.add(cpf, cpf + ".cpfIsInvalid");
        if (isNull(cpf)) errors.add("cpf", "cpf" + ".cpfIsNull");
        return false;
    }
}
