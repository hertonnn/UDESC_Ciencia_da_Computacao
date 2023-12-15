
public class Fatoriais extends Gerador {

	public void gerar(int n) {
		int acumulador = 1;
		
		for(int i=2; i <= n; i++) {
			acumulador = 1;
			
			for (int j =2 ; j <= i; j++) {
				
				acumulador *= j;
			}
			
			sequencia.add(acumulador);
			
		}
	}

}