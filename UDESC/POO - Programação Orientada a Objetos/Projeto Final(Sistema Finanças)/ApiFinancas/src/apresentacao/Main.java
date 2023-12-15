package apresentacao;
import apresentacao.telas.Cadastro;
import apresentacao.telas.Dashboard;
import apresentacao.telas.Login;
import negocio.Sistema;

public class Main {
    public static void main(String[] args) {

        Sistema sistema = Sistema.getInstance();
        // Login login = new Login();
        // Cadastro cadastro = new Cadastro();

        sistema.realizaLogin("herton", "123123");
        new Dashboard(sistema);

        // login.getBotao().addActionListener(entrar -> {               
        //     String nome = login.getCampoNome().getText();
        //     String senha = String.valueOf(login.getCampoSenha().getPassword());

        //     if(!login.vazio() && sistema.realizaLogin(nome, senha)){
        //         login.fecharLogin();
        //         new Dashboard(sistema);
        //     }
        //     else{
        //         login.invalido();
        //     }  

        // });
            
        // login.getBotaoCadastro().addActionListener(irCadastro -> {
        //     login.fechaLogin();
        //     cadastro.abrir();

        // });
        // cadastro.getBotao().addActionListener(cadastrar ->{

        //     String nomeCad = cadastro.getCampoNome().getText();
        //     String senhaCad = String.valueOf(cadastro.getCampoSenha().getPassword());

        //     if(!cadastro.vazio() && sistema.realizaCadastro(nomeCad, senhaCad)){
        //         cadastro.cadastrado();
        //         cadastro.fechar();
        //         login.abreLogin();
        //     }
        //     else{
        //         cadastro.invalido();
        //     }       
        // });

        // cadastro.getBotaoVoltar().addActionListener(voltar ->{
        //     cadastro.fechar();
        //     login.abreLogin();
        // });

    }

}
