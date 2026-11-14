package br.com.aep.GerenciadorDeResiduo.service;

import br.com.aep.GerenciadorDeResiduo.model.Estoque;
import br.com.aep.GerenciadorDeResiduo.model.TipoResiduo;
import br.com.aep.GerenciadorDeResiduo.repository.EstoqueRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public List<Estoque> listarEstoques() {
        return repository.findAll(Sort.by("idEstoque"));
    }

    public Estoque buscarPorTipo(TipoResiduo tipo) {
        return repository.findByTipoResiduo(tipo).orElse(null);
    }

    public void adicionarQuantidade(TipoResiduo tipo, BigDecimal quantidade) {
        Estoque estoque = buscarPorTipo(tipo);
        if (estoque == null) {
            throw new IllegalArgumentException(
                    "Estoque não encontrado para o tipo: " + tipo
            );
        }
        estoque.setQuantidadeTotal(
                estoque.getQuantidadeTotal().add(quantidade)
        );
        repository.save(estoque);
    }

    public void atualizarQuantidade(TipoResiduo tipo, BigDecimal novaQuantidade) {
        Estoque estoque = buscarPorTipo(tipo);
        if (estoque == null) {
            throw new IllegalArgumentException(
                    "Estoque não encontrado para o tipo: " + tipo
            );
        }
        if (novaQuantidade.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O estoque não pode ficar negativo."
            );
        }
        estoque.setQuantidadeTotal(novaQuantidade);
        repository.save(estoque);
    }
}