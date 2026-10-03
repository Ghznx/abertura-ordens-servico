package br.com.aberturaordensservico.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aberturaordensservico.model.Setor;
import br.com.aberturaordensservico.service.SetorService;

@RestController
@RequestMapping("/setores")
public class SetorController {
    
    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public ResponseEntity<Setor> cadastrar(@RequestBody Setor setor) {
        Setor novoSetor = setorService.cadastrar(setor);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSetor);
    } 
}
