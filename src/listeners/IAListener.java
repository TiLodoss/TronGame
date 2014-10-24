package listeners;

/**
 * Interface IAListener
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Listener du comportement des IA
 *
 */
public interface IAListener {
	public void hasMoved();
	public void onDirectionChanged(int newDirection);
	public void onIADeath();

}
