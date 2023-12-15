
public class NumerosPrimos extends Gerador {

	public void gerar(int n) {
		int quant = 0;
		int num = 2;
		do {
			if(primo(num)) {
				sequencia.add(num);
				quant++;
			}
			num++;
		} while(quant < n);
	}
	
	public boolean primo(int v ) {
		for (int i = 2; i < v; i++) {
			if(v % i == 0) {
				return false;
			}
		}
		return true;
	}
}