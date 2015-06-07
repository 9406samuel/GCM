package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Toolkit;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ButtonGroup;

import Controller.Controller;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;

public class Application {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public Application() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 711, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel optionsPane = new JPanel();
		optionsPane.setBorder(new TitledBorder(null, "Options:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel codePanel = new JPanel();
		codePanel.setBorder(new TitledBorder(null, "Code:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel insertFilePanel = new JPanel();
		insertFilePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Insert file:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton browserButton = new JButton("Browse...");
		GroupLayout gl_insertFilePanel = new GroupLayout(insertFilePanel);
		gl_insertFilePanel.setHorizontalGroup(
			gl_insertFilePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_insertFilePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(browserButton)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_insertFilePanel.setVerticalGroup(
			gl_insertFilePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_insertFilePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(browserButton)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		insertFilePanel.setLayout(gl_insertFilePanel);
		
		JPanel outputFilePane = new JPanel();
		outputFilePane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Output file:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton generateButton = new JButton("Generate");
		GroupLayout gl_outputFilePane = new GroupLayout(outputFilePane);
		gl_outputFilePane.setHorizontalGroup(
			gl_outputFilePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_outputFilePane.createSequentialGroup()
					.addGap(20)
					.addComponent(generateButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_outputFilePane.setVerticalGroup(
			gl_outputFilePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_outputFilePane.createSequentialGroup()
					.addComponent(generateButton, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addContainerGap())
		);
		outputFilePane.setLayout(gl_outputFilePane);
		
		JPanel outputGraphicPanel = new JPanel();
		outputGraphicPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Output graphic:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton analizeButton = new JButton("Analize");
		GroupLayout gl_outputGraphicPanel = new GroupLayout(outputGraphicPanel);
		gl_outputGraphicPanel.setHorizontalGroup(
			gl_outputGraphicPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_outputGraphicPanel.createSequentialGroup()
					.addGap(18)
					.addComponent(analizeButton, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(20))
		);
		gl_outputGraphicPanel.setVerticalGroup(
			gl_outputGraphicPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_outputGraphicPanel.createSequentialGroup()
					.addComponent(analizeButton, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addContainerGap())
		);
		outputGraphicPanel.setLayout(gl_outputGraphicPanel);
		
		JPanel resultsPanel = new JPanel();
		resultsPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Results:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		resultsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(insertFilePanel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(optionsPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(codePanel, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(outputGraphicPanel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(outputFilePane, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
						.addComponent(resultsPanel, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(codePanel, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(resultsPanel, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(insertFilePanel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addComponent(optionsPane, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addComponent(outputFilePane, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addComponent(outputGraphicPanel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JScrollPane resultsScrollPane = new JScrollPane();
		resultsPanel.add(resultsScrollPane);
		
		JTextArea resultsTextArea = new JTextArea();
		resultsScrollPane.setViewportView(resultsTextArea);
		codePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane codeScrollPane = new JScrollPane();
		codePanel.add(codeScrollPane);
		
		JTextArea codePane = new JTextArea();
		codeScrollPane.setViewportView(codePane);
		
		JRadioButton jpegFormat = new JRadioButton("JPEG");
		buttonGroup.add(jpegFormat);
		
		JRadioButton pngFormat = new JRadioButton("PNG");
		buttonGroup.add(pngFormat);
		
		JRadioButton pdfFormat = new JRadioButton("PDF");
		buttonGroup.add(pdfFormat);
		GroupLayout gl_optionsPane = new GroupLayout(optionsPane);
		gl_optionsPane.setHorizontalGroup(
			gl_optionsPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_optionsPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpegFormat)
					.addGap(54)
					.addComponent(pngFormat)
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addComponent(pdfFormat)
					.addGap(19))
		);
		gl_optionsPane.setVerticalGroup(
			gl_optionsPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optionsPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_optionsPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(pdfFormat)
						.addComponent(jpegFormat)
						.addComponent(pngFormat))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		optionsPane.setLayout(gl_optionsPane);
		frame.getContentPane().setLayout(groupLayout);
	}
}
