import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Panel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.ScrollPane;
import java.awt.Point;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.TextField;

public class UserInterface extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=144,119
	 */
	private final TextField textField1 = new TextField();
	/**
	 * @wbp.nonvisual location=154,119
	 */
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
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
	public UserInterface() {
		setTitle("Aplikasi Basket Market Analysis");
		setBackground(SystemColor.controlShadow);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane jScrollPane1 = new JScrollPane();
		
		JLabel label1 = new JLabel("APLIKASI BASKET MARKET ANALYSIS");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		
		JLabel label2 = new JLabel("Hasil Analisis :");
		label2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel label3 = new JLabel("Masukan File : ");
		label3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JButton btnPilihFile = new JButton("Pilih File");
		btnPilihFile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnPilihFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnFileActionPerfomed(evt);
            }
        });
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
						.addComponent(label2)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label3)
							.addGap(53)
							.addComponent(btnPilihFile, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label3)
						.addComponent(btnPilihFile))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(label2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
		);
		
		JTextArea textHasilAnalisis = new JTextArea();
		textHasilAnalisis.setRows(5);
		textHasilAnalisis.setColumns(20);
		jScrollPane1.setViewportView(textHasilAnalisis);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void btnFileActionPerfomed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		String filename = f.getAbsolutePath();
		textField1.setText(filename);
		
	}
	
	
}
