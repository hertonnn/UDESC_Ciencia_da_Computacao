
public class Main {

    public static void main(String[] args) {

        VeiculoFactory factory = new VeiculoFactory();

        Veiculo v1 = factory.createVeiculo(TipoVeiculo.CARRO);
        Veiculo v2 = factory.createVeiculo(TipoVeiculo.BARCO);
        Veiculo v3 = factory.createVeiculo(TipoVeiculo.MOTO);
        Veiculo v4 = factory.createVeiculo(TipoVeiculo.AVIAO);

    }

}