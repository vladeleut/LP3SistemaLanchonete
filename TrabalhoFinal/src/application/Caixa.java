package application;

public class Caixa {

	private int id_sessao;
	private String nomeUsuario;
	private String dataAbertura;
	private String dataFechamento;
	private double movimento;
	public int getId_sessao() {
		return id_sessao;
	}
	public void setId_sessao(int id_sessao) {
		this.id_sessao = id_sessao;
	}
	public String getDataAbertura() {
		return dataAbertura;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public String getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(String dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public double getMovimento() {
		return movimento;
	}
	public void setMovimento(double movimento) {
		this.movimento = movimento;
	}
	
	
}
