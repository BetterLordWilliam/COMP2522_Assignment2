package life;

import life.Lifeform;

//For now, color legend:
//1 -- green
//2 -- yellow

// Plant Class
// Author: Will Otterbein
// Version: 2024-1
public class Herbivore extends Lifeform {
    
    public Herbivore() {
        this.LC = YELLOW;
        this.lifespan = 5;
        this.health = lifespan;
    }
    
    // Behaviour of Plant
    @Override
    public void behave() {
        // Do the behavior of a herbivore... whateverelse that might be
    }
}