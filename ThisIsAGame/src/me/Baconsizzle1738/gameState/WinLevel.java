package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//import java.io.File;
import java.io.FileWriter;

import me.Baconsizzle1738.Objects.*;
import me.Baconsizzle1738.main.*;

public class WinLevel extends Room{
	
	float alpha, inc, alpha2, inc2;
	boolean start, end; 
	
	HUD hud;
	
	Button menuButton;
	
	public WinLevel(int spawnX, int spawnY, GameHandler h, int lvl, HUD hud) {
		super(spawnX, spawnY, h, lvl);
		
		alpha = 0;
		inc = 0.02f;
		start = false;
		end = false;
		alpha2 = 0;
		inc2 = 0.02f;
		
		menuButton = new Button(450, 450, ID.Button, lvl, "FREE AT LAST", 100, 20, new Color(0, 200, 0), new Color(0, 180, 0), Color.GRAY, new Font(Font.MONOSPACED, Font.BOLD, 12));
		
		this.hud = hud;
	}

	@Override
	public void startLevel() {
		start = true;
		handler.addObject(menuButton);
	}

	@Override
	public void reset() {
		//nothing here
	}

	@Override
	public boolean isComplete() {
		
		
		
		
		return false;
	}
	
	/**
	 * Saves the score of the player to a file
	 */
	public void saveScore() {
		try {
			FileWriter f = new FileWriter("data/scores.dat", true);
			f.append(hud.playerName + " " + hud.score + "\n");
			f.close();
		}
		catch (Exception e) {
			System.out.println("Error in saving score");
			e.printStackTrace();
		}
		
	}

	@Override
	public void tick() {
		if (start) {
			alpha+=inc;
			if (alpha >= 1f) {
				alpha = 1;
				start = false;
			}
		}
		if (menuButton.isReleased() && hud.playerName.length()>1) {
			end = true;
			handler.removeObject(menuButton);
		}
		if (end) {
			alpha2 += inc2;
			if (alpha2>=1f) {
				alpha = 1f;
				this.saveScore();
				System.exit(0);
			}
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(alpha2, 0.7f, alpha2, alpha));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setColor(new Color(180, 180, 180));
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 46));
		g.drawString("YOU ESCAPED", 350,  200);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
		g.drawString(hud.playerName, Game.WIDTH/2 - g.getFontMetrics().stringWidth(hud.playerName)/2-17, 300);
		g.drawString("ESCAPED WITH A SCORE OF "+ hud.score, Game.WIDTH/2 - g.getFontMetrics().stringWidth("ESCAPED WITH A SCORE OF "+ hud.score)/2, 350);
		
		
	}

	@Override
	public void hardReset() {
		alpha = 0;
		start = false;
		end = false;
		alpha2 = 0;
	}

}
