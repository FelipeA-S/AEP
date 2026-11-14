
package br.com.aep.GerenciadorDeResiduo.model;

public enum TipoResiduo {
    PAPELAO("Papelão"),
    PLASTICO("Plástico"),
    VIDRO("Vidro"),
    METAL("Metal"),
    ORGANICO("Orgânico"),
    PAPEL("Papel"),
    DESCARTE_ESPECIAL("Descarte Especial");

    private final String descricao;

    TipoResiduo(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
