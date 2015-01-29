package org.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InGame extends JPanel implements MouseListener{
	Image background;
	private int[] x;
	private int[] y;
	public InGame() {
		this.addMouseListener(this);
		this.setFocusable(true);
		ImageIcon ii = new ImageIcon("assets/field34x34.png");
		background = ii.getImage();
		x = new int[42];
		y = new int[42];
		int currentX = 45;
		int currentY = 45;
		for (int i = 0; i < 42; i++) {
			
			x[i] = currentX;
			y[i] = currentY;
			
			if (i < 10) {
				currentX += 46;
			} else if(i < 21) {
				currentY += 46;
			} else if (i < 31) {
				currentX -= 46;
			} else {
				currentY -= 46;
			}
		}
	}
	
	public void tick() {
		
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, null);
		g.drawOval(x[41],y[41],45,45);
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
