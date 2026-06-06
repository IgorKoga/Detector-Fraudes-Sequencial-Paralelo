public class ValidadorFraude {
    public static boolean ehSuspeita(Transacao t) {
        boolean ehFraude = false;
        try {
            // Simula uma análise pesada ou latência de rede
            Thread.sleep(20);
            if (t.getValor() > 5000 && t.getCpfCliente().startsWith("000")) {
                ehFraude = true;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return ehFraude;
    }
}
