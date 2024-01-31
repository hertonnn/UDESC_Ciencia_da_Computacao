package dados;

import java.util.ArrayList;

public class Categoria {

    private int id;
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
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    
}
