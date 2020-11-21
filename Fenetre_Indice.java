

import java.util.LinkedList; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Dimension;
import java.math.*;

public class Fenetre_Indice extends Fenetres implements ActionListener{
	public JButton jb1;
	public JButton jb2;
	public JButton jb3;
	public JButton jb4;
	public JFrame fenetre;
	private JLabel fondAccueil1;
	private JLabel fondAccueil2;
	private int CompteurPoints=0;
	CardLayout cl = new CardLayout();
	JPanel content = new JPanel();
	//Liste des noms de nos conteneurs pour la pile de cartes
	String[] listContent = {"CARD_1", "CARD_2"};
	
	
	
	
	public Fenetre_Indice(){
	 /*Fenêtre */

	System.out.println(CompteurPoints);
	JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame fenetre = new JFrame("TEST");
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fenetre.setSize(800,650);
	fenetre.setLocation(300,200);
	fenetre.setVisible(true);

	//On définit les boutons de la fenetre
	JButton jb1 = new JButton("Button 1");
	jb1.setPreferredSize(new Dimension (300,100));
    JButton jb2 = new JButton("Button 2");
    jb2.setPreferredSize(new Dimension (300,100));
	JButton jb3 = new JButton("Button 3");
	jb3.setPreferredSize(new Dimension (300,100));
    JButton jb4 = new JButton("Button 4");
    jb4.setPreferredSize(new Dimension (300,100));
	JButton jb5 = new JButton("Retour au jeu");
    jb5.setBounds(10,5,150,50);
    jb5.addActionListener(this);
    JButton voirindice= new JButton("VOIR L'INDICE");
    voirindice.setBounds(600,260,150,50);
    voirindice.setVisible(false);
	JLabel gagne = new JLabel("Bravo tu as gagn\u00e9");
    gagne.setBounds(600,220,150,50);
    gagne.setVisible(false);
    JLabel perdu = new JLabel("<html><center>D\u00e9sol\u00e9 tu n'as pas</center><br><center>r\u00e9pondu correctement,</center><br><center>retente ta chance plus tard</center>");
    perdu.setBounds(600,130,200,200);
    perdu.setVisible(false);
    
    
    //On crée une liste des 4 boutons réponse afin de placer aléatoirement les JLabels
    LinkedList<JButton> bt = new LinkedList<JButton>();
    bt.add(jb1);
    bt.add(jb2);
    bt.add(jb3);
    bt.add(jb4);    
    
    
    //On crée deux conteneurs de couleur différente (un pour chaque fenetre)
    fondAccueil1 = new JLabel(new ImageIcon("./imageINSA/fondAccueil1.png"));
    fondAccueil1.setBounds(0,0,800,650);
    fondAccueil2 = new JLabel(new ImageIcon("./imageINSA/fondAccueil.png"));
    fondAccueil2.setBounds(0,0,800,650);
    
    //Creation est affichage de la première fenetre, affichage des regles du mini jeu et du bouton pour commencer le jeu
    JPanel card1 = new JPanel();
    card1.setLayout(new BorderLayout());
    JButton bouton = new JButton("Commencer le jeu");
    card1.add(bouton,BorderLayout.PAGE_END);
    JPanel Regles = new JPanel();
	Regles.setBounds(75,100,650,300);
	Regles.setBackground(new Color(0f,0f,0f,.15f ));
	Regles.setLayout(new FlowLayout());
    Regles.setVisible(true);
    JLabel label2 = new JLabel("<html><center>Gagner des indices</center><br><br>Dans la fen\u00eatre suivante une image va appara\u00eetre<br>Vous devrez donner la sp\u00e9cialit\u00e9 de la personne<br> afin de gagner un indice. <br><br> Bonne Chance !</html>");
    Font font1 = new Font("Arial",Font.BOLD,20);
	label2.setFont(font1);
	Regles.add(label2);
    card1.add(Regles,BorderLayout.CENTER);
    card1.add(fondAccueil1);
    
    
    //On définit la base de donnée du jeu avec ses attributs necessaires pour l'affichage des questions et des réponses
	Image_Indice image0 = new Image_Indice(0,"SOPHIE GERMAIN","MATHEMATICIENNE");
    Image_Indice image1 = new Image_Indice(1,"BLAISE PASCAL","PHILOSOPHE");
    Image_Indice image2 = new Image_Indice(2,"CHARLES-AUGUSTIN COULOMB","OFFICIER ET PHYSICIEN");
    Image_Indice image3 = new Image_Indice(3,"GASTON BERGER","HAUT FONCTIONNAIRE");
    
    //On cree la liste  de ces Images_Indice
	LinkedList<Image_Indice> ret = new LinkedList<Image_Indice>();
	JLabel Image_Indice0 = new JLabel(new ImageIcon("./imageINSA/img0.png"));
    JLabel Image_Indice1 = new JLabel(new ImageIcon("./imageINSA/img1.png"));
    JLabel Image_Indice2 = new JLabel(new ImageIcon("./imageINSA/img2.png"));
    JLabel Image_Indice3 = new JLabel(new ImageIcon("./imageINSA/img3.png"));
	ret.add(image0);
	ret.add(image1);
	ret.add(image2);
	ret.add(image3);
	image0.add(Image_Indice0);
    image1.add(Image_Indice1);
	image2.add(Image_Indice2);
	image3.add(Image_Indice3);

		//Creation des Jpanels qui vont remplir la deuxième fenetre
		Image_Indice Conteneur_Centre = PanAleatoire(ret);
		SpecialiteButton(bt,Conteneur_Centre);
		AutreSpecialiteButton(bt,Conteneur_Centre,ret);
	
		Conteneur_Centre.setBounds(265,30,270,300);
		Conteneur_Centre.setBackground(new Color(244,161,65));
		Conteneur_Centre.setLayout(new BoxLayout(Conteneur_Centre,BoxLayout.Y_AXIS));
        Conteneur_Centre.setVisible(true);
		
		JPanel Conteneur_Milieu = new JPanel();
		Conteneur_Milieu.setBounds(75,360,650,40);
		Conteneur_Milieu.setBackground(new Color(0f,0f,0f,.15f ));
		Conteneur_Milieu.setLayout(new FlowLayout());
        Conteneur_Milieu.setVisible(true);
        JLabel label1 = new JLabel("Quelle \u00e9tait la sp\u00e9cialit\u00e9 de "+Conteneur_Centre.nom_Image+" ?");
        Font font = new Font("Arial",Font.BOLD,22);
		label1.setFont(font);
		Conteneur_Milieu.add(label1);
		
		JPanel Conteneur_Bas = new JPanel();
		Conteneur_Bas.setBounds(75,400,650,215);
		Conteneur_Bas.setBackground(new Color(1f,1f,1f,.3f));
		Conteneur_Bas.setLayout(new FlowLayout());
		Conteneur_Bas.add(jb1);
		Conteneur_Bas.add(jb2);
		Conteneur_Bas.add(jb3);
		Conteneur_Bas.add(jb4);
        Conteneur_Bas.setVisible(true);
		
		
		JPanel card2 = new JPanel();
		card2.setLayout(null);
		card2.add(Conteneur_Centre);
		card2.add(Conteneur_Milieu);
		card2.add(Conteneur_Bas);
		card2.add(jb5);
		card2.add(voirindice);
		card2.add(perdu);
		card2.add(gagne);
		card2.add(fondAccueil2);
		
	
	
    jb1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			if(jb1.getText()==Conteneur_Centre.specialite_Image){
			//Via cette instruction, on change la couleur du bouton
			jb1.setOpaque(true);
			jb1.setBackground(Color.GREEN);
			CompteurPoints++;
			voirindice.setVisible(true);
			gagne.setVisible(true);
			
			PtBonus=PtBonus+1;
			infoBonus.setText("Points bonus : "+PtBonus);
			System.out.println(PtBonus);
			
			}
			else{
			jb1.setOpaque(true);
			jb1.setBackground(Color.RED);
			perdu.setVisible(true);
			setVisible(false);
			}
			}
	});
	
    jb2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			if(jb2.getText()==Conteneur_Centre.specialite_Image){
			//Via cette instruction, on change la couleur du bouton
			jb2.setOpaque(true);
			jb2.setBackground(Color.GREEN);
			CompteurPoints++;
			voirindice.setVisible(true);
			gagne.setVisible(true);
			
			PtBonus=PtBonus+1;
			infoBonus.setText("Points bonus : "+PtBonus);
			System.out.println(PtBonus);
			}
			else{
			jb2.setOpaque(true);
			jb2.setBackground(Color.RED);
			perdu.setVisible(true);
			setVisible(false);
			}
			}
    });
    jb3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			if(jb3.getText()==Conteneur_Centre.specialite_Image){
			//Via cette instruction, on change la couleur du bouton
			jb3.setOpaque(true);
			jb3.setBackground(Color.GREEN);
			CompteurPoints++;
			voirindice.setVisible(true);
			gagne.setVisible(true);
			
			PtBonus=PtBonus+1;
			
			System.out.println(PtBonus);
			}
			else{
			jb3.setOpaque(true);
			jb3.setBackground(Color.RED);
			perdu.setVisible(true);
			setVisible(false);
			}
			}
    });
    jb4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			if(jb4.getText()==Conteneur_Centre.specialite_Image){
			//Via cette instruction, on change la couleur du bouton
			jb4.setOpaque(true);
			jb4.setBackground(Color.GREEN);
			CompteurPoints++;
			voirindice.setVisible(true);
			gagne.setVisible(true);
			temps=temps-10;
			PtBonus=PtBonus+1;
			infoBonus.setText("Points bonus : "+PtBonus);
			System.out.println(PtBonus);
			}
			else{
			jb4.setOpaque(true);
			jb4.setBackground(Color.RED);
			perdu.setVisible(true);
			setVisible(false);
			}
			}
    });
    
    
	
    voirindice.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			}
    });
	
    
    
    
    
    
    //Définition de l'action du bouton
    bouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        //Via cette instruction, on passe au prochain conteneur de la pile
        cl.next(content);
      }
    });
   

    //On définit le layout
    content.setLayout(cl);
    //On ajoute les cartes à la pile avec un nom pour les retrouver
    content.add(card1, listContent[0]);
	content.add(card2, listContent[1]);
    fenetre.add(content);
    fenetre.setVisible(true);
	fenetre.setVisible(true);
	
}	
	
	
	
    
		
 

