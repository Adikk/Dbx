package dropbox;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.dropbox.core.DbxException;

import sender.SenderService;
import skaner.Skaner;
import skaner.SkanerService;
import timer.Timer;

import java.awt.Color;
import java.awt.Font;

public class MainFrame extends JFrame{
		
	private static final long serialVersionUID = 1L;
	
	private JButton button1 = new JButton("Wybierz folder");
	private JButton button2 = new JButton("Zamknij program");
	
	private JTextArea textarea3 = new JTextArea ("");
	private JScrollPane panel1 = new JScrollPane(textarea3);
	
	private JLabel label1 = new JLabel("");
	private JLabel label2 = new JLabel("");
	private JLabel label3 = new JLabel("");
	
	private JFileChooser filechooser = new JFileChooser();
	
	
	private Skaner skaner = null;
	private Timer timer = null;

	public static void main(String[] args) throws IOException, DbxException{
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				ConfigLoader config = new ConfigLoader();
				config.odczytParam(Keys.plikKonfiguracyjny);
				
				Dropbox dropbox = new Dropbox(config.getToken());
				try {
					dropbox.polacz();
				} catch (IOException | DbxException e) {
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
					e.printStackTrace();
					System.exit(0);
				}
				
				SkanerService skanerService = new SkanerService(config.getSciezka());
				skanerService.inicjujSkaner();
				
				Timer timerService = new Timer();
				timerService.inicjujTimer();
				
				SenderService senderService = new SenderService(dropbox, config.getIloscWatkow(), skanerService.getSkaner(), timerService);
				senderService.rozpocznijWysylanie();
				
				MainFrame ex = new MainFrame(timerService);
				ex.setVisible(true);
				ex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}});
		
	}
	
	public void wystartujTimer(){
		Thread aktualizator = new Thread(){
			@Override
			public void run(){
				while(true){
					try {
						aktualizujStatystyki(timer.getStat());
						aktualizujStatystyki(timer.getSredniaIB(), timer.getSrednia(), timer.getRozmiarPl());
					
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		aktualizator.start();
	}
	
	public void aktualizujStatystyki(String stat){
		textarea3.setText(stat);
	}
	
	public void aktualizujStatystyki(double sredniaIB, double srednia, long rozmiarPlikow){
		
		System.out.println("£¹czna iloœæ bajtów wys³anych: " + rozmiarPlikow);
		System.out.println("Œrednia iloœæ bajtów na sekunde: " + sredniaIB);
		System.out.println("Œrednia iloœæ plików na sekunde: " + srednia);
		
		label1.setText("£¹czna iloœæ bajtów wys³anych: " + rozmiarPlikow);
		label2.setText("Œrednia iloœæ bajtów na sekunde: " + sredniaIB);
		label3.setText("Œrednia iloœæ plików na sekunde: " + srednia);
	}

	public MainFrame(Timer timer) {
		setBackground(Color.DARK_GRAY);
		
		setTitle("Dropbox app");
		setSize(570, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		button1.setBounds(25,381,150,40);
		button2.setBounds(375,381,150,40);
		
		panel1.setBounds(25,50,500,320);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		label1.setBounds(25,430,700,40);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label2.setBounds(25,470,700,40);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label3.setBounds(25,510,700,40);
		
		getContentPane().add(panel1);
		getContentPane().add(button1);
		getContentPane().add(button2);
		getContentPane().add(label1);
		getContentPane().add(label2);
		getContentPane().add(label3);
		
		JLabel lblWysanePliki = new JLabel("Wys³ane pliki :");
		lblWysanePliki.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWysanePliki.setBounds(25, 19, 139, 20);
		getContentPane().add(lblWysanePliki);
		
		filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
            	if(filechooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
        			File plik=filechooser.getSelectedFile();
        			if(plik.isDirectory()) skaner.ustawFolder(plik);
            	}
            }
		});
		
		button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	System.exit(0);
            }
		});

		this.timer = timer;
		
		wystartujTimer();
	}
}
