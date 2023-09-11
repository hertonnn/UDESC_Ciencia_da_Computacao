package dados;

import java.awt.List;
import java.util.ArrayList;

public class Categoria {

    private String nome;
    private ArrayList<String> subCategorias;

    public Categoria(String nome){
        this.nome = nome;
    }
    public void addSubcategoria(String novaSubCategoria){
        this.subCategorias = new ArrayList<>();
        this.subCategorias.add(novaSubCategoria);
    }

    public String getNome() {
        return nome;
    }
    public ArrayList<String> getSubCategorias() {
        return subCategorias;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
// public Categoria(){

    //     //this.casa = new ArrayList<String>();
    //     this.casa.add("água");
    //     this.casa.add("luz");
    //     this.casa.add("gás");
    //     this.casa.add("aluguel ou financiamento");
    //     this.casa.add("IPTU");
    //     this.casa.add("internet");
    //     this.casa.add("condomínio");

    //     //this.alimentacao = new ArrayList<String>();
    //     this.alimentacao.add("compras do mês");
    //     this.alimentacao.add("delivery");
    //     this.alimentacao.add("restaurante");
    
    //     //this.lazer = new ArrayList<String>();
    //     this.lazer.add("Shopping");
    //     this.lazer.add("Cinema");
    //     this.lazer.add("Serviços de streaming");
    //     this.lazer.add("Parque");

    //     //this.transporte = new ArrayList<String>();
    //     this.transporte.add("IPVA");
    //     this.transporte.add("financiamento");
    //     this.transporte.add("gasolina");
    //     this.transporte.add("transporte público");
    //     this.transporte.add("Aplicativos(uber, buser, entre outros)");

    //     //this.saude = new ArrayList<String>();
    //     this.saude.add("convênio médico");
    //     this.saude.add("academia");
    //     this.saude.add("convênio odontológico");
    //     this.saude.add("medicamentos");

    // }

    // private ArrayList<String> casa;
    // private ArrayList<String> alimentacao;
    // private ArrayList<String> lazer;
    // private ArrayList<String> transporte;
    // private ArrayList<String> saude;
    // public void setLazer(String gastoLazer) {
    //     this.lazer.add(gastoLazer);
    // }
    // public void setCasa(String gastoCasa) {
    //     this.casa.add(gastoCasa);
    // }
    // public void setAlimentacao(String gastoAlimentacao) {
    //     this.alimentacao.add(gastoAlimentacao);
    // }
    // public void setTransporteo(String gastoTransporte) {
    //     this.transporte.add(gastoTransporte);
    // }
    // public void setSaude(String gastoSaude) {
    //     this.saude.add(gastoSaude);
    // }