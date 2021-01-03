package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GrassBlock extends GameObject{
	
	private float trans = 1;
	private Player player;
	
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
		return new Rectangle(x-40, y-40, 100, 100);
	}

}
