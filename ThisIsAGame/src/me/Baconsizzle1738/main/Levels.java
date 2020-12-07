package me.Baconsizzle1738.main;

import java.awt.Graphics;

public class Levels {
	GameHandler handler;
	HUD hud;
	public static int level;
	
	public Levels(HUD hud, GameHandler handler) {
		this.hud = hud;
		this.handler = handler;
		handler.addObject(new Player(500, 500, ID.Player, 0));
		level = 0;
	}
	
	public void tick() {
		while (Game.gameStarted) {
			
		}
	}
	
	
	public void render(Graphics g) {
		
	}
}
