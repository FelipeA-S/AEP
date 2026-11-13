
package br.com.aep.GerenciadorDeResiduo.service;

import org.springframework.stereotype.Service;
import br.com.aep.GerenciadorDeResiduo.model.Funcionario;
import br.com.aep.GerenciadorDeResiduo.dto.FuncionarioRequestDTO;
import br.com.aep.GerenciadorDeResiduo.dto.FuncionarioResponseDTO;

@Service
public class FuncionarioService {

    public FuncionarioResponseDTO criar(FuncionarioRequestDTO dados) {

        Funcionario funcionario = new Funcionario(
                dados.salario(),
                dados.funcao()
        );

        return new FuncionarioResponseDTO(
                funcionario.getSalario(),
                funcionario.getFuncao()
        );
    }
}
