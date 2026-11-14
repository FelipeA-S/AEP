package br.com.aep.GerenciadorDeResiduo.service;

import br.com.aep.GerenciadorDeResiduo.model.Gerente;
import br.com.aep.GerenciadorDeResiduo.repository.GerenteRepository;
import org.springframework.stereotype.Service;

@Service
public class GerenteService {

    private final GerenteRepository repository;

    public GerenteService(GerenteRepository repository) {
        this.repository = repository;
    }

    public Gerente criar(double salario, String funcao, String setorResp) {
        return repository.save(new Gerente(salario, funcao, setorResp));
    }
}