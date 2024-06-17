package model;

public class PessoaJuridica extends Pessoa {
	private String cnpj;
	
	public PessoaJuridica(int id, String nome, String cnpj) {
		super(id, nome);
		this.cnpj = cnpj;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	// método exibir
	public void exibir() {
		System.out.println("ID: " + getId());
		System.out.println("Nome: " + getNome());
		System.out.println("CNPJ: " + cnpj);
		System.out.println("____________" + "\n");
	}
}

