package br.com.aep.GerenciadorDeResiduo.controller;

import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import br.com.aep.GerenciadorDeResiduo.service.ResiduoService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

//esta classe é responsavel pelass requisições http dos residuos
@RestController
//define o endereço para todos os metodos do Controller
@RequestMapping("/residuos")
public class ResiduoController {
    //declarando uma instância da classe de serviço dos residuos
    private final ResiduoService residuoService;
    public ResiduoController(ResiduoService residuoService) {
        this.residuoService = residuoService;
    }

    //indica que está função está responsavel pela consulta dos dados
    @GetMapping
    public List<Residuo> listarResiduos() {
        return residuoService.listarResiduos();
    }

    //indica que está função está responsavel pela entrada dos dados
    @PostMapping
    public Residuo cadastrarResiduo(@RequestBody Residuo residuo) {
        return residuoService.cadastrarResiduo(residuo);
    }
}
