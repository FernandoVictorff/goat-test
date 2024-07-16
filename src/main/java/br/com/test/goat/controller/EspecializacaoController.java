package br.com.test.goat.controller;

import br.com.test.goat.controller.interfaces.IEspecializacaoController;
import br.com.test.goat.dto.CreateEspecializacaoDTO;
import br.com.test.goat.dto.EspecializacaoDTO;
import br.com.test.goat.dto.UpdateEspecializacaoDTO;
import br.com.test.goat.service.interfaces.IEspecializacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("especializacao")
public class EspecializacaoController implements IEspecializacaoController<Long> {

    private final IEspecializacaoService especializacaoService;

    public EspecializacaoController(IEspecializacaoService especializacaoService) {
        this.especializacaoService = especializacaoService;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EspecializacaoDTO> findById(@PathVariable("id") Long id) {
        return especializacaoService.findById(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<EspecializacaoDTO> create(@RequestBody CreateEspecializacaoDTO createEspecializacaoDTO) {
        return especializacaoService.create(createEspecializacaoDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<EspecializacaoDTO> update(@RequestBody UpdateEspecializacaoDTO updateEspecializacaoDTO, @PathVariable("id") Long id) {
        return especializacaoService.update(updateEspecializacaoDTO, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        return especializacaoService.delete(id);
    }

    @Override
    @GetMapping("/servidor/{idServidor}")
    public ResponseEntity<List<EspecializacaoDTO>> findAllEspecializacaoByIdServidor(@PathVariable("idServidor") Long idServidor) {
        return especializacaoService.findAllEspecializacaoByIdServidor(idServidor);
    }

    @Override
    @GetMapping("/approve/{idEspecializacao}")
    public ResponseEntity toApprove(@PathVariable("idEspecializacao") Long idEspecializacao) {
        return especializacaoService.toApprove(idEspecializacao);
    }

    @Override
    @PostMapping("/reprove/{idEspecializacao}")
    public ResponseEntity toApprove(
        @PathVariable("idEspecializacao") Long idEspecializacao,
        @RequestBody String reason
    ) {
        return especializacaoService.toReprove(idEspecializacao, reason);
    }
}
