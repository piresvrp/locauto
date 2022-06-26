package com.locauto.locauto.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;


@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    @NotNull
    private double Kmentrega;
    @NotBlank
    @NotNull
    private double PrecoFinal;
    private Date DataAvalicao;

    public Avaliacao (){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getKmentrega() {
        return Kmentrega;
    }

    public void setKmentrega(double kmentrega) {
        Kmentrega = kmentrega;
    }

    public double getPrecoFinal() {
        return PrecoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        PrecoFinal = precoFinal;
    }

    public Date getDataAvalicao() {
        return DataAvalicao;
    }

    public void setDataAvalicao(Date dataAvalicao) {
        DataAvalicao = dataAvalicao;
    }

}
