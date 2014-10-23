package threads;

import entities.GameEntity;
import entities.Player;

/**
 * Classe PlayerThread
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe representant un thread gerant un joueur
 *
 */
public class PlayerThread extends EntityThread{

	/**
	 * Constructeur de PlayerThread
	 * @param player
	 */
	public PlayerThread(Player player) {
		this.entity = player;
	}
	
	/**
	 * Methode run
	 */
	public void run()
	{
		
	}

}
