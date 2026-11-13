
package br.com.aep.GerenciadorDeResiduo.model;

import java.math.BigDecimal;

public class Estoque {

    private Long idEstoque;
    private TipoResiduo tipoResiduo;
    private BigDecimal quantidadeTotal;

    public Estoque() {
    }

    public Estoque(Long idEstoque, TipoResiduo tipoResiduo,
                   BigDecimal quantidadeTotal) {
        this.idEstoque = idEstoque;
        this.tipoResiduo = tipoResiduo;
        this.quantidadeTotal = quantidadeTotal;
    }

    public Long getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduo tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public BigDecimal getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(BigDecimal quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }
}
