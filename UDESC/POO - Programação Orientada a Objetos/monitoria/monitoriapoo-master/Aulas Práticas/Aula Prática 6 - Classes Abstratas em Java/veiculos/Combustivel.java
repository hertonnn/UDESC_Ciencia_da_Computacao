public enum Combustivel {

    GASOLINA("gasolina"), ALCOOL("alcool"), DIESEL("diesel"), GAS("gas");

    private String tipo;

    private Combustivel(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}