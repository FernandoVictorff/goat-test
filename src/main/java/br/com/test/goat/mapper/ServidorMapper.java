package br.com.test.goat.mapper;

import br.com.test.goat.dto.CreateServidorDTO;
import br.com.test.goat.dto.ServidorDTO;
import br.com.test.goat.entity.Servidor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public abstract class ServidorMapper {

    @Mapping(source = "id", target = "matricula")
    public abstract ServidorDTO fromEntityToDTO(Servidor servidor);

}
