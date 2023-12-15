public class Main {
    public static void main(String[] args) {
        // Instanciando cursos
        Curso curso1 = new Curso("Java Fundamentals", 150.0, 40);
        Curso curso2 = new Curso();

        curso2.setNome("Web Development");
        curso2.setPreco(200.0);
        curso2.setDuracaoHoras(60);

        // Instanciando alunos
        Aluno aluno1 = new Aluno("João", "joao@example.com", 25);
        Aluno aluno2 = new Aluno();

        aluno2.setNome("Maria");
        aluno2.setEmail("maria@example.com");
        aluno2.setIdade(30);

        // Instanciando compras
        Compra compra1 = new Compra(aluno1, curso1, 150.0);
        Compra compra2 = new Compra();

        compra2.setAluno(aluno2);
        compra2.setCurso(curso2);
        compra2.setValor(200.0);

        // Instanciando carrinho de compras
        CarrinhoCompras carrinho = new CarrinhoCompras();
        carrinho.adicionarCompra(compra1);
        carrinho.adicionarCompra(compra2);

        // Exibindo informações
        System.out.println("Informações dos objetos:");

        System.out.println("Curso 1: " + curso1);
        System.out.println("Curso 2: " + curso2);

        System.out.println("Aluno 1: " + aluno1);
        System.out.println("Aluno 2: " + aluno2);

        System.out.println("Compra 1: " + compra1);
        System.out.println("Compra 2: " + compra2);

        System.out.println("Carrinho de Compras: " + carrinho);
        System.out.println("Total no carrinho: $" + carrinho.calcularTotal());
    }
}