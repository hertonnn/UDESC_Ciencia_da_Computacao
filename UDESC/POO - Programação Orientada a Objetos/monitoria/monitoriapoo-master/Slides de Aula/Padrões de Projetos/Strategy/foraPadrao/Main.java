import dados.Pessoa;
import dados.TipoCliente;

public class Main {

    public static void main(String[] args) {

        Pessoa p1 = new Pessoa();
        p1.setNome("João");
        p1.setCpf(123);
        p1.setMinutosUsados(30);
        p1.setTipoCliente(TipoCliente.ASSINANTE);

        Pessoa p2 = new Pessoa();
        p2.setNome("Julia");
        p2.setCpf(456);
        p2.setMinutosUsados(30);
        p2.setTipoCliente(TipoCliente.NAOASSINANTE);

        System.out.println("Pessoa 1: R$" + p1.getDespesa());
        System.out.println("Pessoa 2: R$" + p2.getDespesa());
    }
}