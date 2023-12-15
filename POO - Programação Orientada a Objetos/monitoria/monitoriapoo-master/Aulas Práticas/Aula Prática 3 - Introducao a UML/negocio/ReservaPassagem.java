package negocio;

import java.util.LinkedList;

import dados.Cidade;
import dados.Cliente;
import dados.Reserva;

public class ReservaPassagem {

    private LinkedList<Cidade> listaDeCidades = new LinkedList<Cidade>();
    private LinkedList<Cliente> listaDeClientes = new LinkedList<Cliente>();

    private int numeroReservas = 0;

    public void cadastrarCidade(Cidade cidade) {
        this.listaDeCidades.add(cidade);
    }

    public void cadastrarCliente(Cliente cliente) {
        this.listaDeClientes.add(cliente);
    }

    public void reservarIda(Cliente cliente, Reserva reserva) {

        reserva.setNumReserva(this.numeroReservas);
        this.numeroReservas++;
        cliente.reservarIda(reserva);

    }

    public void reservarVolta(Cliente cliente, Reserva ida, Reserva volta) {

        ida.setNumReserva(this.numeroReservas);
        this.numeroReservas++;

        volta.setNumReserva(this.numeroReservas);
        this.numeroReservas++;

        cliente.reservarVolta(ida, volta);
    }

    public Cliente buscarCliente(int cpf) {
        for (Cliente c : this.listaDeClientes) {
            if (c.getCpf() == cpf) {
                return c;
            }
        }
        return null;
    }

    public LinkedList<Reserva> mostrarReservas(int cpfCliente) {

        Cliente c = buscarCliente(cpfCliente);

        if (c != null) {
            return c.getReservas();
        }
        return new LinkedList<Reserva>();
    }

    public LinkedList<Cliente> mostrarClientes() {
        return this.listaDeClientes;
    }

    public LinkedList<Cidade> mostrarCidades() {
        return this.listaDeCidades;
    }

}