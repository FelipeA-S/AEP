package br.com.aep.GerenciadorDeResiduo.service;

import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import br.com.aep.GerenciadorDeResiduo.model.TipoResiduo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResiduoService {
    private final List<Residuo> residuos = new ArrayList<>();

    public ResiduoService() {
        Long proximoId = 1L;
        for (TipoResiduo tipo : TipoResiduo.values()) {
            Residuo residuo = new Residuo(
                    proximoId,
                    tipo,
                    tipo.getDescricao()
            );
            residuos.add(residuo);
            proximoId++;
        }
    }

    public List<Residuo> listarResiduos() {
        return residuos;
    }

    public Residuo buscarPorId(Long id) {
        return residuos.stream()
                .filter(residuo -> residuo.getIdResiduo().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Residuo buscarPorTipo(TipoResiduo tipo) {
        return residuos.stream()
                .filter(residuo -> residuo.getTipoResiduo() == tipo)
                .findFirst()
                .orElse(null);
    }
}
