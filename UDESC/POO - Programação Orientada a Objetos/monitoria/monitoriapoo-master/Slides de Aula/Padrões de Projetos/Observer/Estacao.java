import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Estacao implements Sujeito {

    private List<Observador> observadores = new LinkedList<Observador>();

    private double temperatura;

    private Random r = new Random();

    public Estacao() {

        temperatura = r.nextInt(30);

    }

    public void adicionar(Observador observador) {
        observadores.add(observador);
    }

    public void remover(Observador observador) {
        observadores.remove(observador);
    }

    public void notificar() {
        for (Observador o : observadores) {
            o.atualizar(this.temperatura);
        }
    }

    public void medir() {
        temperatura += 2.5 * Math.sin(r.nextInt(100));
        notificar();
    }

}