package br.com.aep.GerenciadorDeResiduo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "gerente")
public class Gerente extends Funcionario {

    @Column(name = "setor_resp", length = 100)
    private String setorResp;

    protected Gerente() {}

    public Gerente(double salario, String funcao, String setorResp) {
        super(salario, funcao);
        this.setorResp = setorResp;
    }

    public String getSetorResp() {
        return setorResp;
    }

    public void setSetorResp(String setorResp) {
        this.setorResp = setorResp;
    }
}