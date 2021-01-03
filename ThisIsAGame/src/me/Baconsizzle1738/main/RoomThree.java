package me.Baconsizzle1738.main;

import java.awt.Graphics;
import java.awt.Point;

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
		handler.addObject(new FollowEnemy(400, 410, ID.FollowEnemy, lvl, 109, 20, 0.69f, handler));
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
			handler.addObject(new FollowEnemy(400, 410, ID.FollowEnemy, lvl, 109, 20, 0.69f, handler));
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
			System.out.println(resetCool);
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
