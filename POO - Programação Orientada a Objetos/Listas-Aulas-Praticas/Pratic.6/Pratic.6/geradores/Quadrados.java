
public class Quadrados extends Gerador {

    public void gerar(int n) {

        for (int i = 0; i < n; i++) {
            sequencia.add(i * i);
        }
    }

}