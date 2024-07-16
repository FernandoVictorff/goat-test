package br.com.test.goat.validator;

import br.com.test.goat.dto.CreateServidorDTO;
import br.com.test.goat.dto.UpdateServidorDTO;
import br.com.test.goat.enums.SexoServidor;
import br.com.test.goat.repository.ServidorRepository;
import br.com.test.goat.validator.interfaces.IServidorValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

import static br.com.test.goat.enums.SexoServidor.getSexoServidorByCharacter;
import static br.com.test.goat.enums.TipoServidor.getTipoServidorByTipo;
import static br.com.test.goat.enums.TipoServidor.values;
import static br.com.test.goat.validator.ValidatorUtils.throwOnError;
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
            var validationErrors2 = new ValidationErrors();
            if (!error.getErrorCode().toUpperCase().contains("NULL")) {
                validationErrors2.add(error.getField(), error.getErrorCode());
                validationErrors = validationErrors2;
            }
        }
        throwOnError(validationErrors);
    }

    private boolean validateIsTipoValid(Integer tipoServidor, ValidationErrors errors) {
        boolean isTipoValid = nonNull(tipoServidor) && Arrays.stream(values()).toList().contains(getTipoServidorByTipo(tipoServidor));
        if (isTipoValid) return true;
        if (nonNull(tipoServidor)) errors.add("tipoServidor", "tipoServidor" + ".tipoIsInvalid");
        if (isNull(tipoServidor)) errors.add("tipoServidor", "tipoServidor" + ".tipoIsNull");
        return false;
    }

    private boolean validateIsSexoValid(Character sexo, ValidationErrors errors) {
        boolean isSexoValid = nonNull(sexo) && Arrays.stream(SexoServidor.values()).toList().contains(getSexoServidorByCharacter(sexo));
        if (isSexoValid) return true;
        if (nonNull(sexo)) errors.add("sexo", "sexo" + ".sexoIsInvalid");
        if (isNull(sexo)) errors.add("sexo", "sexo" + ".sexoIsNull");
        return false;
    }

    private boolean validateIsLegalAge(LocalDate dataNascimento, ValidationErrors errors) {
        boolean isLegalAge = nonNull(dataNascimento) && LocalDate.now().minusYears(18).compareTo(dataNascimento) >= 0;
        if (isLegalAge) return true;
        if (nonNull(dataNascimento)) errors.add("dataNascimento", "dataNascimento" + ".isUnderAge");
        if (isNull(dataNascimento)) errors.add("dataNascimento", "dataNascimento" + ".dataNascimentoIsNull");
        return false;
    }

    private boolean validateName(String nome, ValidationErrors errors) {
        boolean isNameValid = nonNull(nome) && nome.length() > 0 && nome.length() < 60;
        if (isNameValid) return true;
        if (nonNull(nome)) errors.add("nome", "nome" + ".isNomeInvalid");
        if (isNull(nome)) errors.add("nome", "nome" + ".nomeIsNull");
        return false;
    }

    private boolean validateIsEmailValid(String email, ValidationErrors errors) {
        boolean isEmailValid = nonNull(email) && email.contains("@");
        if (isEmailValid) return true;
        if (nonNull(email)) errors.add("email", "email" + ".emailIsInvalid");
        if (isNull(email)) errors.add("email", "email" + ".emailIsNull");
        return false;
    }

    private boolean validateIsCpfValid(String cpf, ValidationErrors errors) {
        boolean isCpfValid =  nonNull(cpf) && cpf.length() == 11;
        if (isCpfValid) return true;
        if (nonNull(cpf)) errors.add("cpf", "cpf" + ".cpfIsInvalid");
        if (isNull(cpf)) errors.add("cpf", "cpf" + ".cpfIsNull");
        return false;
    }
}
