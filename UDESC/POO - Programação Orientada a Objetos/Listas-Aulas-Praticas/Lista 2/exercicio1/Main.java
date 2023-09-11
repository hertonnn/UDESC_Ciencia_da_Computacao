package exercicio1;
import java.util.*;   

public class Main {

    Scanner input = new Scanner(System.in);
    List<Pessoa>  pessoas = new ArrayList<>();

    public void menu(){
        Pessoa pessoa = new Pessoa();
        System.out.println("Digite o nome");
        pessoa.setNome(this.input.nextLine());
        System.out.println("Digite o cpf");
        pessoa.setCpf(Integer.parseInt(this.input.nextLine()));
        System.out.println("Digite a idade");
        pessoa.setIdade(Integer.parseInt(this.input.nextLine()));
        System.out.println("Digite a cidade");
        pessoa.setCidade(this.input.nextLine());
        pessoas.add(pessoa);
    }
    public void ordena(){
        Collections.sort(pessoas);

        printaLista();
    }
    public void printaLista(){

        for(Pessoa pessoa : pessoas){
            
            if(pessoa.getIdade() >= 12 && pessoa.getIdade() <=12){

                System.out.println("1 at\u0301e 12:  crian \u0327cas");
                System.out.println("- "+ pessoa);
            }
            if(pessoa.getIdade() >= 13 && pessoa.getIdade() <=18){
                System.out.println("13 at\u0301e 18:  adolescentes");
                System.out.println("- "+ pessoa);
            } 

            if(pessoa.getIdade() >= 19 && pessoa.getIdade() <=25){
                System.out.println("19 at\u0301e 25:  jovens");
                System.out.println("- "+ pessoa);
            } 

            if(pessoa.getIdade() >= 26 && pessoa.getIdade() <=59){
                System.out.println("26 at\u0301e 59:  adultos");
                System.out.println("- "+ pessoa);
            } 

            if(pessoa.getIdade() >= 60 ){
                System.out.println("60 ou mais:  idosos");
                System.out.println("- "+ pessoa);
            } 
        }
    }
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Main main = new Main();
        int opcao = 0;

        while(opcao!=-1){
            main.menu();
            main.ordena();
            System.out.println("Deseja continuar ? \n sim(-1) ou não (qualquer numero)");
            opcao = input.nextInt();
            
        }
    }
}
