import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.Color;

public abstract class Fenetres extends JFrame implements ActionListener {
	public String couleurChoisie;
	public Fenetre fen;
	public int temps;
	public int deltaT;
	public Timer chronoJeu;
	public int difficulte;
	int PtBonus=100;
	JLabel infoBonus = new JLabel();
	public Fenetres(){
		deltaT=1000;
		temps=0;
		Timer chronoJeu=new Timer(1000,this);
		chronoJeu.start();
	}
	public Fenetre getFenetre(){
		return fen;
	}
	
}
