package interfaces;

public class Carro implements IAcelerar {

    private float velocidade;

    public void acelerar() {
        velocidade += 10;
    }

}