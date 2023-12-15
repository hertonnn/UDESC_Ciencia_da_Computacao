package negocio;
import java.util.ArrayList;

import dados.*;
import dados.Tipo_Registro;
import persistencia.CategoriaDAO;
import persistencia.ContaDAO;
import persistencia.OrcamentoDAO;
import persistencia.RegistroDAO;
import persistencia.UsuarioDAO;

public class Sistema {

    UsuarioDAO conexaoUsuario;
    private static Sistema uniqueInstance;
    private Usuario usuarioLogado; //Usuario logado no momento
    private ArrayList<Orcamento> orcamentosFiltrados;
    private ArrayList<Registro> registrosFiltrados;
    ContaDAO conexaoConta;
    CategoriaDAO conexaoCategoria;
    RegistroDAO conexaoRegistro;
    OrcamentoDAO conexaoOrcamento;

    private Sistema(){
        this.conexaoUsuario = UsuarioDAO.getInstancia();
        this.conexaoConta = ContaDAO.getInstancia();
        this.conexaoCategoria = CategoriaDAO.getInstancia();
        this.conexaoRegistro = RegistroDAO.getInstancia();
        this.conexaoOrcamento = OrcamentoDAO.getInstancia();
    }
    
    public static synchronized Sistema getInstance(){ // Uma única instância de Sistema
        if(uniqueInstance == null){
            uniqueInstance = new Sistema();
        }
        return uniqueInstance;
    }
    // --------------- Usuario -------------------
    public boolean realizaCadastro(String nome, String senha){
        Usuario usuario = new Usuario(nome, senha);
        // A classe conexaoUsuario já indexa o novo usuario automaticamento
        if(conexaoUsuario.inserir(usuario)){
            return true;
        }
        return false;
    }
    public boolean realizaLogin(String nome, String senha){

        Usuario usuario = conexaoUsuario.validar(nome, senha);
        if(usuario != null){
            this.usuarioLogado = usuario;
            carregaDados(usuario);
            return true;
        }
        return false;
    }
    public boolean atualizaUsuario(Usuario usuario){
        if(conexaoUsuario.alterar(usuario)){
            return true;
        }
        return false;
    }
    public boolean removeUsuario(Usuario usuario){
        if(conexaoUsuario.remover(usuario.getId())){
            return true;
        }
        return false;
    }
    public Usuario getUsuarioLogado() {
        return this.usuarioLogado;
    }
    
