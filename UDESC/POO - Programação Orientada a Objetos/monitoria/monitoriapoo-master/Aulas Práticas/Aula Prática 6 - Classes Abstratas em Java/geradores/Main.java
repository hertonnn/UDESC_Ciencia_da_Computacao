import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Gerador gerador;
		List<Integer> resultado;

		// Fibonacci
		gerador = new Fibonacci();
		gerador.gerar(random(50));
		resultado = gerador.getSequencia();
		imprimir("Fibonacci", resultado);

		// Naturais
		gerador = new Naturais();
		gerador.gerar(random(50));
		resultado = gerador.getSequencia();
		imprimir("Naturais", resultado);

		// Quadrados
		gerador = new Quadrados();
		gerador.gerar(random(50));
		resultado = gerador.getSequencia();
		imprimir("Quadrados", resultado);

		// Primos
		gerador = new NumerosPrimos();
		gerador.gerar(random(50));
		resultado = gerador.getSequencia();
		imprimir("Primos", resultado);

		// Fatorial
		gerador = new Fatoriais();
		gerador.gerar(random(50));
		resultado = gerador.getSequencia();
		imprimir("Fatorial", resultado);

		// Perfeitos
		gerador = new Perfeitos();
		gerador.gerar(random(7));
		resultado = gerador.getSequencia();
		imprimir("Perfeitos", resultado);

		// Abundantes
		gerador = new Abundantes();
		gerador.gerar(random(50));
		resultado = gerador.getSequencia();
		imprimir("Abundantes", resultado);

	}

	public static void imprimir(String tipo, List<Integer> seq) {
		if (seq.size() < 1) {
			System.out.print("\n" + tipo + " de " + seq.size() + ": [" + seq.get(seq.size() - 1) + "]");
		} else {
			System.out.print("\n" + tipo + " de " + seq.size() + ": [");
			for (int i = 0; i < seq.size() - 1; i++) {
				System.out.print(seq.get(i) + ", ");
			}
			System.out.print(seq.get(seq.size() - 1) + "]");
		}
	}

	public static int random(int n) {
		Random r = new Random();
		int result = r.nextInt(n);
		while (result == 0) {
			result = r.nextInt(n);
		}
		return result;
	}

}