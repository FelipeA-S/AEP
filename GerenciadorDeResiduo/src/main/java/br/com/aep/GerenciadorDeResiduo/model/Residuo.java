package br.com.aep.GerenciadorDeResiduo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "residuo")
public class Residuo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_residuo")
    private Long idResiduo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_residuo", nullable = false, unique = true, length = 30)
    private TipoResiduo tipoResiduo;

    @Column(name = "descricao_residuo", length = 100)
    private String descricaoResiduo;

    public Residuo() {
    }

    public Residuo(Long idResiduo, TipoResiduo tipoResiduo,
                   String descricaoResiduo) {
        this.idResiduo = idResiduo;
        this.tipoResiduo = tipoResiduo;
        this.descricaoResiduo = descricaoResiduo;
    }

    public Long getIdResiduo() {
        return idResiduo;
    }

    public void setIdResiduo(Long idResiduo) {
        this.idResiduo = idResiduo;
    }

    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduo tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public String getDescricaoResiduo() {
        return descricaoResiduo;
    }

    public void setDescricaoResiduo(String descricaoResiduo) {
        this.descricaoResiduo = descricaoResiduo;
    }
}