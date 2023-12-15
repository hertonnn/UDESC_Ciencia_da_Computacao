public class ProgramaTeste {
    public static void main(String[] srg){

        // Quando declaramos uma variável para associar a um objeto, na verdade, essa variável não guarda o objet, mas, sim, uma maneira de acessá-lo, chamada de referência

        Conta conta1 = new Conta();
        Conta conta2 = new Conta();

        conta1.num_conta = 424242;
        conta1.nome_titular.nome = "Herton"; 
        conta1.saldo = 1200;

        conta2.saldo = 200;

        System.out.println("Saldo conta 1: " + conta1.saldo + "\nSaldo conta 2: " + conta2.saldo);
        
        // usando método transferePara 

        conta1.transferePara(400, conta2);

        System.out.println("Saldo conta 1: " + conta1.saldo + "\nSaldo conta 2: " + conta2.saldo);

        // Usando Classe Cliente

        conta1.nome_titular.sobrenome = "Silveira";
        conta1.nome_titular.cpf = "072.413.652-59";

        System.out.println(conta1.nome_titular.sobrenome);

        // Testando exceção de transferePara 

        conta1.transferePara(850, conta2);
        
        System.out.println("Saldo conta 1: " + conta1.saldo + "\nSaldo conta 2: " + conta2.saldo);



    }
}
