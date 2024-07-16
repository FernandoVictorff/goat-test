package br.com.test.goat.service.interfaces;

import br.com.test.goat.dto.CreateServidorDTO;
import br.com.test.goat.dto.ServidorDTO;
import br.com.test.goat.dto.UpdateServidorDTO;
import org.springframework.http.ResponseEntity;

public interface IServidorService<T> {
    ResponseEntity<ServidorDTO> findById(T id);
    ResponseEntity<ServidorDTO> create(CreateServidorDTO createServidorDTO);
    ResponseEntity update(UpdateServidorDTO updateServidorDTO, T id);
    ResponseEntity delete(T id);
}
