package life;

import java.awt.Color;

//For now, color legend:
//1 -- green
//2 -- yellow

// Plant Class
// Author: Will Otterbein
// Version: 2024-1
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
