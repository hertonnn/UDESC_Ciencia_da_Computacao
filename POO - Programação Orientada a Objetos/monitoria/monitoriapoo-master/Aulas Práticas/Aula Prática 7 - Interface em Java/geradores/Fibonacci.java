
public class Fibonacci extends Gerador {

    public void gerar(int n) {

        sequencia.add(0);
        sequencia.add(1);

        for (int i = 0; i < n - 2; i++) {

            sequencia.add(sequencia.get(sequencia.size() - 1) + sequencia.get(sequencia.size() - 2));

        }

    }

}