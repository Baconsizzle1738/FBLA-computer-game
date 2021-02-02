package me.Baconsizzle1738.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.Baconsizzle1738.main.Game;
import me.Baconsizzle1738.main.ID;

/**
 * This is the flash of white that is displayed on the screen when the player is hurt or dead.
 * @author Baconsizzlw1738
 *
 */

public class PainFlash extends GameObject{
	
	private float life, trans;
	private GameHandler handler;
	/**
	 * Only the life, transparency, and the handler matter for this.
	 * 
	 * @param x			No need
	 * @param y			No need
	 * @param typeId	No need
	 * @param level		No need
	 * @param life		The amount of time that the effect lasts for, the lower the value, the lower the effect (must be below .8).
	 * @param trans		Initial transparency value between 0 and 1.
	 * @param h			The <code>GameHandler</code> so that this object can be automatically removed once fully transparent
	 */
	public PainFlash(int x, int y, ID typeId, int level, float life, float trans, GameHandler h) {
		super(x, y, typeId, level);
		//x and y will do nothing, both set to 0.
		this.x = 0;
		this.y = 0;
		
		handler = h;
		this.life = life;
		this.trans = trans;
	}

	@Override
	public void tick() {
		//gradually make more transparent
		trans -= life;
		//remove once fully transparent
		if (trans<=0) {
			handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(1f, 1f, 1f, trans));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}

	@Override
	public Rectangle getBounds() {
		//no hitbox needed
		return null;
	}

}
