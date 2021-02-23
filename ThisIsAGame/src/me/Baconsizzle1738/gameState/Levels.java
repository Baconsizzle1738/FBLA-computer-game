package me.Baconsizzle1738.gameState;

//github reee
import java.awt.Graphics;
import java.util.ArrayList;

import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.Objects.GameObject;
import me.Baconsizzle1738.main.Game;
import me.Baconsizzle1738.main.HUD;

/**
 * Controls leveling system.
 * @author Baconsizzle1738
 *
 */
public class Levels {
	GameHandler handler;
	HUD hud;
	public int level;
	private int numLevels, controlLevel, leadLevel;
	private ArrayList<Room> room;
	private ArrayList<Transition> transition;
	
	//for the first level to initiate level progression
	private boolean init;
	
	
	/**
	 * Takes handler and hud as a parameter and creates a list of the levels.
	 * @param handler
	 * @param hud
	 */
	public Levels(HUD hud, GameHandler handler) {
		//this.hud = hud;
		this.handler = handler;
		//handler.addObject(new Player(500, 500, ID.Player, -1, this.handler));
		level = 0;
		numLevels = 3;
		
		controlLevel = numLevels+1;
		leadLevel = numLevels+2;
		
		//room.add(new RoomBegin(350, 350, handler, 0));
		room = new ArrayList<>();
		transition = new ArrayList<>();
		//add levels to the list
		room.add(new RoomBegin(0, 0, handler, 0));
		room.add(new RoomOne(305, 300, handler, 1));
		room.add(new RoomTwo(255, 500, handler, 2));
		room.add(new RoomThree(400, 400, handler, 3));
		
		//the main menu rooms
		room.add(new RoomControls(0, 0, handler, controlLevel));
		room.add(new Leaderboard(0, 0, handler, leadLevel));
		
		
		//transitions
		transition.add(new TransitionLevel(new String[]{"first", "second", "last"}, 0));
		transition.add(new TransitionLevel(new String[]{"on", "tw", "th"}, 1));
		transition.add(new TransitionLevel(new String[]{"ree", "reeee", "reeeee", "reeeeeeeeeee"}, 2));
		transition.add(new TransitionLevel(new String[]{"first", "second", "last"}, 3));
		
		//for the first level to initialize
		init = false;
		
		//start with the main menu screen.
		room.get(0).startLevel();
	}
	
	/**
	 * Checks status of level completion on every update.
	 */
	public void tick() {
		//System.out.println(level);
		//if (Game.gameStarted && !Game.isdead && !Game.win) {
		//initiates the first room when the game starts
//		if (!init) {
//			room.get(level).startLevel();
//			init = true;
//		}
		
		//System.out.println(((RoomBegin)room.get(0)).getButton("control").isReleased());
		
		//Why in HECK are the buttons not working proper >:O
		
		
		if (level == 0) {
			//System.out.println("level Check 0");
			//go to controls page
			if (((RoomBegin) room.get(0)).goControls()) {
				//((RoomBegin) room.get(0)).getButton("controls").setRelease(false);
				removeLevelObjects();
				level = controlLevel;
				room.get(level).startLevel();
				//System.out.println(level);
				//System.out.println("goControlsLevel");
			}
			
			//go to leaderboards page
			if (((RoomBegin) room.get(0)).goLead()) {
				//((RoomBegin) room.get(0)).getButton("lead").setRelease(false);
				removeLevelObjects();
				level = leadLevel;
				room.get(level).startLevel();
			}
		}
		
		//check for completion
		if (room.get(level).isComplete()) {
			removeLevelObjects();
			
			//if the room is at a menu screen then return to main menu, otherwise start transition.
			if (level == controlLevel || level == leadLevel) {
				//System.out.println(level);
				((RoomControls)room.get(controlLevel)).getButton().setRelease(false);
				level = 0;
				room.get(level).startLevel();
			}
			
			else {
				//TODO make it so that the transition actually 
				transition.get(level).startTransition();
			}
			
			//System.out.println(level);
			//System.out.println(room.get(level-1).isComplete());
			
			//check if player has completed all levels, win state is activated when true
//			if (level<=numLevels) {
//				room.get(level).startLevel();
//			}
//			else {
//				Game.win = true;
//				Game.takingInput = true;
//				resetDefault();
//			}
		}
		
		//when the transition is done advance level
		if (!isOnMenuLevel()) {
			if (transition.get(level).endTransition()) {
				level++;
				room.get(level).startLevel();
			}
		}
		
		
		//tick only when transition is not complete
		if (!isOnMenuLevel()) {
			if (!transition.get(level).endTransition()) {
				transition.get(level).tick();
			}
		}
		
		
		
		//tick only when the room is not complete
		if (!room.get(level).isComplete()) {
			room.get(level).tick();
		}
		
		//System.out.println(((RoomBegin) room.get(0)).goControls());
		//System.out.println(level);
		//}
		//check if dead
		if (Game.isdead) {
			resetDefault();
		}
	}
	
	/**
	 * Removes all level specific objects
	 */
	public void removeLevelObjects() {
		for (int i = 0; i<handler.objects.size(); i++) {
			GameObject temp = handler.objects.get(i);
			if (temp.getlevelID() == level) {
				handler.removeObject(temp);
				i--;
			}
		}
	}
	
	/**
	 * resets the level
	 */
	public void resetLevel() {
		room.get(level).reset();
	}
	
	/**
	 * Resets everything to default, lives are unaffected.
	 */
	public void resetDefault() {
		
		init = false;
		removeLevelObjects();
		level = 0;
		Game.health = 100;
		Game.keys.reset();
	}
	
	/**
	 * The hud to get health and life data for use in <code>Levels</code>.
	 * @param hud	The <code>HUD</code>.
	 */
	public void setHUD(HUD hud) {
		this.hud = hud;
	}
	/**
	 * Renders the room/level.
	 * @param g	Graphics board to render <code>Room</code> on.
	 */
	public void render(Graphics g) {
		//render only when room is not complete
		if (!room.get(level).isComplete()) {
			room.get(level).render(g);
		}
		
		//render only when incomplete
		if (!isOnMenuLevel()) {
			if (!transition.get(level).endTransition()) {
				transition.get(level).render(g);
			}
		}
		
		
	}
	
	
	private boolean isOnMenuLevel() {
		if (level == controlLevel || level == leadLevel) {
			return true;
		}
		return false;
	}
}
