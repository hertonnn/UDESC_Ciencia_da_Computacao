public class DepartamentoCompras {

    private Deposito deposito = Deposito.getInstance();

    public void realizarCompra(Produto produto) {
        deposito.adicionarProduto(produto);
    }

}