package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			qry.setString(2, i.getPreco());
			qry.setString(3, i.getDescricao());
			
			
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}	
	}

}
