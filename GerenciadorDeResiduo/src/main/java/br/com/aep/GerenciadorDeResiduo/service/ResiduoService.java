package br.com.aep.GerenciadorDeResiduo.service;

import br.com.aep.GerenciadorDeResiduo.model.Residuo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//esta classe é responsavel pelos serviços dos residuos
@Service
public class ResiduoService {

    private final List<Residuo> residuos = new ArrayList<>();

    public List<Residuo> listarResiduos() {
        return residuos;
    }

    public Residuo cadastrarResiduo(Residuo residuo) {
        residuos.add(residuo);
        return residuo;
    }

    public boolean atualizarResiduo(Long id, Residuo residuoAtualizado) {
        for (int i = 0; i < residuos.size(); i++) {
            if (residuos.get(i).getIdResiduo().equals(id)) {
                residuoAtualizado.setIdResiduo(id);
                residuos.set(i, residuoAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean excluirResiduo(Long id) {
        return residuos.removeIf(residuo -> residuo.getIdResiduo().equals(id));
    }
}
