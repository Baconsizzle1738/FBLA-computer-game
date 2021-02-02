package me.Baconsizzle1738.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.Baconsizzle1738.main.ID;
/**
 * This is a level specific object that the player can interact with, and can exist in 4 states: up, down, left, or right.
 * For level/room 1.
 * @author Baconsizzle1738
 *
 */
public class KonamiArrow extends GameObject{

	private String direction;
	/**
	 * Parameters required for <code>KonamiArrow</code>.
	 * @param x			x position.
	 * @param y			y position.
	 * @param typeId	<code>ID</code> of object.
	 * @param level		level identifier.
	 * @param direction	direction of the arrow, input only <code>"up"</code>, <code>"down"</code>, <code>"left"</code> or <code>"right"</code>.
	 */
	public KonamiArrow(int x, int y, ID typeId, int level, String direction) {
		super(x, y, typeId, level);
		this.direction = direction;
	}
	
	/**
	 * Returns the direction the <code>KonamiArrow</code> is pointing.
	 * 
	 * @return	String indicating direction
	 */
	public String getDirection() {
		return direction;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(200, 100, 0));
		g.fillRect(x, y, 20, 20);
		g.setColor(new Color(0, 150, 0));
		//draws the arrow inside the square
		if (direction.equals("up")) {
			int[] xVal = {x+3, x+17, ((x+20)+x)/2};
			int[] yVal = {y+17, y+17, y+3};
			g.fillPolygon(xVal, yVal, 3);
		}
		if (direction.equals("down")) {
			int[] xVal = {x+3, x+17, ((x+20)+x)/2};
			int[] yVal = {y+3, y+3, y+17};
			g.fillPolygon(xVal, yVal, 3);
		}
		if (direction.equals("right")) {
			int[] xVal = {x+3, x+3, x+17};
			int[] yVal = {y+3, y+17, ((y+20)+y)/2};
			g.fillPolygon(xVal, yVal, 3);
		}
		if (direction.equals("left")) {
			int[] xVal = {x+17, x+17, x+3};
			int[] yVal = {y+3, y+17, ((y+20)+y)/2};
			g.fillPolygon(xVal, yVal, 3);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 20);
	}
	
}
