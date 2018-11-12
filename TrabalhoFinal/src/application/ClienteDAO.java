package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ClienteDAO {
	
	private Connection conn;
	public ClienteDAO() {
		conn = new ConnectionFactory().getConnection();
	}
	
	public List<Cliente> getLista() throws SQLException {
		
		String qry = "SELECT * FROM clientes";
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		ResultSet rs = stmt.executeQuery();
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		while(rs.next()) {
			Cliente cli = new Cliente();
			
			cli.setTelefone(rs.getString("telefone"));
			cli.setNome(rs.getString("nome"));
			cli.setEndereco(rs.getString("endereco"));
			cli.setBairro(rs.getString("bairro"));
			cli.setComplemento(rs.getString("complemento"));
			cli.setReferencia(rs.getString("referencia"));
			cli.setObservacoes(rs.getString("observacoes"));
			
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
	
	public Cliente procuraPorTelefone(String telefone){
		
		Cliente cli = new Cliente();
		cli.setTelefone(telefone);
		
		try {
		String qry = "SELECT * FROM clientes WHERE telefone  = ?";
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		stmt.setString(1, telefone);
		ResultSet rs = stmt.executeQuery();
		
		
		if(rs.next()) {
			cli.setNome(rs.getString("nome"));
			cli.setEndereco(rs.getString("endereco"));
			cli.setBairro(rs.getString("bairro"));
			cli.setComplemento(rs.getString("complemento"));
			cli.setReferencia(rs.getString("referencia"));
			cli.setObservacoes(rs.getString("observacoes"));
		}
		
		rs.close();
		stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return cli;
		
	}
	
	public void atualizaClientePorTelefone(Cliente c, String telefone) {
		String sql = "UPDATE Clientes SET telefone = ? , nome = ? , endereco = ? , complemento = ? , bairro = ? , referencia = ? , observacoes = ? WHERE telefone = ?";
		
		try {
			PreparedStatement qry = conn.prepareStatement(sql);
			
			qry.setString(1, c.getTelefone());
			qry.setString(2, c.getNome());
			qry.setString(3, c.getEndereco());
			qry.setString(4, c.getComplemento());
			qry.setString(5, c.getBairro());
			qry.setString(6, c.getReferencia());
			qry.setString(7, c.getObservacoes());
			qry.setString(8, telefone);
			
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	public void apagaPorTelefone(String telefone) {
		
String sql = "DELETE FROM Clientes WHERE telefone = ?";
		
		try {
			PreparedStatement qry = conn.prepareStatement(sql);
			
			qry.setString(1, telefone);
			
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		
		JOptionPane.showMessageDialog(null, "APAGUEI CLIENTE " + telefone, "Warning", JOptionPane.WARNING_MESSAGE);
		
	}

}
