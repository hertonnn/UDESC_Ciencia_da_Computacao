
public class Perfeitos extends Gerador {

    public void gerar(int n) {

        for (int i = 1; i < n; i++) {
            if (perfeitos(i)) {
                sequencia.add(i);
            }
        }

    }

    public boolean perfeitos(int n) {

        int soma = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                soma += i;
            }
        }

        if (n != soma) {
            return false;
        } else {
            return true;
        }
    }

}