// 1º Procedimento do trabalho

package model;

public class CadastroPOO {
    public static void main(String[] args) throws Exception{
        // pessoas fisicas
        String pessoasDados = "pessoas.dat";

        try {
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            PessoaFisica pessoa1 = new PessoaFisica(1, "Ana", "11111111111", 25);
            PessoaFisica pessoa2 = new PessoaFisica(2, "Carlos", "22222222222", 52);

            repo1.inserir(pessoa1);
            repo1.inserir(pessoa2);
            repo1.persistir(pessoasDados);
            System.out.println("Dados de Pessoa Fisica Armazenados.");

            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            repo2.recuperar(pessoasDados);
            System.out.println("Dados de Pessoa Fisica Recuperados.");

            for (PessoaFisica pessoa : repo2.obterTodos()) {
                System.out.println("Id: " + pessoa.getId());
                System.out.println("Nome " + pessoa.getNome());
                System.out.println("CPF: " + pessoa.getCpf());
                System.out.println("Idade: " + pessoa.getIdade());
            }
        } catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        // pessoas juridicas
        String empresasDados = "empresas.dat";
        
        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();

        PessoaJuridica empresa1 = new PessoaJuridica(1, "XPTO Sales", "33333333333333");
        PessoaJuridica empresa2 = new PessoaJuridica(2, "XPTO Solutions", "44444444444444");

        repo3.inserir(empresa1);
        repo3.inserir(empresa2);

        try {
            repo3.persistir(empresasDados);
            System.out.println("Dados de Pessoa Juridica Armazenados");

            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            repo4.recuperar(empresasDados);
            System.out.println("Dados de Pessoa Juridica Recuperados");

            for(PessoaJuridica empresa : repo4.obterTodos()) {
                System.out.println("Id: " + empresa.getId());
                System.out.println("Nome: " + empresa.getNome());
                System.out.println("CNPJ: " + empresa.getCnpj());
            }
        } catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
