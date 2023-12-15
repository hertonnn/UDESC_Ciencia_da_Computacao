
public class Main {

    public static void main(String[] args) {

        Estacao estacao = new Estacao();

        Celsius celsius = new Celsius();
        Fahrenheit fahrenheit = new Fahrenheit();
        Kelvin kelvin = new Kelvin();

        estacao.adicionar(celsius);
        estacao.adicionar(fahrenheit);
        estacao.adicionar(kelvin);

        for (int i = 0; i < 10; i++) {

            estacao.medir();

            System.out.println("Temperatura em graus Celsius: " + celsius);
            System.out.println("Temperatura em graus Fahrenheit: " + fahrenheit);
            System.out.println("Temperatura em Kelvin: " + kelvin);
            System.out.println();

        }

    }

}