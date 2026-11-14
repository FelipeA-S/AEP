package br.com.aep.GerenciadorDeResiduo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_remessa")
public class ItemRemessa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_remessa")
    private Long idItemRemessa;

    // muitos itens apontam para um mesmo resíduo (chave estrangeira id_residuo)
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_residuo")
    private Residuo residuo;

    @Column(nullable = false, precision = 12, scale = 3)
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