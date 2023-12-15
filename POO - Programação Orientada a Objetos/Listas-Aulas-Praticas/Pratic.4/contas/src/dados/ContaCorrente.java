package dados;

public class ContaCorrente extends ContaBancaria{

    public ContaCorrente(){
        super();
    }
    public boolean depositar(float valor){
        if(valor < 0){
            return false;
        }
        else{

            this.saldo += valor;
            return true;
        }
    }
}
