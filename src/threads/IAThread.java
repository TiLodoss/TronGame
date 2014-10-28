package threads;

import listeners.IAListener;
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
	private IAListener iaListener;
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
		if(entity.getStatus() == Const.ENT_ALIVE)
		{
			entity.move(entity);
			iaListener.hasMoved();
		}
	}

	public IAListener getIAListener() {
		return iaListener;
	}

	public void setIAListener(IAListener iaListener) {
		this.iaListener = iaListener;
	}





}
