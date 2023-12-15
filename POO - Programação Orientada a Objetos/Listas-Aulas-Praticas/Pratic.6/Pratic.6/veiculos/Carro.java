public class Carro extends Veiculo {

    private int numeroPortas;
    private Combustivel combustivel;

    public int getNumeroPortas() {
        return this.numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public Combustivel getCombustivel() {
        return this.combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public String info() {
        return "\nCarro\n" + "Cor: " + this.getCor() + "\n" + "Numero de portas: " + numeroPortas + "\n"
                + "Tipo de combustivel: " + combustivel + "\n";
    }

}