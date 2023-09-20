package Servidor;
import java.io.Serializable;

public class Conta implements Serializable {
    private int numero;
    private String titular;
    private double saldo;

    public Conta(int numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }

    // Métodos
    public void depositar(double valor) {
        this.saldo += valor;
    }

    public Boolean sacar(double valor) {
        if (valor <= getSaldo()) {
            this.saldo -= valor;
            return true;
        } else {
            System.out.println("Saldo insuficiente.");
            return false;
        }
    }

    public void transferir(Conta contaDestino, double valor) {
        sacar(valor);
        contaDestino.depositar(valor);
    }

    public String verExtrato() {
        return "Conta: " + this.numero + "\nTitular: " + this.titular + "\nSaldo: " + this.saldo;
    }

    
    // Getters
    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

}
