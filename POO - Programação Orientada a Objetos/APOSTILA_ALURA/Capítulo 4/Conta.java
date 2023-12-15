// Seguindo exemplo da apostilha pag.37 4.2
public class Conta {

    int num_conta;
    Cliente nome_titular = new Cliente(); 
    // quando chamarem   new Conta, 
    //haverá um new Cliente para ele
    
    double saldo;
    
    boolean saca(double valor){
        
        //usamos a palavra-chave  this  para mostrar que esse é um atributo, e não uma simples variável. Vamos tb verificars e 

        if(this.saldo < valor){
            return false;
        }
        else{
            this.saldo = this.saldo - valor;
            return true;
        }

    }
    void deposita(double valor){
        this.saldo += valor;
        
    }
    void imprime_titular(){
        System.out.println(this.nome_titular);
    }
    // Métodos com retorno (precisam ter return no corpo)
    
    double saldo_atual(){
        return this.saldo;
    }

    // O Método transfere()

    boolean transferePara(double valor, Conta conta_destino){
        boolean retirou = this.saca(valor);
        if(!retirou){
            // não deu para sacar
            return false;
        }
        else {
            conta_destino.deposita(valor); // deposita na de interesse
            return true;
        }
    }
    String devolve_conta(){
        return "ok";
    }

}


