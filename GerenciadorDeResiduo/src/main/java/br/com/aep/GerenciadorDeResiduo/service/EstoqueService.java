package br.com.aep.GerenciadorDeResiduo.service;

import br.com.aep.GerenciadorDeResiduo.model.Estoque;
import br.com.aep.GerenciadorDeResiduo.model.TipoResiduo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstoqueService {
    private final List<Estoque> estoques = new ArrayList<>();

    public EstoqueService() {
        Long proximoId = 1L;
        for (TipoResiduo tipo : TipoResiduo.values()) {
            Estoque estoque = new Estoque(
                    proximoId,
                    tipo,
                    BigDecimal.ZERO
            );
            estoques.add(estoque);
            proximoId++;
        }
    }

    public List<Estoque> listarEstoques() {
        return estoques;
    }

    public Estoque buscarPorTipo(TipoResiduo tipo) {
        return estoques.stream()
                .filter(estoque -> estoque.getTipoResiduo() == tipo)
                .findFirst()
                .orElse(null);
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
    }
}
