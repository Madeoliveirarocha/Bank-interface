package br.univali.cc.prog3.banco.visao;

import br.univali.cc.prog3.banco.dominio.Banco;
import br.univali.cc.prog3.banco.dominio.ContaCorrente;
import br.univali.cc.prog3.banco.dominio.SaqueException;

import javax.swing.JOptionPane;

public class CaixaEletronicoGUI {
    private Banco banco;

    public String lerValor(String rotulo) {
        return JOptionPane.showInputDialog(null,rotulo);
    }

    public String lerValor(String rotulo, String[] opcoes) {
        return (String) JOptionPane.showInputDialog(null, null, rotulo, JOptionPane.QUESTION_MESSAGE,null, opcoes,null);
    }

    public void escreverValor(String valor) {
        JOptionPane.showMessageDialog(null, valor);
    }

    public CaixaEletronicoGUI(Banco banco) {
        this.banco = banco;
    }

    public void menu() {
    char opcao;
    do {
        String[] opcoes = {
            "1 - Criar conta simples",
            "2 - Criar conta especial",
            "3 - Depositar",
            "4 - Sacar",
            "5 - Transferir",
            "6 - Extrato",
            "S - Sair"
        };
        String valorSelecionado = this.lerValor("Selecione uma opção", opcoes);
        if (valorSelecionado == null) {
            opcao = 'S';
        } else {
            opcao = valorSelecionado.toUpperCase().charAt(0);
        }

        try {
            switch (opcao) {
                case '1':
                    criarContaSimples();
                    break;
                case '2':
                    criarContaEspecial();
                    break;
                case '3':
                    depositar();
                    break;
                case '4':
                    sacar();
                    break;
                case '5':
                    transferir();
                    break;
                case '6':
                    extrato();
                    break;
            }
        } catch (SaqueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    } while (opcao != 'S');
}

    private void criarContaSimples() {
        double saldoInicial = Double.parseDouble(lerValor("Informe o saldo inicial"));
        int numeroConta = Integer.parseInt(lerValor("Informe o número da conta"));
        this.banco.criarConta(saldoInicial, numeroConta);
    }
    
   private void criarContaEspecial() {
        double saldoInicial = Double.parseDouble(lerValor("Informe o saldo inicial"));
        int numeroConta = Integer.parseInt(lerValor("Informe o número da conta"));
        double limite = Double.parseDouble(lerValor("Informe o limite da conta"));
        this.banco.criarContaEspecial(saldoInicial, numeroConta, limite);
    }
    
    private void depositar() {
        int numero = Integer.parseInt(lerValor("Informe o numero da conta"));
        double valor = Double.parseDouble(lerValor("Informe o valor para depósito"));
        this.banco.depositar(numero, valor);
    }
    
    private void sacar() throws SaqueException {
        try {
            int numero = Integer.parseInt(lerValor("Informe o numero da conta"));
            double valor = Double.parseDouble(lerValor("Informe o valor para saque"));

            ContaCorrente contaCorrente = this.banco.localizarConta(numero);
            if (contaCorrente != null) {
                if (contaCorrente.getSaldo() >= valor) {
                    this.banco.sacar(numero, valor);
                } else {
                    throw new SaqueException("Saldo insuficiente");
                }
            } else {
                throw new SaqueException("Conta não encontrada");
            }
        } catch (NumberFormatException e) {
            throw new SaqueException("Valor inválido. Informe um número válido");
        }
    }
    
   private void transferir() {
    try {
        int numeroOrigem = Integer.parseInt(lerValor("Informe o número da conta de origem"));
        int numeroDestino = Integer.parseInt(lerValor("Informe o número da conta de destino"));
        double valor = Double.parseDouble(lerValor("Informe o valor para transferência"));

        ContaCorrente contaOrigem = this.banco.localizarConta(numeroOrigem);
        ContaCorrente contaDestino = this.banco.localizarConta(numeroDestino);

        if (contaOrigem == null) {
            throw new IllegalArgumentException("Conta de origem não encontrada.");
        }
        if (contaDestino == null) {
            throw new IllegalArgumentException("Conta de destino não encontrada.");
        }

        this.banco.transferir(numeroOrigem, numeroDestino, valor);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Valor inválido. Informe um número válido.");
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

    private void extrato(){
        int numero = Integer.parseInt(lerValor("Informe o numero da conta"));
        this.escreverValor(this.banco.emitirExtrato(numero));
        System.out.println(this.banco.emitirExtrato(numero));
    }
}
