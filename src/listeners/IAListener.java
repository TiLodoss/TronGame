package listeners;

/**
 * Interface IAListener
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Listener du comportement des IA
 *
 */
public interface IAListener {
	public void hasMoved(); //Methode declenchee lorsque l'IA s'est deplacee
	public void onIADeath(); // Methode declenchee lorsque l'IA meurt

}
