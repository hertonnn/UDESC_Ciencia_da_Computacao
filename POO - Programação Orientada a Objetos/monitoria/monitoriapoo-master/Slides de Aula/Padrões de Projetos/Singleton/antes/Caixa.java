
public class Caixa {

    private Deposito deposito = new Deposito();

    public void realizarVenda(Produto produto) throws Exception {
        deposito.removerProduto(produto);
    }

}