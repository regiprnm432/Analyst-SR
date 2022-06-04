package com.mba.analyst_sr.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.mba.analyst_sr.*;

import java.awt.SystemColor;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.JCheckBox;

/**
 * This class will be a driver for 
 * Market Basket Analysis program.
 * We will use Even driven programming paradigm
 * 
 * @author Regi Purnama
 * @author Sendi Setiawan
 *
 */
public class UserInterface extends JFrame {
	String[][] resultTable;
	JScrollPane scrollPane;
	private File file;

	private JPanel contentPane;
	private JTextField threshold;
	private JTable hasilAnalysis;
	private JTableHeader tHeader;
	private JTextField filePath;
	private JTextField txtThreshold;
	private JTextField confidence;
	private JTextField lift;
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
		setTitle("Market Basket Analysis");
		setBackground(SystemColor.controlShadow);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label1 = new JLabel("Analyst-SR");
		label1.setForeground(SystemColor.desktop);
		label1.setBackground(Color.WHITE);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 60));
		
		JButton btnPilihFile = new JButton("Choose File");
		btnPilihFile.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
		btnPilihFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnFileActionPerfomed(evt);
            }
        });
		

		
		threshold = new JTextField();
		threshold.setHorizontalAlignment(SwingConstants.CENTER);
		threshold.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
		threshold.setColumns(10);
		
		
		scrollPane = new JScrollPane();
		
		filePath = new JTextField();
		filePath.setEditable(false);
		filePath.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
		filePath.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));

		JButton startAnalyze = new JButton("Analyze");
		startAnalyze.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));

		resultTable = new String[0][8];
		tableConfiguration();
		
		txtThreshold = new JTextField();
		txtThreshold.setHorizontalAlignment(SwingConstants.CENTER);
		txtThreshold.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
		txtThreshold.setText("Threshold");
		txtThreshold.setEditable(false);
		txtThreshold.setBackground(UIManager.getColor("Button.shadow"));
		txtThreshold.setColumns(10);
		
		JCheckBox cBoxConfidence = new JCheckBox("Set Confidence");
		cBoxConfidence.setBackground(UIManager.getColor("Button.shadow"));
		cBoxConfidence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cBoxConfidence.isSelected()) {
					confidence.setEditable(true);
					confidence.setBackground(Color.WHITE);
				} else {
					confidence.setEditable(false);
					confidence.setBackground(UIManager.getColor("Button.disabledForeground"));
					confidence.setText("");
				}
			}
		});
		cBoxConfidence.setFont(new Font("Eras Medium ITC", Font.PLAIN, 14));
		
		confidence = new JTextField();
		confidence.setBackground(UIManager.getColor("Button.disabledForeground"));
		confidence.setEditable(false);
		confidence.setHorizontalAlignment(SwingConstants.CENTER);
		confidence.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
		confidence.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("%");
		lblNewLabel_1_1.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
		
		JCheckBox cBoxLift = new JCheckBox("Set Lift");
		cBoxLift.setBackground(UIManager.getColor("Button.shadow"));
		cBoxLift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cBoxLift.isSelected()) {
					lift.setEditable(true);
					lift.setBackground(Color.WHITE);
				} else {
					lift.setEditable(false);
					lift.setBackground(UIManager.getColor("Button.disabledForeground"));
					lift.setText("");
				}
			}
		});
		
		startAnalyze.addActionListener(new ActionListener() {

			/**
			 * This method will perform analysis from the read file
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				TransactionData.numberOfTransactions = 0;
				TransactionData.idReference.clear();
				
				ArrayList<Rules> analysisResult;

				MarketBasketAnalysis analysis = new MarketBasketAnalysis();

				analysis.setFile(file);
				analysis.processFile();
				analysis.makeInitialTree();
				analysis.countAllItemsSupport();
				Double thresholdInInt = (Double.parseDouble(threshold.getText())/100) * TransactionData.numberOfTransactions;
				analysis.setThreshold(thresholdInInt.intValue());
				
				analysisResult = analysis.getAnalysisResult();
				Collections.sort(analysisResult);
				resultTable = new String[analysisResult.size()][8];

				int count = 0;
				for(Rules rule : analysisResult) {
					if (cBoxConfidence.isSelected() == true && cBoxLift.isSelected() == true) {
						if(rule.getConfidence() < Double.parseDouble(confidence.getText())/100.0 || rule.getLift() < Double.parseDouble(lift.getText()))
							continue;

					} else {
						if (cBoxConfidence.isSelected() == true && rule.getConfidence() < Double.parseDouble(confidence.getText()) / 100.0) {
							continue;
						}
						if (cBoxLift.isSelected() == true && rule.getLift() < Double.parseDouble(lift.getText())) {
							continue;
						} 
					}
					resultTable[count] = rule.getResultInRowFormat();
					count++;
				}
				resultTable = Arrays.copyOf(resultTable, count);
				tableConfiguration();
			}
		});
		
		cBoxLift.setFont(new Font("Eras Medium ITC", Font.PLAIN, 14));
		
		lift = new JTextField();
		lift.setBackground(UIManager.getColor("Button.disabledForeground"));
		lift.setEditable(false);
		lift.setHorizontalAlignment(SwingConstants.CENTER);
		lift.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
		lift.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(435)
					.addComponent(label1, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
					.addGap(467))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(btnPilihFile)
					.addGap(6)
					.addComponent(filePath, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(txtThreshold, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(threshold, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(cBoxConfidence, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(confidence, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(cBoxLift, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lift, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 955, Short.MAX_VALUE)
					.addComponent(startAnalyze, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(label1)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPilihFile, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(filePath, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtThreshold, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(threshold, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1)))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(cBoxConfidence, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(confidence, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNewLabel_1_1)))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(cBoxLift, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lift, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(startAnalyze, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void btnFileActionPerfomed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		file = chooser.getSelectedFile();
		String filename = file.getAbsolutePath();
		filePath.setText(filename);
		
	}
	
	private void tableConfiguration() {
		String[] header = {"Antecedents", "Consequents", "Rules", "<html><center>Antecedents<br>Support</center></html>", "<html><center>Consequents<br>Support</center></html>", "<html><center>Rules<br>Support</center></html>", "Confidence", "Lift"};
		hasilAnalysis = new JTable(resultTable, header);
		tHeader = hasilAnalysis.getTableHeader();
		tHeader.setFont(new Font("Bahnscrifth", Font.BOLD, 16));
		hasilAnalysis.getTableHeader().setPreferredSize(new Dimension (scrollPane.getWidth(), 45));
		hasilAnalysis.setAutoCreateRowSorter(true);
		hasilAnalysis.setRowSelectionAllowed(false);
		hasilAnalysis.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		hasilAnalysis.setRowHeight(30);
		hasilAnalysis.setDragEnabled(false);
		DefaultTableCellRenderer rRenderer = new DefaultTableCellRenderer();
		rRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		hasilAnalysis.getColumnModel().getColumn(7).setCellRenderer(rRenderer);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		hasilAnalysis.getColumnModel().getColumn(6).setCellRenderer(renderer);
		hasilAnalysis.getColumnModel().getColumn(5).setCellRenderer(renderer);
		hasilAnalysis.getColumnModel().getColumn(4).setCellRenderer(renderer);
		hasilAnalysis.getColumnModel().getColumn(3).setCellRenderer(renderer);
		
		TableColumn column = null;
		column = hasilAnalysis.getColumnModel().getColumn(2);
		column.setPreferredWidth(300);
		
		for(int i = 0; i < 8; i++) {
			if(i == 2) 
				hasilAnalysis.getColumnModel().getColumn(i).setPreferredWidth(300);
			else if(i == 0 || i == 1) 
				hasilAnalysis.getColumnModel().getColumn(i).setPreferredWidth(180);
			else if(i == 3 || i == 4) 
				hasilAnalysis.getColumnModel().getColumn(i).setPreferredWidth(120);
			else if(i == 7)
				hasilAnalysis.getColumnModel().getColumn(i).setPreferredWidth(70);
			else
				hasilAnalysis.getColumnModel().getColumn(i).setPreferredWidth(100);

		}
		scrollPane.setViewportView(hasilAnalysis);	
	}
}