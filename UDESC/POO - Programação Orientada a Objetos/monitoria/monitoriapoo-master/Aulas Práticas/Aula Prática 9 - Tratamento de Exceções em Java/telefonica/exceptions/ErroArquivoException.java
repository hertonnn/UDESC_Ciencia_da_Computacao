package exceptions;

public class ErroArquivoException extends Exception {

    private String caminho;

    public ErroArquivoException(String mensagem) {
        super(mensagem);
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

}