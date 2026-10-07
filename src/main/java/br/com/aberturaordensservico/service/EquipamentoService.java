package br.com.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.aberturaordensservico.model.Setor;
import br.com.aberturaordensservico.repository.SetorRepository;

import br.com.aberturaordensservico.model.Equipamento;
import br.com.aberturaordensservico.repository.EquipamentoRepository;

@Service
public class EquipamentoService {
    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository , SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public Equipamento cadastrar(String nome, String numeroPatrimonio, Integer setorId) {

        Optional<Setor> setor = setorRepository.findById(setorId);

        boolean existe = setorRepository.existsById(setorId);

        if (!existe) {
            throw new IllegalArgumentException("Setor não encontrado");
        }

        Equipamento equipamento = new Equipamento();
        equipamento.setNome(nome);
        equipamento.setNumeroPatrimonio(numeroPatrimonio);
        equipamento.setSetor(setor.get());

        return equipamentoRepository.save(equipamento);
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizar(
          Integer id, Equipamento novosDados) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);

        if(equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = equipamentoEncontrado.get();
        equipamento.setNome(novosDados.getNome());
        equipamento.setNumeroPatrimonio(novosDados.getNumeroPatrimonio());

        return Optional.of(equipamentoRepository.save(equipamento));
    }

    public boolean excluir(Integer id) {

        if(!equipamentoRepository.existsById(id)) {
            return false;
        }

        equipamentoRepository.deleteById(id);
        return true;
    }
}
