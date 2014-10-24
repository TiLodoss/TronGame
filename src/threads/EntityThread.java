package threads;

import entities.GameEntity;

/**
 * Classe EntityThread
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe abstraite representant un thread gerant une entite du jeu
 *
 */
public abstract class EntityThread extends Thread{
	protected GameEntity entity; // entitee controlee dans le thread

	public GameEntity getEntity() {
		return entity;
	}

	public void setEntity(GameEntity entity) {
		this.entity = entity;
	}
	
	
}
