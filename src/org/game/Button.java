package org.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Button extends JPanel{
	
	public boolean clicked;
	private static Image button;
	private int x;
	private int y;
	
	public Button (int x, int y, boolean clicked){
		this.x = x;
		this.y = y;
		this.clicked = clicked;
		getButtonImage(clicked);
		
	}
	
	public static void getButtonImage(boolean clicked) {
		ImageIcon ij = new ImageIcon("assets/ButtonClicked.png");
		ImageIcon ik = new ImageIcon("assets/ButtonUnclicked.png");	

		if (clicked) {
			button = ij.getImage();
		} else {
			button = ik.getImage();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(button, x , y, null);
	}
	
	public int getButtonX (){
		return this.x;
	}
	
	public void setButton (boolean clicked){
		this.clicked = clicked;
	}

}
