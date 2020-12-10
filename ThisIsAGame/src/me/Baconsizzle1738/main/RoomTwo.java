package me.Baconsizzle1738.main;

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
		handler.addObject(new StaticEnemy(210, 400, ID.StaticEnemy, lvl, 500, 3, 1, 40, -1));
	}

}
