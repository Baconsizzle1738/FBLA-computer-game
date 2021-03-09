package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

import me.Baconsizzle1738.Objects.*;
import me.Baconsizzle1738.main.*;

public class Leaderboard extends Room{

	private Button backButton;
	
	private TreeSet<ScoreData> scores;
	
	public Leaderboard(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
		Font f = new Font(Font.MONOSPACED, Font.BOLD, 10);
		
		scores = new TreeSet<ScoreData>();
		
		backButton = new Button(450, 500, ID.Button, lvl, "BACK", 100, 30, Color.red, new Color(160, 0, 0), new Color(200, 200, 200), f);
	}

	@Override
	public void startLevel() {
		handler.addObject(backButton);
		backButton.setRelease(false);
		
		scores.clear();
		try {
			Scanner scan = new Scanner(new File("data/scores.dat"));
			while (scan.hasNextLine()) {
				scores.add(new ScoreData(scan.nextLine()));
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reset() {
		
	}

	@Override
	public boolean isComplete() {
		return backButton.isReleased();
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(180, 0, 0));
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		g.drawString("LEADERBOARD", 330, 90);
		
		g.setColor(new Color(175, 175, 175));
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		g.drawString("1.", 200, 150);
		g.drawString("2.", 200, 175);
		g.drawString("3.", 200, 200);
		g.drawString("4.", 200, 225);
		g.drawString("5.", 200, 250);
		g.drawString("6.", 200, 275);
		g.drawString("7.", 200, 300);
		g.drawString("8.", 200, 325);
		g.drawString("9.", 200, 350);
		g.drawString("10.", 200, 375);
		
		int cnt = 0;
		for (ScoreData s : scores) {
			//System.out.println(cnt);
			g.drawString(s.getName(), 235, 150+25*cnt);
			g.drawString(s.getScore()+"", 700, 150+25*cnt);
			cnt++;
			if (cnt>= 10) {
				cnt = 0;
				break;
			}
		}
	}

	@Override
	public void hardReset() {
		//nothing here
	}

}
