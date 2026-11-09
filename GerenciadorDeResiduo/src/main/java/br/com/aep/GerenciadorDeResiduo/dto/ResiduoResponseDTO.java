package br.com.aep.GerenciadorDeResiduo.dto;

import java.math.BigDecimal;

public class ResiduoResponseDTO {
    private Long idResiduo;
    private String tipoResiduo;
    private BigDecimal pesoResiduo;
    private String descricaoResiduo;

    public ResiduoResponseDTO(Long idResiduo, String nomeResiduo, BigDecimal pesoResiduo, String descricaoResiduo) {
        this.idResiduo = idResiduo;
        this.tipoResiduo = nomeResiduo;
        this.pesoResiduo = pesoResiduo;
        this.descricaoResiduo = descricaoResiduo;
    }

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
}
