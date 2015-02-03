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
	}
	
	public void mouseClicked(MouseEvent e) {
		synchronized (this) {
				if (e.getX() > Game.CELL_SIZE * 6
						&& e.getX() < Game.CELL_SIZE * 7
						&& e.getY() > Game.CELL_SIZE * 6
						&& e.getY() < Game.CELL_SIZE * 7) {
					repaint();
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