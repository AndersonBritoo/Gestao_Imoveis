import java.util.Locale;
import java.util.Scanner;

// Métodos para LER do teclado com VALIDAÇÃO.
// Todos repetem a pergunta até o valor ser válido -> o programa nunca rebenta.
public class Leitura {

    // Um só Scanner para o programa todo.
    // Locale.US = os decimais escrevem-se com PONTO (ex: 1500.50)
    static Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    // Lê um inteiro entre min e max
    static int lerInteiro(String mensagem, int min, int max) {
        while (true) {
            System.out.print(mensagem);
            if (sc.hasNextInt()) {           // o que foi escrito é um inteiro?
                int valor = sc.nextInt();
                sc.nextLine();               // limpa o Enter que ficou para trás
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } else {
                sc.nextLine();               // não era número: deita fora
            }
            System.out.println("  Inválido! Escreva um número entre " + min + " e " + max + ".");
        }
    }

    // Lê um decimal entre min e max
    static double lerDecimal(String mensagem, double min, double max) {
        while (true) {
            System.out.print(mensagem);
            if (sc.hasNextDouble()) {
                double valor = sc.nextDouble();
                sc.nextLine();
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } else {
                sc.nextLine();
            }
            System.out.println("  Inválido! Escreva um número válido (use ponto nas casas decimais).");
        }
    }

    // Lê um texto que não pode ficar vazio
    static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = sc.nextLine().trim();   // trim() tira espaços a mais
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("  Não pode ficar vazio.");
        }
    }

    // Lê um texto que pode ficar vazio (nos filtros, vazio = "qualquer")
    static String lerTextoOpcional(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }

    // true se o texto só tiver dígitos 0-9
    static boolean soDigitos(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) < '0' || texto.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    // NIF: exatamente 9 dígitos
    static String lerNif(String mensagem) {
        while (true) {
            String nif = lerTexto(mensagem);
            if (nif.length() == 9 && soDigitos(nif)) {
                return nif;
            }
            System.out.println("  O NIF tem de ter 9 dígitos.");
        }
    }

    // Data no formato dd/mm/aaaa
    static String lerData(String mensagem) {
        while (true) {
            String data = lerTexto(mensagem);
            if (dataValida(data)) {
                return data;
            }
            System.out.println("  Data inválida! Use dd/mm/aaaa.");
        }
    }

    // Verifica "dd/mm/aaaa": 10 carateres, barras nos sítios certos,
    // dígitos no resto, dia 1-31 e mês 1-12
    static boolean dataValida(String data) {
        if (data.length() != 10 || data.charAt(2) != '/' || data.charAt(5) != '/') {
            return false;
        }
        String dia = data.substring(0, 2);
        String mes = data.substring(3, 5);
        String ano = data.substring(6, 10);
        if (!soDigitos(dia) || !soDigitos(mes) || !soDigitos(ano)) {
            return false;
        }
        int d = Integer.parseInt(dia);
        int m = Integer.parseInt(mes);
        return d >= 1 && d <= 31 && m >= 1 && m <= 12;
    }

    // Pergunta S/N. Devolve true se for S.
    static boolean confirmar(String mensagem) {
        while (true) {
            String resposta = lerTexto(mensagem + " (S/N): ").toUpperCase();
            if (resposta.equals("S")) {
                return true;
            }
            if (resposta.equals("N")) {
                return false;
            }
        }
    }

    // Mostra os tipos numerados e o utilizador escolhe pelo número.
    // Se comQualquer for true, aparece "0 - Qualquer" (devolve null).
    static TipoImovel lerTipo(boolean comQualquer) {
        TipoImovel[] tipos = TipoImovel.values();   // vetor com as 5 constantes
        int min = 1;
        if (comQualquer) {
            System.out.println("  0 - Qualquer");
            min = 0;
        }
        for (int i = 0; i < tipos.length; i++) {
            System.out.println("  " + (i + 1) + " - " + tipos[i]);
        }
        int op = lerInteiro("Tipo: ", min, tipos.length);
        if (op == 0) {
            return null;
        }
        return tipos[op - 1];    // opção 1 = posição 0 do vetor
    }

    // Igual ao lerTipo, mas para o estado
    static EstadoImovel lerEstado(boolean comQualquer) {
        EstadoImovel[] estados = EstadoImovel.values();
        int min = 1;
        if (comQualquer) {
            System.out.println("  0 - Qualquer");
            min = 0;
        }
        for (int i = 0; i < estados.length; i++) {
            System.out.println("  " + (i + 1) + " - " + estados[i]);
        }
        int op = lerInteiro("Estado: ", min, estados.length);
        if (op == 0) {
            return null;
        }
        return estados[op - 1];
    }

    // Transforma um valor em texto com 2 casas decimais e €. Ex: 1500.5 -> "1500,50 €"
    static String dinheiro(double valor) {
        return String.format("%.2f €", valor);
    }
}
