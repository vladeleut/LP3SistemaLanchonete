package application;

public class Usuario {
	private String login;
	private String senha;
	private String nome;
	private int flagAdmin;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getFlagAdmin() {
		return flagAdmin;
	}
	public void setFlagAdmin(int flagAdmin) {
		this.flagAdmin = flagAdmin;
	}
	

}
