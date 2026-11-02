import java.util.Scanner;

public class Agente {
    static Scanner sc = new Scanner(System.in);
    static int[] codigos = new int[100];
    static String[] nomes = new String[100];
    static int[] nifs = new int[100];
    static double[] vendasAgente = new double[100];   // total de vendas (€)
    static double[] comissaoAgente = new double[100]; // total de comissão (€)
    static int total = 0;

    public static boolean existe(int cod) {
        for (int i = 0; i < total; i++) {
            if (codigos[i] == cod) {
                return true;
            }
        }
        return false;
    }

    // fazer um array do agente.

    public static int codlerAgente() {
        int codAgente;
        do {
            System.out.println("Insira o código de agente (3 dígitos)");
            codAgente = sc.nextInt();
            if (codAgente < 100 || codAgente > 999) {
                System.out.println("O código tem de ter 3 dígitos.");
            } else if (existe(codAgente)) {
                System.out.println("Esse código já existe. Tente outro.");
            }
        } while (codAgente < 100 || codAgente > 999 || existe(codAgente));
        return codAgente;
    }

    public static String lerNome() {
        String nomeAgente;
        System.out.println("Nome: ");
        do {
            nomeAgente = sc.nextLine();
        } while (nomeAgente.length() == 0);
        return nomeAgente;
    }

    public static int lerNif() {
        String texto;
        int nif = 0;
        int erros;

        do {
            erros = 0;
            System.out.println("Nif: ");
            texto = sc.next();

            if (texto.length() != 9) {
                erros++;
            }

            for (int i = 0; i < texto.length(); i++) {
                if (texto.charAt(i) < '0' || texto.charAt(i) > '9') {
                    erros++;
                }
            }

            if (erros > 0) {
                System.out.println("O NIF tem de ter 9 números.");
            } else {
                nif = Integer.parseInt(texto);

                for (int j = 0; j < total; j++) {
                    if (nifs[j] == nif) {
                        erros++;
                    }
                }
                if (erros > 0) {
                    System.out.println("Esse NIF já existe. Tente outro.");
                }
            }
        } while (erros > 0);

        return nif;
    }

    public static void registarAgente() {
        codigos[total] = codlerAgente();
        nomes[total] = lerNome();
        nifs[total] = lerNif();
        vendasAgente[total] = 0;
        comissaoAgente[total] = 0;
        total++;
    }

    public static void adicionarVenda(int codAgente, double valor, double comissao) {
        for (int i = 0; i < total; i++) {
            if (codigos[i] == codAgente) {
                vendasAgente[i] += valor;
                comissaoAgente[i] += comissao;
            }
        }
    }

    public static double totalVendas(int codAgente) {
        for (int i = 0; i < total; i++) {
            if (codigos[i] == codAgente) {
                return vendasAgente[i];
            }
        }
        return 0;
    }

    public static double totalComissao(int codAgente) {
        for (int i = 0; i < total; i++) {
            if (codigos[i] == codAgente) {
                return comissaoAgente[i];
            }
        }
        return 0;
    }

}
