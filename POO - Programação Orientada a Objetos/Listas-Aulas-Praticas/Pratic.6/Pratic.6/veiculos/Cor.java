public enum Cor {

    ROXO("roxo"), AZUL("azul"), VERDE("verde"), VERMELHO("vermelho"), BRANCO("branco"), PRETO("preto");

    private String cor;

    private Cor(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

}