
public class Temporada {

    private int numero;

    public Temporada(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String toString() {
        return "Temporada " + numero;
    }

    public boolean equals(Object o) {
        Temporada t = (Temporada) o;
        if (t.getNumero() == numero) {
            return true;
        }
        return false;
    }

}