package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class StaticEnemy extends Enemy{
	
	private int beginX, beginY, endX, endY, volX, volY;
	
	
	//an enemy that moves within a restrained location
	public StaticEnemy(int x, int y, ID typeId, int level, int x2, int y2, int volX, int volY, int dmg, int cd) {
		
		super(x, y, typeId, level, cd, dmg);
		beginX = x;
		beginY = y;
		endX = x2;
		endY = y2;
		this.volX = volX;
		this.volY = volY;
		if (beginX > endX) {
			volX*=-1;
		}
		if (beginY > endY) {
			volY*=-1;
		}
		
	}
	
	public StaticEnemy(int x, int y, ID typeId, int level, int x2, int volX, int dmg, int cd) {
		
		super(x, y, typeId, level, cd, dmg);
		beginX = x;
		beginY = y;
		endX = x2;
		endY = y;
		this.volX = volX;
		this.volY = 0;
		if (beginX > endX) {
			volX*=-1;
		}
	}
	
	public StaticEnemy(int x, int y, ID typeId, int level, int y2, int volY, int dmg, int cd, int temp) {
		
		super(x, y, typeId, level, cd, dmg);
		beginX = x;
		beginY = y;
		endX = x;
		endY = y2;
		this.volX = 0;
		this.volY = volY;
		if (beginX > endX) {
			volX*=-1;
		}
	}

	@Override
	public void tick() {
		if (!canDoDamage) {
			cooldownTimer++;
			if (cooldownTimer>=damageCooldown) {
				canDoDamage = true;
				cooldownTimer = 0;
			}
		}
		
		x+=volX;
		y+=volY;
		
		//check for the movement bounds for the static enemy
		if (endX>beginX) {
			if (x+volX != Game.clamp(x+volX, beginX, endX)) {
				volX*=-1;
			}
		}
		else if (endX<beginX) {
			if (x+volX != Game.clamp(x+volX, endX, beginX)) {
				volX*=-1;
			}
		}
		
		if (endY>beginY) {
			if (y+volY != Game.clamp(y+volY, beginY, endY)) {
				volY*=-1;
			}
		}
		else if (endY<beginY) {
			if (y+volY != Game.clamp(y+volY, endY, beginY)) {
				volY*=-1;
			}
		}
		
		
		
//		if (endX>beginX) {
//			if (x>endX || x<beginX) {
//				volX*=-1;
//			}
//		}
//		else if (endX<beginX) {
//			if (x<endX || x>beginX) {
//				volX*=-1;
//			}
//		}
//		
//		if (endY>beginY) {
//			if (y>endY || y<beginY) {
//				volY*=-1;
//			}
//		}
//		else if (endY<beginY) {
//			if (y<endY || y>beginY) {
//				volY*=-1;
//			}
//		}
		
	}

	@Override
	public void render(Graphics g) {
		
		
		g.setColor(Color.red);
		g.fillRect(x, y, 30, 30);
		
	}
	
	public void setDamageAbility(boolean thing) {
		canDoDamage = thing;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,30,30);
	}
	
}
