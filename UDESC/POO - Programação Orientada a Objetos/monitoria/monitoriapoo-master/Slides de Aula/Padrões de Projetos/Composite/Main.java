
public class Main {

    public static void main(String[] args) {

        CompositeSerie serie = new CompositeSerie();

        Serie s = new Serie("Friends", 1994);

        Temporada t1 = new Temporada(1);
        Temporada t2 = new Temporada(2);
        Temporada t3 = new Temporada(3);

        Episodio e1 = new Episodio("The Pilot", 1);
        Episodio e2 = new Episodio("The One with the Sonogram at the End", 2);
        Episodio e3 = new Episodio("The One with George Stephanopoulos", 3);

        Episodio e4 = new Episodio("The One with Ross's New Girlfriend", 1);
        Episodio e5 = new Episodio("The One with the Breast Milk", 2);
        Episodio e6 = new Episodio("The One Where Heckles Dies", 3);

        Episodio e7 = new Episodio("The One with the Princess Leia Fantasy", 1);
        Episodio e8 = new Episodio("The One Where No One's Ready", 2);
        Episodio e9 = new Episodio("The One with the Jam", 3);
        Episodio e10 = new Episodio("The One at the Beach", 25);

        serie.adicionarSerie(s);

        serie.adicionarTemporada(t1);
        serie.adicionarEpisodio(e1, t1);
        serie.adicionarEpisodio(e2, t1);
        serie.adicionarEpisodio(e3, t1);

        serie.adicionarTemporada(t2);
        serie.adicionarEpisodio(e4, t2);
        serie.adicionarEpisodio(e5, t2);
        serie.adicionarEpisodio(e6, t2);

        serie.adicionarTemporada(t3);
        serie.adicionarEpisodio(e7, t3);
        serie.adicionarEpisodio(e8, t3);
        serie.adicionarEpisodio(e9, t3);
        serie.adicionarEpisodio(e10, t3);

        System.out.println(serie);

    }

}