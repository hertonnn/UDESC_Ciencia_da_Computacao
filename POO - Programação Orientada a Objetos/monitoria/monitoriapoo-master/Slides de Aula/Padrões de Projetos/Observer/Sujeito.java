
public interface Sujeito {

    public void adicionar(Observador observador);

    public void remover(Observador observador);

    public void notificar();

}