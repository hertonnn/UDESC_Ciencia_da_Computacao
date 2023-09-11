package dados;

// igualar objetos a.equals(b) se a é igual a b 

public class Reserva {
    private int numReserva;
    private String dataVooIda;
    private String horaVoo;
    private float preco;
    private String classeVoo;
    private boolean idaEvolta;
    private int poltrona;

    private Cidade cidadeida;
    private Cidade cidadevolta;
    
    private Reserva volta;
    
// Sem métodos getters e setters até eu realmente precisar deles (treino de lógica antes de escrita)
    public void setVolta(Reserva volta){
        this.volta = volta;
    }
    public void setCidadeIda(Cidade cidadeida){
        this.cidadeida = cidadeida;
    }
    public void setCidadeVolta(Cidade cidadevolta){
        this.cidadevolta = cidadevolta;
    }
    public void setIdaEvolta(boolean idaevolta){
        this.idaEvolta = idaevolta;
    }
    public void setDataVoo(String data){
        this.dataVooIda = data;
    }
    public void setHoraVoo(String hora){
        this.horaVoo = hora;
    }
    public void setClasseVoo(String classe){
        this.classeVoo = classe;
    }
    public void setPoltrona(int poltrona){
        this.poltrona = poltrona;
    }

    public String toString(){
        return  "\nCidade volta: " + cidadevolta.getNome() + "\nCidade ida: " + cidadeida.getNome() + "\nEstado ida: " + cidadeida.getEstado() + "\nEstado volta: " + cidadevolta.getEstado() + "\nNúmero da Reserva: " + numReserva + "\nData do Voo: " + dataVooIda + "\nHora do Voo: " + horaVoo+ "\nPreço do Voo: " + preco + "\nClasse do Voo: " + classeVoo + "\nIda e Volta: " + idaEvolta + "\nPoltrona: " + poltrona;
    }

    // public static void main(String[] args) {
    //     Reserva voo = new Reserva();
    //     System.out.println(voo);
    // }
}
