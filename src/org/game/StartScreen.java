package org.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class StartScreen extends JPanel implements MouseListener {
	
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private Image startScreen;
	public int buttonX;
	public int playerCount;
	public int humanCount;
	private boolean countSelected = false;
	boolean humanSelected = false;

	public StartScreen() {
	
		initStartScreen();
		initButtons();
		this.addMouseListener(this);
		this.setFocusable(true);
	}
	
	private void initStartScreen() {

		loadStartScreen();
		int width = startScreen.getWidth(this);
		int height = startScreen.getHeight(this);
		setPreferredSize(new Dimension(width, height));
	}
	
	private void initButtons(){
		
		for (int i = 90; i < 370; i+=130) {
			this.buttons.add(new Button(i, 305, false));
		}
		for (int i = 45; i < 420; i+=120) {
			this.buttons.add(new Button(i, 505, false));
		}
		
	}
	
	private void loadStartScreen() {
		ImageIcon ii = new ImageIcon("assets/START_SCREEN.png");
		startScreen = ii.getImage();
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(startScreen, 0, 0, null);
		for (Button b : buttons) {
			b.paint(g);
		}
	}
	
	
	
	private void choosePlayerCount(){
		synchronized (this) {			
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
			if (!countSelected){
				for (Button b : buttons){
					if (b.getButtonX() == e.getX()){
						b.setButton(true);
					}
				}
				repaint();
				countSelected = true;
			}
			
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