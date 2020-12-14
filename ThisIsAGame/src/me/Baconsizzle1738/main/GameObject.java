package me.Baconsizzle1738.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Class of basic variables and methods for all objects in the game.
 * 
 * @author Baconsizzle1738
 *
 */

public abstract class GameObject {
	
	//position and speed
	protected int x,y,volX,volY;
	protected ID typeId;
	protected int  levelId;
	/**
	 * Constructor takes in basic variables for a <code>GameObject</code>.
	 * 
	 * @param x			x position of object.
	 * @param y			y position of object.
	 * @param typeId	<code>ID</code> of object.
	 * @param level		level that the object is for.
	 */
	public GameObject(int x, int y, ID typeId, int level) {
		this.x = x;
		this.y = y;
		this.typeId = typeId;
		this.levelId = level;
	}
	
	
	/**
	 * Updates logic for the object.
	 */
	public abstract void tick();
	/**
	 * Updates graphics for object.
	 * @param g		Graphics board to render ohject on.
	 */
	public abstract void render(Graphics g);
	
	//for hitbox, Intersects method
	/**
	 * Makes a <code>Rectangle</code> hitbox for <code>GameObject<code>
	 * @return	A <code>Rectangle</code> containing x, y, width, and height of <code>GameObject</code>.
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * Sets the x position of <code>GameOnject</code>.
	 * @param x	x value of position.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the y position of <code>GameOnject</code>.
	 * @param y	y value of position.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Gets the x value of <code>GameObject</code>.
	 * @return	The current x position of <code>GameObject</code>.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y value of <code>GameObject</code>.
	 * @return	The current y position of <code>GameObject</code>.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the <code>ID</code> of the <code>GameObject</code>.
	 * @param id	<code>ID</code> for <code>GameObject</code>.
	 */
	public void settypeID(ID id) {
		this.typeId = id;
	}
	
	/**
	 * Sets the <code>levelID</code> of <code>GameObject</code>.
	 * @param lvl	Sets <code>levelId</code> to <code>lvl</code>.
	 */
	public void setLevelID(int lvl) {
		this.levelId = lvl;
	}
	
	/**
	 * Returns object <code>ID</code>.
	 * @return	Object <code>ID</code>.
	 */
	public ID gettypeID() {
		return typeId;
	}
	
	/**
	 * Returns object level.
	 * @return	Object <code>levelId</code>.
	 */
	public int getlevelID() {
		return levelId;
	}
	
	/**
	 * Sets the horizontal velocity of the object.
	 * @param volX	x velocity of <code>GameObject</code>.
	 */
	public void setVolX(int volX) {
		this.volX = volX;
	}
	
	/**
	 * Sets the vertical velocity of the object.
	 * @param volY	y velocity of <code>GameObject</code>.
	 */
	public void setVolY(int volY) {
		this.volY = volY;
	}
	
}