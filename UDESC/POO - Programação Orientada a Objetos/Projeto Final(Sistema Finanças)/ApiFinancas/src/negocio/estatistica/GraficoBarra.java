package negocio.estatistica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dados.Registro;

public class GraficoBarra extends ChartFactory{
    //receber dataset
    public CategoryDataset recebeDados(ArrayList<Registro> listaRegistros){
        DefaultCategoryDataset dados = new DefaultCategoryDataset();

        // iterando sobre a lista de registros
        for(Registro registro :  listaRegistros){
            dados.addValue(registro.getValor(), registro.getValor(), registro.getCategoria().getNome());
        }
        return dados;
    }
    //criar  gráfico de barras
    public JFreeChart createBar(CategoryDataset dados){

        int numBarras = dados.getRowCount();

        JFreeChart graficoBarra = ChartFactory.createBarChart(
            "Registros",
            "", 
            "Valor", 
            dados,
            PlotOrientation.VERTICAL,
            true,
            false,
            false);

            editaGrafico(graficoBarra, numBarras);
            return graficoBarra;
    }
    //criar grafico completo
    public ChartPanel criaPainelGrafico(ArrayList<Registro> regristro){
        CategoryDataset dados = recebeDados(regristro);
        JFreeChart grafico = createBar(dados);

        ChartPanel painelGrafico = new ChartPanel(grafico);

        painelGrafico.setPreferredSize(new Dimension(400, 200));
        return painelGrafico;
    }
    public void editaGrafico(JFreeChart grafico, int numBarras){
        
        String azulClaro = "#38B6FF";
        String azulEscuro = "#31356E";
        String cinzaEscuro = "#373737";
        Color cor = new Color(0,0,0,0);
        
        // Formatação da área de plotagem
        CategoryPlot plot = grafico.getCategoryPlot();
        plot.setBackgroundPaint(cor);
        plot.setDomainGridlinePaint(Color.decode(cinzaEscuro));
        plot.setRangeGridlinePaint(Color.decode(cinzaEscuro));
        plot.setOutlineVisible(false);
        //Formatar as barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        // - remove o gradiente
        renderer.setBarPainter(new StandardBarPainter());
        // - cores das barras 

        for(int i = 0; i <= numBarras; i++){
            renderer.setSeriesPaint(i, Color.decode(azulClaro));
        }
        // - estilo barras(largura)
        renderer.setItemMargin(0.1);
        // - fundo
        
    }
}
