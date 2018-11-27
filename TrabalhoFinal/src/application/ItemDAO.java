package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
	
	private Connection conn;
	public ItemDAO() {
		conn = new ConnectionFactory().getConnection();
	}
	
	public List<Item> getLista() {
		String qry = "SELECT p.nome, pp.obs, p.preco "
				+ "FROM produto_pedido pp, produto p, pedido pd "
				+ "WHERE pd.situacao = 1 and pp.id_produto = p.id and pp.id_pedido "
				+ "in (select id from pedido where situacao = 1);";
		
		List<Item> itensPedido = new ArrayList<Item>();
		
		try {
		PreparedStatement stmt = this.conn.prepareStatement(qry);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Item i = new Item();
			i.setNome(rs.getString(1));
			i.setObs(rs.getString(2));
			i.setPreco(rs.getDouble(3));
			itensPedido.add(i);
			
		}
		
		rs.close();
		stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erro na lista de itesn");
		}
		return itensPedido;
		
	}

}
