package dados;

import java.util.LinkedList;

public class Cliente {

    private int cpf;
    private String nome;
    private String endereco;
    private int telefone;
    private LinkedList<Reserva> reservas = new LinkedList<Reserva>();

    public int getCpf() {
        return this.cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return this.telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public LinkedList<Reserva> getReservas() {
        return this.reservas;
    }

    public void reservarIda(Reserva reserva) {
        this.reservas.add(reserva);
    }

    public void reservarVolta(Reserva ida, Reserva volta) {
        ida.setIdaEvolta(true);
        ida.setVolta(volta);
    }

    public String toString() {
        String cliente = "";
        cliente += "CPF: " + this.cpf + "\n";
        cliente += "Nome: " + this.nome + "\n";
        cliente += "Endereco: " + this.endereco + "\n";
        cliente += "Telefone: " + this.telefone + "\n";
        return cliente;
    }

}