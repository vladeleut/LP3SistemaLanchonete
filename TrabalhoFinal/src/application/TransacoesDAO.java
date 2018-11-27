package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransacoesDAO {
	
	private Connection conn;
	public TransacoesDAO() {
		conn = new ConnectionFactory().getConnection();
	}
	
	public List<Transacoes> getLista() {
		String qry = "SELECT c.nome, p.id, pg.valor, pg.formaPagto "
				+ "FROM pedido p, clientes c, pagamentos pg "
				+ "WHERE p.id = pg.id_pedido and c.telefone = p.tel_cliente;";
		
		List<Transacoes> transacoes = new ArrayList<Transacoes>();
		
		try {
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Transacoes t = new Transacoes();
			t.setId(rs.getInt(2));
			t.setFormaPagto(rs.getString(4));
			t.setNome(rs.getString(1));
			t.setValor(rs.getDouble(3));
			transacoes.add(t);
			System.out.println(t.getId()+ t.getNome()+ t.getFormaPagto()+ t.getValor());
			
		}
		
		rs.close();
		stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erro na lista de caixaaaaaaa");
		}
		
		return transacoes;
	}

}
