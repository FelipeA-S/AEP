
package br.com.aep.GerenciadorDeResiduo.model;

public class Gerente extends Funcionario {

    private String setorResp;

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
