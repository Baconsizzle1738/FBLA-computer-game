package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//import java.io.File;
import java.io.FileWriter;

import me.Baconsizzle1738.Objects.*;
import me.Baconsizzle1738.main.*;

public class WinLevel extends Room{
	
	float alpha, inc;
	boolean start; 
	
	HUD hud;
	
	Button menuButton;
	
	public WinLevel(int spawnX, int spawnY, GameHandler h, int lvl, HUD hud) {
		super(spawnX, spawnY, h, lvl);
		
		alpha = 0;
		inc = 0.2f;
		start = false;
		
		menuButton = new Button(400, 100, ID.Button, lvl, "FREE AT LAST", 100, 20, Color.RED, new Color(180, 0, 0), Color.GRAY, new Font(Font.MONOSPACED, Font.BOLD, 12));
		
		this.hud = hud;
	}

	@Override
	public void startLevel() {
		start = true;
	}

	@Override
	public void reset() {
		alpha = 0;
		start = false;
	}

	@Override
	public boolean isComplete() {
		if (menuButton.isReleased() && hud.playerName.length()>1) {
			try {
				FileWriter f = new FileWriter("data/scores.dat");
				f.write(hud.playerName + " " + hud.score + "\n");
			}
			catch (Exception e) {
				System.out.println("Error in saving score");
				e.printStackTrace();
			}
			return true;
		}
		
		
		
		return false;
	}

	@Override
	public void tick() {
		if (start) {
			alpha++;
			if (alpha >= 1f) {
				alpha = 1;
				start = false;
			}
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0f, 0.8f, 0f, alpha));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setColor(new Color(180, 180, 180));
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
		g.drawString("YOU ESCAPED", 200,  200);
		g.drawString(hud.playerName + " ESCAPED WITH A SCORE OF "+ hud.score, 250, 300);
		
		
	}

}
