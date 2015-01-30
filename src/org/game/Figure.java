package org.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Figure extends JPanel{
	
	private int x;
	private int y;
	private int position;
	private Image pawn;
	private static int[] boardX;
	private static int[] boardY;
	enum Color {BLUE, RED, YELLOW, GREEN};

	public Figure(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		getImage(color);
	}
	
	private void getImage(Color color) {
		ImageIcon ib = new ImageIcon("assets/Blue_Pawn36x64.png");
		ImageIcon ir = new ImageIcon("assets/Red_Pawn36x64.png");
		ImageIcon iy = new ImageIcon("assets/Yellow_Pawn36x64.png");
		ImageIcon ig = new ImageIcon("assets/Green_Pawn36x64.png");
		
		switch (color){
			case BLUE: pawn = ib.getImage(); break;
			case RED: pawn = ir.getImage(); break;
			case YELLOW: pawn = iy.getImage(); break;
			case GREEN: pawn = ig.getImage(); break;
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(pawn, x + 6, y - 21, null);
	}
	
	public static void setBoardArray() {
		boardX = new int[40];
		boardY = new int[40];
		int currentX = Game.cellSize;
		int currentY = Game.cellSize;
		for (int i = 0; i < 40; i++) {
			
			boardX[i] = currentX;
			boardY[i] = currentY;
			
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
		
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
}
