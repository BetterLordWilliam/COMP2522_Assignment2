package life;

import life.Lifeform;
import world.RandomGenerator;
import world.Cell;

/**
 * Class to represent Herbivore lifeforms
 * 
 * @author Will Otterbein
 * @version 2024-1
 */
public class Herbivore extends Lifeform {
    
    public Herbivore() {
        this.LC = YELLOW;
        this.lifespan = 5;
        this.health = lifespan;
    }
    
    private void eat() {
    	
    }
    
    /**
     * Moves a herbivore.
     * It is possible that after a herbivore moves
     * other behaviours are triggered
     */
    private void move() {
    	if (this.c != null) {
	    	Cell oC = this.c;
	    	Cell nC = c.getNeighbours()[RandomGenerator.nextNumber(c.getNeighbours().length-1)];
	    	while (!nC.isEmpty) {
	    		nC = c.getNeighbours()[RandomGenerator.nextNumber(c.getNeighbours().length-1)];
	    	}
	    	this.c = nC;
	    	nC.setLifeform(this);
	    	oC.setLifeform(null);
    	}
    }
    
    /**
     * Defines the default sequence of Herbivore behaviour
     */
    @Override
    public void behave() {
    	if (health == 0)
    		die();
    	//health--;
        //move();
    }
}