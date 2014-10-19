package entities;

import other.Const;

/**
 * Classe IA
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe representant l'intelligence artificielle
 *
 */
public class IA extends GameEntity
{
	private int diffLvl; //niveau de difficulté
	private int posX, posY; //position en x et en y
	
	public IA(int lvl, int x, int y)
	{
		diffLvl = lvl;
		posX = x;
		posY = y;
	}
	
	/**
	 * Méthode déclenchant le mouvement des IA selon son niveau
	 * @param lvl Niveau de difficulté de l'IA
	 * @return TRUE si le déplacement est possible, FALSE sinon
	 */
	public boolean moveIA(int lvl) {
		switch(lvl) {
			case Const.IA_LVL0:
				//if ()
			break;
		}
		
		return false;
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
		switch(direction) {
			case Const.DIR_LEFT: posX--;
			break;
			
			case Const.DIR_RIGHT: posX++;
			break;
			
			case Const.DIR_BOTTOM: posY--;
			break;
			
			case Const.DIR_TOP: posY++;
			break;
			
			//default:
		}
	}

	@Override
	public boolean collides() {
		// TODO Auto-generated method stub
		return false;
	}

}
