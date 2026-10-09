package br.com.aberturaordensservico.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.aberturaordensservico.model.OrdemServico;
import br.com.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/ordens_servico")
public class OrdemServicoController {
    
    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody OrdemServicoRequest ordemServico) {
        String descricao = ordemServico.getDescricao();
        Integer equipamentoId = ordemServico.getEquipamentoId();

        Optional<OrdemServico> novaOrdemServico = ordemServicoService.cadastrar(descricao, equipamentoId);

        if(novaOrdemServico.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Equipamento não encontrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novaOrdemServico.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Integer id) {

        Optional<OrdemServico> ordemServico = ordemServicoService.buscarPorId(id);

        if(ordemServico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ordemServico.get());
    }

    @GetMapping()
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(ordemServicoService.listar());
    }

}
