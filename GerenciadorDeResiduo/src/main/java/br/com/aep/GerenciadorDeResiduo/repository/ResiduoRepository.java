package br.com.aep.GerenciadorDeResiduo.repository;

import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import br.com.aep.GerenciadorDeResiduo.model.TipoResiduo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResiduoRepository extends JpaRepository<Residuo, Long> {
    Optional<Residuo> findByTipoResiduo(TipoResiduo tipoResiduo);
}