package banco.conta;

import banco.cliente.Cliente;

public class ContaCorrente extends Conta {
    private double limite;
    private double taxaJurosLimite;

    public ContaCorrente(Cliente titular, double limite, double taxaJurosLimite) {
        super(titular);
        this.limite = limite;
        this.taxaJurosLimite = taxaJurosLimite;
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
        if(valor > 0 && getSaldo() + limite >= valor) {
            setSaldo(getSaldo() - valor);
        } else if (valor < 0) {
            throw new Exception("Valor do saque não pode ser negativo ou zero. Valor="+valor);
        } else {
            throw new Exception("Saldo insuficiente na conta.");
        }
        return getSaldo();
    }

    @Override
    public double aplicarJuros() {
        double juros;
        if(getSaldo() >= 0) {
            juros = 0;
        } else {
            juros = getSaldo() * (this.taxaJurosLimite);
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
        System.out.println("-----Extrato Conta Corrente-----");
        super.imprimirExtrato();
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getTaxaJurosLimite() {
        return taxaJurosLimite;
    }

    public void setTaxaJurosLimite(double taxaJurosLimite) {
        this.taxaJurosLimite = taxaJurosLimite;
    }
}
