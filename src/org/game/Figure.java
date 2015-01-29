package org.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Figure extends JPanel{
	
	private int x;
	private int y;
	private int position;
	private Color color;

	public Figure(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fillOval(x, y, Game.cellSize, Game.cellSize);
		g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.BLACK);
		g2.drawOval(x, y, Game.cellSize, Game.cellSize);
		g2.drawOval(x + Game.cellSize / 4, y + Game.cellSize / 4, Game.cellSize / 2, Game.cellSize / 2);
	}
}
