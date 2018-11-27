package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
	
	private Connection conn;
	public PedidoDAO() {
		conn = new ConnectionFactory().getConnection();
	}
	
	public List<Pedido> getLista() {
		String qry = "SELECT p.tel_cliente, c.nome, p.id as nro_pedido, p.hora_abertura, s.situacao ";
			   qry+= "FROM pedido p, clientes c, status_pedidos s ";
			   qry+= "WHERE p.tel_cliente = c.telefone and p.situacao = s.id;";
		
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		try {
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Pedido p = new Pedido();
			p.setTel_cli(rs.getString(1));
			p.setNome_cli(rs.getString(2));
			p.setNumero(rs.getInt(3));
			p.setTsAbertura(rs.getString(4));
			p.setSituacao(rs.getString(5));
			//pegar valor? ajustar qry: , p.valor_total
			pedidos.add(p);
			
		}
		
		rs.close();
		stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erro na lista de pedidos");
		}
		return pedidos;
	}
	
	public Pedido criaPedido(Cliente c) throws SQLException {
		
		String sql = "INSERT INTO pedido (tel_cliente, hora_abertura) VALUES (?,sysdate())";
		
		try {
			PreparedStatement qry = conn.prepareStatement(sql);
			
			qry.setString(1, c.getTelefone());
			
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
			
		Pedido pedido = new Pedido();
		pedido.setNumero(encontraPedidoAberto());
		
		return pedido;
	}
	
	private int encontraPedidoAberto() {
		int nroPedidoAberto = 0;
		String qry = "SELECT id FROM pedido WHERE situacao = 1";
		try {
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			
			nroPedidoAberto = rs.getInt(1);
		}
		
		rs.close();
		stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage() + "Erro na encontraPedidoAberto");
		}
		return nroPedidoAberto;
	}

	public void atualizaStatus(int situacao, int nro_pedido) {
		/*
		Legenda:
		1	Novo - apenas um pode ser novo
		2	Aberto
		3	Em rota
		4	Entregue
		5	Cancelado
		 */
		
		String sql = "UPDATE pedido SET situacao = ? WHERE id = ?";
		
		try {
			PreparedStatement qry = conn.prepareStatement(sql);
			
			qry.setInt(1, situacao);
			qry.setInt(2, nro_pedido);
			
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}	
		
	}
	
	public void avancaStatus(int NroPedido) throws SQLException {
		int statusAtual = 0;
		
		String qry = "SELECT situacao FROM pedido WHERE id = ?";
		
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		stmt.setInt(1, NroPedido);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			statusAtual = rs.getInt(1);
		}
		
		rs.close();
		stmt.close();
		
		atualizaStatus((statusAtual+1), NroPedido);
	}
	
	
	public void evitaErros() {
		String sql = "UPDATE pedido SET situacao = 2 WHERE situacao = 1";
		
		try {
			PreparedStatement qry = conn.prepareStatement(sql);			
			qry.execute();
			qry.close();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public void addProdutoNoPedidoNovo(int idProd, String obs) throws SQLException {
		int idPedido = 0;
		
		String qry = "SELECT id FROM pedido WHERE situacao = 1";
		
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			idPedido = rs.getInt(1);
		}
		
		rs.close();
		stmt.close();
		
		String novaQry = "INSERT INTO produto_pedido(id_produto, id_pedido, obs) values (?,?,?)";
		try {
			PreparedStatement stmt2 = conn.prepareStatement(novaQry);
			stmt2.setInt(1, idProd);
			stmt2.setInt(2, idPedido);
			stmt2.setString(3, obs);
			stmt2.execute();
			stmt2.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("erro no pedido DAO stmt2");
		}
		
		
	}
	
	public void registraPagto(double valor, String formaDePagto) {
		System.out.println(valor);
		System.out.println(formaDePagto);
		PedidoDAO pddao= new PedidoDAO();
		int nroPedido = pddao.encontraPedidoAberto();
		
		String qry = "INSERT INTO pagamentos (id_pedido, valor, formaPagto) values (?,?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setInt(1, nroPedido);
			stmt.setDouble(2, valor);
			stmt.setString(3, formaDePagto);
			stmt.execute();
			stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage()+"Erro ao registrar pagamento - pddao 195");
			
		}
		
	}
	
}
