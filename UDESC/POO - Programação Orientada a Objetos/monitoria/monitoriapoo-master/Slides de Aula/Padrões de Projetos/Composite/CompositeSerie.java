
public class CompositeSerie {

    private Componente raiz = new Galho<Serie>();

    public void adicionarSerie(Serie serie) {
        this.raiz.setObjeto(serie);
    }

    public void adicionarTemporada(Temporada temporada) {
        Galho<Serie> serie = (Galho<Serie>) (raiz);
        serie.adiciona(new Galho<Temporada>(temporada));
    }

    public void adicionarEpisodio(Episodio episodio, Temporada temporada) {
        Galho<Serie> serie = (Galho<Serie>) (raiz);
        for (Componente componente : serie.getComponentes()) {
            if (componente.getObjeto().equals(temporada)) {
                Galho<Temporada> aux = (Galho<Temporada>) componente;
                aux.adiciona(new Folha<Episodio>(episodio));
            }
        }
    }

    public String toString() {
        return raiz.getDesenho();
    }

}