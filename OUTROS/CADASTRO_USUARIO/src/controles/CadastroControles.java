/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Produto;
import java.util.ArrayList;
import java.util.Iterator;

public class CadastroControles {

    public CadastroControles(){
        listaProdutos = new ArrayList<>();
    }
    ArrayList<Produto> listaProdutos;
    public boolean cadastrarProduto(Produto p){
        boolean result = false;
        
        if (p != null && p.getNome().length() >0 && p.getPrecoCusto() != 0.0f && p.getPrecoVenda() != 0.0f && p.getEspecificacoes().length() > 0){
            //aqui está apto para cadstro do produto
            listaProdutos.add(p);
            result = true;
        }
        return result;
    }

    public ArrayList<Produto> obterListaProdutos() {
        return listaProdutos;

    }

     public boolean excluirProduto(long codigo) {
        Iterator<Produto> iterator = listaProdutos.iterator();

        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            if (produto.getCodigo() == codigo) {
                iterator.remove();
                return true; // Produto excluído com sucesso
            }
        }

        return false; // Produto não encontrado
    }}

