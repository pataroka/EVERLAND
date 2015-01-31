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
public class Board extends JPanel implements MouseListener{
	
	private Image board;
	private Image dice;
	private Image mask;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int diceNumber = 1;
	private boolean rollDice;
	private int currentClickedX;
	private int currentClickedY;
	private int currentHoverX;
	private int currentHoverY;
	private boolean moved;
	
	public Board() {
		
		initBoard();
		Figure.setBoardArray();
		
		this.addMouseListener(this);
		this.setFocusable(true);

		players.add(new Player(true, Figure.Color.RED));
		players.add(new Player(Figure.Color.BLUE));
		players.add(new Player(Figure.Color.GREEN));
		players.add(new Player(Figure.Color.YELLOW));
		
		
	}
	
	

	public void tick() {
		for (Player player : players) {
			
			if (!player.isHuman()) {
				// AI logic here
				continue;
			}
			
			diceNumber = 0;
			rollDice = false;
			moved = false;
			synchronized (this) {
				do {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while (diceNumber == 6);
				rollDice = true;
				
				do {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					for (Figure f : player.getFigures()) {
						
						
						// game logic here
					}
				} while (!moved);
			}
			
			repaint();
			
		}
		
	}
	
	private void initBoard() {
        
        loadImage();
		getDiceImage(diceNumber);
        int width = board.getWidth(this);
        int height = board.getHeight(this);
        setPreferredSize(new Dimension(width, height));
    }
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon("assets/field34x34.png");
		board = ii.getImage();
	}
	
	public int dice () {
		Random rnd = new Random();
		diceNumber = 1 + rnd.nextInt(6);
		return diceNumber;
    }
	
	private void getDiceImage(int diceNumber) {
		ImageIcon id1 = new ImageIcon("assets/Dice1.png");
		ImageIcon id2 = new ImageIcon("assets/Dice2.png");
		ImageIcon id3 = new ImageIcon("assets/Dice3.png");
		ImageIcon id4 = new ImageIcon("assets/Dice4.png");
		ImageIcon id5 = new ImageIcon("assets/Dice5.png");
		ImageIcon id6 = new ImageIcon("assets/Dice6.png");
		
		switch (diceNumber){
			case 1: dice = id1.getImage(); break;
			case 2: dice = id2.getImage(); break;
			case 3: dice = id3.getImage(); break;
			case 4: dice = id4.getImage(); break;
			case 5: dice = id5.getImage(); break;
			case 6: dice = id6.getImage(); break;
		}
	}
	
	private void maskImage(){
		ImageIcon im = new ImageIcon("assets/pawnmask.png");
		mask = im.getImage();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(board, 0, 0, null);
		g.drawImage(dice, Game.cellSize * 6 + 3, Game.cellSize * 6 + 4, null);
		g.drawImage(mask, currentHoverX, currentHoverY, null);
		for (Player p : players) {
			p.paint(g);
		}
	}

	
	public void mouseClicked(MouseEvent e) {
		
		synchronized (this) {
			if (!rollDice) {	
				// roll the dice here
				dice();
				getDiceImage(diceNumber);
				repaint();
			} else {
				currentClickedX = e.getX();
				currentClickedY = e.getY();
			}
	
			this.notify();
		}
		
	}

	
	
	public void mouseEntered(MouseEvent e) {
		currentHoverX = e.getX();
		currentHoverY = e.getY();
		maskImage();
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
