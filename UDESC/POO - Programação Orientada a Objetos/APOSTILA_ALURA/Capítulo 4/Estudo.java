class Estudo {
    public static void main(String[] arg) {
        // Printar de forma simples na tela
        System.out.println("Printado \nPrintado2");
        // Casting e Promoção
        double d = 3.14;
        // int i = d;  não compila
        int i = (int) d;
        // ele vai pegar o float e converter para int antes de printar.  Isso tb é importante com contas.
        System.out.println(i);

        // IF E O ELSE 
        // && operador 'E'
        // || operador 'OU'
        i = 4;
        if (i == 3) {
            System.out.println("tu é brabo");
        }
        else {
            System.out.println("tu é fresco é?");
        }

        // O WHILE 

        int c = 1;

        while(c < 10){
            System.out.println(c);
            c++; // isso funciona aqui!
        }

        // O FOR 

        for(int teste = 1; teste < 3; teste++ ){
            System.out.println(teste);

        }

        for (int in = 0; in < 100; in++) {
            if (in > 50 && in < 60) {
                continue; // faz co que ele retorne ao loop
            }    
                System.out.println(in);
        }


    }
}

