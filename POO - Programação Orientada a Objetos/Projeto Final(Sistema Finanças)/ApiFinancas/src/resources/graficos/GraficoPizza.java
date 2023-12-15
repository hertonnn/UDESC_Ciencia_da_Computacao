package resources.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import dados.Categoria;
import dados.Registro;
import dados.Tipo_Registro;

public class GraficoPizza extends ChartFactory{

    static int total;
    ArrayList<Categoria> categorias;
    ArrayList<Registro> registros;

    //receber dataset
    public DefaultPieDataset recebeDados(){
        DefaultPieDataset dados = new DefaultPieDataset();
        double gastoCategoria = 0;
        for(Categoria categoria : categorias){
            for(Registro registro :  registros){
                if(registro.getTipo() == Tipo_Registro.DESPESA && registro.getCategoria().getNome().equals(categoria.getNome())){
                    gastoCategoria += registro.getValor();
                    dados.setValue(categoria.getNome(), gastoCategoria);
                    total += registro.getValor();
                }
            }
            gastoCategoria = 0;
        }
        return dados;
    }
    //criar  gráfico de barras
    public JFreeChart createBar(DefaultPieDataset dados){

        int numFatias = dados.getItemCount();

        JFreeChart graficoPizza = ChartFactory.createPieChart("", dados, false, false, false);

            editaGrafico(graficoPizza, numFatias);
            return graficoPizza;
    }
    //criar grafico completo
    public ChartPanel createPanel(ArrayList<Registro> registros, ArrayList<Categoria> categorias){
        total = 0;
        this.categorias = categorias;
        this.registros = registros;
        DefaultPieDataset dados = recebeDados();
        JFreeChart grafico = createBar(dados);

        ChartPanel painelGrafico = new ChartPanel(grafico);

        return painelGrafico;
    }
    public void editaGrafico(JFreeChart grafico, int numFatias){
        grafico.setBackgroundPaint(null);

        Font quickSand = null;
        try{
        quickSand = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fontes/quicksand/Quicksand_Light_Oblique.otf"));
        }
        catch(IOException|FontFormatException e){
            System.out.println(e);
        }     

        quickSand = quickSand.deriveFont(Font.BOLD, 12);
        
        PiePlot plot = (PiePlot) grafico.getPlot();
        plot.setBackgroundPaint(null);
        plot.setLabelGenerator(new CustomLabelGenerator());
        plot.setBaseSectionOutlinePaint(Color.WHITE);
        plot.setShadowXOffset(0.0);
        plot.setShadowYOffset(0.0);
        plot.setOutlineVisible(false);
        plot.setLabelLinkPaint(Color.BLUE);
        plot.setLabelBackgroundPaint(Color.WHITE);

        plot.setLabelFont(quickSand);

        String[] tonsAzul = {"#cdffff","#94e7ff","#40cfff", "#2499c7","#006691"};
        int i = 0;
        for(Categoria categoria : categorias){
            plot.setSectionPaint(categoria.getNome(), Color.decode(tonsAzul[i]));
            plot.setSectionOutlinePaint(categoria.getNome(), Color.decode(tonsAzul[i]));
            i++;
            if(i >= tonsAzul.length){
                i = 0;
            }
        }
    }

    // Classe para gerar rótulos personalizados
    private static class CustomLabelGenerator implements PieSectionLabelGenerator {
        @Override
        public String generateSectionLabel(PieDataset dataset, Comparable key) {
            if (dataset != null) {
                Number value = dataset.getValue(key);
                if (value != null) {
                    double percent = (value.doubleValue() / total ) * 100.0;
                    DecimalFormat df = new DecimalFormat("0.0"); // Formato personalizado
                    return key.toString() + " " + df.format(percent) + "%";
                }
            }
            return null;
        }

        @Override
        public AttributedString generateAttributedSectionLabel(PieDataset dataset, Comparable key) {
            return null;
        }

}
}
