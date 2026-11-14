package br.com.aep.GerenciadorDeResiduo.repository;

import br.com.aep.GerenciadorDeResiduo.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}