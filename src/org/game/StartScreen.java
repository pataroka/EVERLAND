package org.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class StartScreen extends JPanel implements MouseListener {
	
	private Image startScreen;
	private Image buttonUnclicked;
	private Image buttonClicked;
	public int buttonX;

	public StartScreen() {
	
		initStartScreen();
	
		this.addMouseListener(this);
		this.setFocusable(true);
		
	}
	
	private void initStartScreen() {

		loadStartScreen();
		int width = startScreen.getWidth(this);
		int height = startScreen.getHeight(this);
		setPreferredSize(new Dimension(width, height));
	}
	
	private void loadStartScreen() {
		ImageIcon ii = new ImageIcon("assets/START_SCREEN.png");
		ImageIcon ij = new ImageIcon("assets/ButtonClicked.png");
		ImageIcon ik = new ImageIcon("assets/ButtonUnclicked.png");		
		startScreen = ii.getImage();
		buttonClicked = ij.getImage();
		buttonUnclicked = ik.getImage();
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(startScreen, 0, 0, null);
		for (int i = 90; i <= 370; i+=130){
			g.drawImage(buttonUnclicked, i , 305, null);
		}
		for (int i = 45; i <= 430; i+=120){
			g.drawImage(buttonUnclicked, i , 505, null);
		}
//		paintComponent(g, buttonX);
//		paintComponent2(g, buttonX);
	}
	
	public void paintComponent(Graphics g, int x) {
		super.paint(g);
		switch (x){
			case 90 : g.drawImage(buttonClicked, 90, 305, null); break;
			case 220 : g.drawImage(buttonClicked, 220, 305, null); break;
			case 350 : g.drawImage(buttonClicked, 350, 305, null); break;
		}
	}
	
	public void paintComponent2(Graphics g, int x) {
		super.paint(g);
		switch (x){
			case 45 : g.drawImage(buttonClicked, 45, 505, null); break;
			case 165 : g.drawImage(buttonClicked, 165, 505, null); break;
			case 285 : g.drawImage(buttonClicked, 285, 505, null); break;
			case 405 : g.drawImage(buttonClicked, 405, 505, null); break;
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