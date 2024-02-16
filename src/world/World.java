package world;

import life.Lifeform;
import life.Herbivore;
import life.Plant;
import world.Cell;
import world.RandomGenerator;

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
     * Populate the world with empty cells.
     */
    public void populate() {
        // Initialize all the cells of the world.
        for (int y = 0; y < worldY; y++) {
            for (int x = 0; x < worldX; x++) {
                cells[y][x] = new Cell();
                int lp = RandomGenerator.nextNumber(100);
                System.out.println(lp);
                if (lp >= 85) {
                    cells[y][x].setLifeform(new Herbivore());
                } else if (lp >= 65) {
                    cells[y][x].setLifeform(new Plant());
                }
            }
        }
        
        // Set the neighbours of cells.
    }
    
    /**
     * Cycle through the behaviours of Lifeforms.
     */
    public void cycle() {
        // Do something
    }
}