public Image_Indice PanAleatoire (LinkedList<Image_Indice> img){
	Image_Indice ret = new Image_Indice();
	int a = (int) (Math.random()*(img.size()));
	ret=img.get(a);
	return ret;
}
public void SpecialiteButton(LinkedList<JButton> bt,Image_Indice t){
	int a = (int) (Math.random()*4);
	bt.get(a).setText(t.specialite_Image);
	 }
	 
public void AutreSpecialiteButton(LinkedList<JButton> bt,Image_Indice t, LinkedList<Image_Indice> img){
	
	for (int i=0; i<4; i++){
		if(bt.get(i).getText()!= t.specialite_Image){
			int a = (int) (Math.random()*(img.size()));
			while( img.get(a).specialite_Image == t.specialite_Image || bt.get(0).getText()==img.get(a).specialite_Image || bt.get(1).getText()==img.get(a).specialite_Image || 
				bt.get(2).getText()==img.get(a).specialite_Image || bt.get(3).getText()==img.get(a).specialite_Image){
				a = (int) (Math.random()*(img.size()));
			}
		bt.get(i).setText(img.get(a).specialite_Image);
		}	
	 }
}

public void actionPerformed(ActionEvent e){
		this.setVisible(false); // Fenêtre bizarre donc la fenêtre ne se ferme pas vraiment mais le jeu est jouable sur l'autre fenetre
		}
}
	

