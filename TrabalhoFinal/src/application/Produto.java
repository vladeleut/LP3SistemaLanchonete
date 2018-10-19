package application;

import java.util.List;

public class Produto {
	private int id;
	private List<Item> ingredientes;
	private double preco;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Item> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<Item> ingredientes) {
		this.ingredientes = ingredientes;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
