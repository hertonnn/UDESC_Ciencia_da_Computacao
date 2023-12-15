package dados;

public class CalculadoraInteiros implements IOperacoesBasicas<Integer> {

    @Override
    public Integer soma(Integer operador1, Integer operador2) {
        return operador1 + operador2;
    }

    @Override
    public Integer subtracao(Integer operador1, Integer operador2) {
        return operador1 - operador2;
    }

}