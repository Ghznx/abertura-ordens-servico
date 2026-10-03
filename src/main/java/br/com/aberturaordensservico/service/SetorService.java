package br.com.aberturaordensservico.service;

import br.com.aberturaordensservico.model.Setor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import br.com.aberturaordensservico.repository.SetorRepository;

@Service
public class SetorService {
    
    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public Setor cadastrar(Setor setor) {
        return setorRepository.save(setor);
    }

    public List<Setor> listar() {
        return setorRepository.findAll();
    }

    public Optional<Setor> buscarPorId(Integer id) {
        return setorRepository.findById(id);
    }

    public Optional<Setor> atualizar(Integer id, Setor setorAtualizado) {
        
        Optional<Setor> setorEncontrado = setorRepository.findById(id);

        if(setorEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Setor setor = setorEncontrado.get();
        
        setor.setNome(setorAtualizado.getNome());
        return Optional.of(setorRepository.save(setor));
    }
}
