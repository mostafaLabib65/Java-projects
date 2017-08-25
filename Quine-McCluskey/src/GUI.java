

import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.tree.ExpandVetoException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Font;

public class GUI {

	private JFrame frame;
	 JPanel menu;
	 JPanel action;
	 JPanel generate;
	 JPanel Fin;
	 JPanel generateWrite;
	 JRadioButton rdbtnDontCare;
	 JFileChooser fc;
	 private static JTextField textField;
	 private JTextField dontCare;
	 HashMap<Integer, LinkedList<LinkedList>> list = new HashMap<Integer, LinkedList<LinkedList>>();
		Tabular check = new Tabular();
		private JTextField output;
		private JTextField mintermFileName;
		private JTextField dontCareFile;
		private JTextField leastCost;
		LinkedList<String> finall = new LinkedList<String>();
		/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 642, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel writtenMintermsResult = new JPanel();
		writtenMintermsResult.setVisible(false);
		
		JRadioButton rdbtnDontCare_1 = new JRadioButton("Don't Care");
		final JPanel generateWrite = new JPanel();
		generateWrite.setVisible(false);
		final JPanel chooseFileOrWriteMinterms = new JPanel();
		chooseFileOrWriteMinterms.setVisible(false);
		
		JPanel checkFileChoosed = new JPanel();
		checkFileChoosed.setVisible(false);
		checkFileChoosed.setBounds(12, 0, 618, 452);
		frame.getContentPane().add(checkFileChoosed);
		checkFileChoosed.setLayout(null);
		
		mintermFileName = new JTextField();
		mintermFileName.setSelectedTextColor(Color.RED);
		mintermFileName.setSelectionColor(Color.BLACK);
		mintermFileName.setFont(new Font("Dialog", Font.PLAIN, 15));
		mintermFileName.setForeground(Color.RED);
		mintermFileName.setCaretColor(Color.RED);
		mintermFileName.setEditable(false);
		mintermFileName.setEnabled(false);
		mintermFileName.setBounds(118, 34, 300, 32);
		checkFileChoosed.add(mintermFileName);
		mintermFileName.setColumns(10);
		
		JLabel lblFileName = new JLabel("Minterms file");
		lblFileName.setBounds(12, 39, 109, 23);
		checkFileChoosed.add(lblFileName);
		
		JButton btnGenerate_1 = new JButton("Generate");
		btnGenerate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 File file = new File(fc.getSelectedFile().getPath());
				 StringBuilder fileContents = new StringBuilder((int)file.length());
				 Scanner scanner = null;
					
