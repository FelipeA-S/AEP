package br.com.aep.GerenciadorDeResiduo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estoque")
    private Long idEstoque;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_residuo", nullable = false, unique = true, length = 30)
    private TipoResiduo tipoResiduo;

    @Column(name = "quantidade_total", nullable = false, precision = 12, scale = 3)
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