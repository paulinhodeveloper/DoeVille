package br.org.sesisenai.DoaVille.dto;

public class MaterialDTO {
    private Long id;
    private String nome;
    private Double percentualCompensacao;

    public Double getPercentualCompensacao() {
        return percentualCompensacao;
    }

    public void setPercentualCompensacao(Double percentualCompensacao) {
        this.percentualCompensacao = percentualCompensacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}