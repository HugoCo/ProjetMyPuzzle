import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.Color;

public class Fenetre extends Fenetres implements MouseListener, ActionListener {
	Timer tpsIndice = new Timer(3000,this);
	int taille;
	String path="./image.jpg";
	ImageIcon image ;
	int w ;
	int h ;
	Piece [][] Vide;
	Piece [][] t ;
	Piece [][] alea;
	JButton RemettreZero = new JButton("Recommencer");
	JButton Indice = new JButton("Piece bonne");
	JLabel textBonus = new JLabel("Bonus :");
	JLabel textTools = new JLabel("Outils de jeu :");
	JButton IndicePhoto = new JButton("Voir l'image (3 sec)");
	JButton Verification = new JButton("Verifier!");
	JPanel PanMelange = new JPanel();
	JPanel PanVide = new JPanel();
	JPanel PanIndicePhoto = new JPanel();
	JLabel ImagePhoto ;
	JLabel perdu = new JLabel();
	
	public JButton boutonIndice;
	public JLabel time;
	int a1=0;
	int b1=0;
	int a2=0;
	int b2=0;
	
	
	public Fenetre(int dif, String chemin){
		
		perdu.setBounds(1300,600,400,200);
		perdu.setText("Le puzzle n'est pas correctement dispos\u00e9 !");
		add(perdu);
		perdu.setVisible(false);
		this.path=chemin;
		image = new ImageIcon(path);
		ImagePhoto =  new JLabel(new ImageIcon(path));
		w= image.getIconWidth();
		h= image.getIconHeight();
		System.out.println(this.path);
	    this.taille=dif;
	    
	    
		/*Fenêtre */
		setTitle("MyPuzzle - Jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(0,0,1800,1000);
		setBackground(new Color(213, 151, 90));	
		
		
		initialise();
		/*Boutons*/
		RemettreZero.setBounds(0,200,150,120);
		Indice.setBounds(0,30,150,120);
		Verification.setBounds(170,200,150,120);
		IndicePhoto.setBounds(170,30,150,120);
		
		textBonus.setBounds(0,0,200,25);
		textTools.setBounds(0,170,200,25);
		
		boutonIndice= new JButton("Indices");
		boutonIndice.setBounds(1500,50,150,80);
		boutonIndice.addActionListener(this);
		add(boutonIndice);
		
		time = new JLabel(""+temps);
		time.setBounds(1500,400,100,100);
		time.setBackground(new Color(213, 151, 90));
		add(time);
		/* Panneaux*/
		
		
		PanMelange.setLayout(null);
		PanMelange.setBounds(0,800-h,800,800);
		AddTabJPanel(alea,PanMelange,w,h,taille);
		PanMelange.setBackground(new Color(213, 151, 90));
		add(PanMelange);
	
		PanVide.setLayout(null);
		PanVide.setBounds(0,0,800,800);
		PanVide.setBackground(new Color(213, 151, 90));
		add(PanVide);
		
		
		/*Panel des Boutons*/
		JPanel PanBoutons = new JPanel();
		PanBoutons.setBounds(800,500,600,400);
		PanBoutons.setLayout(null);
		PanBoutons.add(RemettreZero);
		PanBoutons.add(Indice);
		PanBoutons.add(Verification);
		PanBoutons.add(IndicePhoto);
		PanBoutons.add(textBonus);
		PanBoutons.add(textTools);
		PanBoutons.setBackground(new Color(213, 151, 90));
		add(PanBoutons);
		
		infoBonus.setBounds(950,400,500,100);
		infoBonus.setText("Points pour bonus disponibles: "+PtBonus);
		infoBonus.setBackground(new Color(213, 151, 90));
		add(infoBonus);
		
		/*Panel contenant la photo pour le bonus*/
		PanIndicePhoto.setLayout(null);
		PanIndicePhoto.setBounds(700,0,w,h);
		ImagePhoto.setBounds(0,0,w,h);
		PanIndicePhoto.add(ImagePhoto);
		PanIndicePhoto.setBackground(Color.RED);
		add(PanIndicePhoto);
		PanIndicePhoto.setVisible(false);
		
		
		/*Ajout des Listeners*/
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				Vide[i][j].addMouseListener(this);
				alea[i][j].addMouseListener(this);
				System.out.println(alea[i][j].num);
			}
		}
		RemettreZero.addMouseListener(this);
		Indice.addMouseListener(this);
		Verification.addMouseListener(this);
		AddTabJPanel(Vide,PanVide,w,h,taille);
		IndicePhoto.addMouseListener(this);
		
		
		setVisible(true);

		
}  
	public Piece [] [] DecoupeEasy (String path){ // Méthode renvoyant un tableau de JPanel contenant les images découpées
				//dimension du tableau de JPanel
		Piece [] [] p = new Piece[taille][taille]; // Impossible d'utiliser tableau car non dynamique --> pas intéressant car utilisation de 3 switch case avec tailles différentes tableau
		ImageIcon image = new ImageIcon(path);
		int w = image.getIconWidth();
		int h = image.getIconHeight();
			try {
		
				/* Initialisation des objets */
		
				BufferedImage bi = ImageIO.read(new File(path));
				ImageIcon [] [] img;
				JLabel [] [] lbl;
	
				/*Initialisation*/
			
				BufferedImage [][] bfi = new BufferedImage [taille][taille];
				img = new ImageIcon [taille][taille];
				lbl = new JLabel [taille][taille];
			
				for (int i=0;i<taille;i++){ 
					for(int j=0;j<taille;j++){
						p[i][j] = new Piece(taille*j+i); //initialisation de chq panneau du tbleau
						bfi[i][j]=ImageIO.read(new File(path)); // chaque bif lit le chemin pour accéder à l'image
					}
			}
			
			
			
				/*Traitement*/ 
			
				for (int m=0;m<taille;m++){
						for(int n=0;n<taille;n++){
							img[m][n]=new ImageIcon(bfi[n][m].getSubimage((n*w)/taille,(h*m)/taille,(w)/taille,(h)/taille)); 	
				}
			}
			
			/* Ajout de chaque élément traité dans les tableaux/LkdList */
			
				for (int i=0;i<taille;i++){
					for (int j=0;j<taille;j++){
						lbl[i][j]=new JLabel(img[i][j]);
						p[i][j].num=taille*j+i;
						System.out.println(p[i][j].num);
						p[i][j].add(lbl[i][j]);
				}
			}
			
			
			
		
	} catch (IOException e){}
				return p;
} 
	public void AddTabJPanel (Piece [] [] p , JPanel ret,int w,int h, int taille){
	for (int i=0;i<taille;i++){
		for (int j=0;j<taille;j++){
			p[i][j].setBounds((i*w)/taille,((h-8)*j)/taille,(w)/taille,(h)/taille);  
			ret.add(p[i][j]);		
			}
		}
	}
	public void initialise(){
		Vide = TabARemplir(taille);
		t = DecoupeEasy(path);
		alea= melangePuzzle(t,taille);
	}
	public Piece[][] melangePuzzle(Piece[][] p, int taille){
		
	Piece [][] p2 = DecoupeEasy(path);	
	Piece [] [] tableauMelange = new Piece[taille][taille];	
	for(int i=0;i<taille;i++){
		for(int j=0;j<taille;j++){
			tableauMelange[i][j]= new Piece();
			
			int irandom= (int) (Math.random()*taille); 
			int jrandom=(int) (Math.random()*taille);
			while(p2[irandom][jrandom]==null){
				irandom = (int) (Math.random()*taille);
				jrandom = (int) (Math.random()*taille);
				System.out.println("bloque");
				
				
			}
			tableauMelange[i][j]=p2[irandom][jrandom];
			tableauMelange[i][j].ID=1;
			tableauMelange[i][j].num=taille*jrandom+irandom;
			p2[irandom][jrandom]=null;
			}
		}
		return tableauMelange;
	}
	/* Méthode qui permet de déplacer une pièce du tableau mélangé de puzzle de coordonnées (a1,b1) vers le tableau vide aux coordonnées (a2,b2)qui sert de base pour construire le puzzle*/
	public void DeplacerPiece(Piece [][] TabMelange,Piece [][] TabARemplir, int a1, int b1, int a2, int b2){
		Piece p1 = new Piece(TabMelange[a1][b1].num);
		Piece p2 = new Piece(TabMelange[a1][b1].num);
		p2=TabARemplir[a2][b2];
		p1=TabMelange[a1][b1];	
		TabARemplir[a2][b2]=p1;
		TabMelange[a1][b1]=p2;		
		refresh();
		
	}
	public Piece[][] TabARemplir(int taille){
		Piece [] [] ret = new Piece[taille][taille];
		for (int i=0;i<taille;i++){
			for (int j=0;j<taille;j++){
				
				ret[i][j]=new Piece();
				ret[i][j].setBackground(Color.RED);
			}
		}
		return ret;
		
	}
	public void Recommencer(){
		System.out.println("hey");
		Piece[] [] Vide1 = TabARemplir(taille);
		AddTabJPanel(Vide,PanVide,w,h,taille);
		Piece[] [] r = DecoupeEasy(path);
		Piece[] [] alea1= melangePuzzle(r,taille);
		
		
		for (int i=0;i<taille;i++){
			for (int j=0;j<taille;j++){
				Vide[i][j]=Vide1[i][j];
				alea[i][j]=alea1[i][j];
				Vide[i][j].addMouseListener(this);
				alea[i][j].addMouseListener(this);
				}
			}	
			
		refresh();
		}	
	public void refresh(){
		
		PanMelange.setBounds(0,800-h,800,800);
		PanVide.setBounds(0,0,800,800);
		AddTabJPanel(Vide,PanVide,w,h,taille);
		AddTabJPanel(alea,PanMelange,w,h,taille);
		add(PanVide);
		add(PanMelange);
		setVisible(true);
	}
	public boolean VerifPuzzle(){
		int memory=-1;
		boolean ret = true;
		int [] tab = new int [taille*taille];
		for (int i=0;i<taille;i++){
			for (int j=0;j<taille;j++){
				tab[taille*j+i]=Vide[i][j].num;
				if(memory>=tab[taille*j+i]){
					System.out.println(memory+" "+tab[taille*j+i]);
					return false;
					
				}
				memory=tab[taille*j+i];
				System.out.println(tab[taille*j+i]);
				
			}
		}
	return ret;
	}
	public int returnI(int num){
		int ret=0;
		ret = (num -num%taille)/taille;
		return ret;
	}
	public int returnJ(int num){
		int ret=0;
		ret = num%taille;
		return ret;
	}
	
	public void PieceIndice(){
		if(PtBonus>=3){
			PtBonus=PtBonus-3;
			infoBonus.setText("Points bonus : "+PtBonus);
			int irandom= (int) (Math.random()*taille); 
			int jrandom=(int) (Math.random()*taille);
			while(alea[irandom][jrandom].ID!=1){
				irandom= (int) (Math.random()*taille); 
				jrandom=(int) (Math.random()*taille);
			}
			temps+=10;
			DeplacerPiece(alea,Vide,irandom,jrandom,returnI(alea[irandom][jrandom].num),returnJ(alea[irandom][jrandom].num));
		
	}
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){
		if (e.getSource()==IndicePhoto){
			if (PtBonus>=1){
				PtBonus=PtBonus-1;
				infoBonus.setText("Points bonus : "+PtBonus);
				tpsIndice.start();
				PanIndicePhoto.setVisible(true);
				temps+=5;
			}
		}
		if (e.getSource()==Indice){
			PieceIndice();
			refresh();
		}
		if (e.getSource()==RemettreZero){
			Recommencer();
		}
		if (e.getSource()==Verification){
			System.out.println(VerifPuzzle());
			if (VerifPuzzle()){
				FenetreFinDeJeu ffdj = new FenetreFinDeJeu(temps);
				ffdj.setVisible(true);
				this.setVisible(false);
			} else {
				perdu.setVisible(true);
				
			}
		}
		for (int i=0;i<taille;i++){
			for (int j=0;j<taille;j++){
				if (e.getSource()==alea[i][j]){
					a1=i;
					b1=j;
		}
			}
		}
		for (int i=0;i<taille;i++){
			for (int j=0;j<taille;j++){
				if (e.getSource()==Vide[i][j]){
					DeplacerPiece(alea,Vide,a1,b1,i,j);
					}
				}
		}
	}
	public void actionPerformed(ActionEvent e){
		temps+=deltaT/1000;
		time.setText("Score total : "+String.valueOf(temps));
		infoBonus.setText("Points pour bonus disponibles: "+PtBonus);
		if(e.getSource()==boutonIndice){
	        Fenetre_Indice fenindice = new Fenetre_Indice();
		    fenindice.setVisible(true);
		}
		if(e.getSource()==tpsIndice){
			tpsIndice.stop();
			PanIndicePhoto.setVisible(false);
	}
	}
}

	
