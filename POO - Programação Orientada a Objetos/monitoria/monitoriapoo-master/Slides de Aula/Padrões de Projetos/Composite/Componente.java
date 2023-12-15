public abstract class Componente<T> {

    private int nivel;
    private T objeto;

    public abstract String getDesenho();

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public T getObjeto() {
        return this.objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    protected String espaco() {
        StringBuilder espaco = new StringBuilder();
        for (int i = 0; i < getNivel(); i++) {
            espaco.append("\t");
        }
        return espaco.toString();
    }

}