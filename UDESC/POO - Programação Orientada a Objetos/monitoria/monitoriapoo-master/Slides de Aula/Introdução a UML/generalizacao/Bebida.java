package generalizacao;

import generalizacao.Produto;

class Bebida extends Produto{

    private float volume;

    public void setVolume(float volume){
        this.volume = volume;
    }

    public float getVolume(){
        return this.volume;
    }

}