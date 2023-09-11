package negocio;

import java.util.ArrayList;

import dados.*;
import dados.Registro.TipoRegistro;
import negocio.estatistica.GraficoBarra;
import negocio.estatistica.GraficoLinha;
import negocio.estatistica.GraficoPizza;

public class Sistema {

    private ArrayList<Usuario> listaUsuarios;
    GraficoBarra graficoBarra;
    GraficoLinha graficoLinha;
    GraficoPizza graficoPizza;

    public Sistema(){
        this.listaUsuarios = new ArrayList<>();
    }
    public boolean realizaCadastro(Usuario usuario){
        if(verificaNome(usuario.getNome())){
            listaUsuarios.add(usuario);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean realizaLogin(String nome, String senha){
        for(Usuario usuario : listaUsuarios){
            if(usuario.getNome() == nome && usuario.getSenha() == senha){
                return true;
            }
        }
        return false;
    }
    public boolean realizaRegistro(Registro registro, Usuario usuario, Conta conta){
        if(registro.getTipo() == TipoRegistro.RECEITA){
            conta.deposita(registro.getValor());
        }
        if(registro.getTipo() == TipoRegistro.DESPESA){
            if(conta.saca(registro.getValor())){}
            else{
                return false;
            }
        }
        // adicionando "históricos"
        usuario.adicionaRegistro(registro);
        conta.adicionaRegistro(registro);
        return true;
    }
    public void realizaOrcamento(Orcamento orcamento, Usuario usuario){
        usuario.adicionaOrcamento(orcamento);
    }
    public void criaConta(Conta conta, Usuario usuario){
        usuario.setConta(conta);
    }
    public ArrayList<Usuario> getListaUsuarios(){
        return this.listaUsuarios;
    }
    // Parte Estatistica dos registros
    public void criaGraficoBarra(ArrayList<Registro> registros){
        graficoBarra = new GraficoBarra();
        graficoBarra.criaPainelGrafico(registros);
    }
    public void criaGraficoLinha(ArrayList<Registro> registros){
        graficoLinha = new GraficoLinha();
        graficoLinha.criaPainelGrafico(registros);
    }
    public void criaGraficoPizza(ArrayList<Registro> registros){
        graficoPizza = new GraficoPizza();
        graficoPizza.criaPainelGrafico(registros);
    }
    // 
    // Auxiliares 
    public boolean verificaNome(String nome){
        for(Usuario usuario : listaUsuarios){
            if(usuario.getNome() == nome){
                return false;
            }
        }
        return true;
    }
}
