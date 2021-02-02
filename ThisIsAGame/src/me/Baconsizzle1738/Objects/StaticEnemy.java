package me.Baconsizzle1738.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.Baconsizzle1738.main.Game;
import me.Baconsizzle1738.main.ID;
/**
 * StaticEnemy is an Enemy type that moves within a restrained location
 * @author Baconsizzle1738
 *
 */
public class StaticEnemy extends Enemy{
	
	private int beginX, beginY, endX, endY, volX, volY;
	
	
	//an enemy that moves within a restrained location
	
	//this is for enemy that moves in a "box"
	/**
	 *An enemy that moves within a restrained location
	 *
	 * @param x			Initial x position
	 * @param y			Initial y position
	 * @param typeId	ID of enemy
	 * @param level		Level the enemy is in
	 * @param x2		Second x position
	 * @param y2		Second y position
	 * @param volX		X velocity of StaticEnemy
	 * @param volY		Y velocity of StaticEnemy
	 * @param dmg		Magnitude of damage dealt by enemy per hit
	 * @param cd		Damage cooldown timer(in ticks)
	 */
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
	
	//constrained only to the x axis
	/**
	 * Enemy that moves within a constrained line
	 * 
	 * @param x			Initial x position
	 * @param y			Initial y position
	 * @param typeId	ID of enemy
	 * @param level		Level the enemy is in
	 * @param end		Second position, x or y
	 * @param vol		Velocity of StaticEnemy
	 * @param dmg		Magnitude of damage dealt by enemy per hit
	 * @param cd		Damage cooldown timer(in ticks)
	 * @param dir		Axis of movement
	 */
	public StaticEnemy(int x, int y, ID typeId, int level, int end, int vol, int dmg, int cd, String dir) {
		
		super(x, y, typeId, level, cd, dmg);
		beginX = x;
		beginY = y;
		
		if (dir.equals("x")) {
			endX = end;
			endY = y;
			this.volX = vol;
			this.volY = 0;
		}
		else if (dir.equals("y")) {
			endX = x;
			endY = end;
			this.volX = 0;
			this.volY = vol;
		}
		
		
		
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
	
	
//	public void setDamageAbility(boolean thing) {
//		canDoDamage = thing;
//	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,30,30);
	}
	
}
