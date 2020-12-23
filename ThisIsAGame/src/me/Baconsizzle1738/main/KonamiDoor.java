package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class KonamiDoor extends GameObject{

	public KonamiDoor(int x, int y, ID typeId, int level) {
		super(x, y, typeId, level);
		
	}

	@Override
	public void tick() {
		
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
