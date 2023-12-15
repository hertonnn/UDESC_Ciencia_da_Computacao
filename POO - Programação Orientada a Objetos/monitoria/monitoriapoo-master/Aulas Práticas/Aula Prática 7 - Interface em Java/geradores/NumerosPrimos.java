
public class NumerosPrimos extends Gerador {

    public void gerar(int n) {

        for (int i = 2, cont = 0; cont != n; i++) {
            if (primo(i)) {
                sequencia.add(i);
                cont++;
            }
        }

    }

    public boolean primo(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}