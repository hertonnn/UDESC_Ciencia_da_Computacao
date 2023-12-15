public class DepartamentoCompras {

    private Deposito deposito = new Deposito();

    public void realizarCompra(Produto produto) {
        deposito.adicionarProduto(produto);
    }

}