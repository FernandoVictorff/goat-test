package br.com.test.goat.controller;

import br.com.test.goat.controller.interfaces.IServidorController;
import br.com.test.goat.dto.CreateServidorDTO;
import br.com.test.goat.dto.ServidorDTO;
import br.com.test.goat.dto.UpdateServidorDTO;
import br.com.test.goat.service.interfaces.IServidorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servidor")
public class ServidorController implements IServidorController<Long> {

    private final IServidorService service;

    public ServidorController(IServidorService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ServidorDTO> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<ServidorDTO> create(@RequestBody CreateServidorDTO createServidorDTO) {
        return service.create(createServidorDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody UpdateServidorDTO updateServidorDTO, @PathVariable("id") Long id) {
        return service.update(updateServidorDTO, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
