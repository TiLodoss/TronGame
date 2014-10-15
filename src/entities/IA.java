package entities;

/**
 * Classe IA
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe representant l'intelligence artificielle
 *
 */
public class IA extends GameEntity
{
	private int diffLvl; //niveau de difficulte
	
	public IA(int lvl)
	{
		this.diffLvl = lvl;
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collides() {
		// TODO Auto-generated method stub
		return false;
	}

}
