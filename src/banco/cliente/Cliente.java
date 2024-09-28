package banco.cliente;

import banco.conta.ContaCorrente;
import banco.conta.ContaPoupanca;

import java.time.LocalDate;

public class Cliente extends Pessoa{
    private double saldoTotalCliente;
    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;

    public Cliente(long id, String nome, String cpf, LocalDate dataNascimento) {
        super(id, nome, cpf, dataNascimento);
    }

    public double getSaldoTotalCliente() {
        saldoTotalCliente = 0;
        if(this.contaCorrente != null){
            saldoTotalCliente += this.contaCorrente.getSaldo();
        }
        return saldoTotalCliente;
    }

    public void setSaldoTotalCliente(double saldoTotalCliente) {
        this.saldoTotalCliente = saldoTotalCliente;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public ContaPoupanca getContaPoupanca() {
        return contaPoupanca;
    }

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }
}
