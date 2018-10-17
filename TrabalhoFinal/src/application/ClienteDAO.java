package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
	
	private Connection conn;
	public ClienteDAO() {
		conn = new ConnectionFactory().getConnection();
	}
	
	public List<Cliente> getLista() throws SQLException {
		
		String qry = "SELECT nome, endereco, telefone FROM clientes";
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		ResultSet rs = stmt.executeQuery();
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		while(rs.next()) {
			Cliente cli = new Cliente();
			cli.setNome(rs.getString("nome"));
			cli.setEndereco(rs.getString("endereco"));
			cli.setTelefone(rs.getString("telefone"));
			
			clientes.add(cli);
						
		}
		
		rs.close();
		stmt.close();
		
		return clientes;
		
	}
	
	public void insereCliente(Cliente c) {
		String sql = "INSERT INTO Clientes(telefone, nome, endereco, complemento, bairro, referencia, observacoes) VALUES (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement qry = conn.prepareStatement(sql);
			
			qry.setString(1, c.getTelefone());
			qry.setString(2, c.getNome());
			qry.setString(3, c.getEndereco());
			qry.setString(4, c.getComplemento());
			qry.setString(5, c.getBairro());
			qry.setString(6, c.getReferencia());
			qry.setString(7, c.getObservacoes());
			
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
