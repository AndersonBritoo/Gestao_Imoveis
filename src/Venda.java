public class Venda {
    static final double COMISSAO = 5; // percentagem

    static int[] codVendas = new int[100];
    static int[] codImoveis = new int[100];
    static int[] agenteVenda = new int[100];
    static String[] datas = new String[100];
    static double[] valores = new double[100];
    static double[] comissoes = new double[100];
    static int totalVendas = 0;

    public static int gerarCodVenda() {
        int codVenda;
        int erros;
        do {
            erros = 0;
            codVenda = (int) (Math.random() * 900) + 100;
            for (int i = 0; i < totalVendas; i++) {
                if (codVendas[i] == codVenda) {
                    erros++;
                }
            }
        } while (erros > 0);
        return codVenda;
    }

    public static int lerCodImovel() {
        int codImovel;
        do {
            System.out.println("Código do imóvel: ");
            codImovel = Agente.sc.nextInt();
        } while (codImovel <= 0);
        return codImovel;
    }

    public static int lerAgenteVenda() {
        int codAgente;
        do {
            System.out.println("Código do agente que fez a venda: ");
            codAgente = Agente.sc.nextInt();
            if (!Agente.existe(codAgente)) {
                System.out.println("Esse agente não existe. Tente outro.");
            }
        } while (!Agente.existe(codAgente));
        return codAgente;
    }

    public static String lerData() {
        System.out.println("Data da venda (dd/mm/aaaa): ");
        return Agente.sc.next();
    }

    public static double lerValor() {
        double valor;
        do {
            System.out.println("Valor final da venda (€): ");
            valor = Agente.sc.nextDouble();
        } while (valor <= 0);
        return valor;
    }

    public static void registoVenda() {
        if (Agente.total == 0) {
            System.out.println("Registe primeiro um agente.");
            return;
        }

        int i = totalVendas;
        codVendas[i] = gerarCodVenda();
        codImoveis[i] = lerCodImovel();
        agenteVenda[i] = lerAgenteVenda();
        datas[i] = lerData();
        valores[i] = lerValor();
        comissoes[i] = valores[i] * COMISSAO / 100;

        Agente.adicionarVenda(agenteVenda[i], valores[i], comissoes[i]);
        totalVendas++;
    }

    public static void listarVendas() {
        for (int i = 0; i < totalVendas; i++) {
            System.out.println("Venda " + codVendas[i] + " - Imóvel " + codImoveis[i]
                    + " - Agente " + agenteVenda[i] + " - " + datas[i]
                    + " - " + valores[i] + " € - Comissão: " + comissoes[i] + " €");
        }
    }
}
