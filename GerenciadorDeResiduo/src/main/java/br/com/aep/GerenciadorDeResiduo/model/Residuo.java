package br.com.aep.GerenciadorDeResiduo.model;

//imports
import java.math.BigDecimal;

public class Residuo {
    //atributos
    private Long  idResiduo;
    private String tipoResiduo;
    private BigDecimal pesoResiduo;
    private String descricaoResiduo;

    //contrutores
    public Residuo() {}
    public Residuo(Long idResiduo, String tipoResiduo, BigDecimal pesoResiduo, String descricaoResiduo) {
        this.idResiduo = 1L;
        this.tipoResiduo = tipoResiduo;
        this.pesoResiduo = pesoResiduo;
        this.descricaoResiduo = descricaoResiduo;
    }

    //getters
    public Long getIdResiduo() {
        return idResiduo;
    }
    public String getTipoResiduo() {
        return tipoResiduo;
    }
    public BigDecimal getPesoResiduo() {
        return pesoResiduo;
    }
    public String getDescricaoResiduo() {
        return descricaoResiduo;
    }

    //setters
    public void setIdResiduo(Long idResiduo) {
        this.idResiduo = idResiduo;
    }
    public void setTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }
    public void setPesoResiduo(BigDecimal pesoResiduo) {
        this.pesoResiduo = pesoResiduo;
    }
    public void setDescricaoResiduo(String descricaoResiduo) {
        this.descricaoResiduo = descricaoResiduo;
    }
}

