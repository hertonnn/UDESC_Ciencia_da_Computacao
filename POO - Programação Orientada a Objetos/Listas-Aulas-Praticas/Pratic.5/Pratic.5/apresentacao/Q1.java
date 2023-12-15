package apresentacao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q1 {

    private Map<Integer, List<Integer>> todasAstaboadas = new HashMap<Integer, List<Integer>>();    

    
    public List<Integer> geraTaboada(int n){
        List<Integer> taboadaN = new ArrayList<Integer>();

        for(int i = 1; i < 11; i++){
            taboadaN.add(n*i);
        }

        return taboadaN;
    }

    public static void main(String[] args) {

        Q1 p = new Q1();
        
        /// p.todasAstaboadas.clear(); só limpa???
        for(int i = 1; i < 11; i++){
            // caso essa chave não seja única ele sobreescreve o objeto
            p.todasAstaboadas.put(i, p.geraTaboada(i));
        }

        for(Integer num : p.todasAstaboadas.keySet()){
            System.out.print("Tabuada " + num);
            System.out.print(" [");
            for(Integer l : p.todasAstaboadas.get(num)){
                System.out.print(" "+ l);

            }
             System.out.println(" ]");
        }
    }
}
