package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Callback;

public class ProdutoDAO {
	
	private Connection conn;
	public ProdutoDAO() {
		conn = new ConnectionFactory().getConnection();
	}
	
	public void insereProduto(Produto p) {
		String sql = "INSERT INTO Produto(nome, preco) VALUES (?,?)";
		
		try {
			PreparedStatement qry = conn.prepareStatement(sql);
			
			qry.setString(2, String.valueOf(p.getPreco()));
			qry.setString(1, p.getNome());
			
			
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void relacionaProdIng(String nomeProd, String nomeIng) {
		int idProd;
		int idIng;
		idProd = recuperaIdProd(nomeProd);
		
		IngredienteDAO idao = new IngredienteDAO();
		idIng= idao.recuperaIdIng(nomeIng);
		
		try {
		
		String sql = "INSERT INTO produto_ingrediente (id_ing, id_prod) VALUES (?,?)";
		PreparedStatement qry = conn.prepareStatement(sql);
		
		qry.setInt(1, idIng);
		qry.setInt(2,  idProd);
		
		qry.execute();
		qry.close();
		}catch(Exception e) {
			System.out.println("Deu ruim na prod_ing, classe produtoDAO");
			System.out.println(e.getMessage());
		}
		
	}
	
	public int recuperaIdProd(String nomeProd) {
		
		int id = 0;
		
		try {
		String qry = "SELECT id FROM produto WHERE nome = ?";
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		stmt.setString(1, nomeProd);
		ResultSet rs = stmt.executeQuery();

		if(rs.next()) {
			id = rs.getInt("id");	
		}
		
		rs.close();
		
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return id;
		
	}

	public List<Produto> getLista() {
		List<Produto> linhas = new ArrayList<Produto>();
		IngredienteDAO idao = new IngredienteDAO();

		String qry = "SELECT nome, preco FROM  produto";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Produto p = new Produto();
				p.setNome(rs.getString(1));
				p.setPreco(rs.getDouble(2));
				p.setIngredientes(idao.nomeIngPorProd(p.getNome()));
				System.out.println(idao.nomeIngPorProd(p.getNome()));
				linhas.add(p);
			}

			rs.close();
			stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erro na lista de produtos");
		}

		return linhas;
	}

}
