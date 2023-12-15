package exceptions;

public class ErroNaLeituraException extends ErroArquivoException {

    public ErroNaLeituraException() {
        super("Erro durante a leitura do arquivo");
    }

}