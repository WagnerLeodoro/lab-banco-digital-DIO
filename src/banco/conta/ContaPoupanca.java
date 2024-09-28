package banco.conta;

import banco.cliente.Cliente;

public class ContaPoupanca extends Conta {
    double taxaJuros;
    public ContaPoupanca(Cliente titular, double taxaJuros) {
        super(titular);
        this.taxaJuros = taxaJuros;
    }

    @Override
    public double depositar(double valor) throws Exception {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
        } else {
            throw new Exception("Valor do depósito não pode ser negativo ou zero. Valor="+valor);
        }
        return getSaldo();
    }

    @Override
    public double sacar(double valor) throws Exception {
        if (this.saldo < valor) {
            throw new Exception("Saldo insuficiente na conta");
        }
        return this.saldo -= valor;
    }

    @Override
    public double aplicarJuros()  {
        double juros = 0;
        if(getSaldo() > 0) {
            juros = getSaldo() * (this.taxaJuros);
        }
        setSaldo(getSaldo() + juros);
        return juros;
    }

    @Override
    public double transferir(double valor, Conta contaDestino) throws Exception {
        if(this.getSaldo() > 0 && valor > 0) {
            this.sacar(valor);
            contaDestino.depositar(valor);
        } else {
            throw new Exception("Não foi possível transferir");
        }
        return getSaldo();
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("------Extrato Conta Poupanca------");
        super.imprimirExtrato();
    }
}
