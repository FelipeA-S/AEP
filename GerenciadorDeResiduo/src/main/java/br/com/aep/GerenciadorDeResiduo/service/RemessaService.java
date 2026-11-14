package br.com.aep.GerenciadorDeResiduo.service;

import br.com.aep.GerenciadorDeResiduo.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
            if (item == null ||
                    item.getResiduo() == null ||
                    item.getResiduo().getIdResiduo() == null ||
                    item.getQuantidade() == null ||
                    item.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException(
                        "A remessa possui informações inválidas."
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
            boolean duplicado = itensRegistrados.stream()
                    .anyMatch(itemRegistrado ->
                            itemRegistrado.getResiduo().getIdResiduo()
                                    .equals(residuo.getIdResiduo())
                    );
            if (duplicado) {
                throw new IllegalArgumentException(
                        "Não é permitido repetir o mesmo resíduo na remessa."
                );
            }
            Estoque estoque = estoqueService.buscarPorTipo(
                    residuo.getTipoResiduo()
            );
            if (estoque == null) {
                throw new IllegalArgumentException(
                        "Estoque não encontrado para o resíduo informado."
                );
            }
            ItemRemessa itemRegistrado = new ItemRemessa(
                    proximoIdItem + itensRegistrados.size(),
                    residuo,
                    item.getQuantidade()
            );
            itensRegistrados.add(itemRegistrado);
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

    public Remessa editarRemessa(Long idRemessa, List<ItemRemessa> novosItens) {
        Remessa remessa = buscarPorId(idRemessa);
        if (remessa == null) {
            throw new IllegalArgumentException(
                    "Remessa não encontrada."
            );
        }
        if (novosItens == null || novosItens.isEmpty()) {
            throw new IllegalArgumentException(
                    "A remessa deve possuir pelo menos um resíduo."
            );
        }
        Map<TipoResiduo, BigDecimal> variacoes = new HashMap<>();
        List<ItemRemessa> itensAtualizados = new ArrayList<>();
        for (ItemRemessa item : novosItens) {
            if (item == null ||
                    item.getResiduo() == null ||
                    item.getResiduo().getIdResiduo() == null ||
                    item.getQuantidade() == null ||
                    item.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException(
                        "A remessa possui informações inválidas."
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
            boolean duplicado = itensAtualizados.stream()
                    .anyMatch(i ->
                            i.getResiduo().getIdResiduo()
                                    .equals(residuo.getIdResiduo())
                    );
            if (duplicado) {
                throw new IllegalArgumentException(
                        "Não é permitido repetir resíduos na remessa."
                );
            }
            if (estoqueService.buscarPorTipo(
                    residuo.getTipoResiduo()) == null) {
                throw new IllegalArgumentException(
                        "Estoque não encontrado para o resíduo."
                );
            }
            ItemRemessa novoItem = new ItemRemessa(
                    proximoIdItem + itensAtualizados.size(),
                    residuo,
                    item.getQuantidade()
            );
            itensAtualizados.add(novoItem);
            variacoes.merge(
                    residuo.getTipoResiduo(),
                    item.getQuantidade(),
                    BigDecimal::add
            );
        }
        for (ItemRemessa itemAntigo : remessa.getItens()) {
            TipoResiduo tipo = itemAntigo.getResiduo().getTipoResiduo();
            variacoes.merge(
                    tipo,
                    itemAntigo.getQuantidade().negate(),
                    BigDecimal::add
            );
        }
        Map<TipoResiduo, BigDecimal> novosSaldos = new HashMap<>();
        for (Map.Entry<TipoResiduo, BigDecimal> variacao
                : variacoes.entrySet()) {
            Estoque estoque = estoqueService.buscarPorTipo(
                    variacao.getKey()
            );
            BigDecimal saldoFinal = estoque.getQuantidadeTotal()
                    .add(variacao.getValue());
            if (saldoFinal.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(
                        "A edição deixaria o estoque negativo para: "
                                + variacao.getKey().getDescricao()
                );
            }
            novosSaldos.put(variacao.getKey(), saldoFinal);
        }
        for (Map.Entry<TipoResiduo, BigDecimal> saldo
                : novosSaldos.entrySet()) {
            estoqueService.atualizarQuantidade(
                    saldo.getKey(),
                    saldo.getValue()
            );
        }
        remessa.setItens(itensAtualizados);
        proximoIdItem += itensAtualizados.size();
        return remessa;
    }

    public void excluirRemessa(Long idRemessa) {
        Remessa remessa = buscarPorId(idRemessa);
        if (remessa == null) {
            throw new IllegalArgumentException(
                    "Remessa não encontrada."
            );
        }
        Map<TipoResiduo, BigDecimal> novosSaldos = new HashMap<>();
        for (ItemRemessa item : remessa.getItens()) {
            TipoResiduo tipo = item.getResiduo().getTipoResiduo();
            Estoque estoque = estoqueService.buscarPorTipo(tipo);
            if (estoque == null) {
                throw new IllegalArgumentException(
                        "Estoque não encontrado para: " + tipo.getDescricao()
                );
            }
            BigDecimal saldoFinal = estoque.getQuantidadeTotal()
                    .subtract(item.getQuantidade());
            if (saldoFinal.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(
                        "Não é possível excluir: o estoque de "
                                + tipo.getDescricao()
                                + " ficaria negativo."
                );
            }
            novosSaldos.put(tipo, saldoFinal);
        }
        for (Map.Entry<TipoResiduo, BigDecimal> saldo
                : novosSaldos.entrySet()) {
            estoqueService.atualizarQuantidade(
                    saldo.getKey(),
                    saldo.getValue()
            );
        }
        remessas.remove(remessa);
    }

    public void gerarRelatorio() {
        System.out.println("\n========================================");
        System.out.println("       RELATÓRIO GERAL DO ATERRO");
        System.out.println("========================================");
        System.out.println(
                "Total de remessas cadastradas: " + remessas.size()
        );
        BigDecimal pesoTotal = BigDecimal.ZERO;
        for (Estoque estoque : estoqueService.listarEstoques()) {
            pesoTotal = pesoTotal.add(
                    estoque.getQuantidadeTotal()
            );
        }
        System.out.println(
                "Peso total armazenado: " + pesoTotal + " kg"
        );
        System.out.println("\n========== ESTOQUE POR CATEGORIA ==========");
        for (Estoque estoque : estoqueService.listarEstoques()) {
            BigDecimal quantidade = estoque.getQuantidadeTotal();
            BigDecimal percentual = BigDecimal.ZERO;
            if (pesoTotal.compareTo(BigDecimal.ZERO) > 0) {
                percentual = quantidade
                        .multiply(new BigDecimal("100"))
                        .divide(pesoTotal, 2, java.math.RoundingMode.HALF_UP);
            }
            System.out.println(
                    estoque.getTipoResiduo().getDescricao() +
                            ": " + quantidade +
                            " kg (" + percentual + "%)"
            );
        }
        System.out.println("\n========================================");
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
