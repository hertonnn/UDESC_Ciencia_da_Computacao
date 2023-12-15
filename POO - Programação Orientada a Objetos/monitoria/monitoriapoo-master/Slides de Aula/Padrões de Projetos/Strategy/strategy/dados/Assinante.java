package dados;

public class Assinante implements Cliente {

    public double calculaDespesa(int minutosUsados) {
        return minutosUsados * 0.5;
    }

}