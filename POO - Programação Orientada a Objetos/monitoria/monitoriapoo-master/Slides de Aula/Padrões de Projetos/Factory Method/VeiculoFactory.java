public class VeiculoFactory {

    public Veiculo createVeiculo(TipoVeiculo tipo) {

        switch (tipo) {

            case AVIAO:
                return new Aviao();
            case BARCO:
                return new Barco();
            case CARRO:
                return new Carro();
            case MOTO:
                return new Moto();
            default:
                throw new IllegalArgumentException("Tipo de veículo inexistente");

        }
    }

}