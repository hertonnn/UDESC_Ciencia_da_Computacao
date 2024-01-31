package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dados.Email;
import dados.Usuario;

public class Sistema {
    public static void main (String[] args){
        Sistema sistema = new Sistema();
        Scanner input = new Scanner(System.in);
        List<Usuario> usuarios = new ArrayList<>();
        int op=0;

        while(op!=-1){
            System.out.println("Digite uma opcao");
            System.out.println("1 - Cadastrar Usuario");
            System.out.println("2 - Fazer Login");
            System.out.println("-1 - Sair do Programa");
            
            op = Integer.parseInt((input.nextLine())); 

            switch (op) {
                case 1:
                    Usuario novoUsuario = Usuario.cadastrarUsuario();
                    usuarios.add(novoUsuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;
                case 2:
                System.out.print("Digite o nome de usuário: ");
                String nomeUsuario = input.next();
                System.out.print("Digite a senha: ");
                String senha = input.next();
            
                boolean loginSucesso = false;
                for (Usuario usuario : usuarios) {
                    if (usuario.fazerLogin(nomeUsuario, senha, usuarios)) {
                        loginSucesso = true;
                        System.out.println("Login realizado om sucesso");
                    }
                    else{
                        System.out.println("Usuário e/ou senha incorretos");
                    }
                }
                break;
                case -1:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            Integer.parseInt((input.nextLine())); 

        }
            input.close();
    }
}
