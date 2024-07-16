package br.com.test.goat.service.interfaces;

import br.com.test.goat.dto.CreateEspecializacaoDTO;
import br.com.test.goat.dto.EspecializacaoDTO;
import br.com.test.goat.dto.UpdateEspecializacaoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEspecializacaoService<T> {
    ResponseEntity <EspecializacaoDTO> findById(T id);

    ResponseEntity<EspecializacaoDTO> create(CreateEspecializacaoDTO createEspecializacaoDTO);

    ResponseEntity<EspecializacaoDTO> update(UpdateEspecializacaoDTO updateEspecializacaoDTO, T id);

    ResponseEntity delete(T id);

    ResponseEntity<List<EspecializacaoDTO>> findAllEspecializacaoByIdServidor(T id);

    ResponseEntity toApprove(T idEspecializacao);

    ResponseEntity toReprove(T idEspecializacao, String reason);
}
