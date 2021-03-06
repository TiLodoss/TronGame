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
	protected int posX, posY; // position du joueur ou de l'ia
	protected int currentDirection; //direction courante de l'entite
	
	protected ArrayList<Tile> tiles; //ensemble des tuiles appartenant a l'entite
	protected Tile currentTile; //tuile ou se trouve actuellement l'entite
	
	//Methodes abstraites
	public abstract boolean move(GameEntity entity, int direction); //deplacement dans une direction
	public abstract boolean move(GameEntity entity); //deplacement
	
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
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
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
	public int getCurrentDirection() {
		return currentDirection;
	}
	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}
	
	
}
