
package br.com.aep.GerenciadorDeResiduo.controller;

import org.springframework.web.bind.annotation.*;
import br.com.aep.GerenciadorDeResiduo.dto.GerenteRequestDTO;
import br.com.aep.GerenciadorDeResiduo.dto.GerenteResponseDTO;
import br.com.aep.GerenciadorDeResiduo.service.GerenteService;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    private final GerenteService service;

    public GerenteController(GerenteService service) {
        this.service = service;
    }

    @PostMapping
    public GerenteResponseDTO criar(@RequestBody GerenteRequestDTO dados) {
        return service.criar(dados);
    }
}
