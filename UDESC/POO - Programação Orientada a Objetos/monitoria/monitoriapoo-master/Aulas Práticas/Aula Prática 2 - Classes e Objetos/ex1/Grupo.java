public class Grupo {

    private Pessoa[] pessoas = new Pessoa[5];
    private int numeroPessoas = 0;

    public Grupo() {

    }

    public void ordena() {
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (this.pessoas[j].calculaImc() > this.pessoas[i].calculaImc()) {
                    Pessoa aux = this.pessoas[j];
                    this.pessoas[j] = this.pessoas[i];
                    this.pessoas[i] = aux;
                }
            }
        }
    }

    public void setPessoa(Pessoa p) {

        if (this.numeroPessoas < 5) {

            pessoas[this.numeroPessoas] = p;
            this.numeroPessoas++;

        }

    }

    public Pessoa[] getPessoas() {
        return this.pessoas;
    }
}