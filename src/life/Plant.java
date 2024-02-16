package life;

import java.awt.Color;

/**
 * Class to represent Plant lifeforms.
 * 
 * @author Will Otterbein
 * @version 2024-1
 */
public class Plant extends Lifeform implements HerbEdible {
	
	public Plant() {
		this.LC = GREEN;
		this.lifespan = 5;
		this.health = lifespan;
	}
	
	private void breed() {
		// TODO Auto-generated method stub
		System.out.println("Plant breeds");
	}
	
	// Behaviour of Plant
	@Override
	public void behave() {
		// Do the behavior of a herbivore... whateverelse that might be
	}
}
