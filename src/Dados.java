import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// Onde ficam GUARDADOS os dados do programa (3 listas)
// + métodos de procura + carregar o ficheiro dados.txt
public class Dados {

    // Limites do enunciado (final = constante)
    static final int MAX_IMOVEIS = 50;
    static final int MAX_AGENTES = 10;

    // As 3 listas do programa
    static ArrayList<Agente> agentes = new ArrayList<>();
    static ArrayList<Imovel> imoveis = new ArrayList<>();
    static ArrayList<Venda> vendas = new ArrayList<>();

    // Procura um agente pelo código. Se não existir devolve null.
    static Agente procurarAgente(int codigo) {
        for (Agente a : agentes) {
            if (a.codigo == codigo) {
                return a;
            }
        }
        return null;
    }

    // Procura um imóvel pelo código. Se não existir devolve null.
    static Imovel procurarImovel(int codigo) {
        for (Imovel im : imoveis) {
            if (im.codigo == codigo) {
                return im;
            }
        }
        return null;
    }

    // true se já houver um agente com este NIF
    static boolean nifExiste(String nif) {
        for (Agente a : agentes) {
            if (a.nif.equals(nif)) {
                return true;
            }
        }
        return false;
    }

    // Conta os imóveis que ainda não foram vendidos
    static int contarImoveisAtivos() {
        int total = 0;
        for (Imovel im : imoveis) {
            if (im.estado != EstadoImovel.VENDIDO) {
                total++;
            }
        }
        return total;
    }

    // ---------------------------------------------------------------
    // Lê o ficheiro linha a linha. Cada linha tem campos separados por ";"
    //   AGENTE;codigo;nome;nif
    //   IMOVEL;codigo;TIPO;localizacao;area;preco;ESTADO;codigoAgente;comissao
    //   VENDA;codigoImovel;data;valor
    // Linhas vazias ou começadas por # são ignoradas.
    // ---------------------------------------------------------------
    static void carregar(String nomeFicheiro) {
        // Em Java, abrir um ficheiro obriga a usar try/catch (pode não existir)
        try {
            Scanner leitor = new Scanner(new File(nomeFicheiro));
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                if (!linha.isEmpty() && !linha.startsWith("#")) {
                    String[] campos = linha.split(";");   // "A;B;C" -> {"A","B","C"}
                    switch (campos[0]) {
                        case "AGENTE": carregarAgente(campos); break;
                        case "IMOVEL": carregarImovel(campos); break;
                        case "VENDA":  carregarVenda(campos);  break;
                    }
                }
            }
            leitor.close();
            System.out.println("Dados carregados: " + agentes.size() + " agentes, "
                    + imoveis.size() + " imóveis, " + vendas.size() + " vendas.");
        } catch (Exception e) {
            System.out.println("Não foi possível ler o ficheiro " + nomeFicheiro + ". O programa começa vazio.");
        }
    }

    // AGENTE;101;Ana Silva;123456789
    static void carregarAgente(String[] campos) {
        int codigo = Integer.parseInt(campos[1]);       // texto -> inteiro
        agentes.add(new Agente(codigo, campos[2], campos[3]));
    }

    // IMOVEL;10001;APARTAMENTO;Viseu;95.5;150000;DISPONIVEL;101;3.0
    static void carregarImovel(String[] campos) {
        int codigo = Integer.parseInt(campos[1]);
        TipoImovel tipo = TipoImovel.valueOf(campos[2]);   // "MORADIA" -> TipoImovel.MORADIA
        String local = campos[3];
        double area = Double.parseDouble(campos[4]);
        double preco = Double.parseDouble(campos[5]);
        EstadoImovel estado = EstadoImovel.valueOf(campos[6]);
        Agente agente = procurarAgente(Integer.parseInt(campos[7]));
        double comissao = Double.parseDouble(campos[8]);

        if (agente != null) {   // só aceita se o agente existir
            Imovel im = new Imovel(codigo, tipo, local, area, preco, agente, comissao);
            im.estado = estado;
            imoveis.add(im);
        }
    }

    // VENDA;10002;15/09/2026;310000
    static void carregarVenda(String[] campos) {
        Imovel im = procurarImovel(Integer.parseInt(campos[1]));
        String data = campos[2];
        double valor = Double.parseDouble(campos[3]);

        if (im != null) {
            GestaoVendas.efetuarVenda(im, data, valor);   // o MESMO método usado no menu
        }
    }
}
