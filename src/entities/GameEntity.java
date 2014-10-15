package entities;

/**
 * Classe GameEntity
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe abstraite pour les entités du jeu (joueur/IA)
 *
 */
public abstract class GameEntity {
	public abstract void move(int direction);
	public abstract boolean collides();
	
	/**
	 * Constructeur de GameEntity
	 */
	public GameEntity()
	{
		super();
	}

}
