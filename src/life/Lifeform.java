package life;

import misc.Colors;
import world.Cell;

import java.awt.Color;

/**
 * Lifeform abstract class.
 * Commonalities of all the lifeforms
 * 
 * @author Will Otterbein
 * @version 2024-1
 */
public abstract class Lifeform implements Colors {
	// Attributes of lifeforms
	public int color;
	public int health;
	public int lifespan;
	public Color LC;
	public Cell c;
	
	/**
	 * Kills a specified Lifeform
	 * 
	 * Sets all references to this Lifeform to be NULL.
	 * Garbage collected will destroy this memory (hopefully quick)
	 */
	protected void die() {
		Cell oC = c;
		this.c.setLifeform(null);
		this.c = null;
	}
	
	// Behaviours of lifeforms
	public abstract void behave();
}
