package dados;

public enum TipoCliente {

    ASSINANTE(new Assinante()), NAOASSINANTE(new NaoAssinante());

    private Cliente tipo;

    private TipoCliente(Cliente tipo) {
        this.tipo = tipo;
    }

    public Cliente getTipo() {
        return this.tipo;
    }

}