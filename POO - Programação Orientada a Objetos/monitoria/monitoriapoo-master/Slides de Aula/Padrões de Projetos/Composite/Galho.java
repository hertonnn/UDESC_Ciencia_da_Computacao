import java.util.LinkedList;
import java.util.List;

public class Galho<T> extends Componente<T> {

    private List<Componente> componentes = new LinkedList<Componente>();

    public Galho() {

    }

    public Galho(T objeto) {
        setObjeto(objeto);
    }

    @Override
    public String getDesenho() {

        StringBuilder desenho = new StringBuilder();
        desenho.append(espaco());
        desenho.append(getObjeto().toString() + "\n");

        for (Componente componente : componentes) {
            desenho.append(componente.getDesenho());
        }

        return desenho.toString();
    }

    public List<Componente> getComponentes() {
        return this.componentes;
    }

    public void adiciona(Componente componente) {
        componente.setNivel(getNivel() + 1);
        this.componentes.add(componente);
    }

}