package recursos.graficos;
import java.awt.BasicStroke;
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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dados.Registro;
import dados.Tipo_Registro;

public class GraficoLinha2 extends ChartFactory{
    String azulClaro = "#0CC0DF";
    Locale localBrasil = new Locale("pt", "BR");
    Double saldoConta;
    //receber dataset
    public CategoryDataset recebeDados(ArrayList<Registro> listaRegistros){
        DefaultCategoryDataset dados = new DefaultCategoryDataset();
        Double retorno;
        // iterando sobre a lista de registros
        for(Registro registro :  listaRegistros){
            String data_formatada = registro.getData().format(DateTimeFormatter.ofPattern("dd/MM"));
            if(registro.getTipo().compareTo(Tipo_Registro.DESPESA) == 0){
                retorno = saldoConta + registro.getValor();
            }
            else{
                retorno = saldoConta - registro.getValor();
            }
            dados.addValue(retorno, "", data_formatada);
        }
        return dados;
    }
    //criar  gráfico de linha
    public JFreeChart createBar(CategoryDataset dados){

        JFreeChart graficoLinha = ChartFactory.createLineChart(
            "",
            "", 
            "", 
            dados,
            PlotOrientation.VERTICAL,
            true,
            false,
            false);

            editaGrafico(graficoLinha);
            return graficoLinha;
    }
    //criar grafico completo
    public ChartPanel createPanel(ArrayList<Registro> registro, Double saldoAtual){
        this.saldoConta = saldoAtual;
        CategoryDataset dados = recebeDados(registro);
        JFreeChart grafico = createBar(dados);
        grafico.setBackgroundPaint(Color.decode(azulClaro));

        ChartPanel painelGrafico = new ChartPanel(grafico);
        return painelGrafico;
    }
    public void editaGrafico(JFreeChart grafico){

       // Formatação da área de plotagem
        CategoryPlot plot = grafico.getCategoryPlot();
        plot.setBackgroundPaint(Color.decode(azulClaro));
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.getRenderer().setSeriesPaint(0, Color.WHITE); 
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(3.0f));
        plot.getRenderer().setBaseSeriesVisibleInLegend(false);
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setNumberFormatOverride(NumberFormat.getCurrencyInstance(localBrasil));

    }
}
