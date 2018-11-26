package application;

public class Pedido {
	private int numero;
	private String tel_cli;
	private String nome_cli;
	private String situacao;
	public String getTel_cli() {
		return tel_cli;
	}
	public void setTel_cli(String tel_cli) {
		this.tel_cli = tel_cli;
	}
	public String getNome_cli() {
		return nome_cli;
	}
	public void setNome_cli(String nome_cli) {
		this.nome_cli = nome_cli;
	}
	private String tsAbertura;
	private String tsFechamento; 
	
	public int getNumero() {
		return numero;
	}
	public String getTsAbertura() {
		return tsAbertura;
	}
	public void setTsAbertura(String tsAbertura) {
		this.tsAbertura = tsAbertura;
	}
	public String getTsFechamento() {
		return tsFechamento;
	}
	public void setTsFechamento(String tsFechamento) {
		this.tsFechamento = tsFechamento;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
}
