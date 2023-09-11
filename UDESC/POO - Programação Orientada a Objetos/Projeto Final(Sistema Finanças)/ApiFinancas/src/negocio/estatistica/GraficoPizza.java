package negocio.estatistica;

import java.awt.Color;
import java.awt.Dimension;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import dados.Registro;

public class GraficoPizza extends ChartFactory{
    //receber dataset
    public DefaultPieDataset recebeDados(ArrayList<Registro> listaRegistros){
        DefaultPieDataset dados = new DefaultPieDataset();

        // iterando sobre a lista de registros
        for(Registro registro :  listaRegistros){
            dados.setValue(registro.getCategoria().getNome(), registro.getValor());
        }
        return dados;
    }
    //criar  gráfico de barras
    public JFreeChart createBar(DefaultPieDataset dados){

        //int numFatias = dados.getItemCount();

        JFreeChart graficoPizza = ChartFactory.createPieChart("Registros", dados, false, false, false);

            //editaGrafico(graficoPizza, numFatias);
            return graficoPizza;
    }
    //criar grafico completo
    public ChartPanel criaPainelGrafico(ArrayList<Registro> regristro){
        DefaultPieDataset dados = recebeDados(regristro);
        JFreeChart grafico = createBar(dados);

        ChartPanel painelGrafico = new ChartPanel(grafico);

        painelGrafico.setPreferredSize(new Dimension(400, 200));
        return painelGrafico;
    }

}
