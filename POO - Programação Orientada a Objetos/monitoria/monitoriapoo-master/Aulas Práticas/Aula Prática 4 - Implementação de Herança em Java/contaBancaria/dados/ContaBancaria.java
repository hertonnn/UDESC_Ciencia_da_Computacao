package dados;

public class ContaBancaria {

    private int cpf;
    protected float saldo;

    public ContaBancaria() {
        this.saldo = 0;
    }

    public int getCpf() {
        return this.cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public float getSaldo() {
        return this.saldo;
    }

    public float sacar(float valor) {
        saldo -= valor;
        return valor;
    }

    public String gerarExtrato() {
        return "Saldo disponível: R$" + this.saldo;
    }

    public String toString() {
        return "CPF: " + this.cpf;
    }

}