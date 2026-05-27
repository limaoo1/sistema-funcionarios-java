import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        try {
            File arquivo = new File("funcionarios.txt");
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                String nome = dados[0];
                int idade = Integer.parseInt(dados[1]);
                String cargo = dados[2];
                
                Funcionario f = new Funcionario(nome, idade, cargo);
                funcionarios.add(f);
                 
        }

        leitor.close();

    } catch (FileNotFoundException e) {
        System.out.println("Nenhum arquivo encontrado. Um novo será criado ao sair.");
    }

        int opcao;

        do {

            System.out.println("=================================");
            System.out.println("     SISTEMA DE FUNCIONÁRIOS     ");
            System.out.println("=================================");
            System.out.println("Data e hora: " + LocalDateTime.now().format(formato));
            System.out.println("---------------------------------");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Buscar funcionário");
            System.out.println("4 - Remover funcionário");
            System.out.println("5 - Editar funcionário");
            System.out.println("6 - Créditos");
            System.out.println("7 - Sair");
            System.out.println("---------------------------------");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {

                System.out.println("Nome:");
                String nome = scanner.nextLine();

                System.out.println("Idade:");
                if (!scanner.hasNextInt()) {
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("ERRO: Digite apenas números para a idade.");
                    System.out.println("-----------------------------------------");
                    scanner.nextLine();
                    continue;
                
                }
                
                int idade = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Cargo:");
                String cargo = scanner.nextLine();

                Funcionario f = new Funcionario(nome, idade, cargo);

                funcionarios.add(f);

                System.out.println("Funcionário cadastrado!");

            } else if (opcao == 2) {

                listarFuncionarios(funcionarios);

            } else if (opcao == 3) {

                buscarFuncionario(funcionarios, scanner);

            } else if (opcao == 4) {

                System.out.println("Digite o nome do funcionário que deseja remover:");
                String nomeRemover = scanner.nextLine();

                boolean removido = false;

                for (int i = 0; i < funcionarios.size(); i++) {

                    if (funcionarios.get(i).nome.equalsIgnoreCase(nomeRemover)) {
                        funcionarios.remove(i);
                        System.out.println("Funcionário removido com sucesso.");
                        removido = true;
                        break;
                }
            }

                    if (!removido) {
                        System.out.println("Funcionário não encontrado.");
                    }

            } else if (opcao == 5) {

                System.out.println("Digite o nome do funcionário que deseja editar:");
                String nomeEditar = scanner.nextLine();

                boolean editado = false;

                for (Funcionario f : funcionarios) {

                    if (f.nome.equalsIgnoreCase(nomeEditar)) {
                        System.out.println("Novo nome:");
                        f.nome = scanner.nextLine();

                        System.out.println("Nova idade:");
                        if (!scanner.hasNextInt()) {
                            System.out.println("ERRO: Digite apenas números para a idade.");
                            scanner.nextLine();
                            continue;
                        }

                        f.idade = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Novo cargo:");
                        f.cargo = scanner.nextLine();

                        System.out.println("Funcionário editado com sucesso.");

                        editado = true;
                        break;
                    }
                }

                if (!editado) {
                    System.out.println("Funcionário não encontrado.");
                }


            } else if (opcao == 6) {
                
                mostrarCreditos();

            } else if (opcao == 7) {

                System.out.println("Encerrando sistema...");

            } else {

                System.out.println("Opção inválida.");
            }


        } while (opcao != 7);

        try {
            FileWriter arquivo = new FileWriter("funcionarios.txt");

            for (Funcionario f : funcionarios) {
                arquivo.write(f.nome + ";" + f.idade + ";" + f.cargo + "\n");
            }

            arquivo.close();
            System.out.println("Dados salvos com sucesso!");

    } catch (IOException e) {
        System.out.println("Erro ao salvar os dados.");
    }

        scanner.close();
    }

    public static void mostrarCreditos() {
        System.out.println("Sistema desenvolvido por Luis Felipe.");
        System.out.println("Projeto criado em Java.");
    }

    public static void listarFuncionarios(ArrayList<Funcionario> funcionarios) {

        System.out.println("Funcionários:");
        System.out.println("-------------------");
        System.out.println("Total de funcionários: " + funcionarios.size());

        if (funcionarios.isEmpty()) {

            System.out.println("Nenhum funcionário cadastrado.");

        } else {

            for (Funcionario f : funcionarios) {
                f.mostrarDados();
            }
        }
    }

    public static void buscarFuncionario(ArrayList<Funcionario> funcionarios, Scanner scanner) {

        System.out.println("Digite o nome do funcionário que deseja buscar:");
        String nomeBusca = scanner.nextLine();

        boolean encontrado = false;

        for (Funcionario f : funcionarios) {
            if (f.nome.equalsIgnoreCase(nomeBusca)) {
                f.mostrarDados();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Funcionário não encontrado.");
    }
}
}