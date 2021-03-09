package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.Baconsizzle1738.Objects.Button;
import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.main.ID;

public class DeathLevel extends Room{

	private Button menuButton;
	
	
	public DeathLevel(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
		menuButton = new Button(400, 100, ID.Button, lvl, "YOU TRIED", 100, 20, Color.RED, new Color(180, 0, 0), Color.GRAY, new Font(Font.MONOSPACED, Font.BOLD, 12));
	}

	@Override
	public void startLevel() {
		handler.addObject(menuButton);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
