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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.dropbox.core.DbxException;

import sender.SenderService;
import skaner.Skaner;
import skaner.SkanerService;
import timer.TimerService;

import java.awt.Color;
import java.awt.Font;

public class MainFrame extends JFrame{
	
	
	JButton b1 = new JButton("Wybierz folder");
	JButton b2 = new JButton("Zamknij program");
	
	JTextArea t3 = new JTextArea ("");
	JScrollPane p1 = new JScrollPane(t3);
	
	JLabel l1 = new JLabel("");
	JLabel l2 = new JLabel("");
	JLabel l3 = new JLabel("");
	
	int iloscWatkow=0;
	String sciezka;
	String stat="";
	JFileChooser fc = new JFileChooser();
	boolean bool=true;
	int sekundy = 0;
	int iloscPl = 0;
	long rozmiarPl = 0;
	
	
	Skaner skaner = null;

	public static void main(String[] args) throws IOException, DbxException{
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame ex = new MainFrame();
				ex.setVisible(true);
				ex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
				Keys keys = new Keys();
				
				ConfigLoader config = new ConfigLoader();
				try {
					config.odczytParam(keys.getPlikKonfiguracyjny());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Dropbox dropbox = new Dropbox(config.getToken());
				try {
					dropbox.polacz();
				} catch (IOException | DbxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				SkanerService skanerService = new SkanerService(config.sciezka);
				skanerService.inicjujSkaner();
				
				TimerService timerService = new TimerService(ex);
				timerService.inicjujTimer();
				
				SenderService senderService = new SenderService(dropbox.client, config.iloscWatkow, skanerService.getSkaner(), timerService.getTimer());
				senderService.rozpocznijWysylanie();
			}});
		
	}
	
	public void aktualizujStatystyki(String staty){
		t3.setText(staty);
	}
	
	public void aktualizujStatystyki(String sredniaIB, String srednia){
		l1.setText("£¹czna iloœæ bajtów wys³anych: " + rozmiarPl);
		l2.setText("Œrednia iloœæ bajtów na sekunde: " + sredniaIB);
		l3.setText("Œrednia iloœæ plików na sekunde: " + srednia);
	}

	public MainFrame() {
		setBackground(Color.DARK_GRAY);
		
		setTitle("Dropbox app");
		setSize(570, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		b1.setBounds(25,381,150,40);
		b2.setBounds(375,381,150,40);
		
		p1.setBounds(25,50,500,320);
		l1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		l1.setBounds(25,430,700,40);
		l2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		l2.setBounds(25,470,700,40);
		l3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		l3.setBounds(25,510,700,40);
		
		getContentPane().add(p1);
		getContentPane().add(b1);
		getContentPane().add(b2);
		getContentPane().add(l1);
		getContentPane().add(l2);
		getContentPane().add(l3);
		
		JLabel lblWysanePliki = new JLabel("Wys³ane pliki :");
		lblWysanePliki.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWysanePliki.setBounds(25, 19, 139, 20);
		getContentPane().add(lblWysanePliki);
		
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
            	if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
        			File plik=fc.getSelectedFile();
        			if(plik.isDirectory()) skaner.ustawFolder(plik);
            	}
            }
		});
		
		b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	System.exit(0);
            }
		});

	}
}