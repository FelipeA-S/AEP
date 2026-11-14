package br.com.aep.GerenciadorDeResiduo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    private double salario;

    @Column(length = 100)
    private String funcao;

    protected Funcionario() {}

    public Funcionario(double salario, String funcao) {
        this.salario = salario;
        this.funcao = funcao;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
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