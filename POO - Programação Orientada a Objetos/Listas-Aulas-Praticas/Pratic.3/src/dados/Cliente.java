package dados;

public class Cliente {
    private int TAMRESERVAS = 100;
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private Reserva[] reservas = new Reserva[TAMRESERVAS];
    private int quantReservas;

    // Sem métodos getters e setters antes de precisar deles(treino de logica antes de escrita)
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getCpf(){
        return this.cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public Reserva[] getReservas(){
        return this.reservas;
    }
    public int getQuantReservas(){
        return this.quantReservas;
    }


    public void reservarIda(Reserva reserva){
        this.reservas[this.quantReservas] = reserva;
        ++quantReservas;
    }
    public void reservarVolta(Reserva ida, Reserva volta){
        ida.setVolta(volta);
        ida.setIdaEvolta(true);
    }
    
    public String toString(){
        return "\nNome: " + nome + "\nCpf: " + cpf + "\nEndereço: " + endereco + "\nTelefone: " + telefone;
    }

}
