package life;

import misc.Colors;
import world.Cell;

import java.awt.Color;

//Lifeform Class
//Author: Will Otterbein
//Version: 2024-1
public abstract class Lifeform implements Colors {
	// Attributes of lifeforms
	public int color;
	public int health;
	public int lifespan;
	public Color LC;
	public Cell c;
	
	// Make the cell die
	protected void die() {
		// TODO Auto-generated method stub
		Cell oC = c;
		this.c.setLifeform(null);
		this.c = null;
	}
	
	// Behaviours of lifeforms
	public abstract void behave();
}
