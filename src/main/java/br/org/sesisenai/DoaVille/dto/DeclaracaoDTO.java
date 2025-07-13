package br.org.sesisenai.DoaVille.dto;

import java.time.LocalDate;
import java.util.List;

public class DeclaracaoDTO {
    private Long id;
    private Long clienteId;
    private LocalDate dataInicioPeriodo;
    private LocalDate dataFimPeriodo;
    private List<br.org.sesisenai.DoaVille.dto.ItemDeclaracaoDTO> itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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

    public List<br.org.sesisenai.DoaVille.dto.ItemDeclaracaoDTO> getItens() {
        return itens;
    }

    public void setItens(List<br.org.sesisenai.DoaVille.dto.ItemDeclaracaoDTO> itens) {
        this.itens = itens;
    }
}