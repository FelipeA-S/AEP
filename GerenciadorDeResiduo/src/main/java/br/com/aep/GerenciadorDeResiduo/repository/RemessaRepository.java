package br.com.aep.GerenciadorDeResiduo.repository;

import br.com.aep.GerenciadorDeResiduo.model.Remessa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemessaRepository extends JpaRepository<Remessa, Long> {
}