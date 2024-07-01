package com.carteiraDigital.carteiraDigital.model.conta;

import com.carteiraDigital.carteiraDigital.dtos.ContaDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pessoas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 11, unique = true, nullable = false)
    private String documento;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    public Conta(ContaDTO conta) {
        this.nome = conta.nome();
        this.documento = conta.documento();
        this.email = conta.email();
        this.senha = conta.senha();
        this.saldo = conta.saldo();
        this.tipo = conta.tipo();
    }


    public Conta(){}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public BigDecimal getSaldo() { return saldo; }

    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public Tipo getTipo() { return tipo; }

    public void setTipo(Tipo tipo) { this.tipo = tipo; }
}
