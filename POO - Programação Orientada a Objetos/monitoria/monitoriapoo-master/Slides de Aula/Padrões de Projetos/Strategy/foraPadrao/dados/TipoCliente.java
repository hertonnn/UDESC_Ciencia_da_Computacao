package dados;

public enum TipoCliente {

    ASSINANTE(1), NAOASSINANTE(2);

    private int tipoCliente;

    private TipoCliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getTipo() {
        return this.tipoCliente;
    }

}