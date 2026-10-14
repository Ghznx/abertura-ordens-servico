package br.com.aberturaordensservico.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.aberturaordensservico.model.OrdemServico;


public interface OrdemServicoRepository extends 
        JpaRepository<OrdemServico, Integer> {
    
        public List<OrdemServico> findByDataEncerramentoIsNull();
}
