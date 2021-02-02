package me.Baconsizzle1738.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map.Entry;
//import java.util.Set;

import me.Baconsizzle1738.main.ID;

/**
 * The <code>Dinnerbone GameObject</code> is a reference to the easter egg
 * in Minecraft where the player can name an entity "dinnerbone" and have
 * it flip upside down. In Room 2, the player has to rotate this "box" 180
 * degrees in order to pass the level.
 * 
 * @author Baconsizzle1738
 *
 */
public class Dinnerbone extends GameObject{
	
	private double orientation;
	private HashMap<String, Double> actions;
	private HashMap<String, Boolean> actionDone;
	//private int test = 0;
	
	/**
	 * The box for the player to turn upside down on room 2
	 * 
	 * @param x			x position of box
	 * @param y			y position of box
	 * @param typeId	ID of box
	 * @param level		level the box belongs in
	 */
	public Dinnerbone(int x, int y, ID typeId, int level) {
		super(x, y, typeId, level);
		orientation = 0;
		
		actions = new HashMap<String, Double>();
		actionDone = new HashMap<String, Boolean>();
		
		actions.put("turn", 45.0);
		actions.put("turn2", 90.0);
		actions.put("reflect", 0.0);
		actions.put("turnR", -45.0);
		
		actionDone.put("turn", false);
		actionDone.put("turn2", false);
		actionDone.put("reflect", false);
		actionDone.put("turnR", false);
	}
	
	/**
	 * Performs desired action on the box
	 * @param a	Action to perform on the box
	 */
	public void doAction(String a) {
		//check if action has been done before
		if (!actionDone.get(a)) {
			//do action
			orientation+=actions.get(a);
			if (!a.equals("reflect")) {
				actionDone.replace(a, true);
			}
			
		}
		else {
			//undo action
			orientation-=actions.get(a);
			actionDone.replace(a, false);
		}
		
		//force all angles to be coterminal with their respective angles between 360 and 0 deg
		if (orientation>360) {
			orientation-=360;
		}
		if (orientation<0) {
			orientation+=360;
		}
		//update the reflect action to current orientation
		actions.remove("reflect");
		actions.put("reflect", 360-2*orientation);
		//actions.replace("turnR", 360-(2*orientation)+90);
	}
	
//	public void setOrientation (double o) {
//		orientation = o;
//	}
	/**
	 * Resets data of the box to default position (0.0 deg, no actions performed)
	 */
	public void reset() {
		//reset orientation
		orientation = 0.0;
		//make it as if no action has been performed yet
		actionDone.replace("turn", false);
		actionDone.replace("turn2", false);
		actionDone.replace("reflect", false);
		actionDone.replace("turnR", false);
		//update reflect action to 0.0 degrees
		actions.replace("reflect", 0.0);
		//actions.replace("turnR", 0.0);
	}
	
//	private void addOrientation (double o) {
//		orientation += o;
//	}
	/**
	 * Returns the orientation of the box in degrees
	 * @return	Returns the orientation of the box in degrees (double)
	 */
	public double getOrientation() {
		return orientation;
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
		//new graphics board so transformations do not apply to the entire screen
		Graphics2D g2 = (Graphics2D) g.create();
		
		//center the graphics board so the box rotates about its center point
		g2.translate(x, y);
		g2.rotate(Math.toRadians(orientation));
		
		//draw actual box
		g2.setColor(new Color(0, 150, 20));
		g2.fillRect(-25, -25, 50, 50);
		g2.setColor(new Color(210, 210, 210));
		g2.drawString("UP ^", -15, -5);
		g2.dispose();
		
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x-25, y-25, 50, 50);
	}

}
