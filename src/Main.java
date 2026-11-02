// Ponto de entrada: carrega os dados e mostra o menu.
// O trabalho está nos ficheiros GestaoAgentes, GestaoImoveis e GestaoVendas.
public class Main {

    public static void main(String[] args) {
        System.out.println("=== GESTÃO DA IMOBILIÁRIA ===");
        Dados.carregar("dados.txt");

        int opcao;
        do {
            mostrarMenu();
            opcao = Leitura.lerInteiro("Opção: ", 0, 10);

            switch (opcao) {
                case 1:  GestaoAgentes.registarAgente();  break;
                case 2:  GestaoImoveis.registarImovel();  break;
                case 3:  GestaoImoveis.atualizarImovel(); break;
                case 4:  GestaoImoveis.listarImoveis();   break;
                case 5:  GestaoImoveis.filtrarImoveis();  break;
                case 6:  GestaoVendas.registarVenda();    break;
                case 7:  GestaoVendas.listarVendas();     break;
                case 8:  GestaoVendas.estatisticas();     break;
                case 9:  GestaoImoveis.removerImoveis();  break;
                case 10: GestaoAgentes.listarAgentes();   break;
                case 0:  System.out.println("Adeus!");    break;
            }
        } while (opcao != 0);
    }

    static void mostrarMenu() {
        System.out.println("\n========= MENU =========");
        System.out.println(" 1 - Registar agente");
        System.out.println(" 2 - Registar imóvel");
        System.out.println(" 3 - Atualizar imóvel");
        System.out.println(" 4 - Listar imóveis");
        System.out.println(" 5 - Filtrar imóveis");
        System.out.println(" 6 - Registar venda");
        System.out.println(" 7 - Listar vendas");
        System.out.println(" 8 - Estatísticas");
        System.out.println(" 9 - Remover imóvel");
        System.out.println("10 - Listar agentes");
        System.out.println(" 0 - Sair");
    }
}
