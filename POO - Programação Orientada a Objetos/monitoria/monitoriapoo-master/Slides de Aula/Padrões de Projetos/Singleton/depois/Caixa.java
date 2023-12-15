
public class Caixa {

    private Deposito deposito = Deposito.getInstance();

    public void realizarVenda(Produto produto) throws Exception {
        deposito.removerProduto(produto);
    }

}