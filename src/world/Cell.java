package world;

import life.Lifeform;

/**
 * Cell class.
 * 
 * @author Will Otterbein
 * @version 2024-1
 */
public class Cell {
	// The cells neighbors
	private Cell[] neighbours;
	
	private Lifeform l = null;
	public boolean isEmpty = true;
	
	/**
	 * Sets the Lifeform of a Cell.
	 * Makes it NULL if there is to be no lifeforms
	 * 
	 * @param l Lifeform new lifeform
	 */
	public void setLifeform(Lifeform l) {
		this.l = l;
		if (l != null) {
			l.setCell(this);
			isEmpty = false;
		} else {
			isEmpty = true;
		}
	}
	
	/**
	 * Returns the lifeform of the Cell.
	 * 
	 * @return Lifeform the cell's lifeform
	 */
	public Lifeform getLifeform() {
		return l;
	}
	
	/** 
	 * Set the neighbours for a Cell.
	 * 
	 * @param n Cell[] array of adjacent Cells
	 */
	public void setNeighbours(Cell[] n) {
		neighbours = n;
	}
	
	/**
	 * Returns the neighbours of a cell.
	 * 
	 * @return Cell[] array of adjacent Cells
	 */
	public Cell[] getNeighbours() {
		return neighbours;
	}
}
