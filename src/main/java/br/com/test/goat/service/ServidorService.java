package br.com.test.goat.service;


import br.com.test.goat.dto.CreateServidorDTO;
import br.com.test.goat.dto.ServidorDTO;
import br.com.test.goat.dto.UpdateServidorDTO;
import br.com.test.goat.entity.Servidor;
import br.com.test.goat.exceptions.ServidorNotFoundException;
import br.com.test.goat.mapper.ServidorMapper;
import br.com.test.goat.repository.ServidorRepository;
import br.com.test.goat.service.interfaces.IServidorService;
import br.com.test.goat.validator.interfaces.IServidorValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ServidorService implements IServidorService<Long> {
    
    private final ServidorRepository servidorRepository;
    private final ServidorMapper servidorMapper;
    private final IServidorValidator servidorValidator;

    public ServidorService(
        ServidorRepository servidorRepository,
        ServidorMapper servidorMapper,
        IServidorValidator servidorValidator
    ) {
        this.servidorRepository = servidorRepository;
        this.servidorMapper = servidorMapper;
        this.servidorValidator = servidorValidator;
    }

    @Override
    public ResponseEntity<ServidorDTO> findById(Long id) {
        if (servidorRepository.existsById(id))
            return ResponseEntity.ok(
                servidorMapper.fromEntityToDTO(
                    servidorRepository.findById(id).get()
                )
            );
        throw new ServidorNotFoundException("Servidor not found: " + id);
    }

    @Override
    public ResponseEntity<ServidorDTO> create(CreateServidorDTO createServidorDTO) {
        servidorValidator.validate(createServidorDTO);
        return ResponseEntity.ok(
            servidorMapper.fromEntityToDTO(
                servidorRepository.save(
                    Servidor.newBuilder()
                        .cpf(createServidorDTO.cpf())
                        .email(createServidorDTO.email())
                        .nome(createServidorDTO.nome())
                        .dataNascimento(createServidorDTO.dataNascimento())
                        .sexo(createServidorDTO.sexo())
                        .tipoServidor(createServidorDTO.tipoServidor())
                        .build()
                )
            )
        );
    }

    @Override
    public ResponseEntity update(UpdateServidorDTO updateServidorDTO, Long id) {
        Servidor servidor = servidorRepository.findById(id).orElseThrow(() -> new ServidorNotFoundException("Servidor not found: " + id));
        servidorValidator.validate(updateServidorDTO);
        return ResponseEntity.ok(
            servidorMapper.fromEntityToDTO(
                servidorRepository.save(
                    Servidor.newBuilder()
                        .cpf(isNull(updateServidorDTO.cpf()) ? servidor.getCpf() : updateServidorDTO.cpf())
                        .email(isNull(updateServidorDTO.email()) ? servidor.getEmail() : updateServidorDTO.email())
                        .nome(isNull(updateServidorDTO.nome()) ? servidor.getNome() : updateServidorDTO.nome())
                        .dataNascimento(isNull(updateServidorDTO.dataNascimento()) ? servidor.getDataNascimento() : updateServidorDTO.dataNascimento())
                        .sexo(isNull(updateServidorDTO.sexo()) ? servidor.getSexo() : updateServidorDTO.sexo())
                        .tipoServidor(isNull(updateServidorDTO.tipoServidor()) ? servidor.getTipoServidor() : updateServidorDTO.tipoServidor())
                        .build()
                )
            )
        );
    }

    @Override
    public ResponseEntity delete(Long id) {
        if (servidorRepository.existsById(id)) {
            servidorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new ServidorNotFoundException("Servidor not found: " + id);
    }
}
