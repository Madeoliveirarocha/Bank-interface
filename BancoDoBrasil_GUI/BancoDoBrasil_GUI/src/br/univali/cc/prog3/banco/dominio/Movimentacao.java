package br.univali.cc.prog3.banco.dominio;

public class Movimentacao {
    private String descricao;
    private char tipo; //D C
    private double valor;

    public Movimentacao(String descricao, char tipo, double valor) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getMovimentacao(){
        return "("+this.tipo+") "+this.descricao+" R$"+this.valor;
    }
}
