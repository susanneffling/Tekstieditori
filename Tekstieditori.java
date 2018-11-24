import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tekstieditori extends JFrame {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tekstieditori window = new Tekstieditori();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tekstieditori() {

		frame = new JFrame("Tekstieditori");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Tekstieditori.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Text-Color-Black.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TextArea textArea = new TextArea();
		textArea.setText("T\u00E4nne voi kirjoittaa");
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAvaa = new JMenu("Tiedosto");
		menuBar.add(mnAvaa);
		
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showOpenDialog(null);
				
				String rivi = "";
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				Scanner lukija = null;
					
				try {
					File tiedosto = new File(uusiTiedosto);
					lukija = new Scanner(tiedosto);
					
					while (lukija.hasNextLine()) {
						rivi += lukija.nextLine() + "\n";
						System.out.println(rivi);
					}
			}
				
				catch (FileNotFoundException p ) {
					System.out.println("Valitsemaasi tiedostoa ei löydy.");
				}
				
				textArea.setText(rivi);
				
				}
		});
		
		mntmAvaa.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnAvaa.add(mntmAvaa);
		
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);
				
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);
				try {
					
					PrintWriter writer = new PrintWriter(uusiTiedosto);
					String sisalto = textArea.getText();
					
					writer.println(sisalto);
					
					writer.flush();
					writer.close();
					
				} catch (Exception e1) {
					System.out.println("Tallennus ei onnistunut.");
					e1.printStackTrace();
}
				
			}
		});
		mntmTallenna.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnAvaa.add(mntmTallenna);
		
		JMenuItem mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnAvaa.add(mntmLopeta);
		
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mntmSulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmSulje.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mntmSulje.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnAvaa.add(mntmSulje);
		
		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
								
				String rivi = textArea.getText();
				rivi = rivi.toLowerCase();
				
				String haettava = "auto";
				int indeksi = rivi.indexOf(haettava);
				
				textArea.setSelectionStart(indeksi);
				textArea.setSelectionEnd(indeksi + haettava.length());
				System.out.println("Indeksi: " + indeksi);
				
			}
		});
		
		mntmEtsi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnMuokkaa.add(mntmEtsi);
		
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String korvaa = textArea.getText(); 
				String replaceString = korvaa.replaceAll("auto","kaara");
				textArea.setText(replaceString); 
			}
		});
		mnMuokkaa.add(mntmKorvaa);
		
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		JPanel tietoja = new JPanel();
		String text = 
		"Tekijä" +"\n" +
		"Susan Neffling" +"\n"+
		"Laurea-ammattikorkeakoulu" +"\n";
	    JTextArea teksti = new JTextArea(text);
	    tietoja.add(teksti);
		
		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mntmTietojaOhjelmasta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog
				(
					null, 
					tietoja, 
					"Tekijän tiedot : ",
					JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		mntmTietojaOhjelmasta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnTietoja.add(mntmTietojaOhjelmasta);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showOpenDialog(null);
				
				String rivi = "";
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				Scanner lukija = null;
					
				try {
					File tiedosto = new File(uusiTiedosto);
					lukija = new Scanner(tiedosto);
					
					while (lukija.hasNextLine()) {
						rivi += lukija.nextLine() + "\n";
						System.out.println(rivi);
					}
			}
				
				catch (FileNotFoundException p ) {
					System.out.println("Valitsemaasi tiedostoa ei löydy.");
				}
				
				textArea.setText(rivi);
			}
		});
		
		button.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		toolBar.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JFileChooser valintaikkuna = new JFileChooser();
					valintaikkuna.showSaveDialog(null);
					
					String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
					
					System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);
					try {
						
						PrintWriter writer = new PrintWriter(uusiTiedosto);
						String sisalto = textArea.getText();
						
						writer.println(sisalto);
						
						writer.flush();
						writer.close();
						
					} catch (Exception e1) {
						System.out.println("Tallennus ei onnistunut.");
						e1.printStackTrace();
	}
			}
		});
		
		button_1.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		toolBar.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Cut.png")));
		toolBar.add(button_2);
	}
}
