package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
public int recuperaIdProd(String nomeIng) {
		
		int id = 0;
		
		try {
		String qry = "SELECT id FROM produto WHERE nome = ?";
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		stmt.setString(1, nomeIng);
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

}
