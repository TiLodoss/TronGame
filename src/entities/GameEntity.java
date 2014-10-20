package entities;

import graphics.Tile;

import java.util.ArrayList;

import other.Const;

/**
 * Classe GameEntity
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe abstraite pour les entites du jeu (joueur/IA)
 *
 */
public abstract class GameEntity {
	protected int ownerCode; //identifiant pour les cases colorees par l'entite
	protected int score; //score du joueur ou de l'ia
	protected int status; //statut (vivant ou mort)
	protected ArrayList<Tile> tiles; //ensemble des tuiles appartenant a l'entite
	protected Tile currentTile; //tuile ou se trouve actuellement l'entite
	
	//Methodes abstraites
	public abstract void move(int direction); //deplacement dans une direction
	public abstract boolean collides(); //detection de collision
	
	/**
	 * Constructeur de GameEntity
	 */
	public GameEntity()
	{
		this.score = 0;
		this.status = Const.ENT_ALIVE;
		this.tiles = new ArrayList<Tile>();
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	public Tile getCurrentTile() {
		return currentTile;
	}
	public void setCurrentTile(Tile currentTile) {
		this.currentTile = currentTile;
	}
	public int getOwnerCode() {
		return ownerCode;
	}
	public void setOwnerCode(int ownerCode) {
		this.ownerCode = ownerCode;
	}
}
