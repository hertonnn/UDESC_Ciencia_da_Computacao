package dados;

public class NaoAssinante implements Cliente {

    public double calculaDespesa(int minutosUsados) {
        return minutosUsados * 0.7;
    }

}