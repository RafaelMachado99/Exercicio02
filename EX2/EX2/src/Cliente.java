import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Cliente {
    String nome;
    String cpf;
    int idade;
    char genero;

    public Cliente(String nome, String cpf, int idade, char genero) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.genero = genero;
    }
}

public class SistemaControleBar {
    private List<Cliente> clientesNoBar;

    public SistemaControleBar() {
        clientesNoBar = new ArrayList<>();
    }

    public void adicionarCliente(String nome, String cpf, int idade, char genero) {
        Cliente cliente = new Cliente(nome, cpf, idade, genero);
        clientesNoBar.add(cliente);
    }

    public void removerCliente(String cpf) {
        Cliente clienteParaRemover = null;
        for (Cliente cliente : clientesNoBar) {
            if (cliente.cpf.equals(cpf)) {
                clienteParaRemover = cliente;
                break;
            }
        }
        if (clienteParaRemover != null) {
            clientesNoBar.remove(clienteParaRemover);
        }
    }

    public Cliente buscarClientePorCPF(String cpf) {
        for (Cliente cliente : clientesNoBar) {
            if (cliente.cpf.equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public int getTotalClientesNoBar() {
        return clientesNoBar.size();
    }

    public double getPercentualGenero(char genero) {
        long count = clientesNoBar.stream().filter(cliente -> cliente.genero == genero).count();
        return (double) count / clientesNoBar.size() * 100;
    }

    public static void main(String[] args) {
        SistemaControleBar sistema = new SistemaControleBar();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1 - Adicionar cliente");
            System.out.println("2 - Remover cliente");
            System.out.println("3 - Buscar cliente por CPF");
            System.out.println("4 - Total de clientes no bar");
            System.out.println("5 - Percentual de clientes por gênero");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF do cliente: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Idade do cliente: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Gênero do cliente (M/F): ");
                    char genero = scanner.nextLine().charAt(0);
                    sistema.adicionarCliente(nome, cpf, idade, genero);
                    System.out.println("Cliente adicionado com sucesso!");
                    break;
                case 2:
                    System.out.print("CPF do cliente a ser removido: ");
                    String cpfRemover = scanner.nextLine();
                    sistema.removerCliente(cpfRemover);
                    System.out.println("Cliente removido com sucesso!");
                    break;
                case 3:
                    System.out.print("CPF do cliente a ser buscado: ");
                    String cpfBuscar = scanner.nextLine();
                    Cliente cliente = sistema.buscarClientePorCPF(cpfBuscar);
                    if (cliente != null) {
                        System.out.println("Cliente encontrado: " + cliente.nome);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 4:
                    int totalClientes = sistema.getTotalClientesNoBar();
                    System.out.println("Total de clientes no bar: " + totalClientes);
                    break;
                case 5:
                    System.out.println("Percentual de clientes por gênero:");
                    System.out.println("Masculino: " + sistema.getPercentualGenero('M') + "%");
                    System.out.println("Feminino: " + sistema.getPercentualGenero('F') + "%");
                    break;
                case 6:
                    System.out.println("Saindo do sistema.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
