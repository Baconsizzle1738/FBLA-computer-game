package me.Baconsizzle1738.main;

import java.awt.Graphics;

public class RoomBegin extends Room {

	public RoomBegin(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
	}

	@Override
	public void startLevel() {
		
	}

	@Override
	public boolean isComplete() {
		if (Game.gameStarted) {
			return true;
		}
		return false;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

}
