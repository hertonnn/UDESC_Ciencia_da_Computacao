public class Main {

    public static void main(String[] args) {

        Grupo g = new Grupo();

        Pessoa p1 = new Pessoa();
        p1.setNome("Joao");
        p1.setAltura(1.70f);
        p1.setIdade(19);
        p1.setMassa(70.0f);

        Pessoa p2 = new Pessoa();
        p2.setNome("Julia");
        p2.setAltura(1.65f);
        p2.setIdade(19);
        p2.setMassa(62.5f);

        Pessoa p3 = new Pessoa();
        p3.setNome("Marcos");
        p3.setAltura(1.79f);
        p3.setIdade(20);
        p3.setMassa(75);

        Pessoa p4 = new Pessoa();
        p4.setNome("Luiza");
        p4.setAltura(1.68f);
        p4.setIdade(20);
        p4.setMassa(65);

        Pessoa p5 = new Pessoa();
        p5.setNome("Leticia");
        p5.setAltura(1.66f);
        p5.setIdade(20);
        p5.setMassa(69);

        g.setPessoa(p1);
        g.setPessoa(p2);
        g.setPessoa(p3);
        g.setPessoa(p4);
        g.setPessoa(p5);

        g.ordena();

        for (int i = 0; i < 5; i++) {
            System.out.println("Nome: " + g.getPessoas()[i].getNome());
            System.out.println("Altura: " + g.getPessoas()[i].getAltura());
            System.out.println("Idade: " + g.getPessoas()[i].getIdade());
            System.out.println("Massa: " + g.getPessoas()[i].getMassa());
            System.out.println("IMC: " + g.getPessoas()[i].calculaImc() + "\n");
        }

    }
}