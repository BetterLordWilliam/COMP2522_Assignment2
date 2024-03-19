package life;

import java.util.ArrayList;

import life.Lifeform;
import world.RandomGenerator;
import world.Cell;

/**
 * Class to represent Herbivore lifeforms
 * 
 * @author Will Otterbein
 * @version 2024-1
 */
public class Omnivore extends Lifeform {
    
    private static final int REQ_OMNI = 1;
    private static final int REQ_FOOD = 1;
    private static final int REQ_EMPTY = 3;
    
    public Omnivore() {
        setColour(BLUE);
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
    	this.health = this.lifespan + 1;	// replenish animal health
    }
    
    /**
     * Returns the Cell which is where the animal should go
     * 
     * @param oC old Cell of the herbivore
     * @return nC Cell where the animal will move
     */
    private Cell getGoodMove(Cell oC) {
        Cell[] cells = getCell().getNeighbours();
        Cell nC = cells[RandomGenerator.nextNumber(cells.length - 1)];
        if (nC.getLifeform() instanceof OmniEdible) {
            eat(nC.getLifeform());
        }
        return nC;
    }
    
    /**
     * Moves a herbivore.
     * It is possible that after a herbivore moves
     * other behaviours are triggered
     */
    private void move() {
    	if (getCell() != null) {
	    	Cell oC = getCell();
	    	Cell nC = getGoodMove(oC);
	    	setCell(nC);
	    	nC.setLifeform(this);
	    	oC.setLifeform(null);
    	}
    }
    
    /**
     * Function to handle the breeding logic for a plant.
     * 
     * """" Works by getting the plants neighbours, 
     *      saving empty ones and counting the numbers of plants and empty.
     *      Then it checks if these numbers match its conditions,
     *      randomly selects one of the empty ones,
     *      Puts a new plant there. """
     */
    private void breed() {
        if (getCell() != null) {
            Cell[] nS = getCell().getNeighbours();
            ArrayList<Cell> pMoves = new ArrayList<>();
            
            int nOmni = 0;
            int nFood = 0;
            int eCells = 0;
            
            // Determine the number of plant neighbours and empty cell neighbours
            // Save the empty ones in a list
            for (int i = 0; i < nS.length; i++) {
                Lifeform cL = nS[i].getLifeform();
                if (cL == null) {
                    pMoves.add(nS[i]);
                    eCells++;
                } else if (cL instanceof Omnivore) {
                    nOmni++;
                } else if (cL instanceof OmniEdible) {
                    nFood++;
                }
            }
            
            // If the breed conditions are met
            if (nFood >= REQ_FOOD && eCells >= REQ_EMPTY && nOmni == REQ_OMNI) {
                Cell nC = pMoves.get(RandomGenerator.nextNumber(eCells));
                nC.setLifeform(new Omnivore());
            }
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
        if (!moved) {
            move();
            moved = true;
        }
        if (!breeded) {
            breed();
            breeded = true;
        }
    }
}