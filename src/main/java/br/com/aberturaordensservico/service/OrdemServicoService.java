package br.com.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.aberturaordensservico.model.Equipamento;
import br.com.aberturaordensservico.model.OrdemServico;
import br.com.aberturaordensservico.repository.EquipamentoRepository;
import br.com.aberturaordensservico.repository.OrdemServicoRepository;
import java.time.LocalDateTime;

@Service
public class OrdemServicoService {
    private OrdemServicoRepository ordemServicoRepository;
    private EquipamentoRepository equipamentoRepository;

    OrdemServicoService(OrdemServicoRepository ordemServicoRepository , EquipamentoRepository equipamentoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<OrdemServico> cadastrar(String descricao, Integer equipamentoId) {
        
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(equipamentoId);

        if(equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }
        
        OrdemServico ordemServico = new OrdemServico();

        ordemServico.setDescricao(descricao);
        ordemServico.setEquipamento(equipamentoEncontrado.get());
        ordemServico.setDataAbertura(LocalDateTime.now());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }
}
