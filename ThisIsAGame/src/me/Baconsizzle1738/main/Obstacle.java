package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstacle extends GameObject{
	private int width, height;
	private Color color;
	
	public Obstacle(int x, int y, ID typeId, int level, int height, int width, Color col) {
		super(x, y, typeId, level);
		
		this.height = height;
		this.width = width;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
