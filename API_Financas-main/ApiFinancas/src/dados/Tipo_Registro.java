package dados;

public enum Tipo_Registro {

        RECEITA("RECEITA"),
        DESPESA("DESPESA");

        private String tipo;

        Tipo_Registro(){

        }
        Tipo_Registro(String nome){
               this.tipo = nome;
        }
        public String getTipo() {
            return tipo;
        }
}
