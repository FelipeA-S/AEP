
package br.com.aep.GerenciadorDeResiduo.model;

public class Residuo {

    private Long idResiduo;
    private TipoResiduo tipoResiduo;
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
