package dados;

public class Cidade {
    private String nome;
    private String estado;

    public String getNome() {
        return this.nome;
    }

    public String setNome(String nome) {
        return this.nome = nome;
    }

    public String getEstado() {
        return this.estado;
    }

    public String setEstado(String estado) {
        return this.estado = estado;
    }

    public String toString() {
        return "\nNome: " + this.nome + "\nEstado: " + this.estado;

    }
    // Comparar dois objetos em java 
    /*public boolean equals(Object o) {
        if (o instanceof Cidade) {
            Cidade c = (Cidade) o;
            if (nome.equals(c.getNome()) && estado.equals(c.getEstado())) {

                return true;
            } else {
                return false;
            }

        }
    }*/
}