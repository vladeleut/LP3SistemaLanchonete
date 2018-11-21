package application;

public class Produto {
	private int id;
	private String nome;
	private String ingredientes;
	private double preco;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(String list) {
		this.ingredientes = list;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
