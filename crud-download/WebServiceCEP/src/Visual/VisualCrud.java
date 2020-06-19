package Visual;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bd.dao.Alunos;
import bd.dbo.Aluno;
import WebService.ClienteWS;
import bd.dbo.Logradouro;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class VisualCrud {

	private JFrame frame;
    private JTextField txtRA;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtCEP;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JTextField txtComple;
	private JTextField txtNumero;
	private boolean cepValido = false;
	

	private Aluno newAlunoInstance() throws Exception
	{
		return new Aluno(Integer.parseInt(txtRA.getText()), txtNome.getText(), txtEmail.getText(), Integer.parseInt(txtCEP.getText()),
	              txtEndereco.getText(), txtBairro.getText(), txtCidade.getText(), txtEstado.getText(), txtComple.getText(), 
	              Integer.parseInt(txtNumero.getText()));
	}
	
	private void IncluirAluno() throws Exception
	{
		if(!this.cepValido)
			throw new Exception("CEP inválido");
		Aluno a = newAlunoInstance();
		Alunos.incluir(a);
		Aluno al = procurarAluno(a.getNome());
		exibirAlunos(al);
	}
	
	private void exibirAlunos(Aluno aluno){
		
		txtRA.setText(aluno.getRa()+"");
		txtNome.setText(aluno.getNome());
		txtEmail.setText(aluno.getEmail());
		txtCEP.setText(aluno.getCep() + " ");
		txtEndereco.setText(aluno.getEndereco());
		txtBairro.setText(aluno.getBairro());
		txtCidade.setText(aluno.getCidade());
		txtEstado.setText(aluno.getEstado());
		txtComple.setText(aluno.getComplemento());
		txtNumero.setText(aluno.getNumero() + "");	
	}

	private Aluno procurarAluno(int ra) throws Exception
	{
		return Alunos.getAluno(ra);
	}
	private Aluno procurarAluno(String nome) throws Exception {
		
		return Alunos.getAluno(nome);
	}

	private void excluirAluno() throws Exception
	{
	   Alunos.excluir(Integer.parseInt(txtRA.getText()));
	}
	
	private void LimparCampos()
	{
		txtRA.setText(null);
		txtNome.setText(null);
		txtEmail.setText(null);
		txtCEP.setText(null);
		limparEndereco();
	}
	
	private void alterarAluno(int ra) throws Exception
	{
		Aluno aluno = newAlunoInstance();
		aluno.setRa(ra);
		Alunos.atualizar(aluno);
	}
	
	private void pontaCep(String cep) {
		
		try {
			
			Logradouro log = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "http://api.postmon.com.br/v1/cep", cep);
			alterarEndereco(log);
			this.cepValido = true;
		}
		catch(Exception e) {
			limparEndereco();
			this.cepValido = false;
		}
	}
	
	private void alterarEndereco(Logradouro log)
	{
		txtEndereco.setText(log.getLogradouro());
		txtBairro.setText(log.getBairro());
		txtCidade.setText(log.getCidade());
		txtEstado.setText(log.getEstado());
	}
	
	private void limparEndereco()
	{
		txtEndereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtEstado.setText(null);
		txtComple.setText(null);
		txtNumero.setText(null);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualCrud window = new VisualCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
 
	/**
	 * Create the application.
	 */
	public VisualCrud() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 589, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consulta CEP : ");
		lblNewLabel.setBounds(183, 11, 197, 14);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 30, 551, 329);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Alunos", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel_1.add(panel, BorderLayout.CENTER);
		
		
		txtRA = new JTextField();
		txtRA.setColumns(5);
		txtRA.setBounds(56, 27, 86, 30);
		panel.add(txtRA);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(56, 68, 283, 31);
		panel.add(txtNome);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(48, 109, 291, 25);
		panel.add(txtEmail);
		
		txtCEP = new JTextField();
		txtCEP.setColumns(8);
		txtCEP.setBounds(253, 27, 86, 30);
		panel.add(txtCEP);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(50);
		txtEndereco.setBounds(72, 143, 313, 30);
		panel.add(txtEndereco);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(20);
		txtBairro.setBounds(56, 184, 329, 29);
		panel.add(txtBairro);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(20);
		txtCidade.setBounds(72, 263, 130, 30);
		panel.add(txtCidade);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(25);
		txtEstado.setBounds(284, 263, 65, 30);
		panel.add(txtEstado);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(25);
		txtNumero.setBounds(320, 221, 65, 30);
		panel.add(txtNumero);
		
		txtComple = new JTextField();
		txtComple.setColumns(20);
		txtComple.setBounds(92, 222, 165, 29);
		panel.add(txtComple);
		
		JLabel lblRA = new JLabel("RA:");
		lblRA.setBounds(16, 27, 30, 30);
		panel.add(lblRA);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 68, 41, 30);
		panel.add(lblNome);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 106, 41, 30);
		panel.add(lblEmail);
		
		JLabel lblCEP = new JLabel("CEP:");
		lblCEP.setBounds(213, 27, 30, 30);
		panel.add(lblCEP);
		
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(10, 147, 65, 30);
		panel.add(lblEndereco);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(12, 183, 45, 30);
		panel.add(lblBairro);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(16, 263, 45, 30);
		panel.add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(228, 263, 45, 30);
		panel.add(lblEstado);
		
		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(8, 221, 82, 30);
		panel.add(lblComplemento);
		
		JLabel lblNumero = new JLabel("N\u00BA :");
		lblNumero.setBounds(290, 221, 36, 30);
		panel.add(lblNumero);
		
		
		txtCEP.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				int cep = txtCEP.getText().length();
				if(cep == 8)
					pontaCep(txtCEP.getText());
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				int cep = txtCEP.getText().length();
				if(cep == 8)
					pontaCep(txtCEP.getText());
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				int cep = txtCEP.getText().length();
				if(cep == 8)
					pontaCep(txtCEP.getText());
				
			}
			
		});
		
		JButton btnInserir = new JButton("Incluir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					IncluirAluno();
					JOptionPane.showMessageDialog(btnInserir, "Aluno inserido com sucesso");
					
				}
				catch(Exception err)
				{
					System.err.println(err.getMessage());
				}
			}
			
		});
		btnInserir.setBounds(406, 22, 89, 23);
		panel.add(btnInserir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					excluirAluno();
					LimparCampos();
					JOptionPane.showMessageDialog(null, "Aluno excluído!!");
				}
				catch(Exception err)
				{
					System.err.println(err.getMessage());
				}
			}
		});
		btnExcluir.setBounds(406, 56, 89, 23);
		panel.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					alterarAluno(Integer.parseInt(txtRA.getText()));
					JOptionPane.showMessageDialog(null, "Alteração realizada");
				}
				catch(Exception err)
				{
					System.err.println(err.getMessage());
				}
			}
		});
		btnAlterar.setBounds(406, 90, 89, 23);
		panel.add(btnAlterar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Aluno aluno = null;
				
				try
				{
					if(!txtRA.getText().trim().equals("")) 
				     	aluno = procurarAluno(Integer.parseInt(txtRA.getText()));
				  
					else 
						aluno = procurarAluno(txtNome.getText());
					    
					exibirAlunos(aluno);
					txtRA.setEditable(false);
					btnInserir.setEnabled(false);
					
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null, "Aluno não cadastrado!");
				}
			}
		});
		btnConsultar.setBounds(406, 124, 89, 23);
		panel.add(btnConsultar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LimparCampos();
				btnInserir.setEnabled(true);
				txtRA.setEditable(true);
			}
		});
		btnLimpar.setBounds(405, 159, 90, 25);
		panel.add(btnLimpar);
		
	}
}