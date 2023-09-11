import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Imovel {
    private double largura;
    private double comprimento;
    private double preco;

    public Imovel(double largura, double comprimento, double preco) {
        this.largura = largura;
        this.comprimento = comprimento;
        this.preco = preco;
    }

    public double calcularArea() {
        return largura * comprimento;
	}

    public String toString() {
        return String.format("Imóvel [largura=%.2f, comprimento=%.2f, preço=%.2f]", largura, comprimento, preco);
  }
}

class Imobiliaria {
    private String nome;
    private List<Imovel> imoveis;

    public Imobiliaria(String nome) {
        this.nome = nome;
        this.imoveis = new ArrayList<>();
    }

    public void adicionarImovel(Imovel imovel) {
        imoveis.add(imovel);
    }

    public Imovel[] filtrarPorArea(float x) {
        List<Imovel> imoveisFiltrados = new ArrayList<>();
        for (Imovel imovel : imoveis) {
            if (imovel.calcularArea() >= x) {
                imoveisFiltrados.add(imovel);
            }
        }

        Imovel[] resultado = imoveisFiltrados.toArray(new Imovel[0]);
        Arrays.sort(resultado, Comparator.comparingDouble(Imovel::calcularArea));

        return resultado;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Imobiliária ").append(nome).append(":\n");
        for (Imovel imovel : imoveis) {
            sb.append(imovel).append("\n");
        }
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Imobiliaria imobiliaria = new Imobiliaria("Minha Imobiliária");

        imobiliaria.adicionarImovel(new Imovel(10, 20, 200000));
        imobiliaria.adicionarImovel(new Imovel(15, 30, 300000));
        imobiliaria.adicionarImovel(new Imovel(8, 25, 180000));

        System.out.println(imobiliaria);

        float areaMinima = 200;
        Imovel[] imoveisFiltrados = imobiliaria.filtrarPorArea(areaMinima);

        System.out.println("Imóveis com área maior ou igual a " + areaMinima + " m²:");
        for (Imovel imovel : imoveisFiltrados) {
            System.out.println(imovel);
        }
    }
}