import banco.cliente.Cliente;
import banco.conta.ContaCorrente;
import banco.conta.ContaPoupanca;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        Cliente cliente = new Cliente(1, "John Doe", "86680", LocalDate.of(1975, 10,31));

        ContaCorrente contaCorrente = new ContaCorrente(cliente, 500, 0.05);

        contaCorrente.depositar(200);

        ContaPoupanca contaPoupanca = new ContaPoupanca(cliente, 0.025);

        contaCorrente.transferir(500, contaPoupanca);

        contaCorrente.imprimirExtrato();

    }
}