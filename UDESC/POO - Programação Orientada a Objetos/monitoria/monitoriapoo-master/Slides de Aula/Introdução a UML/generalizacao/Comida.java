package generalizacao;

import generalizacao.Produto;

class Comida extends Produto{

    private float peso;

    public void setPeso(float peso){
        this.peso = peso;
    }

    public float getPeso(){
        return this.peso;
    }

}