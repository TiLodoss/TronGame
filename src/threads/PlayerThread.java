package threads;

import other.Const;
import other.InteractionClavier;
import listeners.PlayerListener;
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
	private PlayerListener playerListener;
	private InteractionClavier interaction;

	/**
	 * Constructeur de PlayerThread
	 * @param player
	 */
	public PlayerThread(Player player) {
		this.entity = player;
		this.setName("Player thread");
	}
	
	/**
	 * Methode run
	 */
	@Override
	public void run()
	{
		if(entity.getStatus() == Const.ENT_ALIVE)
		{
			//Deplacer le joueur dans une direction apres appui sur une touche
			if(interaction.isKeyPressed())
			{
				//TODO envoyer plutot une Const.Direction en param
				entity.move(entity, interaction.getKeyPressedCode()); 	
				
				switch(interaction.getKeyPressedCode())
				{
					case 37:
						entity.move(entity, Const.DIR_LEFT); 
						break;
						
					case 38:
						entity.move(entity, Const.DIR_TOP);  
						break;
						
					case 39:
						entity.move(entity, Const.DIR_RIGHT); 
						break;
						
					case 40:
						entity.move(entity, Const.DIR_BOTTOM); 
						break;
						
				}
			}
			
			//Pas de touche appuyee, deplacer le joueur dans sa direction actuelle
			else
			{
				entity.move(entity, entity.getCurrentDirection()); 
			}
			
			playerListener.hasMoved();
		}
		
		//Si le statut est different de ENT_ALIVE c'est qu'il a ete modifie dans move() de l'entite
		else
		{
			playerListener.statusChanged();
		}
	}

	public PlayerListener getPlayerListener() {
		return playerListener;
	}

	public void setPlayerListener(PlayerListener playerListener) {
		this.playerListener = playerListener;
	}

	public InteractionClavier getInteraction() {
		return interaction;
	}

	public void setInteraction(InteractionClavier interaction) {
		this.interaction = interaction;
	}
}
