package Cliente;

import java.io.IOException;
import java.rmi.Naming;
import java.util.Scanner;

import Servidor.InterfaceBancoRMI;
import ultilitario.Cor;

public class BancoRMIClientMain {
    public static void main(String[] args) {
        try {
            InterfaceBancoRMI banco = (InterfaceBancoRMI) Naming.lookup("rmi://localhost/BancoService");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                int numeroContaAcesso = 0;
                boolean entrou = false;
                do {
                    System.out.println(Cor.AZUL.getCodigoANSI());
                    System.out.println("============== MENU ==============");
                    System.out.println("1. Criar conta");
                    System.out.println("2. Acessar conta");
                    System.out.println("3. Sair");
                    System.out.println("==================================");
                    System.out.print("Escolha uma opção: " + Cor.AMARELO.getCodigoANSI());
                    int opcao = scanner.nextInt();
                    System.out.println(Cor.reset());
                    switch (opcao) {
                        case 1:
                            System.out.print(Cor.AZUL.getCodigoANSI());
                            System.out.print("Digite o número da conta: "+ Cor.AMARELO.getCodigoANSI());
                            int numeroConta = scanner.nextInt();
                            System.out.print(Cor.AZUL.getCodigoANSI());
                            System.out.print("Digite o nome do titular: " + Cor.AMARELO.getCodigoANSI());
                            String nomeTitular = scanner.next();
                            banco.criarConta(numeroConta, nomeTitular);
                            System.out.println(Cor.VERDE.getCodigoANSI());
                            System.out.println("Conta criada com sucesso.");
                            System.out.println(Cor.reset());
                            clearScreen();
                            break;
                        case 2:
                            System.out.print(Cor.AZUL.getCodigoANSI());
                            System.out.print("Digite o número da conta: " + Cor.AMARELO.getCodigoANSI());
                            numeroContaAcesso = scanner.nextInt();
                            if (banco.acesso(numeroContaAcesso)) {
                                System.out.print(Cor.VERDE.getCodigoANSI());
                                System.out.println("Acesso realizado com sucesso.");
                                entrou = true;
                            } else {
                                System.out.print(Cor.VERMELHO.getCodigoANSI());
                                System.out.println("Conta não encontrada.");
                            }
                            System.out.print(Cor.reset());
                            clearScreen();
                            break;
                        case 3:
                            scanner.close();
                            System.out.println("Saindo do sistema.");
                            System.exit(0);
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                } while (entrou == false);

                boolean saiu = false;
                while (saiu == false) {
                    System.out.print(Cor.AZUL.getCodigoANSI());
                    System.out.println("============== MENU ==============");
                    System.out.println("1. Ver extrato");
                    System.out.println("2. Depositar");
                    System.out.println("3. Sacar");
                    System.out.println("4. Transferir");
                    System.out.println("5. Sair");
                    System.out.println("==================================");
                    System.out.print("Escolha uma opção: " + Cor.AMARELO.getCodigoANSI());
                    int escolha = scanner.nextInt();
                    System.out.print(Cor.reset());

                    switch (escolha) {
                        case 1:
                            String extrato = banco.verExtrato(numeroContaAcesso);
                            System.out.println("Extrato: " + extrato);
                            clearScreen();
                            break;
                        case 2:
                            System.out.print(Cor.AZUL.getCodigoANSI());
                            System.out.print("Digite o valor a depositar: " + Cor.AMARELO.getCodigoANSI());
                            double valorDeposito = scanner.nextDouble();
                            System.out.print(Cor.reset());
                            System.out.println(banco.depositar(numeroContaAcesso, valorDeposito));
                            clearScreen();
                            break;
                        case 3:
                            System.out.print(Cor.AZUL.getCodigoANSI());
                            System.out.print("Digite o valor a sacar: " + Cor.AMARELO.getCodigoANSI());
                            double valorSaque = scanner.nextDouble();
                            System.out.print(Cor.reset());
                            System.out.println(banco.sacar(numeroContaAcesso, valorSaque));
                            clearScreen();
                            break;
                        case 4:
                            System.out.print(Cor.AZUL.getCodigoANSI());
                            System.out.print("Digite o número da conta de destino: " + Cor.AMARELO.getCodigoANSI());
                            int contaDestino = scanner.nextInt();
                            System.out.print(Cor.AZUL.getCodigoANSI());
                            System.out.print("Digite o valor a transferir: " + Cor.AMARELO.getCodigoANSI());
                            double valorTransferencia = scanner.nextDouble();
                            System.out.print(Cor.reset());
                            System.out.println(banco.transferir(numeroContaAcesso, contaDestino, valorTransferencia));
                            clearScreen();
                            break;
                        case 5:
                            System.out.println("Saindo da conta.");
                            saiu = true;
                            clearScreen();
                            break;
                        default:
                            System.out.print(Cor.VERMELHO.getCodigoANSI());
                            System.out.println("Opção inválida. Tente novamente.");
                            System.out.print(Cor.reset());
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        System.out.print(Cor.AMARELO.getCodigoANSI());
        System.out.println("Pressione qualquer tecla para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.flush();
        System.out.println(Cor.reset());
        System.out.print("\033[H\033[2J");
    }
}
