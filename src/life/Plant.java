package life;

import java.awt.Color;
import java.util.ArrayList;

import world.Cell;
import world.RandomGenerator;

/**
 * Class to represent Plant lifeforms.
 * 
 * @author Will Otterbein
 * @version 2024-1
 */
public class Plant extends Lifeform implements HerbEdible, OmniEdible {
	
	private static final int REQ_PLANTS = 2;
	private static final int REQ_EMPTY = 3;
	
	public Plant() {
		setColour(GREEN);
		this.lifespan = 5;
		this.health = lifespan;
	}
	
	/**
	 * Function to handle the breeding logic for a plant.
	 * 
	 * """" Works by getting the plants neighbours, 
	 * 		saving empty ones and counting the numbers of plants and empty.
	 * 		Then it checks if these numbers match its conditions,
	 * 		randomly selects one of the empty ones,
	 * 		Puts a new plant there. """
	 */
	public void breed() {
    	if (getCell() != null) {
        	Cell[] nS = getCell().getNeighbours();
        	ArrayList<Cell> pMoves = new ArrayList<>();
        	
        	int nPlants = 0;
        	int eCells = 0;
        	
        	// Determine the number of plant neighbours and empty cell neighbours
        	// Save the empty ones in a list
        	for (int i = 0; i < nS.length; i++) {
        		Lifeform cL = nS[i].getLifeform();
        		if (cL == null) {		// Empty cells (save those)
        			pMoves.add(nS[i]);
        			eCells++;			// Adjacent plants
        		} else if (cL instanceof HerbEdible) {
        			nPlants++;
        		}
        	}
        	
        	// System.out.println(this + " " + nPlants + " " + eCells);
        	
        	// If the breed conditions are met
        	if (nPlants == REQ_PLANTS && eCells >= REQ_EMPTY) {
        		Cell nC = pMoves.get(RandomGenerator.nextNumber(eCells));
        		nC.setLifeform(new Plant());	// Seed the random cell with a plant
        	}
    	}
	}
	
	/**
	 * Function to handle the various behaviours of a plant.
	 */
	@Override
	public void behave() {
		// Do the behavior of a herbivore... whateverelse that might be
		breed();
	}
}
