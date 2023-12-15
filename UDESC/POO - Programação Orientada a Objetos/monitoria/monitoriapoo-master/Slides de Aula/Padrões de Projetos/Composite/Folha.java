
public class Folha<T> extends Componente<T> {

    public Folha(T objeto) {
        setObjeto(objeto);
    }

    @Override
    public String getDesenho() {
        return espaco() + getObjeto().toString() + "\n";
    }

}