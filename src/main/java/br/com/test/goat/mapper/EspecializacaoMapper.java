package br.com.test.goat.mapper;

import br.com.test.goat.dto.EspecializacaoDTO;
import br.com.test.goat.entity.Especializacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EspecializacaoMapper {
    public abstract EspecializacaoDTO fromEntityToDTO(Especializacao especializacao);
}
