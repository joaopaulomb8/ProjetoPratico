public class Main {

    public static void main(String[] args) {
        MenuCliente menuCliente = new MenuCliente(new Repositorio<>());
        MenuProduto menuProduto = new MenuProduto(new Repositorio<>());

        int opcao;
        do {
            System.out.println("\n===== SISTEMA DE GESTÃO =====");
            System.out.println("1 - Clientes");
            System.out.println("2 - Produtos");
            System.out.println("0 - Sair");
            opcao = Entrada.inteiro("Escolha: ");

            switch (opcao) {
                case 1 -> menuCliente.exibir();
                case 2 -> menuProduto.exibir();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
