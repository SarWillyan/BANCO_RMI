package Servidor;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceBancoRMI extends Remote {
    String verExtrato(int numeroConta) throws RemoteException;
    String depositar(int numeroConta, double valor) throws RemoteException;
    String sacar(int numeroConta, double valor) throws RemoteException;
    String transferir(int contaOrigem, int contaDestino, double valor) throws RemoteException;
    void criarConta(int numeroConta, String titular) throws RemoteException;
    void removerConta(int numeroConta) throws RemoteException;
    Boolean acesso(int numeroConta) throws RemoteException;
}
