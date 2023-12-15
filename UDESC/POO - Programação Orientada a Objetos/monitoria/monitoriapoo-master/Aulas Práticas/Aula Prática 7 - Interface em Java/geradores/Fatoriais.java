
public class Fatoriais extends Gerador {

    public void gerar(int n) {

        sequencia.add(1);

        for (int i = 1; i <= n; i++) {
            sequencia.add(sequencia.get(i - 1) * (i + 1));
        }

    }

}