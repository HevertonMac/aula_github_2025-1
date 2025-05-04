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
        Menu menu = new Menu("Menu Conta", Arrays.asList("Abrir Conta", "Listar Contas", "Excluir Conta", "Voltar"));
        boolean loop = true;

        while (loop) {
            int opcao = menu.getSelection();

           switch (opcao) {
    case 1:
        // abrirConta();
        break;
    case 2:
        // listarContas();
        break;
    case 3:
        excluirConta();
        break;
    case 4:
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
         Menu menu = new Menu("Menu Operações", Arrays.asList("Depositar", "Sacar", "Transferir", "Consultar Saldo", "Voltar"));
        boolean loop = true;

        while (loop) {
            int opcao = menu.getSelection();

            switch (opcao) {
                case 1:
                    // depositar();
                    break;
                case 2:
                    // sacar();
                    break;
                case 3:
                    // transferir();
                    break;
                case 4:
                    consultarSaldo();
                    break;
                case 5:
                    loop = false;
                    break;
                default:
                    System.out.println("Erro: opção inválida.");
            }
        }
    }
    private static Conta buscarContaPorNumero(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numeroConta) {
                return conta;
            }
        }
        return null;
    }
    private static void consultarSaldo() {
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();

        Conta conta = buscarContaPorNumero(numeroConta);

        if (conta != null) {
            System.out.println("Saldo atual: R$ " + conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
    private static void excluirConta() {
    System.out.print("Digite o número da conta a ser removida: ");
    int numeroConta = scanner.nextInt();

    Conta contaParaRemover = buscarContaPorNumero(numeroConta);

    if (contaParaRemover != null) {
        if (contaParaRemover.getSaldo() == 0) {
            contas.remove(contaParaRemover);
            System.out.println("Conta removida com sucesso.");
        } else {
            System.out.println("Não é possível remover a conta. Saldo diferente de zero.");
        }
    } else {
        System.out.println("Conta não encontrada.");
    }
}
}
