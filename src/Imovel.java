// Um IMÓVEL angariado pela imobiliária.
public class Imovel {

    int codigo;            // 5 dígitos (10000 a 99999)
    TipoImovel tipo;       // APARTAMENTO, MORADIA, ...
    String localizacao;    // cidade ou zona
    double area;           // m²
    double preco;          // preço de venda (€)
    EstadoImovel estado;   // DISPONIVEL, RESERVADO ou VENDIDO
    Agente agente;         // o agente responsável (ligação direta à ficha do agente)
    double comissao;       // percentagem para o agente (ex: 3.5 = 3,5%)

    // Construtor: um imóvel novo fica sempre DISPONIVEL
    Imovel(int codigo, TipoImovel tipo, String localizacao, double area,
           double preco, Agente agente, double comissao) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.area = area;
        this.preco = preco;
        this.estado = EstadoImovel.DISPONIVEL;
        this.agente = agente;
        this.comissao = comissao;
    }

    // Mostra os dados do imóvel numa linha
    void mostrar() {
        System.out.println("[" + codigo + "] " + tipo + " | " + localizacao + " | " + area + " m² | "
                + Leitura.dinheiro(preco) + " | " + estado
                + " | Agente: " + agente.nome + " (" + comissao + "%)");
    }
}
