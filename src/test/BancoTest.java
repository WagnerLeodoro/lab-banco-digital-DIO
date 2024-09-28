package test;

import banco.cliente.Cliente;
import banco.conta.Conta;
import banco.conta.ContaCorrente;
import banco.conta.ContaPoupanca;
import org.junit.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class BancoTest {
    @Test
    public void verificaClasseCliente() throws ClassNotFoundException {
        Class<?> c = Class.forName("banco.cliente.Pessoa");
        assertTrue("A classe Pessoa precisa ser abstrata.", Modifier.isAbstract(c.getModifiers()));

        assertEquals("A classe Pessoa deve possuir 4 atributos.",4,c.getDeclaredFields().length);

        assertEquals("A classe pessoa deve ter 8 métodos. Olhe na especificação.",8,c.getDeclaredMethods().length);
    }

    @Test
    public void verificarClasseContaCorrente() throws ClassNotFoundException {
        Class<?> c = Class.forName("banco.conta.ContaCorrente");

        assertEquals("A classe ContaCorrente não está herdando da classe conta", "banco.conta.Conta", c.getSuperclass().getName());

        assertTrue("A classe Conta precisa ser abstrata.", Modifier.isAbstract(c.getSuperclass().getModifiers()));

        assertEquals("A classe ContaCorrente deve possuir 2 atributos.",2,c.getDeclaredFields().length);

    }

    @Test
    public void criarContaCorrenteSaldoZero() throws Exception {
        Cliente cliente = new Cliente(-1, "Josualdo", "0886", LocalDate.of(1973, 2, 14));
        Conta conta = new ContaCorrente(cliente, 500, 0.05);
        double saldo = conta.getSaldo();
        assertEquals("Era esperado um saldo zerado para conta criada.",0.0, saldo,0.0);
    }

    @Test
    public void criarContaSaldo200() throws Exception {
        Cliente cliente = new Cliente(-1, "Josualdo", "0886", LocalDate.of(1973, 2, 14));
        Conta conta = new ContaCorrente(cliente, 500, 0.05);
        conta.depositar(200);
        double saldo = conta.getSaldo();
        assertEquals(200.0, saldo,0.0);
    }

    @Test
    public void manipulaContaCorrenteDepositar100Deposita20Saca60() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3));
        ContaCorrente c = new ContaCorrente(cliente, 500, 0.05);
        c.depositar(100);
        assertEquals(100.0, c.getSaldo(),0.0);
        c.depositar(20);
        assertEquals(120.0, c.getSaldo(),0.0);
        c.sacar(80);
        assertEquals(40.0, c.getSaldo(),0.0);
    }

    @Test
    public void manipulaContaCorrenteDepositar100Deposita20Saca1000() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3));
        ContaCorrente c = new ContaCorrente(cliente, 1000,0.05);
        c.depositar(100);
        assertEquals(100.0, c.getSaldo(),0.0);
        c.depositar(20);
        assertEquals(120.0, c.getSaldo(),0.0);
        c.sacar(1000);
        assertEquals(-880.0, c.getSaldo(),0.0);
    }

    @Test
    public void manipulaContaCorrenteLimiteDepositar100Deposita20Saca1300() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3));
        ContaCorrente c = new ContaCorrente(cliente, 1000,0.05);
        c.depositar(100);
        assertEquals(100.0, c.getSaldo(),0.0);
        c.depositar(20);
        assertEquals(120.0, c.getSaldo(),0.0);
        try{
            c.sacar(1300);
            fail("Deveria ter ocorrido uma exceção pois houve uma tentativa de saque que passou do limite da conta: "
                    + "Valor Saque=1300 Saldo="+c.getSaldo()+" Limite="+c.getLimite());
        }catch(Exception ex){
            assertEquals("Verifique a mensagem da exceção: ",
                    "Saldo insuficiente na conta.",
                    ex.getMessage());
        }
        assertEquals(120.0, c.getSaldo(),0.0);
    }

    @Test
    public void manipulaContaCorrenteSaca100AplicaJuros() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3));
        ContaCorrente c = new ContaCorrente(cliente, 1000,0.05);
        c.sacar(100);
        c.aplicarJuros();
        assertEquals(-105.0, c.getSaldo(),0.0);
    }

    @Test
    public void criarContaPoupanca() throws Exception{
        ContaPoupanca c = new ContaPoupanca(new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3)), 0.025);
        c.depositar(1000);
        double saldo = c.getSaldo();
        assertEquals("Era esperado um saldo de 1000 para conta criada.",1000.0, saldo,0.0);
    }

    @Test
    public void transferirValorContaCorrenteParaContaPoupanca() throws Exception{
        ContaCorrente cc = new ContaCorrente(new Cliente(2,"Marcelo","0886",LocalDate.of(1995,2,3)), 1000, 0.025);
        ContaPoupanca cp = new ContaPoupanca(new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3)), 0.025);
        cc.depositar(1000);
        cc.transferir(500,cp);
        double saldo = cc.getSaldo();
        assertEquals("Era esperado um saldo de 500 para conta criada.",500.0, saldo,0.0);
    }

    @Test
    public void transferirValorContaPoupancaParaContaCorrente() throws Exception{
        ContaCorrente cc = new ContaCorrente(new Cliente(2,"Marcelo","0886",LocalDate.of(1995,2,3)), 1000, 0.025);
        ContaPoupanca cp = new ContaPoupanca(new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3)), 0.025);
        cc.depositar(1000);
        cc.transferir(500,cp);
        cp.transferir(250,cc);
        double cpSaldo = cp.getSaldo();
        double ccSaldo = cc.getSaldo();
        assertEquals("Era esperado um saldo de 250 para conta poupança.",250.0, cpSaldo,0.0);
        assertEquals("Era esperado um saldo de 750 para conta corrente.",750.0, ccSaldo,0.0);
    }
}