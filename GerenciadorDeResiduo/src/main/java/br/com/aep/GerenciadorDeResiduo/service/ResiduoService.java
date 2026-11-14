package br.com.aep.GerenciadorDeResiduo.service;

import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import br.com.aep.GerenciadorDeResiduo.model.TipoResiduo;
import br.com.aep.GerenciadorDeResiduo.repository.ResiduoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResiduoService {

    private final ResiduoRepository repository;

    public ResiduoService(ResiduoRepository repository) {
        this.repository = repository;
    }

    public List<Residuo> listarResiduos() {
        return repository.findAll(Sort.by("idResiduo"));
    }

    public Residuo buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Residuo buscarPorTipo(TipoResiduo tipo) {
        return repository.findByTipoResiduo(tipo).orElse(null);
    }
}