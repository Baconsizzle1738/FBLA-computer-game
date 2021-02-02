package me.Baconsizzle1738.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.Baconsizzle1738.main.ID;

public class Obstacle extends GameObject{
	private int width, height;
	private Color color;
	
	/**
	 * An obstacle that obstructs player movement
	 * 
	 * @param x			x position of Obstacle
	 * @param y			y position of Obstacle
	 * @param typeId	ID of Obstacle
	 * @param level		Level the Obstacle belongs to
	 * @param width		Width of Obstacle
	 * @param height	Height of Obstacle
	 * @param col		Color of obstacle
	 */
	public Obstacle(int x, int y, ID typeId, int level, int width, int height, Color col) {
		super(x, y, typeId, level);
		
		this.height = height;
		this.width = width;
		color = col;
	}
	
	/**
	 * 
	 * @return	Width of Obstacle
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * 
	 * @return	Height of Obstacle
	 */
	public int getHeight() {
		return height;
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
