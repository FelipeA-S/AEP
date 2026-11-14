
package br.com.aep.GerenciadorDeResiduo.controller;

import org.springframework.web.bind.annotation.*;
import br.com.aep.GerenciadorDeResiduo.dto.FuncionarioRequestDTO;
import br.com.aep.GerenciadorDeResiduo.dto.FuncionarioResponseDTO;
import br.com.aep.GerenciadorDeResiduo.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @PostMapping
    public FuncionarioResponseDTO criar(@RequestBody FuncionarioRequestDTO dados) {
        return service.criar(dados);
    }
}
