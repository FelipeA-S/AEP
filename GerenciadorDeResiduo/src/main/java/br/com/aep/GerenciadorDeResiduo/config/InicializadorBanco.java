package br.com.aep.GerenciadorDeResiduo.config;

import br.com.aep.GerenciadorDeResiduo.model.Estoque;
import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import br.com.aep.GerenciadorDeResiduo.model.TipoResiduo;
import br.com.aep.GerenciadorDeResiduo.repository.EstoqueRepository;
import br.com.aep.GerenciadorDeResiduo.repository.ResiduoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

// Cria o catálogo de resíduos e o estoque zerado na primeira execução.
// @Order(1) garante que isso rode ANTES do MenuPrincipal.
@Component
@Order(1)
public class InicializadorBanco implements CommandLineRunner {

    private final ResiduoRepository residuoRepository;
    private final EstoqueRepository estoqueRepository;

    public InicializadorBanco(ResiduoRepository residuoRepository,
                              EstoqueRepository estoqueRepository) {
        this.residuoRepository = residuoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Override
    public void run(String... args) {
        for (TipoResiduo tipo : TipoResiduo.values()) {
            if (residuoRepository.findByTipoResiduo(tipo).isEmpty()) {
                residuoRepository.save(new Residuo(null, tipo, tipo.getDescricao()));
            }
            if (estoqueRepository.findByTipoResiduo(tipo).isEmpty()) {
                estoqueRepository.save(new Estoque(null, tipo, BigDecimal.ZERO));
            }
        }
    }
}