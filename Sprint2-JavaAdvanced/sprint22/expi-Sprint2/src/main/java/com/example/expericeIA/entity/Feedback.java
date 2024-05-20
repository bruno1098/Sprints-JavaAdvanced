package com.example.expericeIA.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name="feedback_seq", sequenceName = "SQ_T_FEEDBACK", allocationSize = 1)
@Table(name = "T_FEEDBACK")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "feedback_seq")
    @Column(name="ID_FEEDBACK")
    private Long id;

    @Column(name = "CLIENTE", length = 100, nullable = false)
    private String cliente;

    @Column(name = "DESCRICAO", length = 1000, nullable = false)
    private String descricao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "SENTIMENTO", length = 20, nullable = false)
    private String sentimento;

    public Feedback() {}

    public Feedback(Long id, String cliente, String descricao, LocalDate dataCriacao, String sentimento) {
        super();
        this.id = id;
        this.cliente = cliente;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.sentimento = sentimento;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getSentimento() {
        return sentimento;
    }

    public void setSentimento(String sentimento) {
        this.sentimento = sentimento;
    }

    @PostPersist
    private void executar() {
        System.out.println("Feedback salvo com sucesso.");
    }
}