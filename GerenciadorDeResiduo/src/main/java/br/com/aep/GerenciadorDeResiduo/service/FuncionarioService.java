package br.com.aep.GerenciadorDeResiduo.service;

import br.com.aep.GerenciadorDeResiduo.model.Funcionario;
import br.com.aep.GerenciadorDeResiduo.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Funcionario criar(double salario, String funcao) {
        return repository.save(new Funcionario(salario, funcao));
    }
}