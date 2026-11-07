package br.com.aep.GerenciadorDeResiduo.controller;

import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

//esta classe é responsavel pelass requisições http
@RestController
public class ResiduoController {
    //indica uqe está função está responsavel pela consulta das informações
    @GetMapping("/residuos")
    public List<Residuo> listarResiduos() {
        return List.of(
                new Residuo(1L,"plastico",new BigDecimal("150.5"),"plastico de garrafa pet"),
                new Residuo(2L,"metal",new BigDecimal("50.75"),"latas de aluminio")
                );
    }

}
