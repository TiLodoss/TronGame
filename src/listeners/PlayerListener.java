package listeners;

/**
 * Interface PlayerListener
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Listener du comportement du joueur
 *
 */
public interface PlayerListener {
	public void hasMoved();
	public void onDirectionChanged(int newDirection);
	public void onPlayerDeath();

}
