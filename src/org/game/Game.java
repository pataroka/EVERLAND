package org.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame{
	
	static final int WIDTH = 620;
	static final int HEIGHT = 620;
	static final int CELL_SIZE = 45;
	private Image startScreen;
	private Image buttonUnclicked;
	private Image buttonClicked;
	private Board board;

	public Game() {
		
		board= new Board();
		init();
		update();
	}
	
	private void init() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Trouble");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(board);
		this.setVisible(true);
	}
	
	private void update() {
		while (true) {
			board.tick();
			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(startScreen, 0, 0, null);
		
	}
	
	private void loadStartScreen() {
		ImageIcon ii = new ImageIcon("assets/StartScreen.png");
		ImageIcon ij = new ImageIcon("assets/ButtonClicked.png");
		ImageIcon ik = new ImageIcon("assets/ButtonUnclicked.png");		
		startScreen = ii.getImage();
		buttonClicked = ij.getImage();
		buttonUnclicked = ik.getImage();
		
	}
	
	
}
