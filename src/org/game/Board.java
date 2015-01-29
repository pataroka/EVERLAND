package org.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener{
	
	private Image board;
	
	private int[] x;
	private int[] y;
	
	
	public Board() {
		
		initBoard();
		
		this.addMouseListener(this);
		this.setFocusable(true);
		
		x = new int[40];
		y = new int[40];
		
		
		int currentX = 45;
		int currentY = 45;
		
		
		for (int i = 0; i < 40; i++) {
			
			x[i] = currentX;
			y[i] = currentY;
			
			if (i < 10) {
				currentX += 46;
			} else if(i < 20) {
				currentY += 46;
			} else if (i < 30) {
				currentX -= 46;
			} else {
				currentY -= 46;
			}
		}
	}
	
	public void tick() {
		
		
	}
	
	private void initBoard() {
        
        loadImage();
        
        int width = board.getWidth(this);
        int height = board.getHeight(this);
        setPreferredSize(new Dimension(width, height));
    }
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon("assets/field34x34.png");
		board = ii.getImage();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(board, 0, 0, null);
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
