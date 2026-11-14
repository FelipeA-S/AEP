package br.com.aep.GerenciadorDeResiduo.repository;

import br.com.aep.GerenciadorDeResiduo.model.Estoque;
import br.com.aep.GerenciadorDeResiduo.model.TipoResiduo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByTipoResiduo(TipoResiduo tipoResiduo);
}