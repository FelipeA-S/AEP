
package br.com.aep.GerenciadorDeResiduo.service;

import org.springframework.stereotype.Service;
import br.com.aep.GerenciadorDeResiduo.model.Gerente;
import br.com.aep.GerenciadorDeResiduo.dto.GerenteRequestDTO;
import br.com.aep.GerenciadorDeResiduo.dto.GerenteResponseDTO;

@Service
public class GerenteService {

    public GerenteResponseDTO criar(GerenteRequestDTO dados) {

        Gerente gerente = new Gerente(
                dados.salario(),
                dados.funcao(),
                dados.setorResp()
        );

        return new GerenteResponseDTO(
                gerente.getSalario(),
                gerente.getFuncao(),
                gerente.getSetorResp()
        );
    }
}
