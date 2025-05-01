import java.util.*;

public class Main {

    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Conta> contas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Menu mainMenu = new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operações", "Sair"));
        boolean executando = true;

        while (executando) {
            int opcao = mainMenu.getSelection();

            switch (opcao) {
                case 1:
                    menuConta();
                    break;
                case 2:
                    menuCliente();
                    break;
                case 3:
                    menuOperacoes();
                    break;
                case 4:
                    System.out.println("Encerrando o sistema. Até logo!");
                    executando = false;
                    break;
                default:
                    System.out.println("Erro: opção inválida.");
            }
        }
    }

    // --- MÉTODOS PARA CLIENTE ---

    private static void menuCliente() {
        Menu menu = new Menu("Menu Cliente", Arrays.asList("Cadastrar Cliente", "Listar Clientes", "Voltar"));
        boolean loop = true;

        while (loop) {
            int opcao = menu.getSelection();

            switch (opcao) {
                case 1:
                    //cadastrarCliente();
                    break;
                case 2:
                    //listarClientes();
                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    System.out.println("Erro: opção inválida.");
            }
        }
    }

    // --- MÉTODOS PARA CONTA ---

    private static void menuConta() {
        Menu menu = new Menu("Menu Conta", Arrays.asList("Abrir Conta", "Listar Contas", "Voltar"));
        boolean loop = true;

        while (loop) {
            int opcao = menu.getSelection();

            switch (opcao) {
                case 1:
                    //abrirConta();
                    break;
                case 2:
                    //listarContas();
                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    System.out.println("Erro: opção inválida.");
            }
        }
    }


    // --- MÉTODOS PARA OPERAÇÕES (EX: DEPÓSITO, SAQUE) ---

    private static void menuOperacoes() {
        System.out.println("Funcionalidade de operações ainda não implementada.");
        // Aqui você pode adicionar opções como:
        // - Depositar em conta
        // - Sacar da conta
        // - Transferência
    }
}
