package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;

import java.awt.Toolkit;

import javax.swing.JFileChooser;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.Font;

public class Application {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	final JTextArea codeTextArea = new JTextArea();
	JTextArea resultsTextArea = new JTextArea();
	private File f;

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
		
		f = new File("");
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
		
		final JButton browserButton = new JButton("Browse...");
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		
		browserButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = fileChooser.showOpenDialog(frame );
				if (result == JFileChooser.APPROVE_OPTION) {
					f = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + f.getAbsolutePath());
				}else{
					f = new File("");
				}
			}
		});
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
					.addGap(19)
					.addComponent(generateButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_outputFilePane.setVerticalGroup(
			gl_outputFilePane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_outputFilePane.createSequentialGroup()
					.addContainerGap()
					.addComponent(generateButton, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
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
					.addContainerGap()
					.addComponent(analizeButton, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
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
							.addComponent(outputFilePane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(outputGraphicPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(insertFilePanel, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
							.addComponent(optionsPane, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
							.addComponent(outputFilePane, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		JScrollPane resultsScrollPane = new JScrollPane();
		resultsPanel.add(resultsScrollPane);
		
		
		resultsScrollPane.setViewportView(resultsTextArea);
		codePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane codeScrollPane = new JScrollPane();
		codePanel.add(codeScrollPane);
		codeTextArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		codeTextArea.setTabSize(4);
		
		
		codeTextArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				codeTextArea.selectAll();
			}
			@Override
			public void focusLost( FocusEvent f){
				//aqui vay que pasar un archivo con lo que se ingreso como codigo
				System.out.println("hola");
			}
		});
		codeScrollPane.setViewportView(codeTextArea);
		
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(f.getName().equals("")){
				try {
					PrintWriter out = new PrintWriter("input.txt");
					out.print(codeTextArea.getText());
					out.close();
					Controller.getModel().startAnalysis("input.txt");
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
				else{
					System.out.println("nombre del archivo: " + f.getName());
					Controller.getModel().startAnalysis(f.getAbsolutePath());
			}
			}
		});
		
		
		
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
