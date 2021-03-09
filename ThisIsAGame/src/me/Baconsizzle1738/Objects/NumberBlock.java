package me.Baconsizzle1738.Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.Baconsizzle1738.main.ID;

/**
 * These are the little blocks that the player must find in the thrd room and click on.
 * 
 * @author Baconsizzle1738
 *
 */
public class NumberBlock extends GameObject{
	
	private int num;
	
	/**
	 * Takes in all <code>GameObject</code> parameters and also the number that the block displays.
	 * 
	 * @param x			x position
	 * @param y			y position
	 * @param typeId	<code>ID</code> of block
	 * @param level		level that the block belongs to
	 * @param num		number that the block displays.
	 */
	public NumberBlock(int x, int y, ID typeId, int level, int num) {
		super(x, y, typeId, level);
		
		this.num = num;
	}
	
	/**
	 * 
	 * @return Displayed number
	 */
	public int getNum() {
		return num;
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font(Font.MONOSPACED, 0, 11));
		g.setColor(new Color(223, 120, 203));
		g.fillRect(x, y, 13, 13);
		g.setColor(new Color(25, 65, 145));
		g.drawString(num+"", x+3, y+11);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 13, 13);
	}

}
