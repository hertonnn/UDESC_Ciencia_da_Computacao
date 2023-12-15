
public class Kelvin implements Observador {

    private double temperatura;

    public void atualizar(Object objeto) {
        double c = (Double) objeto;
        temperatura = c + 273.15;
    }

    public String toString() {
        return String.format("%.2f", temperatura) + "K";
    }

}