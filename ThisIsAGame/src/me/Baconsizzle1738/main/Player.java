package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	public Player(int x, int y, ID typeId, ID levelId) {
		super(x, y, typeId, levelId);
		
	}

	@Override
	public void tick() {
		x+=volX;
		y+=volY;
		
		x = Game.clamp(x, 0, Game.WIDTH);
		y = Game.clamp(y, 0, Game.HEIGHT);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 32, 32);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,32,32);
	}

}
