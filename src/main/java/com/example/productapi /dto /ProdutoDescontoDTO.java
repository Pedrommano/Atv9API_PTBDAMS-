package com.seuprojeto.dto;

public class ProdutoDescontoDTO {

    private String nome;
    private Double precoOriginal;
    private String descontoAplicado;
    private Double precoFinal;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoOriginal() {
        return precoOriginal;
    }

    public void setPrecoOriginal(Double precoOriginal) {
        this.precoOriginal = precoOriginal;
    }

    public String getDescontoAplicado() {
        return descontoAplicado;
    }

    public void setDescontoAplicado(String descontoAplicado) {
        this.descontoAplicado = descontoAplicado;
    }

    public Double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(Double precoFinal) {
        this.precoFinal = precoFinal;
    }
}
