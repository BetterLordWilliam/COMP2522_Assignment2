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
	protected int health;
	protected int lifespan;
	private Color LC;
	private Cell c;
	
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
	
	/**
	 * Sets the lifeforms colour.
	 * 
	 * @param c
	 */
	public void setColour(Color c) {
		LC = c;
	}
	
	/**
	 * Sets the lifeforms Cell.
	 * 
	 * @param c
	 */
	public void setCell(Cell c) {
		this.c = c;
	}
	
	/**
	 * Gets the lifeforms Cell.
	 * 
	 * @return
	 */
	public Cell getCell() {
		return c;
	}
	
	/**
	 * Gets the lifeforms Colour.
	 * 
	 * @return
	 */
	public Color getColour() {
		return LC;
	}
}
