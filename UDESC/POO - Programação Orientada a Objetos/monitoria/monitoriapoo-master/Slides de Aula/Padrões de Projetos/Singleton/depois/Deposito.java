import java.util.LinkedList;
import java.util.List;

public class Deposito {

    private static Deposito instance = null;

    private Deposito() {

    }

    public static Deposito getInstance() {

        if (instance == null) {
            instance = new Deposito();
        }

        return instance;
    }

    private List<Produto> estoque = new LinkedList<Produto>();

    public void adicionarProduto(Produto produto) {
        estoque.add(produto);
    }

    public void removerProduto(Produto produto) throws Exception {
        if (estoque.contains(produto)) {
            estoque.remove(produto);
        } else {
            throw new Exception("Produto indisponivel!");
        }

    }

}