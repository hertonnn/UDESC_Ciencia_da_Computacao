package recursos.graficos;

import java.awt.Color;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dados.Registro;

public class GraficoBarra extends ChartFactory{
    Locale localBrasil = new Locale("pt", "BR");

    ArrayList<Registro> listaRegistros;
    //receber dataset
    public CategoryDataset recebeDados(ArrayList<Registro> listaRegistros){
        this.listaRegistros = listaRegistros;
        DefaultCategoryDataset dados = new DefaultCategoryDataset();

        // iterando sobre a lista de registros
        for(Registro registro :  listaRegistros){
            String data_formatada = registro.getData().format(DateTimeFormatter.ofPattern("dd/MM"));
            dados.addValue(registro.getValor(), registro.getTipo().getTipo(), data_formatada);
        }
        return dados;
    }
    //criar  gráfico de barras
    public JFreeChart createBar(CategoryDataset dados){

        int numBarras = dados.getRowCount();

        JFreeChart graficoBarra = ChartFactory.createBarChart(
            "",
            "", 
            "", 
            dados,
            PlotOrientation.VERTICAL,
            true,
            false,
            false);

            editaGrafico(graficoBarra, numBarras);
            return graficoBarra;
    }
    //criar grafico completo
    public ChartPanel createPanel(ArrayList<Registro> registros){
        CategoryDataset dados = recebeDados(registros);
        JFreeChart grafico = createBar(dados);

        ChartPanel painelGrafico = new ChartPanel(grafico);

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
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setNumberFormatOverride(NumberFormat.getCurrencyInstance(localBrasil));
        //Formatar as barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        // - remove o gradiente
        renderer.setBarPainter(new StandardBarPainter());
        
        // - cores das barras 
        renderer.setSeriesPaint(0, Color.decode(azulEscuro));
        renderer.setSeriesPaint(1, Color.decode(azulClaro));
        
    }
}
