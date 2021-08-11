package Interface;
import Arquivo.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.Choice;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Arvore.AVLTree;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

@SuppressWarnings({ "unused", "serial" })
public class Interface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private JTable table;
    private javax.swing.JFileChooser seletorDeArquivo;
    private ArrayList<AVLTree<String>> arvore = new ArrayList<AVLTree<String>>();
    private String[] colunas = null;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
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
	public Interface() {
		Arquivo arq = new Arquivo();
		setTitle("Estrutura de dados II");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 541);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(441, 64, 225, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Buscar Por:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setBounds(254, 69, 175, 14);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 18));
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(442, 120, 224, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Procurar Por:");
		lblNewLabel_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(254, 124, 176, 14);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 18));
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(426, 152, 91, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				table.setModel(model);
				String procura = textField.getText();
				int coluna = comboBox.getSelectedIndex();
				model = arvore.get(coluna).search(procura, model);
				table.setModel(model);
				table.selectAll();
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBounds(0, 0, 935, 21);
		menuBar.setBackground(Color.WHITE);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setBackground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Abrir Arquivo");
		mnNewMenu.add(mntmNewMenuItem);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Sair");
		mnNewMenu.add(mntmNewMenuItem_1);
        
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 179, 924, 335);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Coluna 1", "Coluna 2", "Coluna 3", "Coluna 4", "Coluna 5", "Coluna 6", "Coluna 7"
			}
		));
		scrollPane.setViewportView(table);
		
		mntmNewMenuItem_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
        	        System.exit(0);
            }
        });
		
		mntmNewMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @SuppressWarnings("unchecked")
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	comboBox.removeAllItems();
            	JFileChooser fs = new JFileChooser(new File("C:\\"));
            	fs.setDialogTitle("Abrir arquivo");
            	int result = fs.showOpenDialog(null);
    	        if(result == JFileChooser.APPROVE_OPTION){
    	            File file = fs.getSelectedFile();
    	            colunas = arq.gerarColunas(file);
    	            for (int i = 0; i < colunas.length; i++){
    	    			comboBox.addItem(colunas[i]);
    	            }
    	            DefaultTableModel model = new DefaultTableModel(colunas,0);
    	            table.setModel(model);
    	            arvore = arq.gerar(colunas.length);
    	            arvore = arq.ler(colunas.length, file);
    	        }
            }
        });
	}		
}