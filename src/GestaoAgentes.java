// Tudo sobre AGENTES: opção 1 (registar) e opção 10 (listar performance)
public class GestaoAgentes {

    // ---------- 1. REGISTAR AGENTE ----------
    static void registarAgente() {
        System.out.println("\n--- REGISTAR AGENTE ---");

        if (Dados.agentes.size() >= Dados.MAX_AGENTES) {
            System.out.println("Já existem " + Dados.MAX_AGENTES + " agentes (limite).");
            return;
        }

        // código com 3 dígitos e que ainda não exista
        int codigo = Leitura.lerInteiro("Código (100-999): ", 100, 999);
        while (Dados.procurarAgente(codigo) != null) {
            System.out.println("  Esse código já existe.");
            codigo = Leitura.lerInteiro("Código (100-999): ", 100, 999);
        }

        String nome = Leitura.lerTexto("Nome: ");

        // NIF com 9 dígitos e que ainda não exista
        String nif = Leitura.lerNif("NIF: ");
        while (Dados.nifExiste(nif)) {
            System.out.println("  Esse NIF já existe.");
            nif = Leitura.lerNif("NIF: ");
        }

        Agente novo = new Agente(codigo, nome, nif);
        Dados.agentes.add(novo);

        System.out.println("Agente registado:");
        novo.mostrar();
    }

    // Mostra os agentes e pede um código até ser um que exista.
    // (só se chama quando já existe pelo menos 1 agente)
    static Agente pedirAgente() {
        System.out.println("Agentes:");
        for (Agente a : Dados.agentes) {
            System.out.println("  " + a.codigo + " - " + a.nome);
        }
        while (true) {
            int codigo = Leitura.lerInteiro("Código do agente: ", 100, 999);
            Agente a = Dados.procurarAgente(codigo);
            if (a != null) {
                return a;
            }
            System.out.println("  Esse agente não existe.");
        }
    }

    // ---------- 10. LISTAR AGENTES (PERFORMANCE) ----------
    static void listarAgentes() {
        System.out.println("\n--- PERFORMANCE DOS AGENTES ---");

        if (Dados.agentes.isEmpty()) {
            System.out.println("Não há agentes.");
            return;
        }

        for (Agente a : Dados.agentes) {
            System.out.println("[" + a.codigo + "] " + a.nome);
            System.out.println("    Imóveis angariados: " + contarImoveis(a));
            System.out.println("    Vendas realizadas:  " + contarVendas(a) + " (" + Leitura.dinheiro(a.totalVendas) + ")");
            System.out.println("    Comissão acumulada: " + Leitura.dinheiro(a.totalComissao));
        }
    }

    // Quantos imóveis tem este agente
    static int contarImoveis(Agente agente) {
        int total = 0;
        for (Imovel im : Dados.imoveis) {
            if (im.agente == agente) {
                total++;
            }
        }
        return total;
    }

    // Quantas vendas fez este agente
    static int contarVendas(Agente agente) {
        int total = 0;
        for (Venda v : Dados.vendas) {
            if (v.agente == agente) {
                total++;
            }
        }
        return total;
    }
}
