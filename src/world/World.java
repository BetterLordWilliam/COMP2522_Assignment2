package world;

import java.util.*;

import life.Lifeform;
import life.Herbivore;
import life.Plant;
import world.Cell;
import world.RandomGenerator;

/**
 * World class.
 * Behaviours and attributes of the world for The Game of Life.
 * 
 * @author Will Otterbein
 * @version 2024-1
 */
public class World {
    public Cell[][] cells;
    
    public int worldX;
    public int worldY;
    
    /**
     * Constructor for the world.
     * 
     * @param sizeX
     * @param sizeY
     */
    public World(int sizeX, int sizeY) {
        worldX = sizeX;
        worldY = sizeY;
        
        cells = new Cell[worldY][worldX];
    }
    
    /**
     * Method to return the cells around a cell c
     * 
     * @param c Cell object instance
     * @return array of Cells
     */
    private Cell[] returnNeighbours(int x, int y) {
    	List<Cell> neighbours = new ArrayList<>();
    	
    	// Possible directions
    	int[][] directions = {
			{-1,-1},{0, -1},{1,-1},
			{-1,0}		   ,{1,0},
			{-1,1},	{0,1}  ,{1,1}
    	};
    	
    	// Add the viable neighbours
    	for (int[] direction : directions) {
    		int newX = x + direction[0];	// Calculate the X
    		int newY = y + direction[1];	// Calculate the Y
    		
    		if (newX >= 0 && newX < worldX 
    		 && newY >= 0 && newY < worldY) {
    			neighbours.add(cells[newY][newX]);
    		}
    	}
    	
    	return neighbours.toArray(new Cell[0]);
    }
    
    /**
     * Populate the world with empty cells.
     */
    public void populate() {
        // Initialize all the cells of the world.
        for (int y = 0; y < worldY; y++) {
            for (int x = 0; x < worldX; x++) {
                cells[y][x] = new Cell();
                int lp = RandomGenerator.nextNumber(100);
                if (lp > 85) {
                    cells[y][x].setLifeform(new Herbivore());
                } else if (lp > 65) {
                    cells[y][x].setLifeform(new Plant());
                }
            }
        }
        
        // Set the neighbours of cells.
        for (int y = 0; y < worldY; y++) {
        	for (int x = 0; x < worldX; x++) {
        		// Tell the cell who it is beside
        		cells[y][x].setNeighbours(
    				returnNeighbours(x, y)
    			);
        	}
        }
    }
    
    /**
     * Cycle through the behaviour of Cell Lifeforms.
     */
    public void cycle() {
    	LinkedList<Lifeform> behaviourQueue = new LinkedList<>();
    	
        // Enqueue the lifeform behaviours
    	for (int y = 0; y < worldY; y++) {
    		for (int x = 0; x < worldX; x++) {
    			if (cells[y][x].getLifeform() != null) {
    				behaviourQueue.add(cells[y][x].getLifeform());
    			}
    		}
    	}
    	
    	// Complete the lifeform behaviours for the cycle
    	while (!behaviourQueue.isEmpty()) {
    		Lifeform l = behaviourQueue.pollFirst();
    		l.behave();
    	}
    }
}