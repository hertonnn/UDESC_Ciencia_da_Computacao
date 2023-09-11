package apresentacao;

import apresentacao.telas.Dashboard;
import dados.Categoria;
import dados.Conta;
import dados.Registro;
import dados.Tempo;
import dados.Usuario;
import dados.Registro.TipoRegistro;
import negocio.Sistema;

public class Main {
    public static void main(String[] args) {

        Sistema sistema = new Sistema();
        Usuario usuario = new Usuario("herton", "123123");
        sistema.realizaCadastro(usuario);
        Conta conta = new Conta("Minha Carteira", 200.00);
        sistema.criaConta(conta, usuario);
        Categoria categoria = new Categoria("Alimentação");
        Tempo tempo  = new Tempo();
        tempo.setAno(12);
        tempo.setDia(02);
        tempo.setMes(06);
        Registro registro = new Registro(conta, 122.00, TipoRegistro.RECEITA, categoria, tempo, "Gayy");

        sistema.realizaRegistro(registro, usuario, conta);

        System.out.println(conta.getSaldo());
        new Dashboard(usuario);
    }

}
