package com.carteiraDigital.carteiraDigital.model.transacao;

import com.carteiraDigital.carteiraDigital.model.conta.Conta;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;
    @ManyToOne
    @JoinColumn(name = "pagador_id")
    private Conta pagador;
    @ManyToOne
    @JoinColumn(name = "beneficiario_id")
    private Conta beneficiario;

    private LocalDateTime tempo;

    public Transacao(BigDecimal valor, Conta pagador, Conta beneficiario, LocalDateTime tempo) {
        this.valor = valor;
        this.pagador = pagador;
        this.beneficiario = beneficiario;
        this.tempo = tempo;

    }

    public Transacao() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Conta getPagador() {
        return pagador;
    }

    public void setPagador(Conta pagador) {
        this.pagador = pagador;
    }

    public Conta getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Conta beneficiario) {
        this.beneficiario = beneficiario;
    }

    public LocalDateTime getTempo() {
        return tempo;
    }

    public void setTempo(LocalDateTime tempo) {
        this.tempo = tempo;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
