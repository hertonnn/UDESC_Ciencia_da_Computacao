package dados;

public class Tempo {
    private int mes;
    private int dia;
    private int ano;

    public String dataFormat1(){
        String dataFormat = dia+"/"+mes+"/"+ano;
        return dataFormat;
    }
    public String dataFormat2(){
        String dataFormat = dia+"/"+mes;
        return dataFormat;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getAno() {
        return ano;
    }
    public int getDia() {
        return dia;
    }
    public int getMes() {
        return mes;
    }
}
