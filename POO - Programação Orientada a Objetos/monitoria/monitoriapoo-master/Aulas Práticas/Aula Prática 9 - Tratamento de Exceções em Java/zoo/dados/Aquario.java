package dados;

public class Aquario extends Viveiro {

    private float altura;
    private float temperatura;

    public Aquario() {
        super();
    }

    public float getAltura() {
        return this.altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getTemperatura() {
        return this.temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float alturaDisponivel() {

        float altura = 0;

        for (Animal a : animais) {
            altura += a.getAltura();
        }

        return altura;
    }

}