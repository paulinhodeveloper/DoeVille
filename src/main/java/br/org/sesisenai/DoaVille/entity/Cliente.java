package br.org.sesisenai.DoaVille.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cnpj;

    @Column(name = "atividade_economica")
    private String atividadeEconomica;

    private String responsavel;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<br.org.sesisenai.DoaVille.entity.Declaracao> declaracoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAtividadeEconomica() {
        return atividadeEconomica;
    }

    public void setAtividadeEconomica(String atividadeEconomica) {
        this.atividadeEconomica = atividadeEconomica;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public List<br.org.sesisenai.DoaVille.entity.Declaracao> getDeclaracoes() {
        return declaracoes;
    }

    public void setDeclaracoes(List<br.org.sesisenai.DoaVille.entity.Declaracao> declaracoes) {
        this.declaracoes = declaracoes;
    }
}