				 try {
					 scanner = new Scanner(file);
					 String lineSeparator = System.getProperty("line.separator");

				     while(scanner.hasNextLine()) {
				         fileContents.append(scanner.nextLine() + lineSeparator);
				     }
				      fileContents.toString();
				 } 
				 catch(Exception e) {
				        scanner.close();
				    }
			}
		});
		btnGenerate_1.setBounds(12, 241, 117, 25);
		checkFileChoosed.add(btnGenerate_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkFileChoosed.setVisible(false);
				chooseFileOrWriteMinterms.setVisible(true);
			}
		});
		btnBack.setBounds(489, 241, 117, 25);
		checkFileChoosed.add(btnBack);
		
		JButton btnExit_4 = new JButton("Exit");
		btnExit_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit_4.setBounds(259, 241, 117, 25);
		checkFileChoosed.add(btnExit_4);
		
		JLabel lblDontCareFile = new JLabel("Don't care file");
		lblDontCareFile.setBounds(12, 98, 109, 15);
		checkFileChoosed.add(lblDontCareFile);
		
		dontCareFile = new JTextField();
		dontCareFile.setEditable(false);
		dontCareFile.setBounds(118, 96, 300, 32);
		checkFileChoosed.add(dontCareFile);
		dontCareFile.setColumns(10);
		
		JButton btnChooseFile = new JButton("Choose file");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filename = File.separator+"tmp";
				JFileChooser fc = new JFileChooser(new File(filename));
				int result = fc.showOpenDialog(btnChooseFile);
				File selFile = fc.getSelectedFile();
				if(result == JFileChooser.APPROVE_OPTION)
				{
					chooseFileOrWriteMinterms.setVisible(false);
					checkFileChoosed.setVisible(true);
					mintermFileName.setText(fc.getSelectedFile().getName());
				}
			}
		});
		btnChooseFile.setBounds(472, 38, 117, 25);
		checkFileChoosed.add(btnChooseFile);
		
		JButton btnChooseFile_1 = new JButton("Choose file");
		btnChooseFile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = File.separator+"tmp";
				JFileChooser fc = new JFileChooser(new File(filename));
				int result = fc.showOpenDialog(btnChooseFile_1);
				File selFile = fc.getSelectedFile();
				if(result == JFileChooser.APPROVE_OPTION)
				{
					chooseFileOrWriteMinterms.setVisible(false);
					checkFileChoosed.setVisible(true);
					dontCareFile.setText(fc.getSelectedFile().getName());
				}
			}
		});
		btnChooseFile_1.setBounds(472, 93, 117, 25);
		checkFileChoosed.add(btnChooseFile_1);
		final JPanel menu = new JPanel();
		menu.setBounds(12, 0, 618, 452);
		frame.getContentPane().add(menu);
		menu.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				chooseFileOrWriteMinterms.setVisible(true);
			}
		});
		btnStart.setBounds(207, 55, 178, 70);
		menu.add(btnStart);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(237, 242, 117, 25);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(btnExit);
		chooseFileOrWriteMinterms.setBounds(0, 0, 630, 452);
		frame.getContentPane().add(chooseFileOrWriteMinterms);
		chooseFileOrWriteMinterms.setLayout(null);
		
		JButton btnChooseAFile = new JButton("Choose a File");
		btnChooseAFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkFileChoosed.setVisible(true);
				chooseFileOrWriteMinterms.setVisible(false);
			}
		});
		btnChooseAFile.setBounds(176, 12, 221, 52);
		chooseFileOrWriteMinterms.add(btnChooseAFile);
		
		JButton btnWriteMinterms = new JButton("Write Minterms");
		btnWriteMinterms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFileOrWriteMinterms.setVisible(false);
				generateWrite.setVisible(true);
			}
		});
		btnWriteMinterms.setBounds(176, 151, 221, 52);
		chooseFileOrWriteMinterms.add(btnWriteMinterms);
		
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit_1.setBounds(235, 320, 117, 25);
		chooseFileOrWriteMinterms.add(btnExit_1);
		generateWrite.setBounds(0, 0, 630, 452);
		frame.getContentPane().add(generateWrite);
		generateWrite.setLayout(null);
		
		JLabel lblWriteMintermsSeperated = new JLabel("Write minterms seperated by space");
		lblWriteMintermsSeperated.setBounds(179, 12, 260, 32);
		generateWrite.add(lblWriteMintermsSeperated);
		
		textField = new JTextField();
		textField.setBounds(46, 79, 572, 32);
		generateWrite.add(textField);
		textField.setColumns(10);
		
		JLabel lblMinterms = new JLabel("minterms");
		lblMinterms.setBounds(24, 52, 70, 15);
		generateWrite.add(lblMinterms);
		
		dontCare = new JTextField();
		dontCare.setEnabled(false);
		dontCare.setBounds(46, 145, 572, 32);
		generateWrite.add(dontCare);
		dontCare.setColumns(10);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDontCare_1.isSelected() && dontCare.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "You didn't write any dontCares.");
					chooseFileOrWriteMinterms.setVisible(false);
					generateWrite.setVisible(true);
				}
				else{
					if(textField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(frame, "You didn't write any minterms.");
						chooseFileOrWriteMinterms.setVisible(false);
						generateWrite.setVisible(true);
					}
					else{
						try{
							int checker = 0;
							String s1 = new String();
							s1 = textField.getText();
							String s2 = new String();
							s2 = dontCare.getText();
							String[] s1A = s1.split("\\s+");
							String[] s2A = s2.split("\\s+");
							for(int i = 0; i < s1A.length; i++)
							{
								for(int j = 0; j < s2A.length; j++)
								{
									if(s1A[i].equals(s2A[j]))
									{
										checker++;
										break;
									}
								}
							}
							if(checker == 0)
							{
								String minterms = new String();
								StringBuilder input = new StringBuilder();
								input.append(textField.getText());
								if(rdbtnDontCare_1.isSelected())
								{
									input.append(" ");
									input.append(dontCare.getText());
								}
								minterms = input.toString();
								String[] splited = minterms.split("\\s+");
								int x;
								for(int i = 0; i < splited.length; i++)
								{
									 x =   Integer.parseInt(splited[i]);
									int y = check.numberOfOnes(x);
									check.addToList(y, x);
								}
								list = check.list;
								check.merge(check.generate(list));
								check.coverdByImplicant(check.result , splited) ;
								LinkedList<String> finall = new LinkedList<String>();
								finall = check.finalResult(check.result);
								for(int i = 0; i < finall.size(); i++)
								{
									output.setText(finall.get(i));
								}
								int y = 0;
								for(int i = 0; i < finall.size(); i++)
								{
									String s = finall.get(i);
									s.replaceAll("+", "");
									s.replaceAll("'", "");
									if(i == 0)
									{
										y = s.length();
									}
									else
									{
										if(s.length() < y)
										{
											y = s.length();
										}
									}
								}
								for(int i = 0; i < finall.size(); i++)
								{
									
								}
								writtenMintermsResult.setVisible(true);
								generateWrite.setVisible(false);
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Invalid input");
								generateWrite.setVisible(true);
								writtenMintermsResult.setVisible(false);
							}
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(frame, "Invalid input");
							generateWrite.setVisible(true);
							writtenMintermsResult.setVisible(false);
						}
					}
				}
			}
		});
		btnGenerate.setBounds(266, 213, 117, 25);
		generateWrite.add(btnGenerate);
		
		JButton btnExit_2 = new JButton("Back");
		btnExit_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateWrite.setVisible(false);
				chooseFileOrWriteMinterms.setVisible(true);
			}
		});
		btnExit_2.setBounds(266, 344, 117, 25);
		generateWrite.add(btnExit_2);
		
		
		rdbtnDontCare_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!rdbtnDontCare_1.isSelected())
					dontCare.setEnabled(false);
				else if(rdbtnDontCare_1.isSelected())
					dontCare.setEnabled(true);	
			}
		});
		rdbtnDontCare_1.setBounds(8, 114, 149, 23);
		generateWrite.add(rdbtnDontCare_1);
		writtenMintermsResult.setBounds(0, 0, 630, 452);
		frame.getContentPane().add(writtenMintermsResult);
		writtenMintermsResult.setLayout(null);
		
		output = new JTextField();
		output.setEditable(false);
		output.setBounds(12, 12, 606, 111);
		writtenMintermsResult.add(output);
		output.setColumns(10);
		
		JButton btnSaveToTxt = new JButton("save to txt file");
		btnSaveToTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    int retrival = chooser.showSaveDialog(null);
			    if (retrival == JFileChooser.APPROVE_OPTION) {
			    	Path path = Paths.get(chooser.getCurrentDirectory() + "/" + chooser.getSelectedFile().getName());
			    	try {
			    		Files.createDirectories(path.getParent());
			            Files.createFile(path);
			        } catch (FileAlreadyExistsException e) {
			            System.err.println("already exists: " + e.getMessage());
			        } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	try(FileWriter fw = new FileWriter(path.toString(), true);
			    		    BufferedWriter bw = new BufferedWriter(fw);
			    		    PrintWriter out = new PrintWriter(bw))
			    		{
			    		    out.println("the text");
			    		    //more code
			    		    out.println("more text");
			    		    //more code
			    		} catch (IOException e) {
			    			JOptionPane.showMessageDialog(frame, "Error");
			    		}
			    }
			}
		});
		btnSaveToTxt.setBounds(244, 382, 135, 25);
		writtenMintermsResult.add(btnSaveToTxt);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				writtenMintermsResult.setVisible(false);
				textField.setText(null);
				output.setText(null);
				dontCare.setText(null);
			}
		});
		btnNew.setBounds(36, 382, 117, 25);
		writtenMintermsResult.add(btnNew);
		
		JButton btnExit_3 = new JButton("Exit");
		btnExit_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit_3.setBounds(501, 382, 117, 25);
		writtenMintermsResult.add(btnExit_3);
		
		JLabel lblLeastCosted = new JLabel("Least costed");
		lblLeastCosted.setBounds(12, 152, 117, 15);
		writtenMintermsResult.add(lblLeastCosted);
		
		leastCost = new JTextField();
		leastCost.setEditable(false);
		leastCost.setBounds(132, 150, 486, 36);
		writtenMintermsResult.add(leastCost);
		leastCost.setColumns(10);
		
		JButton btnNewButton = new JButton("Show steps");
		btnNewButton.setBounds(244, 248, 135, 25);
		writtenMintermsResult.add(btnNewButton);
	}
}
