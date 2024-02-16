package world;

import life.Lifeform;

//Plant Class
//Author: Will Otterbein
//Version: 2024-1
public class Cell {
	// The cells neighbors
	private Cell[] neighbours;
	
	private Lifeform l = null;
	public boolean isEmpty = true;
	
	// Sets the lifeform associated with this cell.
	// Two way relationship: cell has lifeform, life form has a cell.
	public void setLifeform(Lifeform l) {
		this.l = l;
		if (l != null) {
			l.c = this;
		}
	}
	
	// Returns the lifeform in this cell.
	public Lifeform getLifeform() {
		return l;
	}
	
	// Set the neighbours for a Cell.
	public void setNeighbours(Cell[] n) {
		neighbours = n;
	}
	
	// Returns the neighbours of a cell.
	public Cell[] getNeighbours() {
		return neighbours;
	}
}
