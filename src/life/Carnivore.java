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
public class Carnivore extends Lifeform implements OmniEdible {
    
    private static final int REQ_CARN = 1;
    private static final int REQ_HERB = 2;
    private static final int REQ_EMPTY = 3;
    
    public Carnivore() {
        setColour(RED);
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
        Cell[] cellsActual = getCell().getNeighbours();
        Cell[] cells = cellsActual.clone();
        int index = RandomGenerator.nextNumber(cells.length - 1);
        Cell nC = cells[index];
        while(!nC.isEmpty && cells[index] == null) {
            if (nC.getLifeform() instanceof OmniEdible) {
                eat(nC.getLifeform());
            }
            cells[index] = null;
            index = RandomGenerator.nextNumber(cells.length - 1);
            nC = cellsActual[index];
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
            
            int nHerb = 0;
            int nCarn = 0;
            int eCells = 0;
            
            // Determine the number of plant neighbours and empty cell neighbours
            // Save the empty ones in a list
            for (int i = 0; i < nS.length; i++) {
                Lifeform cL = nS[i].getLifeform();
                if (cL == null) {
                    pMoves.add(nS[i]);
                    eCells++;
                } else if (cL instanceof HerbEdible) {
                    nHerb++;
                } else if (cL instanceof Carnivore) {
                    nCarn++;
                }
            }
            
            // If the breed conditions are met
            if (nHerb >= REQ_HERB && eCells >= REQ_EMPTY && nCarn >= REQ_CARN) {
                Cell nC = pMoves.get(RandomGenerator.nextNumber(eCells));
                nC.setLifeform(new Carnivore());
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