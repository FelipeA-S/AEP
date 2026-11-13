package br.com.aep.GerenciadorDeResiduo.service;

import br.com.aep.GerenciadorDeResiduo.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

@Service
public class RemessaService {
    private final ResiduoService residuoService;
    private final EstoqueService estoqueService;
    private final List<Remessa> remessas = new ArrayList<>();

    private Long proximoIdRemessa = 1L;
    private Long proximoIdItem = 1L;

    public RemessaService(ResiduoService residuoService, EstoqueService estoqueService) {
        this.residuoService = residuoService;
        this.estoqueService = estoqueService;
    }

    public Remessa cadastrarRemessa(List<ItemRemessa> itensRecebidos) {
        if (itensRecebidos == null || itensRecebidos.isEmpty()) {
            throw new IllegalArgumentException(
                    "A remessa deve possuir pelo menos um resíduo."
            );
        }
        List<ItemRemessa> itensRegistrados = new ArrayList<>();

        for (ItemRemessa item : itensRecebidos) {
            if (item.getResiduo() == null ||
                    item.getQuantidade() == null ||
                    item.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException(
                        "Todos os itens devem possuir resíduo e quantidade válida."
                );
            }
            Residuo residuo = residuoService.buscarPorId(
                    item.getResiduo().getIdResiduo()
            );
            if (residuo == null) {
                throw new IllegalArgumentException(
                        "Resíduo não encontrado no catálogo."
                );
            }
            itensRegistrados.add(
                    new ItemRemessa(
                            proximoIdItem + itensRegistrados.size(),
                            residuo,
                            item.getQuantidade()
                    )
            );
        }

        for (ItemRemessa item : itensRegistrados) {
            estoqueService.adicionarQuantidade(
                    item.getResiduo().getTipoResiduo(),
                    item.getQuantidade()
            );
        }

        Remessa remessa = new Remessa(
                proximoIdRemessa,
                LocalDateTime.now(),
                itensRegistrados
        );
        remessas.add(remessa);

        proximoIdRemessa++;
        proximoIdItem += itensRegistrados.size();
        return remessa;
    }

    public List<Remessa> listarRemessas() {
        return remessas;
    }

    public Remessa buscarPorId(Long id) {
        return remessas.stream()
                .filter(remessa -> remessa.getIdRemessa().equals(id))
                .findFirst()
                .orElse(null);
    }
}
