package negocio;

import dados.Veterinario;

public class SistemaPetShop {
	
	private Veterinario veterinarios[]; 
	private int quantVeterinarios;
	
	public SistemaPetShop(int tamVeterinario) {
		veterinarios = new Veterinario[tamVeterinario];
	}
	
	public void cadastrarVeterinario(Veterinario vet) {
		if (quantVeterinarios < veterinarios.length) {
			veterinarios[quantVeterinarios] = vet;
			quantVeterinarios++;
		}
	}

	public Veterinario[] getVeterinarios() {
		return veterinarios;
	}

	public int getQuantVeterinarios() {
		return quantVeterinarios;
	}
	
	

}
