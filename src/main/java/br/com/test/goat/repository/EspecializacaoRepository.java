package br.com.test.goat.repository;

import br.com.test.goat.entity.Especializacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EspecializacaoRepository extends JpaRepository<Especializacao, Long> {

    @Query(value = "SELECT * FROM tb_especializacao e WHERE e.idServidor = ?1", nativeQuery = true)
    Optional<List<Especializacao>> findAllEspecializacaoByIdServidor(Long idServidor);
}
