package ultilitario;

public enum Cor {
    VERMELHO("\u001B[31m"),
    AZUL("\u001B[34m"),
    VERDE("\u001B[32m"),
    AMARELO("\u001B[33m"),
    ROXO("\u001B[35m"),
    LARANJA("\u001B[38;5;208m"),
    ROSA("\u001B[38;5;198m"),
    PRETO("\u001B[30m"),
    BRANCO("\u001B[37m");

    private final String codigoANSI;

    Cor(String codigoANSI) {
        this.codigoANSI = codigoANSI;
    }

    public String getCodigoANSI() {
        return codigoANSI;
    }

    // Método para resetar o estilo ANSI após a cor
    public static String reset() {
        return "\u001B[0m";
    }
}
