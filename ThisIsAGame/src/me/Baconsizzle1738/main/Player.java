   package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	GameHandler handler;
	HUD hud;
	public Player(int x, int y, ID typeId, int level, GameHandler h) {
		super(x, y, typeId, level);
		handler = h;
	}

	@Override
	public void tick() {
		x+=volX;
		y+=volY;
		
		for (int i = 0; i<handler.objects.size(); i++) {
			
			//for enemy collision detection
			if (handler.objects.get(i).gettypeID() == ID.Enemy || handler.objects.get(i).gettypeID() == ID.FollowEnemy) {
				
				Enemy temp = (Enemy) handler.objects.get(i);
				if (this.getBounds().intersects(temp.getBounds())) {
					//System.out.println("heq");
					if (temp.canDoDamage) {
						Game.health-=temp.damage;
						//System.out.println("hit");
						temp.setDamageAbility(false);
					}
				}
				
			}
			
			//for obstacle collision detection
			if (handler.objects.get(i).gettypeID() == ID.Obstacle) {
				
				Obstacle temp = (Obstacle) handler.objects.get(i);
				if (this.getBounds().intersects(temp.getBounds())) {
					
					x-=volX;
					y-=volY;
					
					if ((x+32)-temp.getX() != 0 && x+32 < temp.getX()) {
						x += temp.getX()-(x+32);
					}
					
					if (x - (temp.getX()+temp.getWidth()) != 0 && x > temp.getX()+temp.getWidth()) {
						x -= x - (temp.getX()+temp.getWidth());
					}
					
					//for y
					if ((y+32)-temp.getY() != 0 && y+32 < temp.getY()) {
						y += temp.getY()-(y+32);
					}
					
					if (y - (temp.getY()+temp.getHeight()) != 0 && y > temp.getY()+temp.getHeight()) {
						y -= y - (temp.getY()+temp.getHeight());
					}
					
//					if (x+32>temp.getX()) {
//						x = temp.getX()-32;
//					}
//					else if (x<temp.getX()) {
//						x = temp.getX()+temp.getWidth();
//					}
					
				}
				
			}
		}
		
		x = Game.clamp(x, 0, Game.WIDTH-48);//x is 16 px off
		y = Game.clamp(y, 0, Game.HEIGHT-71);//y is 39 px off
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 32, 32);
		
	}
	
//	public boolean isHitByEnemy() {
//		
//		return false;
//	}
	
//	public void hitByEnemy(Enemy e) {
//		Game.health-=e.damage;
//	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

}
