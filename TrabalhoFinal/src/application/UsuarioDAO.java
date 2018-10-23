package application;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
	
	public boolean verificaLogin(String login) throws SQLException {
		int count = -1;
	
		String qry = "SELECT count(*) FROM sist_usuario WHERE login = ?";
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		stmt.setString(1, login);
		ResultSet rs = stmt.executeQuery();

		if(rs.next()) {
			count = rs.getInt("count(*)");	
		}
		
		rs.close();
		stmt.close();
		
		if(count == 0) {
			return false;
		}else {
			//JOptionPane.showMessageDialog(null, "Usuário Não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
			return true;
		}
	}
	
	public boolean fazLogin(String login, String senha) throws HeadlessException, SQLException {
		
		String senhaBanco = "";
		
		if(verificaLogin(login)) {
			
			String qry = "SELECT senha FROM sist_usuario WHERE login = ?";
			PreparedStatement stmt = this.conn.prepareStatement(qry);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				senhaBanco = rs.getString("senha");
			}
			
			rs.close();
			stmt.close();
			
			
			if(senha.equals(senhaBanco)) {
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "Senha Inválida", "Erro", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Usuário Não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
	}

	public Usuario usuarioPorLogin(String login) throws SQLException {
		
		String qry = "SELECT senha, nome, isAdmin FROM sist_usuario WHERE login = ?";
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		stmt.setString(1, login);
		ResultSet rs = stmt.executeQuery();
		
		Usuario user = new Usuario();
		user.setLogin(login);
		
		if(rs.next()) {
			user.setSenha(rs.getString("senha"));
			user.setNome(rs.getString("nome"));
			user.setFlagAdmin(rs.getInt("isAdmin"));
		}
		
		rs.close();
		stmt.close();
		return user;
	}
	
}
