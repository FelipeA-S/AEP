package br.com.aep.GerenciadorDeResiduo.model;

import java.math.BigDecimal;

public class ItemRemessa {
    private Long idItemRemessa;
    private Residuo residuo;
    private BigDecimal quantidade;

    public ItemRemessa() {
    }
    public ItemRemessa(Long idItemRemessa, Residuo residuo,
                       BigDecimal quantidade) {
        this.idItemRemessa = idItemRemessa;
        this.residuo = residuo;
        this.quantidade = quantidade;
    }

    public Long getIdItemRemessa() {
        return idItemRemessa;
    }
    public void setIdItemRemessa(Long idItemRemessa) {
        this.idItemRemessa = idItemRemessa;
    }

    public Residuo getResiduo() {
        return residuo;
    }
    public void setResiduo(Residuo residuo) {
        this.residuo = residuo;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
}
