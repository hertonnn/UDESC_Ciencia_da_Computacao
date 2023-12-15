
public class Main {

    public static void main(String[] args) {

        DepartamentoCompras d = new DepartamentoCompras();

        Caixa c1 = new Caixa();
        Caixa c2 = new Caixa();
        Caixa c3 = new Caixa();

        Produto agua = new Produto("Agua", 1.5);

        for (int i = 0; i < 5; i++) {

            d.realizarCompra(agua);

        }

        realizarVenda(c1, agua);
        realizarVenda(c1, agua);
        realizarVenda(c2, agua);
        realizarVenda(c3, agua);
        realizarVenda(c2, agua);
        realizarVenda(c1, agua);

    }

    public static void realizarVenda(Caixa caixa, Produto produto) {

        try {

            caixa.realizarVenda(produto);
            System.out.println("Venda realizada!");

        } catch (Exception e) {

            System.out.println("Venda nao realizada! - " + e.getMessage());

        }

    }

}