import java.awt.*;
import javax.swing.*;


public class Piece extends JPanel{
	int num=0;
	int ID =0; // 1 si appartient alea et 0 si appartient vide
	public Piece(int num, int ID){
		this.num=num;
		this.ID=ID;
	}
	public Piece(int num){
		 this.num=num;
		}
	public Piece(){
		
}
}

