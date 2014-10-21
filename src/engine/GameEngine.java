package engine;

import java.awt.Color;
import java.util.ArrayList;

import other.Const;
import entities.GameEntity;
import entities.IA;
import entities.Player;
import exceptions.GameException;
import graphics.GamePanel;
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
	private GamePanel gPanel;
	private ArrayList<Tile> coloredTiles; //liste des tuiles colorees par le joueur/les ia
	
	/**
	 * Constructeur de GameEngine
	 * @param window
	 */
	public GameEngine(MainWindow window, GamePanel gPanel)
	{
		this.window = window;
		this.gPanel = gPanel;
		this.entities = new ArrayList<GameEntity>();
		this.coloredTiles = new ArrayList<Tile>();
		this.player = new Player();
		this.nbRounds = Const.NB_MAXROUNDS;
		this.currentRound = 0;
		this.entities.add(player);
		
		this.entities.add(new IA(this.gPanel, Const.IA_LVL0, Const.C_IA1, 0, 0)); //ia idiote (deplacement spirale)
		this.entities.add(new IA(this.gPanel, Const.IA_LVL1, Const.C_IA2, 50, 50)); //ia moyenne (deplacement random)
		this.entities.add(new IA(this.gPanel, Const.IA_LVL2, Const.C_IA3, 15, 75)); // ia intelligente (suit le joueur en diagonale)
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
		//TEST
		for(int i=0;i<10;i++)
		{
			//entities.get(0).getTiles().add(window.getGamePanel().getTiles()[0][i]); //on ajoute des tuiles a colorer pour le joueur
			
			//A voir si on fait directement paintTile a partir d'ici (plus simple)
			//ou si on doit stocker les infos sur chaque tuile dans une liste et l'envoyer au panel
			try 
			{
				window.getGamePanel().paintTile(1, i, entities.get(0).getOwnerCode());	
			} 
			
			catch (GameException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(Tile t : entities.get(0).getTiles())
		{
			//t.setOwner(Const.C_PLAYER); //FIXME pas bon, ça change aussi l'owner sur la tuile de tiles[][]
		}
		
		//refresh();
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
			System.out.println("nb tuile colorees : "+entities.get(i).getTiles().size());
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getNbRounds() {
		return nbRounds;
	}

	public void setNbRounds(int nbRounds) {
		this.nbRounds = nbRounds;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public ArrayList<Tile> getColoredTiles() {
		return coloredTiles;
	}

	public void setColoredTiles(ArrayList<Tile> coloredTiles) {
		this.coloredTiles = coloredTiles;
	}
	
	
	

}
