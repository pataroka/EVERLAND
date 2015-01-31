package org.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Figure extends JPanel{
	
	private int x;
	private int y;
	private int position;
	private Image pawn;
	public static ArrayList<Point> positions;
	enum Color {BLUE, RED, YELLOW, GREEN};
	private Color color;

	public Figure(int x, int y, Color color) {
		this.color = color;
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
		positions = new ArrayList<Point>();
		int currentX = Game.CELL_SIZE;
		int currentY = Game.CELL_SIZE;
		for (int i = 0; i < 40; i++) {
			
			positions.add(new Point(currentX, currentY));
			
			if (i < 10) {
				currentX += Game.CELL_SIZE;
			} else if(i < 20) {
				currentY += Game.CELL_SIZE;
			} else if (i < 30) {
				currentX -= Game.CELL_SIZE;
			} else {
				currentY -= Game.CELL_SIZE;
			}
		}
		
		currentX = Game.CELL_SIZE * 2;
		for (int i = 40; i < 44; i++) {
			
			positions.add(new Point(currentX, Game.CELL_SIZE * 6));
			
			currentX += Game.CELL_SIZE; 
		}
		
		currentY = Game.CELL_SIZE * 2;
		for (int i = 44; i < 48; i++) {
			
			positions.add(new Point(Game.CELL_SIZE * 6, currentY));
			
			currentY += Game.CELL_SIZE; 
		}
		
		currentX = Game.CELL_SIZE * 10;
		for (int i = 48; i < 52; i++) {
			
			positions.add(new Point(currentX, Game.CELL_SIZE * 6));
			
			currentX -= Game.CELL_SIZE; 
		}
		
		currentY = Game.CELL_SIZE * 10;
		for (int i = 52; i < 56; i++) {
			
			positions.add(new Point(Game.CELL_SIZE * 6, currentY));
			
			currentY -= Game.CELL_SIZE; 
		}
	}
	
	public void setDefault() {
		
	}
		
	public Color getColor() {
		return this.color;
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
	
	public void setMove(int position) {
		this.position = position;
		this.x = positions.get(position).x;
		this.y = positions.get(position).y;
	}
}
