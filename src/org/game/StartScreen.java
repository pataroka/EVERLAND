package org.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class StartScreen extends JPanel implements MouseListener {
	
	private Image startScreen;
	public static boolean unclicked;
	private static Image button;
	public int buttonX;
	public int playerCount;
	public int humanCount;

	public StartScreen() {
	
		initStartScreen();
	
		this.addMouseListener(this);
		this.setFocusable(true);
	}
	
	private void initStartScreen() {

		loadStartScreen();
		getButtonImage(unclicked);
		int width = startScreen.getWidth(this);
		int height = startScreen.getHeight(this);
		setPreferredSize(new Dimension(width, height));
	}
	
	private void loadStartScreen() {
		ImageIcon ii = new ImageIcon("assets/START_SCREEN.png");
		startScreen = ii.getImage();
		
	}
	
	public static void button() {
		getButtonImage(unclicked);

	}

	public static void getButtonImage(boolean unclicked) {
		ImageIcon ij = new ImageIcon("assets/ButtonClicked.png");
		ImageIcon ik = new ImageIcon("assets/ButtonUnclicked.png");	

		if (unclicked) {
			button = ij.getImage();
		} else {
			button = ik.getImage();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(startScreen, 0, 0, null);
		for (int i = 90; i <= 370; i+=130){
			g.drawImage(button, i , 305, null);
		}
		for (int i = 45; i <= 430; i+=120){
			g.drawImage(button, i , 505, null);
		}
	}
	
	
	
	private void choosePlayerCount(){
		synchronized (this) {
			boolean countSelected = false;
			do {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (!countSelected);
			
			switch (buttonX){
				case 90 : playerCount = 2; break;
				case 220 : playerCount = 3; break;
				case 350 : playerCount = 4; break;
			}
		}
		
	}
	
	private void chooseHumanPlayer(){
		
		synchronized (this) {
			boolean humanSelected = false;
			do {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (!humanSelected);
			
			switch (buttonX){
				case 45 : humanCount = 1; break;
				case 165 : humanCount = 2; break;
				case 285 : humanCount = 3; break;
				case 405 : humanCount = 4; break;
			}
		}
		
		
		
	}
	
	public void mouseClicked(MouseEvent e) {
		synchronized (this) {
			buttonX = e.getX();
			this.notify();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}