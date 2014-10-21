package entities;

import other.Const;

/**
 * Classe Player
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe representant le joueur
 *
 */
public class Player extends GameEntity
{
	/**
	 * Constructeur de Player
	 */
	public Player()
	{
		super.setOwnerCode(Const.C_PLAYER);
	}
	
	@Override
	public boolean move(GameEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collides() {
		// TODO Auto-generated method stub
		return false;
	}

}
