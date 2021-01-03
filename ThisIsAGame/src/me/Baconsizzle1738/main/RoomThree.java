package me.Baconsizzle1738.main;

import java.awt.Graphics;
import java.awt.Point;

/**
 * This is the third room.
 * The player must find the correct number and collect it in order
 * to advance to the next level. In this case, the number is 3 as
 * hinted by the fact that the Level indicator in the HUD displays
 * MISSINGNO instead of 3.
 * 
 * @author zheng
 *
 */
public class RoomThree extends Room{
	
	Player p;
	
	private Point[][] grass;

	public RoomThree(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
		p = new Player(spawnX, spawnY, ID.Player, lvl, handler);
		grass = new Point[25][30];
		//put points in for the grass generation
		for (int i = 0; i<grass.length; i++) {
			for (int j = 0; j<grass[i].length; j++) {
				grass[i][j] = new Point(grassX(i), grassY(j));
			}
		}
	}
	
	private int grassX(int n) {
		return 250+20*n;
	}
	
	private int grassY(int n) {
		return 100+20*n;
	}
	
	@Override
	public void startLevel() {
		
		handler.addObject(p);
		
		handler.addObject(new FollowEnemy(280, 150, ID.FollowEnemy, lvl, 85, 10, 0.62f, handler));
		handler.addObject(new FollowEnemy(550, 410, ID.FollowEnemy, lvl, 109, 20, 1.2f, handler));
		
		handler.addObject(new StaticEnemy(grassX(12), grassY(0), ID.Enemy, lvl, Game.HEIGHT-50, 3, 8, 25, "y"));
		handler.addObject(new StaticEnemy(grassX(0), grassY(10), ID.Enemy, lvl, grassX(23), 3, 8, 25, "x"));
		for (int i = 0; i<grass.length; i++) {
			for (int j = 0; j<grass[i].length; j++) {
				handler.addObject(new GrassBlock(grass[i][j].x, grass[i][j].y, ID.Interactable, lvl, p));
			}
		}
		
	}
	
	@Override
	public void reset() {
		if (canReset) {
			for (int i = 0; i<handler.objects.size(); i++) {
				if (handler.objects.get(i).gettypeID() == ID.FollowEnemy || handler.objects.get(i).gettypeID() == ID.Interactable) {
					handler.removeObject(handler.objects.get(i));
					i--;
				}
			}
			handler.addObject(new FollowEnemy(280, 150, ID.FollowEnemy, lvl, 85, 10, 0.62f, handler));
			handler.addObject(new FollowEnemy(550, 410, ID.FollowEnemy, lvl, 109, 20, 0.69f, handler));
			for (int i = 0; i<grass.length; i++) {
				for (int j = 0; j<grass[i].length; j++) {
					handler.addObject(new GrassBlock(grass[i][j].x, grass[i][j].y, ID.Interactable, lvl, p));
				}
			}
			SetPlayerSpawn();
			canReset = false;
		}
		
		
	}

	@Override
	public boolean isComplete() {
		
		return false;
	}

	@Override
	public void tick() {
		//level reset cooldown timer
		if (!canReset) {
			resetCool++;
			//System.out.println(resetCool);
			if (resetCool>50) {
				canReset = true;
				resetCool = 0;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
	}

}
