package dados;

public class ContaBancaria {
    
    private int cpf;
    protected float saldo;
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    public boolean sacar(float valor){
        if(this.saldo > valor){
            this.saldo = this.saldo - valor;
            return true; // acredito que seja para retornar o restante na conta, até pq n faz sentido retornar o valor que que eu acabei de pedir
        }
        return false;
    }
    public String gerarExtrato(){
        return " O saldo atual: " + this.saldo; 
    }

}
