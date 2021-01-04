package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class NumberBlock extends GameObject{
	
	private int num;
	
	public NumberBlock(int x, int y, ID typeId, int level, int num) {
		super(x, y, typeId, level);
		
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
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
