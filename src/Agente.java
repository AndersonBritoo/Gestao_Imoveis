// Um AGENTE imobiliário (o vendedor).
// A classe é como uma "ficha": cada new Agente(...) cria uma ficha nova.
public class Agente {

    int codigo;            // 3 dígitos (100 a 999)
    String nome;
    String nif;            // 9 dígitos, guardado como texto
    double totalVendas;    // soma das vendas (€)
    double totalComissao;  // soma das comissões (€)

    // Construtor: preenche a ficha. Os totais começam a zero.
    // this.codigo = campo da ficha | codigo = valor recebido
    Agente(int codigo, String nome, String nif) {
        this.codigo = codigo;
        this.nome = nome;
        this.nif = nif;
        this.totalVendas = 0;
        this.totalComissao = 0;
    }

    // Mostra os dados do agente numa linha
    void mostrar() {
        System.out.println("[" + codigo + "] " + nome + " | NIF: " + nif);
    }
}
