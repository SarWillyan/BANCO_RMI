package Servidor;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class BancoRMIServerMain {
    public static void main(String[] args) {
        try {
            BancoRMIServer bancoServer = new BancoRMIServer(); // Instancia o servidor
            LocateRegistry.createRegistry(1099); // Porta padrão RMI
            Naming.rebind("BancoService", bancoServer); // Nome do serviço
            System.out.println("Servidor RMI pronto.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
