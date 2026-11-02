import java.util.ArrayList;

// Tudo sobre IMÓVEIS: opções 2, 3, 4, 5 e 9
// Ideia principal: UM só método filtrar() serve para listar e para pesquisar.
public class GestaoImoveis {

    // ---------- 2. REGISTAR IMÓVEL ----------
    static void registarImovel() {
        System.out.println("\n--- REGISTAR IMÓVEL ---");

        if (Dados.agentes.isEmpty()) {
            System.out.println("Registe primeiro um agente.");
            return;
        }
        if (Dados.contarImoveisAtivos() >= Dados.MAX_IMOVEIS) {
            System.out.println("Já existem " + Dados.MAX_IMOVEIS + " imóveis ativos (limite).");
            return;
        }

        // código com 5 dígitos e que ainda não exista
        int codigo = Leitura.lerInteiro("Código (10000-99999): ", 10000, 99999);
        while (Dados.procurarImovel(codigo) != null) {
            System.out.println("  Esse código já existe.");
            codigo = Leitura.lerInteiro("Código (10000-99999): ", 10000, 99999);
        }

        TipoImovel tipo = Leitura.lerTipo(false);
        String local = Leitura.lerTexto("Localização: ");
        double area = Leitura.lerDecimal("Área (m²): ", 1, 100000);
        double preco = Leitura.lerDecimal("Preço (€): ", 1, 100000000);
        Agente agente = GestaoAgentes.pedirAgente();
        double comissao = Leitura.lerDecimal("Comissão (%): ", 0.1, 100);

        Imovel novo = new Imovel(codigo, tipo, local, area, preco, agente, comissao);
        Dados.imoveis.add(novo);

        System.out.println("Imóvel registado:");
        novo.mostrar();
    }

    // ---------- 3. ATUALIZAR IMÓVEL ----------
    static void atualizarImovel() {
        System.out.println("\n--- ATUALIZAR IMÓVEL ---");

        Imovel im = pedirImovel();
        if (im == null) {
            return;
        }
        if (im.estado == EstadoImovel.VENDIDO) {
            System.out.println("Imóvel já vendido, não pode ser alterado.");
            return;
        }

        int op;
        do {
            im.mostrar();
            System.out.println("1 - Preço | 2 - Estado | 3 - Agente | 4 - Comissão | 0 - Terminar");
            op = Leitura.lerInteiro("O que quer alterar? ", 0, 4);

            switch (op) {
                case 1:
                    im.preco = Leitura.lerDecimal("Novo preço (€): ", 1, 100000000);
                    break;
                case 2:
                    EstadoImovel novo = Leitura.lerEstado(false);
                    if (novo == EstadoImovel.VENDIDO) {
                        System.out.println("  Para vender use a opção \"Registar venda\".");
                    } else {
                        im.estado = novo;
                    }
                    break;
                case 3:
                    im.agente = GestaoAgentes.pedirAgente();
                    break;
                case 4:
                    im.comissao = Leitura.lerDecimal("Nova comissão (%): ", 0.1, 100);
                    break;
            }
        } while (op != 0);

        System.out.println("Informação atualizada:");
        im.mostrar();
    }

    // ---------- 4. LISTAR IMÓVEIS ----------
    static void listarImoveis() {
        System.out.println("\n--- LISTAR IMÓVEIS ---");
        System.out.println("1 - Todos | 2 - Por tipo | 3 - Por agente | 0 - Voltar");
        int op = Leitura.lerInteiro("Opção: ", 0, 3);

        // null / "" / 0 = esse critério não interessa
        if (op == 1) {
            mostrarLista(filtrar(null, "", 0, null, null));
        } else if (op == 2) {
            TipoImovel tipo = Leitura.lerTipo(false);
            mostrarLista(filtrar(tipo, "", 0, null, null));
        } else if (op == 3 && !Dados.agentes.isEmpty()) {
            Agente agente = GestaoAgentes.pedirAgente();
            mostrarLista(filtrar(null, "", 0, null, agente));
        }
    }

    // ---------- 5. FILTRAR IMÓVEIS ----------
    static void filtrarImoveis() {
        System.out.println("\n--- FILTRAR IMÓVEIS ---");
        System.out.println("(0 ou Enter = ignorar esse critério)");

        TipoImovel tipo = Leitura.lerTipo(true);
        String local = Leitura.lerTextoOpcional("Localização: ");
        double precoMax = Leitura.lerDecimal("Preço máximo (€): ", 0, 100000000);
        EstadoImovel estado = Leitura.lerEstado(true);

        mostrarLista(filtrar(tipo, local, precoMax, estado, null));
    }

    // Devolve uma lista nova só com os imóveis que cumprem TODOS os critérios.
    // Critério vazio (null, "" ou 0) = não interessa.
    static ArrayList<Imovel> filtrar(TipoImovel tipo, String local, double precoMax,
                                     EstadoImovel estado, Agente agente) {
        ArrayList<Imovel> resultado = new ArrayList<>();

        for (Imovel im : Dados.imoveis) {
            boolean serve = true;   // começa a servir; se falhar um critério deixa de servir

            if (tipo != null && im.tipo != tipo) {
                serve = false;
            }
            if (!local.isEmpty() && !im.localizacao.equalsIgnoreCase(local)) {
                serve = false;
            }
            if (precoMax > 0 && im.preco > precoMax) {
                serve = false;
            }
            if (estado != null && im.estado != estado) {
                serve = false;
            }
            if (agente != null && im.agente != agente) {
                serve = false;
            }

            if (serve) {
                resultado.add(im);
            }
        }
        return resultado;
    }

    // Mostra todos os imóveis de uma lista
    static void mostrarLista(ArrayList<Imovel> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum imóvel encontrado.");
            return;
        }
        for (Imovel im : lista) {
            im.mostrar();
        }
        System.out.println("Total: " + lista.size());
    }

    // ---------- 9. REMOVER IMÓVEIS ----------
    static void removerImoveis() {
        System.out.println("\n--- REMOVER IMÓVEIS ---");
        System.out.println("1 - Remover um | 2 - Remover todos | 0 - Voltar");
        int op = Leitura.lerInteiro("Opção: ", 0, 2);

        if (op == 1) {
            Imovel im = pedirImovel();
            if (im != null && Leitura.confirmar("Remover o imóvel " + im.codigo + "?")) {
                Dados.imoveis.remove(im);
                System.out.println("Imóvel removido.");
            }
        } else if (op == 2) {
            if (Leitura.confirmar("Remover TODOS os imóveis?")) {
                Dados.imoveis.clear();
                System.out.println("Todos os imóveis foram removidos (as vendas ficam no histórico).");
            }
        }
    }

    // Pede um código e devolve o imóvel (ou null se não existir)
    static Imovel pedirImovel() {
        int codigo = Leitura.lerInteiro("Código do imóvel: ", 10000, 99999);
        Imovel im = Dados.procurarImovel(codigo);
        if (im == null) {
            System.out.println("Esse imóvel não existe.");
        }
        return im;
    }
}
