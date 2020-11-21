import java.util.LinkedList; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.Font;

public class FenetreFinDeJeu extends Fenetres implements ActionListener {

    
	public JPanel conteneur;
	public JLabel txt;
	public JButton Recommencer;
	public JButton Quitter;
	public JLabel fondEcran;
  


public FenetreFinDeJeu(int temps){
	
	//chronoJeu.stop();
	this.temps=temps;
    this.setTitle("MyPuzzle - Partie terminee");
    this.setSize(500,500);
    this.setLocation(200,200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        conteneur=new JPanel();
		conteneur.setBounds(0,0,500,500);
		conteneur.setLayout(null);
         

        txt=new JLabel("");
        Font font = new Font("Arial",Font.BOLD,40);
		txt.setFont(font);
  		txt.setText("Fin du jeu ! Score :"+ temps);
		txt.setBounds(50 , 130,400,150);
        conteneur.add(txt);


        Recommencer = new JButton("Recommencer");
		Recommencer.setBounds(20,300,160,70);
		Recommencer.setBackground(new Color(173, 255,47));
		conteneur.add(Recommencer);
		Recommencer.addActionListener(this);

        Quitter = new JButton("Quitter");
		Quitter.setBounds(310,300,160,70);
		Quitter.setBackground(new Color(220,20 ,60));
		conteneur.add(Quitter);
		Quitter.addActionListener(this);

        fondEcran = new JLabel(new ImageIcon("./fondEcran.jpg"));
		fondEcran.setBounds(0,0,getWidth(),getHeight());
		conteneur.add(fondEcran);
			
	
        this.add(conteneur);
  	    this.setVisible(true);

 }


  public void actionPerformed(ActionEvent e){
		
		if (e.getSource()== Recommencer){
			
			this.dispose();    
			//~ fen.setVisible(true);
			FenetreAccueil maFenetre = new FenetreAccueil();
						
		   }
		
		else if (e.getSource()==Quitter){
		this.dispose();
			
				}

 
 }

}
