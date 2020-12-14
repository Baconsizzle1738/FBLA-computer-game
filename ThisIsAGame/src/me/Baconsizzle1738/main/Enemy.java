package me.Baconsizzle1738.main;
/**
 * The enemy is a <code>GameObject</code> that hurts the player.
 * @author Baconsizzle1738
 *
 */
public abstract class Enemy extends GameObject{
	protected int damageCooldown;
	protected int damage;
	protected int cooldownTimer = 0;
	protected boolean canDoDamage = false;
	HUD hud;
	
	/**
	 * The constructor instantiates all the bare minimum requirements for the enemy.
	 * 
	 * @param x			Starting x position
	 * @param y			Starting y position
	 * @param typeId	The <code>ID</code> of the <code>GameObject</code>.
	 * @param level		The level that the <code>GameObject</code> is in.
	 * @param cool		The cooldown for the damage.
	 * @param dmg		The damage dealt to the player
	 */
	public Enemy(int x, int y, ID typeId, int level, int cool, int dmg) {
		super(x, y, typeId, level);
		
		damageCooldown = cool;
		damage = dmg;
	}
	
	/**
	 * Changes whether or not <code>Enemy</code> can do damage.
	 * @param thing	Boolean variable that determines whether the <code>Enemy</code> can do damage or not.
	 */
	public void setDamageAbility(boolean thing) {
		canDoDamage = thing;
	}
}
