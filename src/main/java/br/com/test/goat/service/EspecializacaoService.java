package br.com.test.goat.service;

import br.com.test.goat.dto.CreateEspecializacaoDTO;
import br.com.test.goat.dto.EspecializacaoDTO;
import br.com.test.goat.dto.UpdateEspecializacaoDTO;
import br.com.test.goat.entity.Especializacao;
import br.com.test.goat.exceptions.EspecializacaoNotFoundException;
import br.com.test.goat.exceptions.ServidorNotFoundException;
import br.com.test.goat.mapper.EspecializacaoMapper;
import br.com.test.goat.repository.EspecializacaoRepository;
import br.com.test.goat.service.interfaces.IEspecializacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.test.goat.enums.StatusEspecializacao.*;
import static java.util.Objects.isNull;

@Service
public class EspecializacaoService implements IEspecializacaoService<Long> {

    private final EspecializacaoRepository especializacaoRepository;
    private final EspecializacaoMapper especializacaoMapper;

    public EspecializacaoService(
        EspecializacaoRepository especializacaoRepository,
        EspecializacaoMapper especializacaoMapper
    ) {
        this.especializacaoRepository = especializacaoRepository;
        this.especializacaoMapper = especializacaoMapper;
    }

    @Override
    public ResponseEntity<EspecializacaoDTO> findById(Long id) {
        if (especializacaoRepository.existsById(id))
            return ResponseEntity.ok(
                especializacaoMapper.fromEntityToDTO(
                    especializacaoRepository.findById(id).get()
                )
            );
        throw new ServidorNotFoundException("Especializacao not found: " + id);
    }

    @Override
    public ResponseEntity<EspecializacaoDTO> create(CreateEspecializacaoDTO createEspecializacaoDTO) {
        return ResponseEntity.ok(
            especializacaoMapper.fromEntityToDTO(
                especializacaoRepository.save(
                    Especializacao.newBuilder()
                        .idServidor(createEspecializacaoDTO.idServidor())
                        .idStatusDeferimento(AGUARDANDO_DEFERIMENTO.getCode())
                        .area(createEspecializacaoDTO.area())
                        .tipo(createEspecializacaoDTO.tipo())
                        .cargaHoraria(createEspecializacaoDTO.cargaHoraria())
                        .valorTotalDoCurso(createEspecializacaoDTO.valorTotalDoCurso())
                        .build()
                )
            )
        );
    }

    @Override
    public ResponseEntity<EspecializacaoDTO> update(UpdateEspecializacaoDTO updateEspecializacaoDTO, Long id) {
        Especializacao especializacao = especializacaoRepository.findById(id).orElseThrow(() -> new EspecializacaoNotFoundException( "Especializacao not found: " + id));
        return ResponseEntity.ok(
            especializacaoMapper.fromEntityToDTO(
                especializacaoRepository.save(
                    Especializacao.newBuilder()
                        .id(especializacao.getId())
                        .idServidor(especializacao.getIdServidor())
                        .idStatusDeferimento(especializacao.getIdStatusDeferimento())
                        .area(isNull(updateEspecializacaoDTO.area()) ? especializacao.getArea() : updateEspecializacaoDTO.area())
                        .tipo(isNull(updateEspecializacaoDTO.tipo()) ? especializacao.getTipo() : updateEspecializacaoDTO.tipo())
                        .cargaHoraria(isNull(updateEspecializacaoDTO.cargaHoraria()) ? especializacao.getCargaHoraria() : updateEspecializacaoDTO.cargaHoraria())
                        .valorTotalDoCurso(isNull(updateEspecializacaoDTO.valorTotalDoCurso()) ? especializacao.getValorTotalDoCurso() : updateEspecializacaoDTO.valorTotalDoCurso())
                        .build()
                    )
            )
        );
    }

    @Override
    public ResponseEntity delete(Long id) {
        if (especializacaoRepository.existsById(id)) {
            especializacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new EspecializacaoNotFoundException("Especializacao not found: " + id);
    }

    @Override
    public ResponseEntity<List<EspecializacaoDTO>> findAllEspecializacaoByIdServidor(Long idServidor) {
        return ResponseEntity.ok(
            especializacaoRepository.findAllEspecializacaoByIdServidor(idServidor).orElseThrow(
                () -> new EspecializacaoNotFoundException("Especializacao not found for servidor: " + idServidor)
            )
                .stream()
                .map(e -> especializacaoMapper.fromEntityToDTO(e))
                .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity toApprove(Long idEspecializacao) {
        Especializacao especializacao = especializacaoRepository.findById(idEspecializacao).orElseThrow(
            () -> new EspecializacaoNotFoundException("Especializacao not found: " + idEspecializacao)
        );

        especializacaoRepository.save(
            Especializacao.newBuilder()
                .id(especializacao.getId())
                .idServidor(especializacao.getIdServidor())
                .idStatusDeferimento(DEFERIDO.getCode())
                .area(especializacao.getArea())
                .tipo(especializacao.getTipo())
                .cargaHoraria(especializacao.getCargaHoraria())
                .valorTotalDoCurso(especializacao.getValorTotalDoCurso())
                .build()
        );

        return ResponseEntity.ok("approved especializacao");
    }

    @Override
    public ResponseEntity toReprove(Long idEspecializacao, String reason) {
        Especializacao especializacao = especializacaoRepository.findById(idEspecializacao).orElseThrow(
            () -> new EspecializacaoNotFoundException("Especializacao not found: " + idEspecializacao)
        );

        especializacaoRepository.save(
            Especializacao.newBuilder()
                .id(especializacao.getId())
                .idServidor(especializacao.getIdServidor())
                .idStatusDeferimento(INDEFERIDO.getCode())
                .area(especializacao.getArea())
                .tipo(especializacao.getTipo())
                .cargaHoraria(especializacao.getCargaHoraria())
                .valorTotalDoCurso(especializacao.getValorTotalDoCurso())
                .build()
        );

        return ResponseEntity.ok(reason);
    }
}
