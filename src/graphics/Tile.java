package graphics;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;

import other.Const;

/**
 * Classe Tile
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Tuile de la grille de jeu (GamePanel)
 *
 */
public class Tile {
	private Color color;
	private JPanel panel;
	private int owner; // joueur/ia ayant colore la tuile (ou non)
	private int x,y; //position de la tuile sur la grille
	
	/**
	 * Constructeur de Tile
	 * @param rectangle
	 * @param color
	 */
	public Tile(int x, int y, JPanel panel, Color color)
	{
		this.panel = panel;
		this.color = color;
		this.panel.setBackground(color);
		this.owner = Const.C_NONE; //pas de propriétaire par défaut
	}
	
	/**
	 * Constructeur de Tile
	 * @param x
	 * @param y
	 * @param panel
	 */
	public Tile(int x, int y, JPanel panel)
	{
		this.panel = panel;
		this.owner = Const.C_NONE;
	}
	
	/**
	 * Methode pour colorer la tuile avec une couleur donnee
	 * @param color
	 */
	public void paintPanel(Color color)
	{
		this.panel.setBackground(color);
		this.setColor(color);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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
