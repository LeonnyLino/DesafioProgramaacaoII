package Tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CarroDAO;
import model.Carro;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.ScrollPane;
import javax.swing.ScrollPaneConstants;

public class TelaCadastro extends JFrame implements ActionListener{
	private JTextField tfPlaca;
	private JTextField tfChassi;
	private JTextField tfCor;
	private JTextField tfModelo;
	private JTextField tfMarca;
	private JTextField tfAno;
	private JRadioButton rbtnRemover, rbtnAlterar;
	private JButton btnInserir, btnAlterar, btnRemover;
	private Carro carro;
	private Vector<Carro> vectorCarros;
	private JList<Carro> listCarros;
	private TextArea textArea;
	private JScrollPane areaDetext;
	private ButtonGroup rbtnGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cadastro");
		setBounds(100, 100, 510, 383);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Carros");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 11, 147, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(20, 51, 46, 14);
		getContentPane().add(lblPlaca);
		
		JLabel lblChassi = new JLabel("Chassi");
		lblChassi.setBounds(20, 76, 46, 14);
		getContentPane().add(lblChassi);
		
		JLabel lblCor = new JLabel("Cor");
		lblCor.setBounds(20, 101, 46, 14);
		getContentPane().add(lblCor);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(22, 126, 46, 14);
		getContentPane().add(lblModelo);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(22, 151, 46, 14);
		getContentPane().add(lblMarca);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(22, 176, 46, 14);
		getContentPane().add(lblAno);
		
		tfPlaca = new JTextField();
		tfPlaca.setBounds(83, 51, 160, 20);
		getContentPane().add(tfPlaca);
		tfPlaca.setColumns(10);
		
		tfChassi = new JTextField();
		tfChassi.setColumns(10);
		tfChassi.setBounds(83, 73, 160, 20);
		getContentPane().add(tfChassi);
		
		tfCor = new JTextField();
		tfCor.setColumns(10);
		tfCor.setBounds(83, 98, 160, 20);
		getContentPane().add(tfCor);
		
		tfModelo = new JTextField();
		tfModelo.setColumns(10);
		tfModelo.setBounds(83, 123, 160, 20);
		getContentPane().add(tfModelo);
		
		tfMarca = new JTextField();
		tfMarca.setColumns(10);
		tfMarca.setBounds(83, 148, 160, 20);
		getContentPane().add(tfMarca);
		
		tfAno = new JTextField();
		tfAno.setColumns(10);
		tfAno.setBounds(83, 173, 160, 20);
		getContentPane().add(tfAno);
		
		btnInserir = new JButton("Inserir");
		btnInserir.setBounds(10, 214, 89, 23);
		getContentPane().add(btnInserir);
		
		btnRemover = new JButton("Remover");
		btnRemover.setBounds(109, 214, 89, 23);
		getContentPane().add(btnRemover);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(10, 248, 89, 23);
		getContentPane().add(btnAlterar);
		
		rbtnAlterar = new JRadioButton("Alterar");
		rbtnAlterar.setBounds(109, 244, 109, 23);
		getContentPane().add(rbtnAlterar);
		
		rbtnRemover = new JRadioButton("Remover");
		rbtnRemover.setBounds(109, 270, 109, 23);
		getContentPane().add(rbtnRemover);
		
		rbtnGroup = new ButtonGroup();
		rbtnGroup.add(rbtnRemover);
		rbtnGroup.add(rbtnAlterar);
		
		textArea = new TextArea();
		areaDetext = new JScrollPane(textArea);
		areaDetext.setBounds(279, 28, 185, 242);
		getContentPane().add(areaDetext);
		
		Thread threadListar = new Thread(carro);
		threadListar.start();
		
		
		btnAlterar.addActionListener(this);
		btnInserir.addActionListener(this);
		btnRemover.addActionListener(this);
		rbtnAlterar.addActionListener(this);
		rbtnRemover.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CarroDAO cDao = new CarroDAO();
		if(e.getSource() == btnInserir) {
			
			cDao.inserir(new Carro(tfPlaca.getText(), Long.parseLong(tfChassi.getText()), tfCor.getText(), tfModelo.getText(),
						tfMarca.getText(), Integer.parseInt(tfAno.getText())));
			JOptionPane.showMessageDialog(this, "Carro cadastrado");
			
			Thread threadListar = new Thread(carro);
			threadListar.start();
			
			for(Carro c:cDao.listar()) {
				textArea.append("Placa: " + c.getPlaca());
				textArea.append("\nChassi: " + String.valueOf(c.getChassi()));
				textArea.append("\nCor: " +c.getCor());
				textArea.append("\nModelo: " +c.getModelo());
				textArea.append("\nMarca: " +c.getMarca());
				textArea.append("\nAno: " +String.valueOf(c.getAno()));
				textArea.append("\n________________________________");
				
				try {
					Thread.sleep(2000);
				}catch (InterruptedException t) {
					t.printStackTrace();
				}
			}
			
			
			tfPlaca.setText("");
			tfChassi.setText("");
			tfCor.setText("");
			tfModelo.setText("");
			tfMarca.setText("");
			tfAno.setText("");
		}
		
		if(rbtnAlterar.isSelected()) {
				for(Carro c:cDao.listar()) {
					if(c.getPlaca().equals(tfPlaca.getText())) {
						tfChassi.setText(String.valueOf(c.getChassi()));
						tfCor.setText(c.getCor());
						tfModelo.setText(c.getModelo());
						tfMarca.setText(c.getMarca());
						tfAno.setText(String.valueOf(c.getAno()));
					}else {
						JOptionPane.showMessageDialog(this, "Carro não encontrado");
					}
					
				}

				if(e.getSource() == btnAlterar) {
					carro.setPlaca(tfPlaca.getText());
					cDao.atualizar(carro);
				}
				
			
		}
		
		if(e.getSource() == btnRemover) {
			cDao.deleter(tfPlaca.getText());
			JOptionPane.showMessageDialog(this, "Carro deletado");
		}
		
	}
}
