import java.util.List;
import java.util.Random;
import java.util.LinkedList;

public abstract class Gerador implements Sequencia {

    protected List<Integer> sequencia = new LinkedList<Integer>();

    public abstract void gerar(int n);

    public List<Integer> getSequencia() {
        return this.sequencia;
    }

    public int sortear() {
        final Random r = new Random();
        return sequencia.get(r.nextInt(sequencia.size()));
    }

    public long somatorio() {
        long soma = 0;
        for (final int x : sequencia) {
            soma += x;
        }
        return soma;
    }

    public long produtorio() {
        long produto = 1;
        for (final int x : sequencia) {
            produto = produto * x;

        }
        return produto;
    }

    public double mediaGeometrica() {
        return Math.pow(produtorio(), 1.0 / (double) (sequencia.size()));
    }

    public double mediaAritmetica() {
        return somatorio() / (double) (sequencia.size());
    }

    public double variancia() {

        final double media = mediaAritmetica();
        double soma = 0;

        for (final int x : sequencia) {
            soma += Math.pow(x - media, 2);
        }

        return soma / ((double) (sequencia.size()) - 1);
    }

    public double desvioPadrao() {
        return Math.sqrt(variancia());
    }

    public int amplitude() {

        int maior = sequencia.get(0);
        int menor = sequencia.get(0);

        for (final int x : sequencia) {
            if (x > maior) {
                maior = x;
            }
            if (x < menor) {
                menor = x;
            }
        }

        return maior - menor;
    }

}