package model;

import java.awt.TextArea;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import dao.CarroDAO;

public class Carro implements Runnable {
	private long id;
	private String placa;
	private Long chassi;
	private String cor;
	private String modelo;
	private String marca;
	private int ano;
	
	public Carro( ) { }
	
	

	public Carro(String placa, Long chassi, String cor, String modelo, String marca, int ano) {
		this.placa = placa;
		this.chassi = chassi;
		this.cor = cor;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
	}



	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getChassi() {
		return chassi;
	}

	public void setChassi(Long chassi) {
		this.chassi = chassi;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public void run() {
		
			
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
