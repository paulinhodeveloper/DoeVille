package br.org.sesisenai.DoaVille.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SolicitacaoDoacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ItemDoacao itemDoacao;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private LocalDateTime dataSolicitacao;

    @Column(nullable = false)
    private String enderecoEntrega;

    @Column(nullable = false)
    private String bairroEntrega;

    public Long getId() { return id; }

    public ItemDoacao getItemDoacao() { return itemDoacao; }

    public void setItemDoacao(ItemDoacao itemDoacao) { this.itemDoacao = itemDoacao; }

    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataSolicitacao() { return dataSolicitacao; }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }

    public String getEnderecoEntrega() { return enderecoEntrega; }

    public void setEnderecoEntrega(String enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }

    public String getBairroEntrega() { return bairroEntrega; }

    public void setBairroEntrega(String bairroEntrega) { this.bairroEntrega = bairroEntrega; }
}