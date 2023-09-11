package negocio;

import dados.Cidade;
import dados.Cliente;
import dados.Reserva;

public class ReservaPassagem {
    private int TAMCIDADE = 100;
    private int TAMCLIENTE = 100;
    private int quantCidades = 0;
    private int quantClientes = 0;
    
    private Cidade[] cidades = new Cidade[TAMCIDADE];
    private Cliente[] clientes = new Cliente[TAMCLIENTE];

    public Cliente getCliente(String cpf){

        for(int i = 0; i < this.quantClientes; i++){
            if(validaCpf(cpf)){
                return this.clientes[i];
            }
        }
        return null;

    }
    public int getQuantClient(){
        return this.quantClientes;
    }
    public int getQuantCidade(){
        return this.quantCidades;
    }
    public void cadastrarCidade(Cidade cidade){
        this.cidades[this.quantCidades] = cidade;
        this.quantCidades++;
    }
    public void cadastrarCliente(Cliente cliente){
        this.clientes[this.quantClientes] = cliente;
        this.quantClientes++;
    }
    public void reservarIda(Cliente cliente, Reserva reserva){
        cliente.reservarIda(reserva);
    }
    public void reservarVolta(Cliente cliente, Reserva ida, Reserva volta){
        cliente.reservarVolta(ida, volta);
    }
    public Reserva[] mostrarReservas(String cpf){
        for(int i = 0; i < this.quantClientes; i++){
            if(validaCpf(cpf)){
                return this.clientes[i].getReservas();
            }
        }
// -------------------- problema aqui ----------------------
return null;
    } 
    public Cliente[] mostrarClientes(){
        return this.clientes;
    }
    public Cidade[] mostrarCidades(){
        return this.cidades;
    }
    // Funções extras 
    public boolean validaCpf(String cpf){
        for(int i = 0; i < this.quantClientes; i++){
            if(cpf.equals(this.clientes[i].getCpf().intern())){
                return true;
            }
        }
        return false;
    }
}
