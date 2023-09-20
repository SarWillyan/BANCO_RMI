package Servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import ultilitario.Cor;

public class BancoRMIServer extends UnicastRemoteObject implements InterfaceBancoRMI {
    private Map<Integer, Conta> contas; // Mapa de contas

    // Construtor
    public BancoRMIServer() throws RemoteException {
        contas = new HashMap<>();
    }

    // Métodos remotos
    @Override
    public String verExtrato(int numeroConta) throws RemoteException {
        Conta conta = contas.get(numeroConta);
        if (conta != null) {
            return Cor.VERDE.getCodigoANSI() + 
            "Titular: " + conta.getTitular() + ", Saldo: " + conta.getSaldo() +
            Cor.reset();
        } else {
            return "Conta não encontrada.";
        }
    }

    @Override
    public String depositar(int numeroConta, double valor) throws RemoteException {
        Conta conta = contas.get(numeroConta);

        conta.depositar(valor);
        System.out.println("Depósito de " + valor + " realizado com sucesso.");
        return Cor.VERDE.getCodigoANSI() + "Depósito de " + valor + " realizado com sucesso." + Cor.reset();
    }

    @Override
    public String sacar(int numeroConta, double valor) throws RemoteException {
        Conta conta = contas.get(numeroConta);

        boolean saque = conta.sacar(valor);
        if (saque) {
            System.out.println("Saque de " + valor + " realizado com sucesso.");
            return Cor.VERDE.getCodigoANSI() + "Saque de " + valor + " realizado com sucesso." + Cor.reset();
        } else {
            System.out.println("Saldo insuficiente.");
            return Cor.VERMELHO.getCodigoANSI() + "Saldo insuficiente." + Cor.reset();
        }
    }

    @Override
    public String transferir(int contaOrigem, int contaDestino, double valor) throws RemoteException {
        Conta origem = contas.get(contaOrigem);
        Conta destino = contas.get(contaDestino);

        if (destino != null) {
            if (origem.getSaldo() >= valor) {
                origem.sacar(valor);
                destino.depositar(valor);
                System.out.println("Transferência de " + valor + " realizada com sucesso.");
                return Cor.VERDE.getCodigoANSI() + "Transferência de " + valor + " realizada com sucesso." + Cor.reset();
            } else {
                System.out.println("Saldo insuficiente na conta de origem.");
                return Cor.VERMELHO.getCodigoANSI() +  "Saldo insuficiente na conta de origem." + Cor.reset();
            }
        } else {
            System.out.println("Conta de destino não encontrada.");
            return Cor.VERMELHO.getCodigoANSI() +  "Conta de destino não encontrada." + Cor.reset();
        }
    }

    @Override
    public void criarConta(int numeroConta, String titular) throws RemoteException {
        Conta conta = new Conta(numeroConta, titular);
        contas.put(numeroConta, conta);
    }

    @Override
    public void removerConta(int numeroConta) throws RemoteException {
        contas.remove(numeroConta);
    }

    @Override
    public Boolean acesso(int numeroConta) {
        Conta conta = contas.get(numeroConta);
        if (conta != null) {
            return true;
        } else {
            return false;
        }
    }

}
