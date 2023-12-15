package dados;

public class Endereco {

    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private int cep;

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCep() {
        return this.cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String toString() {

        String endereco = "";

        endereco += "Rua: " + this.rua;
        endereco += ", ";
        endereco += this.numero;
        endereco += " - ";
        endereco += this.bairro;
        endereco += ", ";
        endereco += this.cidade;
        endereco += " - ";
        endereco += this.estado;
        endereco += " - CEP: ";
        endereco += this.cep;

        return endereco;
    }

}