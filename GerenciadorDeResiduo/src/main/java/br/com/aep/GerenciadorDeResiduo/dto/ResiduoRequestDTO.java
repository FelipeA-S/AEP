package br.com.aep.GerenciadorDeResiduo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ResiduoRequestDTO {

    @NotBlank(message = "O nome do residuo é obrigatório")
    private String nomeResiduo;
    @NotNull(message = "A quantidade é obrigatória")
    @DecimalMin(value = "0.0",inclusive = false, message = "O peso tem que ser maior que zero")
    private BigDecimal pesoResiduo;
    private String descricaoResiduo;

    public ResiduoRequestDTO() {}

    public String getNomeResiduo() {
        return nomeResiduo;
    }
    public void setNomeResiduo(String nomeResiduo) {
        this.nomeResiduo = nomeResiduo;
    }

    public BigDecimal getPesoResiduo() {
        return pesoResiduo;
    }
    public void setPesoResiduo(BigDecimal pesoResiduo) {
        this.pesoResiduo = pesoResiduo;
    }

    public String getDescricaoResiduo() {
        return descricaoResiduo;
    }
    public void setDescricaoResiduo(String descricaoResiduo) {
        this.descricaoResiduo = descricaoResiduo;
    }
}
