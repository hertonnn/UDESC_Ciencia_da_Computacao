import java.util.Random;

public class Fabrica {

    public Veiculo fabricar() {

        Random r = new Random();

        if (r.nextInt(2) == 1) {

            Carro c = new Carro();

            c.setCor(Cor.values()[r.nextInt(Cor.values().length)]);
            c.setNumeroPortas(2 + 2 * r.nextInt(2));
            c.setCombustivel(Combustivel.values()[r.nextInt(Combustivel.values().length)]);

            return c;

        } else {

            Bicicleta b = new Bicicleta();
            b.setCor(Cor.values()[r.nextInt(Cor.values().length)]);
            b.setNumeroMarchas(r.nextInt(28));

            return b;

        }

    }

}