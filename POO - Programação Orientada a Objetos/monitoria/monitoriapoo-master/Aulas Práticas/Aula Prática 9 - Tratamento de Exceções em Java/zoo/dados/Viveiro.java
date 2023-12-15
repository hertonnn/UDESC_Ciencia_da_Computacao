package dados;

import java.util.List;

public class Viveiro {

    private float comprimento;
    private float largura;
    protected List<Animal> animais;

    public float getComprimento() {
        return this.comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }

    public float getLargura() {
        return this.largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public void addAnimal(Animal a) {
        this.animais.add(a);
    }

    public float larguraDisponivel() {

        float largura = 0;

        for (Animal a : animais) {
            largura += a.getLargura();
        }

        return largura;
    }

    public float comprimentoDisponivel() {

        float comprimento = 0;

        for (Animal a : animais) {
            comprimento += a.getComprimento();
        }

        return comprimento;

    }

}