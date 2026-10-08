package br.com.aberturaordensservico.controller;
import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aberturaordensservico.model.Equipamento;
import br.com.aberturaordensservico.model.Setor;
import br.com.aberturaordensservico.service.EquipamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {
    
    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Equipamento equipamento) {
        String nome = equipamento.getNome();
        String numeroPatrimonio = equipamento.getNumeroPatrimonio();
        Setor setor = equipamento.getSetor();

        Optional<Equipamento> novoEquipamento = equipamentoService.cadastrar(nome, numeroPatrimonio, setor.getId());

        if(novoEquipamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Setor não encontrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novoEquipamento.get());
    } 

    @GetMapping 
    public ResponseEntity<List<Equipamento>> listar() {
        List<Equipamento> equipamentos = equipamentoService.listar();
        return ResponseEntity.ok(equipamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable Integer id) {

        Optional<Equipamento> equipamento = equipamentoService.buscarPorId(id);

        if(equipamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipamento.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(@Valid @PathVariable Integer id, @RequestBody Equipamento equipamentoAtualizado) {
        Optional<Equipamento> equipamento = equipamentoService.atualizar(id, equipamentoAtualizado);

        if(equipamento.isEmpty()) {
            ResponseEntity.status(404).body("Equipamento não encontrado");
        }
        return ResponseEntity.ok(equipamento.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        boolean excluido = equipamentoService.excluir(id);

        if(excluido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
