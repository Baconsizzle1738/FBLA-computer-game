package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This <code>GameObject</code> is a special object that turns translucent when the player goes near it.
 * 
 * @author Baconsizzle1738
 *
 */
public class GrassBlock extends GameObject{
	
	private float trans = 1;
	private Player player;
	
	/**
	 * Takes all inputs for <code>GameObject</code> and also the player to detect the player proximity.
	 * 
	 * @param x			x position
	 * @param y			y posision
	 * @param typeId	<code>ID</code> for the object
	 * @param level		level the object belongs to
	 * @param p			player to detect proximity
	 */
	public GrassBlock(int x, int y, ID typeId, int level, Player p) {
		super(x, y, typeId, level);
		player = p;
	}
	

	@Override
	public void tick() {
		
		if (player.getBounds().intersects(this.getBounds())) {
			trans = 0.4f;
		}
		else {
			trans = 1;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0, 0.8f, 0, trans));
		g.fillRect(x, y, 20, 20);
	}

	@Override
	public Rectangle getBounds() {
		//expanded hitbox so it goes translucent when player is near.
		
		return new Rectangle(x-40, y-40, 100, 100);
	}

}
