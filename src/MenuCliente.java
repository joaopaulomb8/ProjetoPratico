import java.util.List;
import java.util.Optional;

public class MenuCliente {

    private final Repositorio<Cliente> repositorio;

    public MenuCliente(Repositorio<Cliente> repositorio) {
        this.repositorio = repositorio;
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n===== CLIENTES =====");
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
        String email = Entrada.email("E-mail: ");
        String telefone = Entrada.texto("Telefone: ");

        Cliente cliente = repositorio.criar(new Cliente(nome, email, telefone));
        System.out.println("Cliente cadastrado com ID " + cliente.getId() + ".");
    }

    private void listar() {
        List<Cliente> clientes = repositorio.listar();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        clientes.forEach(System.out::println);
    }

    private void buscar() {
        System.out.println("1 - Por ID");
        System.out.println("2 - Por nome");
        int tipo = Entrada.inteiro("Escolha: ");

        if (tipo == 1) {
            int id = Entrada.inteiro("ID: ");
            repositorio.buscarPorId(id).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Cliente não encontrado."));
        } else if (tipo == 2) {
            String termo = Entrada.texto("Nome (ou parte dele): ").toLowerCase();
            List<Cliente> encontrados = repositorio.buscar(c -> c.getNome().toLowerCase().contains(termo));
            if (encontrados.isEmpty()) {
                System.out.println("Nenhum cliente encontrado.");
            } else {
                encontrados.forEach(System.out::println);
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void atualizar() {
        int id = Entrada.inteiro("ID do cliente a atualizar: ");
        Optional<Cliente> encontrado = repositorio.buscarPorId(id);

        if (encontrado.isEmpty()) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Cliente cliente = encontrado.get();
        System.out.println("Atual: " + cliente);
        System.out.println("(Pressione Enter para manter o valor atual)");

        String nome = Entrada.textoOpcional("Novo nome: ");
        String email = Entrada.textoOpcional("Novo e-mail: ");
        String telefone = Entrada.textoOpcional("Novo telefone: ");

        if (!nome.isEmpty()) {
            cliente.setNome(nome);
        }
        if (!email.isEmpty()) {
            if (email.contains("@") && email.contains(".")) {
                cliente.setEmail(email);
            } else {
                System.out.println("E-mail inválido, mantido o anterior.");
            }
        }
        if (!telefone.isEmpty()) {
            cliente.setTelefone(telefone);
        }

        repositorio.atualizar(cliente);
        System.out.println("Cliente atualizado: " + cliente);
    }

    private void excluir() {
        int id = Entrada.inteiro("ID do cliente a excluir: ");
        if (repositorio.deletar(id)) {
            System.out.println("Cliente excluído.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
 