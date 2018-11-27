package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TopClienteDAO {
	private Connection conn;
	public TopClienteDAO() {
		conn = new ConnectionFactory().getConnection();
	}
	
	public List<TopCliente> getLista() {
		String qry = "SELECT c.nome, sum(pg.valor) AS valorGasto "
				+ "FROM pagamentos pg  JOIN pedido p ON pg.id_pedido = p.id "
				+ "JOIN clientes c ON c.telefone = p.tel_cliente "
				+ "GROUP by c.nome, p.tel_cliente "
				+ "ORDER by pg.valor desc";
		List<TopCliente> lista = new ArrayList<TopCliente>();
		
		try {
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			TopCliente c = new TopCliente();
			c.setNome(rs.getString(1));
			c.setValor(rs.getDouble(2));
			lista.add(c);
			
		}
		rs.close();
		stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erro na lista de tops");
		}
		return lista;
	}
}
