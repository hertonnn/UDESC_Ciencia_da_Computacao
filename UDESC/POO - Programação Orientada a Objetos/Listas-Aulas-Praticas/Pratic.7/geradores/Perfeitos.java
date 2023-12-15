
public class Perfeitos extends Gerador {

    public void gerar(int n) {

        for (int i = 0; i < n; i++) {
            sequencia.add(perfeitos(i));
        }

    }

    public int perfeitos(int n) {

        return (int) ((Math.pow(2, (n - 1))) * (Math.pow(2, n) - 1));
    }

}