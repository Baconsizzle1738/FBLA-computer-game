package me.Baconsizzle1738.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import me.Baconsizzle1738.Objects.Button;
import me.Baconsizzle1738.Objects.GameHandler;

/**
 * Listens to mouse inputs by the user for the game.
 * @author Baconsizzle1738
 *
 */
public class GameMouseListener implements MouseListener{
	
	
	private HUD hud;
	
	
	private GameHandler handler;
	/**
	 * Listens to mouse actions in <code>HUD</code>.
	 * @param h	The <code>HUD</code> where there is mouse functionality needed.
	 */
	public GameMouseListener(HUD h, GameHandler handler) {
		hud = h;
		this.handler = handler;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		
		//for button presses
		for (int i = 0; i<handler.objects.size(); i++) {
			Button test = new Button(i, i, null, i, null, i, i, null, null, null, null);
			if (handler.objects.get(i).getClass() == test.getClass()) {
				Button temp = (Button) handler.objects.get(i);
				if (temp.getBounds().contains(x, y)) {
					temp.setPress(true);
				}
			}
		}
		
//		//the start button
//		if (hud.startDeathButton.contains(x, y) && !Game.gameStarted && !Game.control && !Game.leaderboard) {
//			hud.isOnStartButton = true;
//		}
//		
//		//the controls button
//		if (hud.controlsButton.contains(x, y) && !Game.gameStarted && !Game.control && !Game.leaderboard) {
//			hud.isOnControlsButton = true;
//		}
//		
//		//controls back button
//		if (hud.controlBackButton.contains(x, y) && Game.control) {
//			hud.isOnControlBackButton = true;
//		}
//		
//		//death button
//		if (hud.startDeathButton.contains(x, y) && Game.isdead) {
//			hud.isOnDeathButton = true;
//			HUD.lives = 3;
//		}
//		
//		//win button
//		if (hud.winButton.contains(x, y) && Game.win && hud.playerName.length() != 0) {
//			hud.isOnWinButton = true;
//		}
//		
//		//leaderboard button
//		if (hud.leadButton.contains(x, y) && !Game.gameStarted && !Game.control && !Game.leaderboard) {
//			hud.isOnLeadButton = true;
//		}
//		
//		//back button for leaderboard
//		if (hud.leadBackButton.contains(x,y) && Game.leaderboard) {
//			hud.isOnLeadBackButton = true;
//		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		//for button presses
		for (int i = 0; i<handler.objects.size(); i++) {
			Button test = new Button(i, i, null, i, null, i, i, null, null, null, null);
			if (handler.objects.get(i).getClass() == test.getClass()) {
				Button temp = (Button) handler.objects.get(i);
				if (temp.getBounds().contains(x, y)) {
					temp.setPress(false);
					temp.setRelease(true);
				}
			}
		}
		
//		//the start button
//		if (hud.startDeathButton.contains(x, y) && !Game.gameStarted && !Game.control && !Game.leaderboard) {
//			hud.isOnStartButton = false;
//			Game.gameStarted = true;
//			//System.out.println("yeet");
//		}
//		
//		//controls button
//		if (hud.controlsButton.contains(x, y) && !Game.gameStarted && !Game.control && !Game.leaderboard) {
//			hud.isOnControlsButton = false;
//			Game.control = true;
//		}
//		
//		//controls back button
//		if (hud.controlBackButton.contains(x, y) && Game.control) {
//			hud.isOnControlBackButton = false;
//			Game.control = false;
//		}
//		
//		//death button
//		if (hud.startDeathButton.contains(x, y) && Game.isdead) {
//			hud.isOnDeathButton = false;
//			Game.gameStarted = false;
//			Game.isdead = false;
//			//System.out.println("yeet");
//		}
//		
//		//win button, saves player score data upon release
//		if (hud.winButton.contains(x, y) && Game.win && hud.playerName.length() != 0) {
//			hud.isOnWinButton = false;
//			Game.gameStarted = false;
//			Game.win = false;
//			Game.takingInput = false;
//			
//			try {
//				FileWriter write = new FileWriter("data/scores.dat", true);
//				
//				write.write(hud.playerName + " " + hud.score + "\n");
//				write.close();
//			}
//			catch (Exception ex) {
//				System.out.println("Error in saving score");
//			}
//			
//			//update the set of score data, put it in the set so it now appears on the leaderboard
//			try {
//				File f = new File("data/scores.dat");
//				Scanner scan = new Scanner(f);
//				Game.scoreData.clear();
//				while (scan.hasNext()) {
//					Game.scoreData.add(new ScoreData(scan.nextLine()));
//				}
//				scan.close();
//			}
//			catch (FileNotFoundException ex) {
//				System.out.println("There was an error.");
//			}
//		}
//		
//		//leaderboard button
//		if (hud.leadButton.contains(x, y) && !Game.gameStarted && !Game.control && !Game.leaderboard) {
//			hud.isOnLeadButton = false;
//			Game.leaderboard = true;
//			System.out.println("LEADERBOARD REEE");
//		}
//		
//		//back button for leaderboard
//		if (hud.leadBackButton.contains(x,y) && Game.leaderboard && Game.cd == 5) {
//			hud.isOnLeadBackButton = false;
//			Game.leaderboard = false;
//		}
				
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
