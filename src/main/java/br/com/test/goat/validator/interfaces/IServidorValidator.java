package br.com.test.goat.validator.interfaces;

import br.com.test.goat.dto.CreateServidorDTO;
import br.com.test.goat.dto.UpdateServidorDTO;

public interface IServidorValidator {
    void validate(CreateServidorDTO createServidorDTO);

    void validate(UpdateServidorDTO updateServidorDTO);
}
