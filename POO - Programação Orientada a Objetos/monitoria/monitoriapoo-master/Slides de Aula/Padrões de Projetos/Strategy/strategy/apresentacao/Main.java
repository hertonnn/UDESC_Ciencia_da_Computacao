package apresentacao;

import dados.Pessoa;
import dados.TipoCliente;

public class Main {

    public static void main(String[] args) {

        Pessoa p1 = new Pessoa();
        p1.setNome("Pessoa 1");
        p1.setCpf(123);
        p1.setMinutosUsados(30);
        p1.setTipoCliente(TipoCliente.ASSINANTE);

        Pessoa p2 = new Pessoa();
        p2.setNome("Pessoa 2");
        p2.setCpf(456);
        p2.setMinutosUsados(30);
        p2.setTipoCliente(TipoCliente.NAOASSINANTE);

        System.out.println(p1.calculaDespesa());
        System.out.println(p2.calculaDespesa());
    }
}