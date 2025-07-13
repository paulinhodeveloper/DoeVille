package br.org.sesisenai.DoaVille.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Declaracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_declaracao")
    private LocalDate dataDeclaracao;

    @Column(name = "data_inicio_periodo")
    private LocalDate dataInicioPeriodo;

    @Column(name = "data_fim_periodo")
    private LocalDate dataFimPeriodo;

    @Column(name = "total_materiais")
    private Double totalMateriais;

    @Column(name = "total_compensacao")
    private Double totalCompensacao;

    @OneToMany(mappedBy = "declaracao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<br.org.sesisenai.DoaVille.entity.ItemDeclaracao> itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataDeclaracao() {
        return dataDeclaracao;
    }

    public void setDataDeclaracao(LocalDate dataDeclaracao) {
        this.dataDeclaracao = dataDeclaracao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataInicioPeriodo() {
        return dataInicioPeriodo;
    }

    public void setDataInicioPeriodo(LocalDate dataInicioPeriodo) {
        this.dataInicioPeriodo = dataInicioPeriodo;
    }

    public LocalDate getDataFimPeriodo() {
        return dataFimPeriodo;
    }

    public void setDataFimPeriodo(LocalDate dataFimPeriodo) {
        this.dataFimPeriodo = dataFimPeriodo;
    }

    public Double getTotalMateriais() {
        return totalMateriais;
    }

    public void setTotalMateriais(Double totalMateriais) {
        this.totalMateriais = totalMateriais;
    }

    public Double getTotalCompensacao() {
        return totalCompensacao;
    }

    public void setTotalCompensacao(Double totalCompensacao) {
        this.totalCompensacao = totalCompensacao;
    }

    public List<ItemDeclaracao> getItens() {
        return itens;
    }

    public void setItens(List<ItemDeclaracao> itens) {
        this.itens = itens;
    }
}