    // carregar os dados da conta do usuario, assim como registros,orcamentos e categorias.
    public boolean carregaDados(Usuario usuario){


        Conta contaUsuario = conexaoConta.getConta(usuario);
        usuario.setConta(contaUsuario);
        contaUsuario.setCategorias(conexaoCategoria.listar(usuario.getId()));
        contaUsuario.setHistorico(conexaoRegistro.listar(contaUsuario));
        contaUsuario.setOrcamentos(conexaoOrcamento.listar(usuario));

        return true;
    }
    // --------------- Registro -------------------
    public boolean realizaRegistro(Registro registro, Usuario usuario, Conta conta){

    // isso resolve o problema, mas pode ser otimizado.
        if(registro.getTipo() == Tipo_Registro.RECEITA){
            if(conexaoRegistro.inserir(registro)){
                conta.deposita(registro.getValor());
                conta.addHistorico(registro);
                conexaoConta.alterar(conta);
                return true;
            }
        }
        if(registro.getTipo() == Tipo_Registro.DESPESA && registro.getValor() < conta.getSaldo()){
            if(conexaoRegistro.inserir(registro)){
                conta.saca(registro.getValor());
                conta.addHistorico(registro);
                conexaoConta.alterar(conta);
                return true;
            }
        }
        return false;
    }
    public ArrayList<Registro> filtraRegistrosMes(int mes, Conta conta){

        registrosFiltrados = new ArrayList<>();
        
        if(mes == 0){
            return conta.getHistorico();
        }
        else{
            for(Registro registro : conta.getHistorico()){
                if(registro.getData().getMonthValue() == mes){
                    registrosFiltrados.add(registro); 
                }
            }
            return registrosFiltrados;
            }
    }
    public ArrayList<Registro> filtraRegistrosCategoria(String categoria, Conta conta){

        registrosFiltrados = new ArrayList<>();
        
        if(categoria.equals("Todas")){
            return conta.getHistorico();
        }
        else{
            for(Registro registro : conta.getHistorico()){
                if(registro.getCategoria().getNome().equals(categoria)){
                    registrosFiltrados.add(registro); 
                }
            }
            return registrosFiltrados;
            }
    }
    // Filtra pelos dois (mudar essa lógica)
    public ArrayList<Registro> filtraRegistros(int mes, String categoria, Conta conta){

        registrosFiltrados = new ArrayList<>();
        
        if(mes == 0 && categoria.equals("Todas")){
            return conta.getHistorico();
        }
        else{

            if(mes == 0){
                for(Registro registro : conta.getHistorico()){
                    if(registro.getCategoria().getNome().equals(categoria)){
                        registrosFiltrados.add(registro); 
                    }
                }
                return registrosFiltrados;
            }
            if(categoria.equals("Todas")){
                for(Registro registro : conta.getHistorico()){
                    if(registro.getData().getMonthValue() == mes){
                        registrosFiltrados.add(registro); 
                    }
                }
                return registrosFiltrados;
            }
            else{
                for(Registro registro : conta.getHistorico()){
                    if(registro.getData().getMonthValue() == mes && registro.getCategoria().getNome().equals(categoria)){
                        registrosFiltrados.add(registro); 
                    }
                }
            }
        }

        return registrosFiltrados;
    }
    // --------------- Orçamento -------------------
    // Filtra pelos dois (mudar essa lógica)
    public boolean realizaOrcamento(Orcamento orcamento, Conta conta){
        if(conexaoOrcamento.inserir(orcamento)){
            conta.addOrcamento(orcamento);
            return true;
        }
        return false;
    }
    // Categoria
    public boolean adicionaCategoria(Categoria categoria, Usuario usuario){
        if(conexaoCategoria.inserir(categoria, usuario.getId())){
            return true;
        }
        return false;
    }
    public ArrayList<Orcamento> filtraOrcamentoCategoria(String categoria, Conta conta){

        orcamentosFiltrados = new ArrayList<>();
        
        if(categoria.equals("Todas")){
            return conta.getOrcamentos();
        }
        else{
            for(Orcamento orcamento : conta.getOrcamentos()){
                if(orcamento.getCategoria().getNome().equals(categoria)){
                    orcamentosFiltrados.add(orcamento); 
                }
            }
            return orcamentosFiltrados;
            }
    }
    public ArrayList<Orcamento> filtraOrcamentos(int mes, String categoria, Conta conta){
        orcamentosFiltrados = new ArrayList<>();
        if(mes == 0 && categoria.equals("Todas")){
            return conta.getOrcamentos();
        }
        else{

            if(mes == 0){
                for(Orcamento orcamento : conta.getOrcamentos()){
                    if(orcamento.getCategoria().getNome().equals(categoria)){
                        orcamentosFiltrados.add(orcamento); 
                    }
                }
                return orcamentosFiltrados;
            }
            if(categoria.equals("Todas")){
                for(Orcamento orcamento : conta.getOrcamentos()){
                    if(orcamento.getData_criacao().getMonthValue() == mes){
                        orcamentosFiltrados.add(orcamento); 
                    }
                }
                return orcamentosFiltrados;
            }
            else{
                for(Orcamento orcamento : conta.getOrcamentos()){
                    if(orcamento.getData_criacao().getMonthValue() == mes && orcamento.getCategoria().getNome().equals(categoria)){
                        orcamentosFiltrados.add(orcamento); 
                    }
                }
            }
        }

        return orcamentosFiltrados;
    }
    // --------------- Conta -------------------
    public void criaConta(Conta conta, Usuario usuario){
        usuario.setConta(conta);
    }
}
