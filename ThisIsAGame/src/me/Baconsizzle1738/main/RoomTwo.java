package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;

public class RoomTwo extends Room{

	public RoomTwo(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
	}

	@Override
	public boolean isComplete() {
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
		//test
		this.SetPlayerSpawn();
		handler.addObject(new StaticEnemy(210, 400, ID.StaticEnemy, lvl, 500, 3, 1, 40, -1));
		handler.addObject(new Obstacle(234, 483, ID.Obstacle, lvl, 625, 18, new Color(50, 200, 0)));
		
		
	}

	@Override
	public void reset() {
		this.startLevel();
	}

}
