package listeners;

/**
 * Interface PlayerListener
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Listener du comportement du joueur
 *
 */
public interface PlayerListener {
	public void hasMoved(); //Methode declenchee lorsque le joueur s'est deplacee
	public void onPlayerDeath(); //Methode declenchee lorsque le joueur meurt
	public void statusChanged(); //Methode declenchee lorsque le statut du joueur change

}
