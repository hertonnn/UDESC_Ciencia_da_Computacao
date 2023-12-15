package exercicio2e3;

import java.util.ArrayList;
import java.util.List;

public class Matriz{
    
    private int n;
    private int m;
    private T[][] matriz;

    public Matriz(int n, int m){
        this.m = m;
        this.n = n;

        matriz = new T[n][m];
    }
    // Enumerador 
    public enum Quadrante{
        PRIMEIRO,
        SEGUNDO,
        TERCEIRO,
        QUARTO
    }
    // Métodos
    public boolean set(T objeto, int i, int j){
        if(i > this.n || j > m){
            return false;
        }
        else{
            this.matriz[i][j] = objeto;
            return true;
        }
        
    }

    public T get(int i, int j){
        if(i > this.n || j > this.m){
            return null;
        }
        return matriz[i][j];
    }

    public List<T> getLinha(int linha){

        List<T> listaLinha = new ArrayList<>();

        for(T elemento : matriz[linha]){
            listaLinha.add(elemento);
        }

        return listaLinha;
    }

    public List<T> getColuna(int coluna){

        List<T> listaColuna = new ArrayList<>();

        for(int i = 0; i < this.n; i++){
            listaColuna.add(matriz[i][coluna]);
        }

        return listaColuna;
    }

    public List<T> getElementosQuadrante(Quadrante quadrante){

        switch(quadrante){
            // sei lá qual é essa lógica para achar os quadrantes
            case PRIMEIRO:
                return pegaQuadrantePrimeiro();
            case SEGUNDO:
                return pegaQuadranteSegundo();
            case TERCEIRO:
                return pegaQuadranteTerceiro();
            case QUARTO:
                return pegaQuadranteQuarto();
        }
        return null;
    }
    public List<T> pegaQuadrantePrimeiro(){
        List<T> elementosQuadrante = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                elementosQuadrante.add(matriz[i][j]);
            }
        }
        return elementosQuadrante;
    }
    public List<T> pegaQuadranteSegundo(){
        List<T> elementosQuadrante = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            for(int j = 3; j < 5; j++){
                elementosQuadrante.add(matriz[i][j]);
            }
        }
        return elementosQuadrante;
    }
    public List<T> pegaQuadranteTerceiro(){
        List<T> elementosQuadrante = new ArrayList<>();

        for(int i = 2; i < 5; i++){
            for(int j = 0; j < 3; j++){
                elementosQuadrante.add(matriz[i][j]);
            }
        }
        return elementosQuadrante;
    }
    public List<T> pegaQuadranteQuarto(){
        List<T> elementosQuadrante = new ArrayList<>();

        for(int i = 3; i < 5; i++){
            for(int j = 3; j < 5; j++){
                elementosQuadrante.add(matriz[i][j]);
            }
        }
        return elementosQuadrante;
    }
    public int getM() {
        return m;
    }
    public int getN() {
        return n;
    }

    

}
