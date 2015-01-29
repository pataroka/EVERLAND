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
	
	
	public Board() {
		
		initBoard();
		
		this.addMouseListener(this);
		this.setFocusable(true);
		
		
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
