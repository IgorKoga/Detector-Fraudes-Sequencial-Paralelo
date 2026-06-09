import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
        public static void main(String[] args) {
            List<Transacao> transacoes = new ArrayList<>();
            Random random = new Random();

            for(int i = 0; i < 1000; i++) {
                String id = String.valueOf(i);
                // 5% de chance de uma transação ser suspeita de fraude
                boolean isFraude = random.nextInt(100) < 5;

                if (isFraude) { // Gera cpf com o prefixo 000...
                    String cpfFraude = "000";
                    for(int j = 0; j < 8; j++){
                        cpfFraude += random.nextInt(10);
                    }
                    double valorAlto = 5001.0 + random.nextInt(5000);
                    transacoes.add(new Transacao(id, valorAlto, cpfFraude));
                } else {
                    String cpf = "";
                    for(int j = 0; j < 8; j++){
                        cpf += random.nextInt(10);
                    }
                    double valorAleatorioBaixo = 10.0 + random.nextInt(4000);
                    transacoes.add(new Transacao(id, valorAleatorioBaixo, cpf));
                }
            }
            System.out.println("Transações: " + transacoes.size());

            // Processamento Sequencial
            long inicioSeq = System.currentTimeMillis();
            List<Transacao> fraudesSeq = transacoes.stream()
                .filter(ValidadorFraude::ehSuspeita)
                .collect(Collectors.toList());
            long fimSeq = System.currentTimeMillis();

            System.out.println("---Sequencial---");
            System.out.println("Fraudes encontradas: " + fraudesSeq.size());
            System.out.println("Tempo: " + (fimSeq - inicioSeq) + " ms");

            // Processamento Paralelo
            long inicioPar = System.currentTimeMillis();
            List<Transacao> fraudesPar = transacoes.parallelStream()
                    .filter(ValidadorFraude::ehSuspeita)
                    .collect(Collectors.toList());
            long fimPar = System.currentTimeMillis();

            System.out.println("---Paralelo---");
            System.out.println("Fraudes encontradas: " + fraudesPar.size());
            System.out.println("Tempo: " + (fimPar - inicioPar) + " ms");

            // Desafio Extra
            List<Transacao> listaExternaInsegura = new ArrayList<>();

            try {
                transacoes.parallelStream()
                        .filter(ValidadorFraude::ehSuspeita)
                        .forEach(listaExternaInsegura::add); // modificação insegura, ::add não foi feito para trabalhar
                                                             //com multiplas threads, multiplas threads vão tentar modificar o mesmo indice (race condition)
            } catch (Exception e) {
                System.out.println("Erro ocorrido durante o forEach inseguro: " + e.getClass().getSimpleName());
            }

            System.out.println("---Desafio Extra---");
            System.out.println("Tamanho correto usando collect: " + fraudesPar.size());
            System.out.println("Tamanho arriscado usando forEach: " + listaExternaInsegura.size());
        }
    }
