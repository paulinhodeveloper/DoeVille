package br.org.sesisenai.DoaVille.dto;

public class ItemDeclaracaoDTO {
    private Long materialId;
    private Double toneladasDeclaradas;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Double getToneladasDeclaradas() {
        return toneladasDeclaradas;
    }

    public void setToneladasDeclaradas(Double toneladasDeclaradas) {
        this.toneladasDeclaradas = toneladasDeclaradas;
    }
}