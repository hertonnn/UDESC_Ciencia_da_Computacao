import java.util.Scanner;

public class ValidaCpf {
    
    public boolean Validacpf(String cpf){
        int tamCpf = 11;
        char[] cpfFormatado = new char[tamCpf]; 


        
        for(int i = 0; i < tamCpf; i++){
            
            cpfFormatado[i] = cpf.toCharArray()[i];
        }
        return true;
}
public static void main(String[] args) {

        ValidaCpf validaCpf = new ValidaCpf();
        Scanner input = new Scanner(System.in);
        System.out.println("Cpf:");
        
        String cpf = input.nextLine();
        System.out.println(cpf.chars());
        Integer.parseInt((input.nextLine()));
}}
