// São como métodos,  chamados no momento de criação de um objeto
public class Construtores {
    // essa variavel com static vai ser global na classe, ou seja, não sera de nenhum objeto
    // criado. 
    private static int totalContas;
    String titular;
    int numero;
    double saldo;
    
    // construtor
    Construtores(String titular){
        System.out.println("Conta sendo criada...\nBem vindo, " + titular);
        Construtores.totalContas = Construtores.totalContas + 1;
    }
    Construtores(int num, String titular){
        this(titular); // chamando outro construtor
        
        
    }
    // static para ser acessado como método da classe 
    public static int getTotalContas() {
        return Construtores.totalContas;
    }

    // Criando conta
    public static void main(String[] srg){
        Construtores conta1 = new Construtores("Tiago");
        Construtores conta2 = new Construtores("Flavio");
        Construtores conta3 = new Construtores("Henrique");
        Construtores conta4 = new Construtores("Carla");    
        // Saber quantas contas foram criadas 
        
        int totaldeContas = Construtores.getTotalContas();
        System.out.println(totaldeContas);
        }
}
