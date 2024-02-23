package world;

import misc.Colors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Game class for The Game of Life.
 * 
 * @author Will Otterbein
 * @version 2024-1
 */
public class Game extends JFrame implements Colors {
	// GAME CONSTANTS
	public static final int modifier = 20;
	
	// Game info
	int sizeX, sizeY;
	int maxTurns, elapsedTurns;
	char choice;
	
	private World w;
	private GamePanel gp;
	
	// Inner class GamePanel
	class GamePanel extends JPanel {
		public GamePanel() {
			// Mouse Listener on game panel
			addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					elapsedTurns++;
					gCycle();
				}
			});
		}
		
		// Draw the world.
		// Specifically, draw the grid elements
		public void drawRect(int posX, int posY, 
			int width, int length, Color c, final Graphics g) {
			// Draw inside
			g.setColor(c);
			g.fillRect(posX, posY, width, length);
			
			// Draw border
			g.setColor(BLACK);
			g.drawRect(posX, posY, width, length);
		}
		
		// Paint the panel
		@Override 
		public void paintComponent(final Graphics g) {
			super.paintComponent(g);
			
			for (int y = 0; y < w.worldY; y++) {
				for (int x = 0; x < w.worldY; x++) {
					Cell currentCell = w.cells[y][x];
					Color c = WHITE;
					
					try {
						// Colors
						c = currentCell.getLifeform().LC;
					} catch (NullPointerException e) {
					}
					
					// Draw cell based of color.
					drawRect(x * modifier, 
							 y * modifier, 
							 modifier, modifier, c, g);
				}
			}
		}
		
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(sizeX * modifier, sizeY * modifier);
        }
	}
	
	// Game creation
	public Game(int maxTurns, int sizeX, int sizeY) {
		this.maxTurns = maxTurns;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		w = new World(sizeX, sizeY);
		gp = new GamePanel();
		w.populate();
		
		// Window stuff
        add(gp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setVisible(true);
        //setResizable(false);
        pack();
	}
	
	// Cycle the game.
	public void gCycle() {
		w.cycle();
		gp.repaint();
	}
	
	// Main Method
	public static void main(String[] args) {
		int numOfTurns = 5;
		int gridSize = 20;
		Game g = new Game(numOfTurns, 25, 25);
	}
}
