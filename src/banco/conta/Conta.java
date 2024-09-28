package banco.conta;

import banco.cliente.Cliente;

public abstract class Conta {

    private static final int AGENCIA_PADRAO = 1;
    private static  int SEQUENCIAL = 1;

    private int agencia;
    private int numero;
    protected double saldo;
    protected Cliente titular;

    public Conta(Cliente titular) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.titular = titular;
    }

    public int getAgencia() {return agencia;}

    public void setAgencia(int agencia) {this.agencia = agencia;}

    public int getNumero() {return numero;}

    public void setNumero(int numero) {this.numero = numero;}

    public double getSaldo() {return saldo;}

    public void setSaldo(double saldo) {this.saldo = saldo;}

    public Cliente getTitular() {return titular;}

    public void setTitular(Cliente titular) {this.titular = titular;}

    public abstract double depositar(double valor) throws Exception;
    public abstract double sacar(double valor) throws Exception;
    public abstract double aplicarJuros() ;
    public abstract double transferir(double valor, Conta contaDestino) throws Exception;

    public void imprimirExtrato() {
        System.out.printf("Titular: %s%n", this.titular.getNome());
        System.out.printf("Agencia: %d%n", this.agencia);
        System.out.printf("Número: %d%n", this.numero);
        System.out.printf("Saldo: %.2f%n", this.saldo);
    }
}
