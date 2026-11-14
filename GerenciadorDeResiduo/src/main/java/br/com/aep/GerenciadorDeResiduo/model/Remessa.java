package br.com.aep.GerenciadorDeResiduo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "remessa")
public class Remessa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_remessa")
    private Long idRemessa;

    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;

    // Uma remessa tem vários itens. A tabela item_remessa recebe a coluna id_remessa.
    // cascade ALL: salvar a remessa salva os itens junto.
    // orphanRemoval: item removido da lista é apagado do banco.
    // EAGER: carrega os itens junto com a remessa (o menu usa os itens fora de transação).
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_remessa")
    private List<ItemRemessa> itens = new ArrayList<>();

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