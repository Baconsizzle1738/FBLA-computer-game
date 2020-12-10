package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;

public class RoomOne extends Room {

	public RoomOne(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		//test
		
	}
	
	@Override
	public boolean isComplete() {
		//test to see if work
		for (int i = 0; i<handler.objects.size(); i++) {
			GameObject temp = handler.objects.get(i);
			if (temp.gettypeID() == ID.Player) {
				if (temp.getX()>500) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void tick() {
		if (started) {
			
		}
	}

	@Override
	public void render(Graphics g) {
		if (started) {
			
		}
	}

	@Override
	public void startLevel() {
		handler.addObject(new StaticEnemy(200, 200, ID.StaticEnemy, lvl, 500, 3, 1, 40));
		handler.addObject(new Obstacle(300, 100, ID.Obstacle, lvl, 20, 100, new Color(50, 200, 0)));
	}

}
