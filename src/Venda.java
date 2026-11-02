// Uma VENDA realizada.
// Guarda o CÓDIGO do imóvel (e não o imóvel): se o imóvel for removido,
// a venda continua no histórico.
public class Venda {

    int numero;          // 1, 2, 3, ...
    int codigoImovel;
    Agente agente;       // quem vendeu
    String data;         // dd/mm/aaaa
    double valor;        // valor final da venda (€)
    double comissao;     // comissão calculada (€)

    Venda(int numero, int codigoImovel, Agente agente, String data, double valor, double comissao) {
        this.numero = numero;
        this.codigoImovel = codigoImovel;
        this.agente = agente;
        this.data = data;
        this.valor = valor;
        this.comissao = comissao;
    }

    void mostrar() {
        System.out.println("Venda nº " + numero + " | " + data + " | Imóvel " + codigoImovel
                + " | Agente: " + agente.nome + " | Valor: " + Leitura.dinheiro(valor)
                + " | Comissão: " + Leitura.dinheiro(comissao));
    }
}
