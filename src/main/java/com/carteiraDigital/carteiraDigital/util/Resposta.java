package com.carteiraDigital.carteiraDigital.util;

public class Resposta <T>{
    private int status;
    private String menssagem;
    private T data;

    public Resposta(int status, String menssagem, T data) {
        this.status = status;
        this.menssagem = menssagem;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
