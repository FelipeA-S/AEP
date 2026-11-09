package br.com.aep.GerenciadorDeResiduo.dto;

import java.math.BigDecimal;

public class ResiduoResponseDTO {
    private Long idResiduo;
    private String nomeResiduo;
    private BigDecimal pesoResiduo;
    private String descricaoResiduo;

    public ResiduoResponseDTO(Long idResiduo, String nomeResiduo, BigDecimal pesoResiduo, String descricaoResiduo) {
        this.idResiduo = idResiduo;
        this.nomeResiduo = nomeResiduo;
        this.pesoResiduo = pesoResiduo;
        this.descricaoResiduo = descricaoResiduo;
    }

    public Long getIdResiduo() {
        return idResiduo;
    }
    public String getNomeResiduo() {
        return nomeResiduo;
    }
    public BigDecimal getPesoResiduo() {
        return pesoResiduo;
    }
    public String getDescricaoResiduo() {
        return descricaoResiduo;
    }
}
