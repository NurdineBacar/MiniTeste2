
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Calculadora extends JFrame {

	private JPanel contentPane;
	private String opera="Indefinido";
	private double result;
	private double resultado;
	private JTextField txtSentece;
	private JComboBox cbOperacao;
	private JTextPane txtV2;
	private JTextField txtExpV1;
	private JTextField txtExpV2;
	private JLabel lblResultado;
	private ObjectoCalculadora [] historico= new ObjectoCalculadora[10];
	private JComboBox cbHistorico;

	public Calculadora() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel painel1 = new JPanel();
		painel1.setBorder(new TitledBorder(null, "Dados Inputs", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		painel1.setBackground(SystemColor.desktop);
		painel1.setBounds(0, 11, 355, 237);
		contentPane.add(painel1);
		painel1.setLayout(null);
		
		JTextPane txtV1 = new JTextPane();
		txtV1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				txtSentece.setText(txtV1.getText()+cbOperacao.getSelectedItem()+txtV2.getText());
			}
		});
		txtV1.setToolTipText("Introduza a variavel 1");
		txtV1.setBounds(83, 54, 155, 20);
		painel1.add(txtV1);
		
		JLabel variavel1 = new JLabel("Variavel 1");
		variavel1.setForeground(new Color(255, 255, 255));
		variavel1.setBounds(19, 57, 76, 14);
		painel1.add(variavel1);
		
		JLabel variavel2 = new JLabel("Variavel 2");
		variavel2.setForeground(Color.WHITE);
		variavel2.setBounds(18, 96, 76, 14);
		painel1.add(variavel2);
		
		txtV2 = new JTextPane();
		txtV2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				txtSentece.setText(txtV1.getText()+cbOperacao.getSelectedItem()+txtV2.getText());
				opera= cbOperacao.getSelectedItem().toString();
			}
		});
		txtV2.setBounds(83, 93, 155, 20);
		painel1.add(txtV2);
		
		JLabel lblOperacao = new JLabel("Operacao");
		lblOperacao.setForeground(Color.WHITE);
		lblOperacao.setBounds(19, 131, 76, 14);
		painel1.add(lblOperacao);
		
		cbOperacao = new JComboBox();
		cbOperacao.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				txtSentece.setText(txtV1.getText()+cbOperacao.getSelectedItem()+txtV2.getText());
				if(cbOperacao.getSelectedItem().toString().equals("!")) {
					txtV2.setEnabled(false);
					txtExpV2.setEnabled(false);
					txtV2.setText("");
					txtExpV2.setText("");
				}else {
					txtV2.setEnabled(true);
					txtExpV1.setEnabled(true);
					txtExpV2.setEnabled(true);
					txtExpV2.setText("1");
				}
				opera=cbOperacao.getSelectedItem().toString();
			}
		});
		cbOperacao.setModel(new DefaultComboBoxModel(new String[] {"----Selecione---", "+", "-", "*", "/", "!", "%", "|"}));
		cbOperacao.setBounds(84, 128, 115, 22);
		painel1.add(cbOperacao);
		
		txtSentece = new JTextField();
		txtSentece.setEditable(false);
		txtSentece.setBounds(26, 165, 293, 20);
		painel1.add(txtSentece);
		txtSentece.setColumns(10);
		
		JLabel expoente1 = new JLabel("Exp. 1");
		expoente1.setForeground(Color.WHITE);
		expoente1.setBounds(245, 56, 40, 14);
		painel1.add(expoente1);
		
		txtExpV1 = new JTextField();
		txtExpV1.setText("1");
		txtExpV1.setBounds(285, 54, 40, 20);
		painel1.add(txtExpV1);
		txtExpV1.setColumns(10);
		
		JLabel expoente = new JLabel("Exp. 2");
		expoente.setForeground(Color.WHITE);
		expoente.setBounds(246, 96, 39, 14);
		painel1.add(expoente);
		
		txtExpV2 = new JTextField();
		txtExpV2.setText("1");
		txtExpV2.setColumns(10);
		txtExpV2.setBounds(283, 90, 40, 20);
		painel1.add(txtExpV2);
		
		JLabel lblHistorico = new JLabel("Historico");
		lblHistorico.setForeground(Color.WHITE);
		lblHistorico.setBounds(19, 201, 76, 14);
		painel1.add(lblHistorico);
		
		cbHistorico = new JComboBox();
		cbHistorico.setModel(new DefaultComboBoxModel(historico));
		cbHistorico.setBounds(82, 196, 180, 22);
		painel1.add(cbHistorico);
		
		JPanel painel2 = new JPanel();
		painel2.setBackground(new Color(255, 255, 255));
		painel2.setBounds(357, 11, 217, 237);
		contentPane.add(painel2);
		painel2.setLayout(null);
		
		JButton btnExecutar = new JButton("EXECUTAR");
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
							
					int exp1=Integer.parseInt(txtExpV1.getText());
					int exp2=Integer.parseInt(txtExpV2.getText());
					double v1=Math.pow(Double.parseDouble(txtV1.getText()), exp1) ;
					double v2= Math.pow(Double.parseDouble(txtV2.getText()), exp2);
					
					int inteiroV1= Integer.parseInt(txtV1.getText());
					
					switch(cbOperacao.getSelectedItem().toString()) {
					case "!": 
						  System.out.println("Hello motherfucker");
						  resultado=v1;
						  while(v1>1) {
								resultado=resultado*(v1-1); 
								
								v1--;
							 }
						  break;
					case "+": resultado=v1+v2; break;
					case "-": resultado=v1-v2; break;
					case "*": resultado= v1*v2;break;
					case "/": if(v2==0) {
						int s1= JOptionPane.showConfirmDialog(null, "Tens certeza que e isso que vc quer?");
						if(s1==0) {
						int s2=	JOptionPane.showConfirmDialog(null, "De verdade?");
						if(s2==0) {
							JOptionPane.showMessageDialog(null, "Isso nao existe seu cabrao");
						}
						}
					}else {
						resultado=v1/v2;
					}
					break;
					case "%": resultado=v1%v2; break;
					case "|": if(v2!=0) {
						int divisao=(int)(v1/v2);
						resultado=divisao;
					}else {
						JOptionPane.showMessageDialog(btnExecutar, "Operacao impossivel");
					}
					}
					
					for(int i=0;i<historico.length;i++) {
						if(historico[i]==null && i<10) {
							historico[i]=new ObjectoCalculadora(v1, opera, v2, resultado);
							break;
						}
					}
					
				} catch (NumberFormatException e2) {
					int exp1=Integer.parseInt(txtExpV1.getText());
					int inteiroV1= (int) Math.pow(Integer.parseInt(txtV1.getText()), exp1);
					int valor=inteiroV1;
					 resultado= inteiroV1;
						
					 while(inteiroV1>1) {
						resultado=resultado*(inteiroV1-1); 
						
						inteiroV1--;
					 }
					 
					 for(int i=0;i<historico.length;i++) {
							if(historico[i]==null && i<10) {
								historico[i]=new ObjectoCalculadora(valor, opera, 0, resultado);
								break;
							}
						}
				}catch (NullPointerException e2) {
					// TODO: handle exception
				}catch (Exception e2) {
					// TODO: handle exception
				}
				
				lblResultado.setText(resultado+"");
				cbHistorico.setModel(new DefaultComboBoxModel(historico));
			}
		});
		btnExecutar.setForeground(new Color(255, 255, 255));
		btnExecutar.setBackground(new Color(0, 255, 64));
		btnExecutar.setBounds(22, 50, 171, 29);
		painel2.add(btnExecutar);
		
		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.setForeground(new Color(255, 255, 255));
		btnLimpar.setBackground(new Color(0, 255, 0));
		btnLimpar.setBounds(22, 106, 171, 29);
		painel2.add(btnLimpar);
		
		JPanel painel3 = new JPanel();
		painel3.setBounds(0, 248, 574, 152);
		contentPane.add(painel3);
		painel3.setLayout(null);
		
		JLabel lbllResultado = new JLabel("Resultado: ");
		lbllResultado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbllResultado.setBounds(10, 11, 118, 27);
		painel3.add(lbllResultado);
		
		lblResultado = new JLabel("...............................");
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(107, 20, 385, 101);
		painel3.add(lblResultado);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new Calculadora();
	}
}
