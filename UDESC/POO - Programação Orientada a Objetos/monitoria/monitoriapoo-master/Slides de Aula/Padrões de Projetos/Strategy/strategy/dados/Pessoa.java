package dados;

public class Pessoa {

    private int cpf;
    private String nome;
    private TipoCliente tipoCliente;
    private int minutosUsados;

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

    public int getMinutosUsados() {
        return this.minutosUsados;
    }

    public void setMinutosUsados(int minutosUsados) {
        this.minutosUsados = minutosUsados;
    }

    public TipoCliente getTipoCliente() {
        return this.tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public double calculaDespesa() {
        return tipoCliente.getTipo().calculaDespesa(minutosUsados);
    }

}