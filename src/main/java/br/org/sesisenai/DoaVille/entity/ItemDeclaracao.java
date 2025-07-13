package br.org.sesisenai.DoaVille.entity;

import jakarta.persistence.*;

@Entity
public class ItemDeclaracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "declaracao_id")
    private Declaracao declaracao;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "percentual_compensacao")
    private Double percentualCompensacao;

    @Column(name = "toneladas_declaradas")
    private Double toneladasDeclaradas;

    @Column(name = "toneladas_compensacao")
    private Double toneladasCompensacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Declaracao getDeclaracao() {
        return declaracao;
    }

    public void setDeclaracao(Declaracao declaracao) {
        this.declaracao = declaracao;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Double getPercentualCompensacao() {
        return percentualCompensacao;
    }

    public void setPercentualCompensacao(Double percentualCompensacao) {
        this.percentualCompensacao = percentualCompensacao;
    }

    public Double getToneladasDeclaradas() {
        return toneladasDeclaradas;
    }

    public void setToneladasDeclaradas(Double toneladasDeclaradas) {
        this.toneladasDeclaradas = toneladasDeclaradas;
    }

    public Double getToneladasCompensacao() {
        return toneladasCompensacao;
    }

    public void setToneladasCompensacao(Double toneladasCompensacao) {
        this.toneladasCompensacao = toneladasCompensacao;
    }
}