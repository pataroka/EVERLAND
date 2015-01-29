package org.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InGame extends JPanel implements MouseListener{
	
	Image background;
	private int[] x;
	private int[] y;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public InGame() {
		this.addMouseListener(this);
		this.setFocusable(true);
		setField();
		players.add(new Player(true, Color.red));
		players.add(new Player(Color.blue));
		players.add(new Player(Color.green));
		players.add(new Player(Color.yellow));
		
	}
	
	private void setField() {
		ImageIcon ii = new ImageIcon("assets/field34x34.png");
		background = ii.getImage();
		x = new int[40];
		y = new int[40];
		int currentX = Game.cellSize;
		int currentY = Game.cellSize;
		for (int i = 0; i < 40; i++) {
			
			x[i] = currentX;
			y[i] = currentY;
			
			if (i < 10) {
				currentX += Game.cellSize;
			} else if(i < 20) {
				currentY += Game.cellSize;
			} else if (i < 30) {
				currentX -= Game.cellSize;
			} else {
				currentY -= Game.cellSize;
			}
		}
	}
	
	public void tick() {
		
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, null);
		for (Player p : players) {
			p.paint(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
