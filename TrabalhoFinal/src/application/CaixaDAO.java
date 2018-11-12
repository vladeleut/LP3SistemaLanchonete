package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CaixaDAO {
	
	private Connection conn;
	public CaixaDAO() {
		conn = new ConnectionFactory().getConnection();
	}
	
	public void abreCaixa(Usuario u) throws SQLException {
		
		
		
		UsuarioDAO udao = new UsuarioDAO();
		u.setId(udao.usuarioPorLogin(u.getLogin()).getId());
		
		String idLogin = "" + u.getId();
		
		String sql = "INSERT INTO Caixa (id_login, hora_abertura) VALUES (?,sysdate())";
		
		try {
			PreparedStatement qry = conn.prepareStatement(sql);
			
			qry.setString(1, idLogin);
			
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	

	public void fechaCaixa() {
		String sql = "UPDATE Caixa SET hora_fechamento = sysdate() , movimentacao = 0 WHERE hora_fechamento is null";
		
		try {
			PreparedStatement qry = conn.prepareStatement(sql);
			
			//qry.setString(1, c.getTelefone()); vou precisar do valor um dia
						
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
}