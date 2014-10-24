package threads;

import other.Const;
import entities.IA;
import entities.Player;

/**
 * Classe IAThread
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe representant un thread gerant une IA
 *
 */
public class IAThread extends EntityThread{
	/**
	 * Constructeur de IAThread
	 * @param ia
	 */
	public IAThread(IA ia) {
		this.entity = ia;
	}
	
	/**
	 * Methode run
	 */
	@Override
	public void run()
	{
		entity.move(entity);
		
		System.out.println("playeeeee");
	}


}
