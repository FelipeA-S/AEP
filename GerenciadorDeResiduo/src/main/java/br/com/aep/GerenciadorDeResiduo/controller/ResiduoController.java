package br.com.aep.GerenciadorDeResiduo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResiduoController {

    @GetMapping("/residuos")
    public String listarResiduos() {
        return "Lista de resíduos cadastrados!";
    }

}
