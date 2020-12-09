   package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	GameHandler handler;
	public Player(int x, int y, ID typeId, int level, GameHandler h) {
		super(x, y, typeId, level);
		handler = h;
	}

	@Override
	public void tick() {
		x+=volX;
		y+=volY;
		
		x = Game.clamp(x, 0, Game.WIDTH-48);//x is 16 px off
		y = Game.clamp(y, 0, Game.HEIGHT-71);//y is 39 px off
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 32, 32);
		
	}
	
	public boolean isHitByEnemy() {
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.StaticEnemy) {
				StaticEnemy temp = (StaticEnemy) handler.objects.get(i);
				if (this.getBounds().intersects(temp.getBounds())) {
					temp.setDamageAbility(false);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,32,32);
	}

}
