import java.util.LinkedList; 
import javax.swing.*;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FenetreAccueil extends Fenetres implements ActionListener, ListSelectionListener, MouseListener {
	private JFileChooser chooser;
	private FileNameExtensionFilter filter;
	private int returnVal;
	private JButton open;
	private JList<String> couleurs; 
	private JList <String> choixNiveau;
	private JLabel textCouleur;
	private JLabel textNiveau;
	private JLabel logo;
	private JLabel fondAccueil;
	private JLabel textPseudo;
	private JButton boutonJouer;
	private File selectedFile;
	protected String path="./image.jpg";
	public int niveauChoisi;
	public String pseudo;
	public String couleurChoisie;
	
	
	public FenetreAccueil(){
		this.fen=fen;
		this.setTitle("MyPuzzle - Accueil");
		this.setSize(800,650);
		this.setLocation(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textPseudo = new JLabel("Choisissez un pseudo");
		textPseudo.setBounds(85,75,150,25);
		
		textCouleur = new JLabel("Choisissez une couleur de fond");
		textCouleur.setBounds(75,150,220,50);
		
		
		logo= new JLabel(new ImageIcon("./logo.png"));
		logo.setBounds(-30,-250,getWidth(),getHeight());
		
		fondAccueil= new JLabel(new ImageIcon("./fondAccueil.png"));
		fondAccueil.setBounds(0,0,getWidth(),getHeight());
		
		
		textNiveau =  new JLabel("Choisissez le niveau de difficulte");
		textNiveau.setBounds(65,175,200,25);
		
		JButton open = new JButton("Selectionner un fichier de puzzle"); //nouveau bouton open
		open.setBounds(25,100,250,60);
		open.addMouseListener(this);
		
		JTextField monChampTexte = new JTextField ("");
		monChampTexte.setBounds(75,100,150,50);
		
		String[] data = {"Bleu","Jaune","Rouge","Vert","Blanc"};
		couleurs = new JList<String>(data);
		couleurs.setBounds(75,200,150,150);
		couleurs.addListSelectionListener(this);
		
		String[] data2 = {"1","2","3"};
		choixNiveau = new JList <String>(data2);
		choixNiveau.setBounds(75,200,150,80);
		choixNiveau.addListSelectionListener(this);
		
		boutonJouer= new JButton("Jouer");
		boutonJouer.setBounds(100,400,150,80);
		boutonJouer.addActionListener(this);
		
		JPanel Conteneur1= new JPanel();
		Conteneur1.setBounds(50,50,300,500);
		Conteneur1.setBackground(new Color(244, 161, 65));
		Conteneur1.setLayout(null);
		Conteneur1.add(textPseudo);
		Conteneur1.add(monChampTexte);
		Conteneur1.add(textCouleur);
		Conteneur1.add(couleurs);
		
		JPanel Conteneur2= new JPanel();
		Conteneur2.setBounds(400,50,300,500);
		Conteneur2.setBackground(new Color(244, 161, 65));
		Conteneur2.setLayout(null);
		Conteneur2.add(open);
		Conteneur2.add(choixNiveau);
		Conteneur2.add(textNiveau);
		Conteneur2.add(boutonJouer);
		
		JPanel ConteneurMain = new JPanel();
		ConteneurMain.setLayout(null);
		ConteneurMain.add(logo);
		ConteneurMain.add(Conteneur1);
		ConteneurMain.add(Conteneur2);
		ConteneurMain.add(fondAccueil);
		
		
		
		add(ConteneurMain);
		setVisible(true);
	}

		
	public void valueChanged(ListSelectionEvent evt) {
		if(evt.getSource()==couleurs){
			couleurChoisie=((String)couleurs.getSelectedValue());
			System.out.println(couleurChoisie);
		}
		if(evt.getSource()==choixNiveau){
			niveauChoisi=Integer.parseInt(choixNiveau.getSelectedValue());
			System.out.println(niveauChoisi);
			difficulte=niveauChoisi;
		}
	}
	
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==boutonJouer){
			pseudo=textPseudo.getText();
	        fen = new Fenetre(difficulte+2,path);
		    this.setVisible(false);
		    fen.setVisible(true);
		}
	}
	
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){
		JFileChooser chooser = new JFileChooser();	
		chooser.setApproveButtonText("Choix du fichier...");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("Vous avez choisi d'ouvrir: "+chooser.getSelectedFile().getName());
			selectedFile=chooser.getSelectedFile();
			this.path = "./"+selectedFile.getName();
		}
	}	
}
			
			
