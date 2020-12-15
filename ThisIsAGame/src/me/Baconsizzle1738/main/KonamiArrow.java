package me.Baconsizzle1738.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class KonamiArrow extends GameObject{

	private String direction;
	
	public KonamiArrow(int x, int y, ID typeId, int level, String direction) {
		super(x, y, typeId, level);
		this.direction = direction;
	}
	
	public String getDirection() {
		return direction;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
	
}
