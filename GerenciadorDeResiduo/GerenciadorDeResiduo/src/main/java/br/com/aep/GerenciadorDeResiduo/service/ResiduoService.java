package br.com.aep.GerenciadorDeResiduo.service;

import br.com.aep.GerenciadorDeResiduo.dto.ResiduoRequestDTO;
import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//esta classe é responsavel pelos serviços dos residuos
@Service
public class ResiduoService {

    private final List<Residuo> residuos = new ArrayList<>();
    private Long proximoId = 1L;

    public List<Residuo> listarResiduos() {
        return residuos;
    }

    public Residuo cadastrarResiduo(ResiduoRequestDTO dto) {
        Residuo residuo = new Residuo(
                proximoId, dto.getTipoResiduo(), dto.getPesoResiduo(), dto.getDescricaoResiduo());

        residuos.add(residuo);
        proximoId++;
        return residuo;
    }

    public boolean atualizarResiduo(Long id, ResiduoRequestDTO dto) {
        for (int i = 0; i < residuos.size(); i++) {
            if (residuos.get(i).getIdResiduo().equals(id)) {
                Residuo atualizado = new Residuo(
                        id, dto.getTipoResiduo(), dto.getPesoResiduo(), dto.getDescricaoResiduo());
                residuos.set(i, atualizado);
                return true;
            }
        }
        return false;
    }

    public boolean excluirResiduo(Long id) {
        return residuos.removeIf(residuo -> residuo.getIdResiduo().equals(id));
    }

    public Residuo buscarPorId(Long id) {
        return residuos.stream().filter(residuo -> residuo.getIdResiduo()
                .equals(id)).findFirst().orElse(null);
    }
}
