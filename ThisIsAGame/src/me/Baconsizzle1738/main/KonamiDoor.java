package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The door that the player goes through to complete room 1.
 * @author Baconsizzle1738
 *
 */
public class KonamiDoor extends GameObject{

	/**
	 * Takes only the parameters for <code>GameObject</code>.
	 * 
	 * @param x			x position
	 * @param y			y position
	 * @param typeId	<code>ID</code> if door
	 * @param level		level the door belongs to.
	 */
	public KonamiDoor(int x, int y, ID typeId, int level) {
		super(x, y, typeId, level);
		
	}

	@Override
	public void tick() {
		//no logic needed
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0,200,200));
		g.fillRect(x, y, 9, 98);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 9, 98);
	}
	
}
