package dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.Carro;

public class test {

	public static void main(String[] args) {
		
		try(Connection conn = new ConnectionFactory().getConnection()){
			System.out.println("Conexão ok!");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		CarroDAO cDAO = new CarroDAO();
		
		cDAO.inserir(new Carro("oex-4700", 123456l, "preto", "Bross", "honda", 2011));
		
		
	}

}
