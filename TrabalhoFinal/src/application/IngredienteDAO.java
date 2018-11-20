package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO {
	
	private Connection conn;
	public IngredienteDAO() {
		conn = new ConnectionFactory().getConnection();
	}
	
	public void insere(Ingrediente i) {
		String sql = "INSERT INTO ingrediente2(nome, preco, descricao) VALUES (?,?,?)";
		
		try {
			PreparedStatement qry = conn.prepareStatement(sql);
			
			qry.setString(1, i.getNome());
			qry.setDouble(2, i.getPreco());
			qry.setString(3, i.getDescricao());
			
			
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public List<Ingrediente> getLista() {
		String qry = "SELECT nome, preco, descricao FROM  ingrediente2";
		
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		
		try {
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Ingrediente i = new Ingrediente();
			i.setNome(rs.getString(1));
			i.setPreco(rs.getDouble(2));
			i.setDescricao(rs.getString(3));
			ingredientes.add(i);
			
		}
		
		rs.close();
		stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erro na lista de ingredientes");
		}
		
		return ingredientes;
	}
	
	public int recuperaIdIng(String nomeIng) {
		
		int id = 0;
		
		try {
		String qry = "SELECT id FROM ingrediente2 WHERE nome = ?";
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
