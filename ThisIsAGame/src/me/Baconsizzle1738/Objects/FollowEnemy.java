package me.Baconsizzle1738.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.Baconsizzle1738.main.ID;


/**
 * The <code>FollowEnemy</code> enemy is a type of <code>Enemy</code>
 * that follows the player at a constant speed.
 *  
 * @author Baconsizzle1738
 *
 */
public class FollowEnemy extends Enemy{
	
	private float trueVol, trueX, trueY, trueVolX, trueVolY;
	GameHandler handler;
	Player p;
	
	/**
	 * Enemy that always follows the player
	 * 
	 * @param x			Starting x position
	 * @param y			Starting y position
	 * @param typeId	GameObject ID
	 * @param level		Level the GameObject belongs to
	 * @param cool		Damage cooldown
	 * @param dmg		Damage dealt on contact with the player
	 * @param trueVol	The velocity the Enemy travels toward the player at at all times
	 * @param h			GameHandler to get player data
	 */
	public FollowEnemy(int x, int y, ID typeId, int level, int cool, int dmg, float trueVol, GameHandler h) {
		super(x, y, typeId, level, cool, dmg);
		this.trueVol = trueVol;
		trueX = x;
		trueY = y;
		handler = h;
		
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Player) {
				p = (Player) handler.objects.get(i);
				break;
			}
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
		
		//vector math yahooey
		int distX = p.getX() - x;
		int distY = p.getY() - y;
		//this is scalar
		float trueDist = (float) Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2));
		float scale = trueDist/trueVol;
		trueVolX = distX/scale;
		trueVolY = distY/scale;
		
		if (x != p.getX()) {
			trueX+= trueVolX;
			x = (int) trueX;
		}
		if (y != p.getY()) {
			trueY+= trueVolY;
			y = (int) trueY;
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(230, 20, 0));
		g.fillRect(x, y, 20, 20);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 20);
	}

}
