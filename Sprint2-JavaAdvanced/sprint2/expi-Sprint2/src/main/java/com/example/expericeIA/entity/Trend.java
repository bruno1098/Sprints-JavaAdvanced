package com.example.expericeIA.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name="trend_seq", sequenceName = "SQ_T_TREND", allocationSize = 1)
@Table(name = "T_TREND")
public class
Trend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "trend_seq")
    @Column(name="ID_TREND")
    private Long id;

    @Column(name = "TOPICO", length = 100, nullable = false)
    private String topico;

    @Column(name = "FREQUENCIA", nullable = false)
    private Integer frequencia;

    @Column(name = "SENTIMENTO_POSITIVO", nullable = false)
    private Double sentimentoPositivo;

    @Column(name = "SENTIMENTO_NEUTRO", nullable = false)
    private Double sentimentoNeutro;

    @Column(name = "SENTIMENTO_NEGATIVO", nullable = false)
    private Double sentimentoNegativo;

    @Column(name = "DATA_INICIO", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "DATA_FIM", nullable = false)
    private LocalDate dataFim;

    public Trend() {}

    public Trend(Long id, String topico, Integer frequencia, Double sentimentoPositivo, Double sentimentoNeutro, Double sentimentoNegativo, LocalDate dataInicio, LocalDate dataFim) {
        super();
        this.id = id;
        this.topico = topico;
        this.frequencia = frequencia;
        this.sentimentoPositivo = sentimentoPositivo;
        this.sentimentoNeutro = sentimentoNeutro;
        this.sentimentoNegativo = sentimentoNegativo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopico() {
        return topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }

    public Double getSentimentoPositivo() {
        return sentimentoPositivo;
    }

    public void setSentimentoPositivo(Double sentimentoPositivo) {
        this.sentimentoPositivo = sentimentoPositivo;
    }

    public Double getSentimentoNeutro() {
        return sentimentoNeutro;
    }

    public void setSentimentoNeutro(Double sentimentoNeutro) {
        this.sentimentoNeutro = sentimentoNeutro;
    }

    public Double getSentimentoNegativo() {
        return sentimentoNegativo;
    }

    public void setSentimentoNegativo(Double sentimentoNegativo) {
        this.sentimentoNegativo = sentimentoNegativo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    @PostPersist
    private void executar() {
        System.out.println("Tendência salva com sucesso.");
    }
}
