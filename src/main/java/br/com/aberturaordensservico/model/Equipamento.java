package br.com.aberturaordensservico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table (name = "equipamento")
public class Equipamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @NotBlank (message = "Nome obrigatório")
    private String nome;

    @NotBlank (message = "Número de patrimônio obrigatório")
    private String numeroPatrimonio;

    @ManyToOne 
    @JoinColumn (name = "setor_id")
    @NotNull (message = "Setor obrigatório")
    private Setor setor;

    public Equipamento() {
    }

    public Equipamento(String nome, String numeroPatrimonio, Setor setor) {
        this.nome = nome;
        this.numeroPatrimonio = numeroPatrimonio;
        this.setor = setor;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Setor getSetor() {
        return setor;
    }
}
