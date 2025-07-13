
package br.org.sesisenai.DoaVille.dto;

import jakarta.validation.constraints.NotBlank;

public class ClienteDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;

    @NotBlank(message = "Atividade econômica é obrigatória")
    private String atividadeEconomica;

    @NotBlank(message = "Responsável é obrigatório")
    private String responsavel;

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
}
