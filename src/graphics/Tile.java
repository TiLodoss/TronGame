package graphics;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * Classe Tile
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Tuile de la grille de jeu (GamePanel)
 *
 */
public class Tile {
	private Rectangle rectangle;
	private Color color;
	private JPanel panel;
	private int owner; // joueur/ia ayant colore la tuile (ou non)
	private int x,y; //position de la tuile sur la grille
	
	/**
	 * Constructeur de Tile
	 * @param rectangle
	 * @param color
	 */
	public Tile(JPanel panel, Rectangle rectangle, Color color)
	{
		this.panel = panel;
		this.rectangle = rectangle;
		this.color = color;
		this.owner = -1;
	}
	
	/**
	 * Constructeur de Tile
	 * @param panel
	 */
	public Tile(int x, int y, JPanel panel)
	{
		this.panel = panel;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		this.panel.setBackground(color);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	

}
