package br.com.aep.GerenciadorDeResiduo.model;

import java.time.LocalDateTime;
import java.util.List;

public class Remessa {
    private Long idRemessa;
    private LocalDateTime dataRegistro;
    private List<ItemRemessa> itens;

    public Remessa() {
    }
    public Remessa(Long idRemessa, LocalDateTime dataRegistro,
                   List<ItemRemessa> itens) {
        this.idRemessa = idRemessa;
        this.dataRegistro = dataRegistro;
        this.itens = itens;
    }

    public Long getIdRemessa() {
        return idRemessa;
    }
    public void setIdRemessa(Long idRemessa) {
        this.idRemessa = idRemessa;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }
    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<ItemRemessa> getItens() {
        return itens;
    }
    public void setItens(List<ItemRemessa> itens) {
        this.itens = itens;
    }
}
