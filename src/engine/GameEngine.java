package engine;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import listeners.IAListener;
import listeners.PlayerListener;
import other.Const;
import other.InteractionClavier;
import threads.EntityThread;
import threads.IAThread;
import threads.PlayerThread;
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
	private GamePanel gPanel; // r�f�rence sur la grille de jeu
	private ArrayList<Tile> coloredTiles; //liste des tuiles colorees par le joueur/les ia
	private int keyPressedCode = 0;
	private GameThread gameThread;

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
		this.player = new Player(gPanel, 10, 10);
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
		window.getGamePanel().cleanGrid();

		if(currentRound < nbRounds)
		{
			currentRound++;
			System.out.println("Round "+currentRound+" commence");

			if(currentRound > 1) //si on a joue plus d'un round, on nettoie la grille au nouveau round
			{
				
				//Repositionnement des entites
				entities.get(0).setPosX(10);
				entities.get(0).setPosY(10);
				entities.get(1).setPosX(0);
				entities.get(1).setPosY(0);
				entities.get(2).setPosX(50);
				entities.get(2).setPosY(50);
				entities.get(2).setPosX(15);
				entities.get(2).setPosY(75);
			}

			//TODO initialisation du round	



			player.setCurrentDirection(Const.DIR_RIGHT); //direction de depart du joueur
			entities.get(1).setCurrentDirection(Const.DIR_RIGHT);//direction de depart de l'ia 1
			
			//Affichage des joueurs/IA a leur position initiale
			try 
			{
				window.getGamePanel().paintTile(entities.get(0).getPosX(), entities.get(0).getPosY(), entities.get(0).getOwnerCode());
				window.getGamePanel().paintTile(entities.get(1).getPosX(), entities.get(1).getPosY(), entities.get(1).getOwnerCode());
				window.getGamePanel().paintTile(entities.get(2).getPosX(), entities.get(2).getPosY(), entities.get(2).getOwnerCode());
				window.getGamePanel().paintTile(entities.get(3).getPosX(), entities.get(3).getPosY(), entities.get(3).getOwnerCode());
			} 

			catch (GameException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			play();
		}

		if(currentRound == nbRounds)
		{
			gameOver();
		}
		return success;
	}

	/**
	 * Methode declenchee a la fin du jeu
	 * @param gameFinished
	 */
	public void gameOver()
	{
		window.displayGameOverDialog(entities);

	}
	
	/**
	 * Methode qui retourne le nombre de joueurs/IA encore en vie
	 * @return
	 */
	public int entitiesAlive()
	{
		int nbAlive =0;
		for(GameEntity e : entities)
		{
			if(e.getStatus() == Const.ENT_ALIVE)
				nbAlive++;
		}
		
		return nbAlive;
	}
	
	/**
	 * Methode de calcul du score de chaque entite (+ mise a jour affichage du score)
	 */
	public void calculateScore()
	{
		for(GameEntity e : entities)
		{
			if(e.getStatus() == Const.ENT_ALIVE)
			{
				e.setScore(e.getScore()+1);
				window.updateEntityScore(e.getOwnerCode(), e.getScore());
				break;
			}
		}
	}

	/**
	 * Methode ou se deroule la boucle principale du jeu
	 */
	public void play()
	{

		Tile[][] tiles = window.getGamePanel().getTiles();
		if(gameThread == null)
		{
			gameThread = new GameThread();
		}
		gameThread.start();


		/*InteractionClavier interaction = new InteractionClavier((Player)entities.get(0));

		window.addKeyListener(interaction);
		interaction.keyPressed(new KeyEvent(window, 
							KeyEvent.KEY_PRESSED,
							(long) 1, 0, KeyEvent.VK_RIGHT,
							KeyEvent.CHAR_UNDEFINED)); */


		//gameOver(); //test fenetre de score

		//TEST
		//for(int i=1;i<10;i++)
		//{
		//entities.get(0).getTiles().add(window.getGamePanel().getTiles()[0][i]); //on ajoute des tuiles a colorer pour le joueur

		//A voir si on fait directement paintTile a partir d'ici (plus simple)
		//ou si on doit stocker les infos sur chaque tuile dans une liste et l'envoyer au panel
		/*try 
			{
				window.getGamePanel().paintTile(entities.get(1).getPosX(), entities.get(1).getPosY(), entities.get(1).getOwnerCode());
			} 

			catch (GameException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
		//}

		/*while (entities.get(1).move(entities.get(1))) {
			try {

				window.getGamePanel().paintTile(entities.get(1).getPosX(), entities.get(1).getPosY(), entities.get(1).getOwnerCode());
			}

			catch(GameException e) {
				e.printStackTrace();
			}
		}*/

		//TEST bouger joueur 1
		/*for(int i=0; i<50; i++) {
				if(entities.get(0).move(entities.get(0),40)) {

					try {
						window.getGamePanel().paintTile(entities.get(0).getPosX(), entities.get(0).getPosY(), entities.get(0).getOwnerCode());
					}
					catch(GameException e) {
						e.printStackTrace();
					}
				}
			}*/

		//for(Tile t : entities.get(0).getTiles())
		//{
		//t.setOwner(Const.C_PLAYER); //FIXME pas bon, ça change aussi l'owner sur la tuile de tiles[][]
		//}

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

	/**
	 * Methode qui reinitialise les donnees du jeu
	 */
	public void resetGame()
	{
		//reinitialiser la grille
		window.getGamePanel().cleanGrid();

		//Remise a zero du score
		for(GameEntity e : entities)
		{
			e.setScore(0);
			e.setStatus(Const.ENT_ALIVE);
		}

		//remise a zero des rounds
		this.setNbRounds(0);
		this.setCurrentRound(0);

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



	/**
	 * Classe interne GameThread
	 * @author Yannis M'RAD, Vincent AUNAI
	 * 
	 * Thread gerant la boucle principale du jeu
	 *
	 */
	private class GameThread extends Thread{
		private GameEngine gameEngine;
		private PlayerThread playerThread; //thread associe au joueur
		private IAThread tIA1, tIA2, tIA3; //thread associes aux IA
		private PlayerListener playerListener;
		private InteractionClavier interaction;

		/**
		 * Constructeur de GameThread
		 */
		public GameThread()
		{
			gameEngine = GameEngine.this;
			playerThread = new PlayerThread(GameEngine.this.getPlayer());
			this.tIA1 = new IAThread((IA) GameEngine.this.getEntities().get(1));
			this.tIA2 = new IAThread((IA) GameEngine.this.getEntities().get(2));
			this.tIA3 = new IAThread((IA) GameEngine.this.getEntities().get(3));


			/* Creation d'une interaction clavier pour le joueur */
			if(this.interaction == null)
			{
				interaction = new InteractionClavier((Player)entities.get(0));
				window.getGamePanel().addKeyListener(interaction);
				playerThread.setInteraction(interaction);
			}

			setPlayerListener();
			setIAListener(tIA1);
			setIAListener(tIA2);
			setIAListener(tIA3);
		}

		/**
		 * Methode run
		 */
		public void run()
		{
			boolean runLoop = true;
			
			//TEST
			tIA2.getEntity().setStatus(Const.ENT_DEAD);
			tIA3.getEntity().setStatus(Const.ENT_DEAD);
			
			while(runLoop)
			{
				playerThread.run();
				tIA1.run();
				//tIA2.run();
				//tIA3.run();


				try {
					//window.getGamePanel().paintTile(entities.get(1).getPosX(), entities.get(1).getPosY(), entities.get(1).getOwnerCode());
					//playerThread.sleep(50);
					tIA1.sleep(50);
					//tIA2.sleep(50);
					//tIA3.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * Definition des methodes du player listener pour repercuter les modifications
		 * sur le GamePanel 
		 */
		public void setPlayerListener()
		{
			playerThread.setPlayerListener(new PlayerListener(){

				@Override
				public void onDirectionChanged(int newDirection) {
					
					
				}

				@Override
				public void onPlayerDeath() {
					playerThread.getEntity().setStatus(Const.ENT_DEAD);
					System.out.println("Joueur mort !");
					
				}
				
				//Si le joueur s'est deplace, colorer la tuile ou il vient de passer
				@Override
				public void hasMoved() {
					try 
					{
						window.getGamePanel().paintTile(entities.get(0).getPosX(), entities.get(0).getPosY(), entities.get(0).getOwnerCode());
					}
					catch(GameException e) 
					{
						System.out.println(e.getMessage());
						onPlayerDeath();
						playerThread.stop();
						
						//Après mort du joueur, verifier s'il reste 1 survivant (fin du round)
						if(entitiesAlive() == 1)
						{
							calculateScore();
							stopAllThreads();
							GameThread.this.interrupt();
							startRound(); //demarrer le round suivant si possible	
						}
					}		
				}
				
			});
		}
		
		/**
		 * Methode qui stoppe les threads des entites
		 */
		public void stopAllThreads()
		{
			if(playerThread.isAlive()) playerThread.stop();
			if(tIA1.isAlive()) tIA1.stop();
			if(tIA2.isAlive()) tIA2.stop();
			if(tIA3.isAlive()) tIA3.stop();
		}
		
		public void setIAListener(final IAThread iaThread)
		{
			iaThread.setIAListener(new IAListener(){

				@Override
				public void onDirectionChanged(int newDirection) {
					
					
				}

				@Override
				public void onIADeath() {
					iaThread.getEntity().setStatus(Const.ENT_DEAD);
					System.out.println("IA morte !");
					iaThread.stop();
					
					//Apres mort de l'ia, verifier s'il reste 1 survivant
					if(entitiesAlive() == 1)
					{
						calculateScore();
						stopAllThreads();
						GameThread.this.interrupt();
						startRound();
					}
					
				}
				
				//Si l'IA s'est deplace, colorer la tuile ou il vient de passer
				@Override
				public void hasMoved() {
					try 
					{
						window.getGamePanel().paintTile(iaThread.getEntity().getPosX(), iaThread.getEntity().getPosY(), iaThread.getEntity().getOwnerCode());
					}
					catch(GameException e) 
					{
						System.out.println(e.getMessage());
						onIADeath();
						//TODO Si l'exception est lancee c'est qu'on a essaye de peindre une case deja occupee
						//donc on peut tuer l'IA ici, a voir si on peut ameliorer ca ?
					}		
				}
				
			});
		}
	
	}





}
