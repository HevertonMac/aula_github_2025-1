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
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    System.out.println("Erro: opção inválida.");
            }
        }
    }

    private static void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente novoCliente = new Cliente(nome, cpf, endereco, telefone);
        clientes.add(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("Lista de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("--------------------------");
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
                    abrirConta();
                    break;
                case 2:
                    listarContas();
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

    private static void abrirConta() {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
    
        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                cliente = c;
                break;
            }
        }
    
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
    
        System.out.print("Digite o número da nova conta: ");
        String numero = scanner.nextLine();
    
        Conta novaConta = new Conta(numero, cliente);
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso!");
    }
    
    private static void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
    
        System.out.println("Lista de Contas:");
        for (Conta conta : contas) {
            System.out.println("Número da Conta: " + conta.getNumero());
            System.out.println("Saldo: R$ " + conta.getSaldo());
            System.out.println("Cliente: " + conta.getCliente().getNome());
            System.out.println("--------------------------");
        }
    }

    // --- MÉTODOS PARA OPERAÇÕES (EX: DEPÓSITO, SAQUE) ---

    private static void menuOperacoes() {
        Menu menu = new Menu("Menu Operações", Arrays.asList("Depositar", "Sacar", "Transferir", "Consultar Saldo", "Voltar"));
        boolean loop = true;

        while (loop) {
            int opcao = menu.getSelection();

            switch (opcao) {
                case 1 -> depositar();
                case 2 -> sacar();
                case 3 -> transferir();
                case 4 -> consultarSaldo();
                case 5 -> loop = false;
                default -> System.out.println("Erro: opção inválida.");
            }
        }
    }

    private static void depositar() {
        System.out.print("Digite o número da conta para depósito: ");
        int numeroConta = scanner.nextInt();
    
        Conta conta = buscarContaPorNumero(numeroConta);
    
        if (conta != null) {
            System.out.print("Digite o valor a ser depositado: R$ ");
            double valor = scanner.nextDouble();
            scanner.nextLine();
    
            if (valor > 0) {
                conta.setSaldo(conta.getSaldo() + valor);
                System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
            } else {
                System.out.println("Valor de depósito inválido.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
    

    private static void sacar() {
        System.out.print("Digite o número da conta para saque: ");
        int numeroConta = scanner.nextInt();
    
        Conta conta = buscarContaPorNumero(numeroConta);
    
        if (conta != null) {
            System.out.print("Digite o valor a ser sacado: R$ ");
            double valor = scanner.nextDouble();
            scanner.nextLine();
    
            if (valor > 0 && valor <= conta.getSaldo()) {
                conta.setSaldo(conta.getSaldo() - valor);
                System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente ou valor inválido.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
    

    private static void transferir() {
        System.out.print("Digite o número da conta de origem: ");
        int numeroContaOrigem = scanner.nextInt();
    
        Conta contaOrigem = buscarContaPorNumero(numeroContaOrigem);
    
        if (contaOrigem != null) {
            System.out.print("Digite o número da conta de destino: ");
            int numeroContaDestino = scanner.nextInt();
    
            Conta contaDestino = buscarContaPorNumero(numeroContaDestino);
    
            if (contaDestino != null) {
                System.out.print("Digite o valor a ser transferido: R$ ");
                double valor = scanner.nextDouble();
                scanner.nextLine();
    
                if (valor > 0 && valor <= contaOrigem.getSaldo()) {
                    contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
                    contaDestino.setSaldo(contaDestino.getSaldo() + valor);
                    System.out.println("Transferência de R$ " + valor + " realizada com sucesso.");
                } else {
                    System.out.println("Saldo insuficiente ou valor inválido.");
                }
            } else {
                System.out.println("Conta de destino não encontrada.");
            }
        } else {
            System.out.println("Conta de origem não encontrada.");
        }
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

    // FIM MÉTODO OPERÇÕES

    private static Conta buscarContaPorNumero(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                return conta;
            }
        }
        return null;
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
