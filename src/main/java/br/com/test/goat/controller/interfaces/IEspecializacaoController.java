package br.com.test.goat.controller.interfaces;

import br.com.test.goat.dto.CreateEspecializacaoDTO;
import br.com.test.goat.dto.EspecializacaoDTO;
import br.com.test.goat.dto.UpdateEspecializacaoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEspecializacaoController<T> {
    ResponseEntity findById(T id);
    ResponseEntity<EspecializacaoDTO> create(CreateEspecializacaoDTO createEspecializacaoDTO);
    ResponseEntity update(UpdateEspecializacaoDTO updateEspecializacaoDTO, T id);
    ResponseEntity deleteById(T id);
    ResponseEntity<List<EspecializacaoDTO>> findAllEspecializacaoByIdServidor(Long idServidor);
    ResponseEntity toApprove(Long idEspecializacao);
    ResponseEntity toApprove(Long idEspecializacao, String reason);
}
