package com.locauto.locauto.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    @NotNull
    private Date DatadDeLocalocao;
    @NotBlank
    @NotNull
    private Date DataDeDevolucao;
    @NotBlank
    @NotNull
    private double KmVeiculo;

    @OneToMany
    @JoinColumn(name="aluguel_id")
    private Set<Avaliacao> avaliacoes;

    public Aluguel(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatadDeLocalocao() {
        return DatadDeLocalocao;
    }

    public void setDatadDeLocalocao(Date datadDeLocalocao) {
        DatadDeLocalocao = datadDeLocalocao;
    }

    public Date getDataDeDevolucao() {
        return DataDeDevolucao;
    }

    public void setDataDeDevolucao(Date dataDeDevolucao) {
        DataDeDevolucao = dataDeDevolucao;
    }

    public double getKmVeiculo() {
        return KmVeiculo;
    }

    public void setKmVeiculo(double kmVeiculo) {
        KmVeiculo = kmVeiculo;
    }
}
