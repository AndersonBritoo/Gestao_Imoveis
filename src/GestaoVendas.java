// Tudo sobre VENDAS: opções 6 (registar), 7 (listar) e 8 (estatísticas)
public class GestaoVendas {

    // ---------- 6. REGISTAR VENDA ----------
    static void registarVenda() {
        System.out.println("\n--- REGISTAR VENDA ---");

        // imóvel tem de existir e não estar vendido
        Imovel im = GestaoImoveis.pedirImovel();
        if (im == null) {
            return;
        }
        if (im.estado == EstadoImovel.VENDIDO) {
            System.out.println("Esse imóvel já foi vendido.");
            return;
        }
        im.mostrar();

        // o agente tem de ser o responsável pelo imóvel
        Agente agente = GestaoAgentes.pedirAgente();
        if (agente != im.agente) {
            System.out.println("Esse agente não é o responsável (é " + im.agente.nome + "). Venda cancelada.");
            return;
        }

        double valor = Leitura.lerDecimal("Valor final (€): ", 1, 100000000);
        String data = Leitura.lerData("Data (dd/mm/aaaa): ");

        Venda v = efetuarVenda(im, data, valor);

        System.out.println("Venda registada!");
        v.mostrar();
        System.out.println(agente.nome + " já vendeu " + Leitura.dinheiro(agente.totalVendas)
                + " e tem " + Leitura.dinheiro(agente.totalComissao) + " de comissão.");
    }

    // Faz as contas da venda (usado pelo menu E pelo ficheiro dados.txt)
    static Venda efetuarVenda(Imovel im, String data, double valor) {
        double comissao = valor * im.comissao / 100;     // ex: 100000 * 3 / 100 = 3000
        int numero = Dados.vendas.size() + 1;

        Venda v = new Venda(numero, im.codigo, im.agente, data, valor, comissao);
        Dados.vendas.add(v);

        im.estado = EstadoImovel.VENDIDO;      // o imóvel fica vendido
        im.agente.totalVendas += valor;        // atualiza os totais do agente
        im.agente.totalComissao += comissao;

        return v;
    }

    // ---------- 7. LISTAR VENDAS ----------
    static void listarVendas() {
        System.out.println("\n--- VENDAS ---");

        if (Dados.vendas.isEmpty()) {
            System.out.println("Ainda não há vendas.");
            return;
        }
        for (Venda v : Dados.vendas) {
            v.mostrar();
        }

        System.out.println("\nComissões pagas por agente:");
        for (Agente a : Dados.agentes) {
            System.out.println("  " + a.nome + ": " + Leitura.dinheiro(a.totalComissao));
        }
    }

    // ---------- 8. ESTATÍSTICAS ----------
    static void estatisticas() {
        System.out.println("\n--- ESTATÍSTICAS ---");

        int vendidos = 0;
        for (Imovel im : Dados.imoveis) {
            if (im.estado == EstadoImovel.VENDIDO) {
                vendidos++;
            }
        }

        double volume = 0;      // acumuladores
        double comissoes = 0;
        for (Venda v : Dados.vendas) {
            volume += v.valor;
            comissoes += v.comissao;
        }

        System.out.println("Imóveis registados: " + Dados.imoveis.size());
        System.out.println("Imóveis ativos:     " + Dados.contarImoveisAtivos());
        System.out.println("Imóveis vendidos:   " + vendidos);
        System.out.println("Volume de vendas:   " + Leitura.dinheiro(volume));
        System.out.println("Comissões pagas:    " + Leitura.dinheiro(comissoes));
    }
}
