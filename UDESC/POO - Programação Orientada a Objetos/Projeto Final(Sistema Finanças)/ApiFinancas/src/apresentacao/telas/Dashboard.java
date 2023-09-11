package apresentacao.telas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import negocio.estatistica.GraficoBarra;
import negocio.estatistica.GraficoLinha;
import negocio.estatistica.GraficoPizza;
import dados.Registro;
import dados.Usuario;

public class Dashboard extends JFrame {
    
    private Panel painel;

    
    public Dashboard(Usuario Usuario){
        
        //Pegar tamanho da tela
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        setSize(d);
        setTitle("Dashboard Pessoal");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fechar janela 
        setLocationRelativeTo(null);
        setResizable(false);
        
        configPainel();
        criaGraficos(Usuario.getRegistros());
        add(painel);
        setVisible(true);
    }
    public void configPainel(){
        //Criando painel
        
        String caminho = "./src/resources/BackDashboard.jpg";
        
        painel = new Panel(caminho);
        painel.setPreferredSize(getSize());
        //painel.setLayout(null);
    }
    public void criaGraficos(ArrayList<Registro> listaRegistros){
        
        GraficoBarra graficoBarra = new GraficoBarra();
        GraficoLinha graficoLinhas = new GraficoLinha();
        GraficoPizza graficoPizza = new GraficoPizza();

        painel.add(graficoBarra.criaPainelGrafico(listaRegistros));
        painel.add(graficoPizza.criaPainelGrafico(listaRegistros));
        painel.add(graficoLinhas.criaPainelGrafico(listaRegistros));    

    }
}
