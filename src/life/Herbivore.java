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
    
    /**
     * Kills the life form that is passed
     * Appropriately increases the lifeform who consumes health.
     * 
     * @param l Lifeform that we are going to eat
     */
    private void eat(Lifeform l) {
    	l.die();					// just call the die method for that plant
    	this.health = lifespan + 1;	// replenish animal health
    }
    
    /**
     * Returns the Cell which is where the animal should go
     * 
     * @param oC old Cell of the herbivore
     * @return nC Cell where the animal will move
     */
    private Cell getGoodMove(Cell oC) {
    	Cell nC = c.getNeighbours()[RandomGenerator.nextNumber(c.getNeighbours().length-1)];
    	while (!nC.isEmpty) {
    		if (nC.getLifeform() instanceof HerbEdible) {
    			// Found a plant!
    			eat(nC.getLifeform());		// Eat the plant
    			break;						// Return to move code
    		}
    		nC = c.getNeighbours()[RandomGenerator.nextNumber(c.getNeighbours().length-1)];
    	}
    	return nC;
    }
    
    /**
     * Moves a herbivore.
     * It is possible that after a herbivore moves
     * other behaviours are triggered
     */
    private void move() {
    	if (this.c != null) {
	    	Cell oC = this.c;
	    	Cell nC = getGoodMove(oC);
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
    	health--;
        move();
    }
}