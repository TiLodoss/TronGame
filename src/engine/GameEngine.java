package engine;

import java.util.ArrayList;

import other.Const;
import entities.GameEntity;
import entities.IA;
import entities.Player;
import graphics.MainWindow;
import graphics.Tile;

/**
 * Classe GameEngine
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe gerant le gameplay du jeu
 *
 */
public class GameEngine {
	
	private MainWindow window; //reference vers la fenetre du jeu
	private ArrayList<GameEntity> entities; //liste des joueurs/IAs
	private Player player; //le joueur
	private int nbRounds, currentRound; //nombre de rounds a jouer et round actuel
	
	/**
	 * Constructeur de GameEngine
	 * @param window
	 */
	public GameEngine(MainWindow window)
	{
		this.window = window;
		this.entities = new ArrayList<GameEntity>();
		this.player = new Player();
		this.nbRounds = Const.NB_MAXROUNDS;
		this.currentRound = 0;
		this.entities.add(player);
		
		this.entities.add(new IA(Const.C_IA1)); //ia idiote (deplacement spirale)
		this.entities.add(new IA(Const.C_IA2)); //ia moyenne (deplacement random)
		this.entities.add(new IA(Const.C_IA3)); // ia intelligente (suit le joueur en diagonale)
	}
	
	/**
	 * Methode permettant de lancer un round du jeu
	 * @return success
	 */
	public boolean startRound()
	{
		boolean success = false;
		
		if(currentRound < nbRounds)
		{
			currentRound++;
			System.out.println("Round "+currentRound+" commence");
			
			//TODO initialisation du round
		}
		return success;
	}
	
	/**
	 * Methode ou se deroule l'action du jeu
	 */
	public void play()
	{
		//TODO
	}
	
	/**
	 * Methode qui provoque un rafraichissement de l'image 
	 * (recupere toutes les tuiles de chaque joueur et les envoie a la window pour colorer)
	 */
	public void refresh()
	{
		//TODO a continuer
		ArrayList<Tile> updatedTiles = new ArrayList<Tile>();
		
		//On ajou
		for(int i=0;i<entities.size();i++)
		{
			updatedTiles.addAll(entities.get(i).getTiles());
		}
		
		this.window.getGamePanel().updateData(updatedTiles);
	}


	public MainWindow getWindow() {
		return window;
	}


	public void setWindow(MainWindow window) {
		this.window = window;
	}


	public ArrayList<GameEntity> getEntities() {
		return entities;
	}


	public void setEntities(ArrayList<GameEntity> entities) {
		this.entities = entities;
	}
	

}
