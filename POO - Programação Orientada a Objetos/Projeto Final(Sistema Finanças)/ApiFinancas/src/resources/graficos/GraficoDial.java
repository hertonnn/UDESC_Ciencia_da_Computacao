package resources.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;

import dados.Orcamento;
import dados.Registro;
import dados.Tipo_Registro;

public class GraficoDial {

    private DefaultValueDataset datasetGasto;
    private DefaultValueDataset datasetOrcamento;
    String[] tonsAzul = {"#6CE5E8","#41B8D5","#2D8BBA","#2F5F98","#31356E"};
    private double gasto;
    private double orcamentoTotal;
    private boolean limiteAtingiu;

    private JFreeChart createChart(ValueDataset valorGasto, ValueDataset valorOrcamento) {
        int orcamento = valorOrcamento.getValue().intValue();
        MeterPlot meterplot = new MeterPlot(valorGasto);
        meterplot.setRange(new Range(0.0, orcamento));

        meterplot.addInterval(new MeterInterval("25%", new Range(0.0, 0.25*orcamento),
            Color.decode(tonsAzul[0]), new BasicStroke(6.0F), Color.decode(tonsAzul[0])));
        meterplot.addInterval(new MeterInterval("50%", new Range(0.25*orcamento, 0.50*orcamento),
            Color.decode(tonsAzul[2]), new BasicStroke(6.0F), Color.decode(tonsAzul[2])));
        meterplot.addInterval(new MeterInterval("75%", new Range(0.50*orcamento, 0.75*orcamento),
            Color.decode(tonsAzul[3]), new BasicStroke(6.0F), Color.decode(tonsAzul[3])));
        meterplot.addInterval(new MeterInterval("100%", new Range(0.75*orcamento, orcamento),
            Color.decode(tonsAzul[4]), new BasicStroke(6.0F), Color.decode(tonsAzul[4])));
        Font quickSand = null;
        try{
        quickSand = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fontes/quicksand/Quicksand_Bold.otf"));
        }
        catch(IOException|FontFormatException e){
            System.out.println(e);
        }
        
        quickSand = quickSand.deriveFont(Font.BOLD, 13);
        meterplot.setNeedlePaint(Color.decode("#191919"));
        meterplot.setDialBackgroundPaint(Color.white);
        meterplot.setDialOutlinePaint(Color.black);
        meterplot.setDialShape(DialShape.CHORD);
        meterplot.setMeterAngle(180);
        meterplot.setTickLabelsVisible(true);
        meterplot.setTickLabelFont(quickSand);
        meterplot.setTickLabelPaint(Color.BLACK);
        meterplot.setTickSize(0.01);
        meterplot.setTickPaint(Color.gray);
        meterplot.setValuePaint(Color.black);
        meterplot.setValueFont(quickSand);

        if(limiteAtingiu){

            meterplot.setUnits("ou seja, " + "R$ " + (gasto - orcamentoTotal) + " excedidos");
        }
        else{
            meterplot.setUnits("gasto");
        }
        meterplot.setNoDataMessage("Sem dados");

        meterplot.setTickLabelFormat(new DecimalFormat("0.00"));
        meterplot.setValueFont(quickSand);
        JFreeChart jfreechart = new JFreeChart("",
            JFreeChart.DEFAULT_TITLE_FONT, meterplot, true);
        jfreechart.setBackgroundPaint(Color.white);
        return jfreechart;
    }

    public JPanel createPanel(ArrayList<Registro> registros, ArrayList<Orcamento> orcamentos) {
        this.gasto = 0;
        this.limiteAtingiu = false;

        for(Orcamento orcamento : orcamentos){
            orcamentoTotal += orcamento.getLimite();
        }
        if(registros.size() == 0 ){
            gasto = 0;
        }
        else{
            for(Registro registro : registros){
                if(registro.getTipo().equals(Tipo_Registro.DESPESA)){
                    gasto += registro.getValor();
                }
            }
            if(gasto >= orcamentoTotal){
                this.limiteAtingiu = true;
            }
            else{
                this.limiteAtingiu = false;
            }
        }

        datasetGasto = new DefaultValueDataset(gasto);
        datasetOrcamento = new DefaultValueDataset(orcamentoTotal);

        JFreeChart chart = createChart(datasetGasto, datasetOrcamento);
        ChartPanel chartpanel = new ChartPanel(chart);
        return chartpanel;
    }
    public boolean atingiu(){
        return limiteAtingiu;
    }
    

}