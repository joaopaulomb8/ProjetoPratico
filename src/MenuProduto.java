import java.util.List;
import java.util.Optional;

public class MenuProduto {

    private final Repositorio<Produto> repositorio;

    public MenuProduto(Repositorio<Produto> repositorio) {
        this.repositorio = repositorio;
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n===== PRODUTOS =====");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");
            opcao = Entrada.inteiro("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> atualizar();
                case 5 -> excluir();
                case 0 -> { }
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        String nome = Entrada.texto("Nome: ");
        double preco = Entrada.decimal("Preço: ");
        int quantidade = Entrada.inteiroOpcional("Quantidade em estoque (Enter = 0): ", 0);

        Produto produto = repositorio.criar(new Produto(nome, preco, quantidade));
        System.out.println("Produto cadastrado com ID " + produto.getId() + ".");
    }

    private void listar() {
        List<Produto> produtos = repositorio.listar();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        produtos.forEach(System.out::println);
    }

    private void buscar() {
        System.out.println("1 - Por ID");
        System.out.println("2 - Por nome");
        int tipo = Entrada.inteiro("Escolha: ");

        if (tipo == 1) {
            int id = Entrada.inteiro("ID: ");
            repositorio.buscarPorId(id).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Produto não encontrado."));
        } else if (tipo == 2) {
            String termo = Entrada.texto("Nome (ou parte dele): ").toLowerCase();
            List<Produto> encontrados = repositorio.buscar(p -> p.getNome().toLowerCase().contains(termo));
            if (encontrados.isEmpty()) {
                System.out.println("Nenhum produto encontrado.");
            } else {
                encontrados.forEach(System.out::println);
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void atualizar() {
        int id = Entrada.inteiro("ID do produto a atualizar: ");
        Optional<Produto> encontrado = repositorio.buscarPorId(id);

        if (encontrado.isEmpty()) {
            System.out.println("Produto não encontrado.");
            return;
        }

        Produto produto = encontrado.get();
        System.out.println("Atual: " + produto);
        System.out.println("(Pressione Enter para manter o valor atual)");

        String nome = Entrada.textoOpcional("Novo nome: ");
        if (!nome.isEmpty()) {
            produto.setNome(nome);
        }
        produto.setPreco(Entrada.decimalOpcional("Novo preço: ", produto.getPreco()));
        produto.setQuantidade(Entrada.inteiroOpcional("Nova quantidade: ", produto.getQuantidade()));

        repositorio.atualizar(produto);
        System.out.println("Produto atualizado: " + produto);
    }

    private void excluir() {
        int id = Entrada.inteiro("ID do produto a excluir: ");
        if (repositorio.deletar(id)) {
            System.out.println("Produto excluído.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}
 