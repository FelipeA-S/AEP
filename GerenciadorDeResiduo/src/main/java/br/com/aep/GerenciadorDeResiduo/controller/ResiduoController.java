package br.com.aep.GerenciadorDeResiduo.controller;

import br.com.aep.GerenciadorDeResiduo.dto.ResiduoRequestDTO;
import br.com.aep.GerenciadorDeResiduo.dto.ResiduoResponseDTO;
import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import br.com.aep.GerenciadorDeResiduo.service.ResiduoService;
import jakarta.validation.Valid;
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
    public List<ResiduoResponseDTO> listarResiduos() {
        return residuoService.listarResiduos().stream().map(this::converterParaDTO).toList();
    }

    //indica que está função está responsavel pela entrada dos dados
    @PostMapping
    public ResponseEntity<ResiduoResponseDTO> cadastrarResiduo(@Valid @RequestBody ResiduoRequestDTO dto) {
        Residuo residuo = residuoService.cadastrarResiduo(dto);
        return ResponseEntity.status(201).body(converterParaDTO(residuo));
    }

    //indica que está função está responsavel por permitir editar os dados
    @PutMapping("/{id}")
    public ResponseEntity<ResiduoResponseDTO> atualizarResiduo(
            @PathVariable Long id, @Valid @RequestBody ResiduoRequestDTO dto) {
        boolean atualizado = residuoService.atualizarResiduo(id, dto);
        if (!atualizado) {
            return ResponseEntity.notFound().build();
        }
        Residuo residuo = residuoService.buscarPorId(id);
        return ResponseEntity.ok(converterParaDTO(residuo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirResiduo(@PathVariable Long id) {
        boolean excluido = residuoService.excluirResiduo(id);
        if (!excluido) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private ResiduoResponseDTO converterParaDTO(Residuo residuo) {
        return new ResiduoResponseDTO(
                residuo.getIdResiduo(), residuo.getTipoResiduo(),
                residuo.getPesoResiduo(), residuo.getDescricaoResiduo());
    }
}
