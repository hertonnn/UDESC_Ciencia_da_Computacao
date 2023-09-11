package negocio.estatistica;

import java.awt.BasicStroke;
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
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dados.Registro;

public class GraficoLinha extends ChartFactory{
    //receber dataset
    public CategoryDataset recebeDados(ArrayList<Registro> listaRegistros){
        DefaultCategoryDataset dados = new DefaultCategoryDataset();

        // iterando sobre a lista de registros
        for(Registro registro :  listaRegistros){
            dados.addValue(registro.getValor(), registro.getCategoria().getNome(), registro.getData().dataFormat2());
        }
        return dados;
    }
    //criar  gráfico de barras
    public JFreeChart createBar(CategoryDataset dados){

        int numBarras = dados.getRowCount();

        JFreeChart graficoBarra = ChartFactory.createLineChart(
            "Registros",
            "", 
            "Valor", 
            dados,
            PlotOrientation.VERTICAL,
            true,
            false,
            false);

            editaGrafico(graficoBarra);
            return graficoBarra;
    }
    //criar grafico completo
    public ChartPanel criaPainelGrafico(ArrayList<Registro> regristro){
        CategoryDataset dados = recebeDados(regristro);
        JFreeChart grafico = createBar(dados);

        ChartPanel painelGrafico = new ChartPanel(grafico);

        painelGrafico.setPreferredSize(new Dimension(800, 200));
        return painelGrafico;
    }
    public void editaGrafico(JFreeChart grafico){

       
    }
}
