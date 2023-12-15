public abstract class Veiculo {

    private Cor cor;

    public Cor getCor() {
        return this.cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public abstract String info();

}