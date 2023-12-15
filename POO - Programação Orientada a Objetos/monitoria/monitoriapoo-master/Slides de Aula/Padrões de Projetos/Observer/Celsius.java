
public class Celsius implements Observador {

    private double temperatura;

    public void atualizar(Object objeto) {
        temperatura = (Double) objeto;
    }

    public String toString() {
        return String.format("%.2f", temperatura) + "ºC";
    }

}