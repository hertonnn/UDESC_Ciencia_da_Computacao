package interfaces;

public class Aviao implements IAcelerar{

    private float velocidade;

    public void acelerar(){
        velocidade += 50;
    }
}