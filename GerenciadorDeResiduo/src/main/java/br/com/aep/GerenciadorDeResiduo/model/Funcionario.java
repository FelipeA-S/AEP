
package br.com.aep.GerenciadorDeResiduo.model;

public class Funcionario {

    private double salario;
    private String funcao;

    public Funcionario(double salario, String funcao) {
        this.salario = salario;
        this.funcao = funcao;
    }


    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
