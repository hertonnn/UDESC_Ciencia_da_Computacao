
public class Fahrenheit implements Observador {

    private double temperatura;

    public void atualizar(Object objeto) {
        double c = (Double) objeto;
        temperatura = c * 1.8 + 32;
    }

    public String toString() {
        return String.format("%.2f", temperatura) + "ºF";
    }

}