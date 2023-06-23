package br.univali.cc.prog3.banco.dominio;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nome;
    private int numero;
    private List<ContaCorrente> contas;
    private int numeroConta = 0;

    public Banco(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
        this.contas = new ArrayList<>();
    }

    public void criarConta(double saldoInicial, int numeroConta) {
        validarNumeroConta(numeroConta);
        ContaCorrente novaConta = new ContaCorrente(numeroConta, saldoInicial);
        this.contas.add(novaConta);
    }

    public void criarContaEspecial(double saldoInicial,int numeroConta, double limite){
        validarNumeroConta(numeroConta);
        ContaCorrente novaConta = new ContaCorrente(numeroConta, saldoInicial, limite);
        this.contas.add(novaConta);
    }

    public ContaCorrente localizarConta(int numero) {
        for (ContaCorrente conta:this.contas){
            if (conta != null && conta.getNumeroConta() == numero) {
                return conta;
            }
        }
        return null;
    }
    
    public void depositar(int numero, double valor) {
        ContaCorrente contaCorrente = this.localizarConta(numero);
        if (contaCorrente != null) {
            contaCorrente.depositar(valor);
        }
    }

    public void sacar(int numero, double valor) {
        ContaCorrente contaCorrente = this.localizarConta(numero);
        if (contaCorrente != null) {
            contaCorrente.sacar(valor); 
        }
    }

    public void transferir(int numeroOrigem, int numeroDestino, double valor) {
        ContaCorrente origem = this.localizarConta(numeroOrigem);
        ContaCorrente destino = this.localizarConta(numeroDestino);
        if (origem != null && destino != null) {
            if (origem.sacar(valor)) {
                destino.depositar(valor);
            }
        }
    }

    public String emitirExtrato(int numero) {
        ContaCorrente contaCorrente = this.localizarConta(numero);
        if (contaCorrente != null) {
            return contaCorrente.emitirExtrato();
        }
        return "nao encontrado";
    }

    public String getNome() {
        return nome;
    }

    private void validarNumeroConta(int numero) {
        for (ContaCorrente conta : contas) {
            if (conta.getNumeroConta() == numero) {
                throw new IllegalArgumentException("Já existe uma conta com o número informado.");
            }
        }
    }
}
