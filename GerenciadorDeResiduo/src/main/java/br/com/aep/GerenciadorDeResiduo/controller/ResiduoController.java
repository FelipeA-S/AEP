package br.com.aep.GerenciadorDeResiduo.controller;

import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import br.com.aep.GerenciadorDeResiduo.service.ResiduoService;
import org.springframework.http.ResponseEntity;
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

    //indica que está função está responsavel por permitir editar os dados
    @PutMapping("/{id}")
    public ResponseEntity<Residuo> atualizarResiduo(@PathVariable Long id, @RequestBody Residuo residuo) {
        boolean atualizado = residuoService.atualizarResiduo(id, residuo);
        if (!atualizado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(residuo);
    }

    //indica que está função está responsavel pela deleção dos dados
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirResiduo(@PathVariable Long id) {
        boolean excluido = residuoService.excluirResiduo(id);
        if (!excluido) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
