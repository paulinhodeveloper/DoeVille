package br.org.sesisenai.DoaVille.entity;

import jakarta.persistence.*;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "percentual_compensacao")
    private Double percentualCompensacao;

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

    public Double getPercentualCompensacao() {
        return percentualCompensacao;
    }

    public void setPercentualCompensacao(Double percentualCompensacao) {
        this.percentualCompensacao = percentualCompensacao;
    }
}