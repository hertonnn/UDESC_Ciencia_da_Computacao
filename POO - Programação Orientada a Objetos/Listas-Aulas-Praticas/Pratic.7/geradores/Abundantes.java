
public class Abundantes extends Gerador {

    public void gerar(int n) {

        for (int i = 1, cont = 0; cont != n; i++) {
            if (abundante(i)) {
                sequencia.add(i);
                cont++;
            }
        }

    }

    public boolean abundante(int n) {

        int soma = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                soma += i;
            }
        }

        if (n > soma) {
            return false;
        } else {
            return true;
        }
    }

}