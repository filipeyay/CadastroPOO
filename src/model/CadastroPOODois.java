// 2º Procedimento do trabalho

package model;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroPOODois {

    public static void main(String[] args) {
        PessoaFisicaRepo repoPessoaFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoPessoaJuridica = new PessoaJuridicaRepo();

        String tipoPessoa;
        String prefixo;
        int opcao;
        Integer id;
        do {
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("===================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("===================================");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    tipoPessoa = fisicaOUjuridica(scanner);
                    switch (tipoPessoa) {
                        case "F":
                            try{
                                PessoaFisica pessoaFisica = lerDadosFisica(scanner);
                                repoPessoaFisica.inserir(pessoaFisica);
                            } catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "J":
                            try{
                                PessoaJuridica pessoaJuridica = lerDadosJuridica(scanner);
                                repoPessoaJuridica.inserir(pessoaJuridica);
                            } catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        default:
                            alertaOpcaoInvalida();
                            break;
                    }
                    break;

                case 2:
                    tipoPessoa = fisicaOUjuridica(scanner);
                    id = getId(scanner);
                    if (id == null){
                        alertaOpcaoInvalida();
                        break;
                    }
                    switch (tipoPessoa) {
                        case "F":
                            PessoaFisica pessoaFisica = repoPessoaFisica.obter(id);
                            if (isPessoaValida(pessoaFisica)){
                                pessoaFisica.exibir();
                                
                                pessoaFisica = alterarDadosPessoaFisica(scanner, pessoaFisica);
                                repoPessoaFisica.alterar(pessoaFisica);
                            } else alertaPessoaInvalida();
                            break;

                        case "J":
                            PessoaJuridica pessoaJuridica = repoPessoaJuridica.obter(id);
                            if (isPessoaValida(pessoaJuridica)){
                                pessoaJuridica.exibir();
                                
                                pessoaJuridica = alterarDadosPessoaJuridica(scanner, pessoaJuridica);
                                repoPessoaJuridica.alterar(pessoaJuridica);
                            } else alertaPessoaInvalida();
                            break;

                        default:
                            alertaOpcaoInvalida();
                            break;
                    }
                    break;
                case 3:
                    tipoPessoa = fisicaOUjuridica(scanner);
                    id = getId(scanner);
                    if (id == null){
                        alertaOpcaoInvalida();
                        break;
                    }
                    
                    switch (tipoPessoa) {
                        case "F":
                            if (repoPessoaFisica.excluir(id))
                                System.out.println("Pessoa excluida com sucesso.");
                            else
                                System.out.println("Falha ao excluir.");
                            break;

                        case "J":
                            if (repoPessoaJuridica.excluir(id))
                                System.out.println("Empresa excluida com sucesso.");
                            else
                                System.out.println("Falha ao excluir.");
                            break;

                        default:
                            alertaOpcaoInvalida();
                            break;
                    }
                    break;

                case 4:
                    tipoPessoa = fisicaOUjuridica(scanner);
                    id = getId(scanner);
                    if (id == null){
                        alertaOpcaoInvalida();
                        break;
                    }
                    
                    switch (tipoPessoa) {
                        case "F":
                            PessoaFisica pessoaFisica = repoPessoaFisica.obter(id);
                            if (isPessoaValida(pessoaFisica))
                                pessoaFisica.exibir();
                            else alertaPessoaInvalida();
                            break;

                        case "J":
                            PessoaJuridica pessoaJuridica = repoPessoaJuridica.obter(id);
                            if (isPessoaValida(pessoaJuridica))
                                pessoaJuridica.exibir();
                            else alertaPessoaInvalida();
                            break;

                        default:
                            alertaOpcaoInvalida();
                            break;
                    }
                    break;

                case 5:
                    tipoPessoa = fisicaOUjuridica(scanner);
                    switch (tipoPessoa) {
                        case "F":
                            for (PessoaFisica pessoa : repoPessoaFisica.obterTodos()) {
                                pessoa.exibir();
                            }
                            break;

                        case "J":
                            for (PessoaJuridica empresa : repoPessoaJuridica.obterTodos()) {
                                empresa.exibir();
                            }
                            break;

                        default:
                            alertaOpcaoInvalida();
                            break;
                    }
                    break;

                case 6:
                    prefixo = obterPrefixo(scanner);
                    
                    try {
                        repoPessoaFisica.persistir(prefixo +".fisica.bin");
                        System.out.println("Dados de Pessoas Físicas Armazenados");
                        
                        repoPessoaJuridica.persistir(prefixo +".juridica.bin");
                        System.out.println("Dados de Pessoas Juridica Armazenados");
                    } catch (IOException ex) {
                        Logger.getLogger(CadastroPOODois.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 7:
                    prefixo = obterPrefixo(scanner);
                    
                    try {
                        repoPessoaFisica.recuperar(prefixo +".fisica.bin");
                        System.out.println("Dados de Pessoas Físicas Recuperados");

                        repoPessoaJuridica.recuperar(prefixo +".juridica.bin");
                        System.out.println("Dados de Pessoas Juridica Armazenados");
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(CadastroPOODois.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 0:
                    System.out.println("Finalizando...");
                    break;
                default:
                    alertaOpcaoInvalida();
                    break;
            }
        } while (opcao != 0);
    }
    
    private static void alertaOpcaoInvalida(){
        System.out.println("Opção inválida. Tente novamente.");
    }
    
    private static void alertaPessoaInvalida(){
        System.out.println("Pessoa/Empresa não encontrada.");
    }
    
    private static boolean isPessoaValida(Object obj){
        return obj != null;
    }
    
    private static String fisicaOUjuridica(Scanner scanner){
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        return scanner.next();
    }
    
    private static String obterPrefixo(Scanner scanner){
        System.out.println("Informe o prefixo do arquivo a ser salvo");
        return scanner.next();
    }
    
    private static Integer getId(Scanner scanner){
        System.out.println("Digite o id da pessoa:");
        try{
            return scanner.nextInt();
        } catch (Exception e) {
            return null;
        }
    }

    private static PessoaFisica lerDadosFisica(Scanner scanner) throws Exception{
        try{
            System.out.println("Insira os dados...");
            System.out.println("Digite o id da pessoa");
            int id = scanner.nextInt();

            System.out.println("Digite o nome da pessoa");
            String nome = scanner.next();

            System.out.println("Digite o cpf da pessoa");
            String cpf = scanner.next();

            System.out.println("Digite a idade da pessoa");
            int idade = scanner.nextInt();

            return new PessoaFisica(id, nome, cpf, idade);
        } catch(Exception e){
            throw new Exception("Dado digitado está incorreto. Tente novamente.");
        }
    }

    private static PessoaJuridica lerDadosJuridica(Scanner scanner) throws Exception{
        try{
            System.out.println("Digite o id da empresa");
            int id = scanner.nextInt();

            System.out.println("Digite o nome da empresa");
            String nome = scanner.next();

            System.out.println("Digite o cnpj da empresa");
            String cnpj = scanner.next();

            return new PessoaJuridica(id, nome, cnpj);
        } catch(Exception e){
            throw new Exception("Dado incorreto. Tente novamente.");
        }
    }
   
    private static PessoaFisica alterarDadosPessoaFisica(Scanner scanner, PessoaFisica pessoa){
        boolean continuar = true;
        
        while(continuar){
            System.out.println("Selecione uma opção:");
            System.out.println("N - Nome");
            System.out.println("C - CPF");
            System.out.println("I - Idade");
            System.out.println("F - Finalizar");
            String opcao = scanner.next();

            try{
                switch (opcao) {
                    case "N":
                        System.out.println("Digite o novo nome:");
                        String nome = scanner.next();
                        pessoa.setNome(nome);
                        break;
                    case "C":
                        System.out.println("Digite o novo CPF:");
                        String cpf = scanner.next();
                        pessoa.setCpf(cpf);
                        break;
                    case "I":
                        System.out.println("Digite a nova idade:");
                        int idade = scanner.nextInt();
                        pessoa.setIdade(idade);
                        break;
                    case "F":
                        continuar = false;
                        break;
                    default:
                        alertaOpcaoInvalida();
                }
            } catch(Exception e){
                alertaOpcaoInvalida();
            }
        }
        return pessoa;
    }

    private static PessoaJuridica alterarDadosPessoaJuridica(Scanner scanner, PessoaJuridica empresa){
        boolean continuar = true;
        
        while(continuar){
            System.out.println("Selecione uma opção:");
            System.out.println("N - Nome");
            System.out.println("C - CNPJ");
            System.out.println("F - Finalizar");
            String opcao = scanner.next();

            try{
                switch (opcao) {
                    case "N":
                        System.out.println("Digite o novo nome:");
                        String nome = scanner.next();
                        empresa.setNome(nome);
                        break;
                    case "C":
                        System.out.println("Digite o novo CNPJ:");
                        String cnpj = scanner.next();
                        empresa.setCnpj(cnpj);
                        break;
                    case "F":
                        continuar = false;
                        break;
                    default:
                        alertaOpcaoInvalida();
                }
            } catch(Exception e){
                alertaOpcaoInvalida();
            }
        }
        return empresa;
    }
}