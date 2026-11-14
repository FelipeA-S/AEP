package br.com.aep.GerenciadorDeResiduo.repository;

import br.com.aep.GerenciadorDeResiduo.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
}