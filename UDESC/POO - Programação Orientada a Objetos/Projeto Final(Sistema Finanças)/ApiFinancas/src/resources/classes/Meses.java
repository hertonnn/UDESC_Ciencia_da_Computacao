package resources.classes;

 public enum Meses{
        
        Janeiro(1),
        Fevereiro(2),
        Março(3),
        Abril(4),
        Maio(5),
        Junho(6),
        Julho(7),
        Agosto(8),
        Setembro(9),
        Outubro(10),
        Novembro(11),
        Dezembro(12);

        private Integer num;

        Meses(Integer num){
            this.num = num;
        }
        public Integer getNum(){
            return this.num;
        }
        public String getNome(){
            return this.toString();
        }

    }
