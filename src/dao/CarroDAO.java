package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Carro;

public class CarroDAO implements IDAOGenerico<Carro> {

	@Override
	public void inserir(Carro c) {
		try(Connection conn = new ConnectionFactory().getConnection()){
			PreparedStatement ps = conn.prepareStatement("insert into carro(placa, chassi, cor, modelo, marca, ano) values(?,?,?,?,?,?)");
			ps.setString(1, c.getPlaca());
			ps.setLong(2, c.getChassi());
			ps.setString(3, c.getCor());
			ps.setString(4, c.getModelo());
			ps.setString(5, c.getMarca());
			ps.setInt(6, c.getAno());
			
			ps.execute();
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public ArrayList<Carro> listar() {
		ArrayList<Carro> carros = new ArrayList<Carro>();
		String sql = "select * from carro";
		
		try(Connection conn = new ConnectionFactory().getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Carro c = new Carro();
				c.setId(rs.getLong("id"));
				c.setPlaca(rs.getString("placa"));
				c.setChassi(rs.getLong("chassi"));
				c.setCor(rs.getString("cor"));
				c.setModelo(rs.getString("modelo"));
				c.setMarca(rs.getString("marca"));
				c.setAno(rs.getInt("ano"));
				carros.add(c);
			}
			
			ps.close();
			rs.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return carros;
	}
	

	@Override
	public void deleter(String placa) {
		try(Connection conn = new ConnectionFactory().getConnection()){
			PreparedStatement ps = conn.prepareStatement("delete from carro where placa = ?");
			ps.setString(1, placa);
			ps.execute();
			ps.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void atualizar(Carro o) {
		try(Connection conn = new ConnectionFactory().getConnection()){
			PreparedStatement ps = conn.prepareStatement
					("update carro set chassi = ?, cor = ?, modelo = ?, marca = ?, ano = ? where placa = ?");
			ps.setLong(1, o.getChassi());
			ps.setString(2, o.getCor());
			ps.setString(3, o.getModelo());
			ps.setString(4, o.getMarca());
			ps.setInt(5, o.getAno());
			
			ps.execute();
			ps.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
	}
	
}
