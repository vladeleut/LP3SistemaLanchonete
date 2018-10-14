package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
	private Connection conn;
	public UsuarioDAO() {
		conn = new ConnectionFactory().getConnection();
	}
	
	public List<Usuario> getLista() throws SQLException {
		
		String qry = "Select login, senha, nome, isAdmin from sist_usuario";
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		ResultSet rs = stmt.executeQuery();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		while(rs.next()) {
			Usuario user = new Usuario();
			user.setLogin(rs.getString("login"));
			user.setSenha(rs.getString("senha"));
			user.setNome(rs.getString("nome"));
			user.setFlagAdmin(rs.getInt("isAdmin"));
			usuarios.add(user);
			
		}
		
		rs.close();
		stmt.close();
		
		return usuarios;
		
	}
	
	
}
