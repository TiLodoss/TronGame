package entities;

//import java.awt.Color;

import java.util.Random;

import engine.GameEngine;
import other.Const;
import graphics.GamePanel;
import graphics.Tile;

/**
 * Classe IA
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe representant l'intelligence artificielle
 *
 */
public class IA extends GameEntity
{
	private GamePanel gPanel; // r�f�rence du GamePanel
	private GameEngine gEngine; //r�f�rence du GameEngines
	private Tile[][] tiles; //r�f�rence du tableau de Tile
	private int compteur = 0; //compteur pour garder une direction
	private Random randDirection = new Random();
	private int direction = randDirection.nextInt(4);//direction choisie au hasard
	private int tourSpirale = 0; // numero du tour de la spirale en cours - IA n�1


	// Classes internes - D�placements possibles pour l'IA 2 et 3
	private static class DeplacementsPossiblesIA2 {
		private static boolean gauchePossible = true;
		private static boolean droitePossible = true;
		private static boolean hautPossible = true;
		private static boolean basPossible = true;
	}

	private static class DeplacementsPossiblesIA3 {
		private static boolean gauchePossible = true;
		private static boolean droitePossible = true;
		private static boolean hautPossible = true;
		private static boolean basPossible = true;
	}

	
	/**
	 * Constucteur d'IA
	 * @param panel
	 * @param engine
	 * @param lvl
	 * @param ownerCodeIA
	 * @param x
	 * @param y
	 */
	public IA(GamePanel panel, GameEngine engine, int lvl, int ownerCodeIA, int x, int y)
	{
		gPanel = panel;
		gEngine = engine;
		tiles = gPanel.getTiles();
		ownerCode = ownerCodeIA;
		posX = x;
		posY = y;
	}

	/**
	 * Methode de deplacement de l'ia facile
	 * @return true
	 */
	public boolean deplacerSpirale() {
		//deplacement a droite
		if (this.posX<Const.NB_MAXTILES-(tourSpirale+1) && this.posY == tourSpirale) {
			if (tiles[this.posY+tourSpirale][this.posX+1].getOwner() == Const.C_NONE) {
				this.posX++;
				return true;
			}
		}

		//deplacement en bas
		else if (this.posX == (Const.NB_MAXTILES-(tourSpirale+1)) && this.posY<Const.NB_MAXTILES-(tourSpirale+1)) {
			if (tiles[this.posY+1][this.posX-tourSpirale].getOwner() == Const.C_NONE) {
				this.posY++;
				return true;
			}
		}

		//deplacement gauche
		else if (this.posX > (0+tourSpirale) && this.posY == (Const.NB_MAXTILES-(tourSpirale+1))){
			this.posX--;
			if (tiles[this.posY-tourSpirale][this.posX].getOwner() == Const.C_NONE) {
				return true;
			}
		}

		//deplacement en haut
		else if (this.posY > (0 + tourSpirale) && this.posX == tourSpirale){
			this.posY--;
			if (tiles[this.posY][this.posX+tourSpirale].getOwner() == Const.C_NONE) {
				return true;
			}

			else if (tiles[this.posY][this.posX+tourSpirale].getOwner() == Const.C_IA1){
				tourSpirale++;
				this.posX = tourSpirale;
				this.posY = tourSpirale;
				return true;
			}
		}

		return false;
	}

	/**
	 * Methode de deplacement de l'ia 2
	 * @param direction
	 */
	public boolean deplacerAleatoire(int direction) {
		switch(direction) {
		// deplacement gauche
		case Const.DIR_LEFT:
			if (this.posX > 0){
				if (DeplacementsPossiblesIA2.gauchePossible) {
					if (tiles[this.posY][this.posX-1].getOwner() == Const.C_NONE) {
						this.posX--;
						DeplacementsPossiblesIA2.droitePossible = false;
						DeplacementsPossiblesIA2.basPossible = true;
						DeplacementsPossiblesIA2.hautPossible = true;
						return true;
					}
				}

				else {
					if (DeplacementsPossiblesIA2.basPossible) return move(this, Const.DIR_BOTTOM);
					else if (DeplacementsPossiblesIA2.hautPossible) return move(this, Const.DIR_TOP);
					else return false;
				}
			}

		// deplacement droite
		case Const.DIR_RIGHT:				
			if (this.posX<Const.NB_MAXTILES-1) {
				if (DeplacementsPossiblesIA2.droitePossible) {
					if (tiles[this.posY][this.posX+1].getOwner() == Const.C_NONE) {
						this.posX++;
						DeplacementsPossiblesIA2.gauchePossible = false;
						DeplacementsPossiblesIA2.hautPossible = true;
						DeplacementsPossiblesIA2.basPossible = true;
						return true;
					}
				}

				else {
					if (DeplacementsPossiblesIA2.basPossible) return move(this, Const.DIR_BOTTOM);
					else if (DeplacementsPossiblesIA2.hautPossible) return move(this, Const.DIR_TOP);
					else return false;
				}
			}

		// deplacement haut
		case Const.DIR_TOP:				
			if (this.posY > 0){
				if (DeplacementsPossiblesIA2.hautPossible) {
					if (tiles[this.posY-1][this.posX].getOwner() == Const.C_NONE) {
						this.posY--;
						DeplacementsPossiblesIA2.basPossible = false;
						DeplacementsPossiblesIA2.gauchePossible = true;
						DeplacementsPossiblesIA2.droitePossible = true;
						return true;
					}
				}

				else {
					if (DeplacementsPossiblesIA2.gauchePossible) return move(this, Const.DIR_LEFT);
					else if (DeplacementsPossiblesIA2.droitePossible) return move(this, Const.DIR_RIGHT);
					else return false;
				}
			}

		// deplacement bas
		case Const.DIR_BOTTOM:				
			if (this.posY<Const.NB_MAXTILES-1) {
				if (DeplacementsPossiblesIA2.basPossible) {
					if (tiles[this.posY+1][this.posX].getOwner() == Const.C_NONE) {
						this.posY++;
						DeplacementsPossiblesIA2.hautPossible = false;
						DeplacementsPossiblesIA2.gauchePossible = true;
						DeplacementsPossiblesIA2.droitePossible = true;
						return true;
					}
				}

				else {
					if (DeplacementsPossiblesIA2.gauchePossible) return move(this, Const.DIR_LEFT);
					else if (DeplacementsPossiblesIA2.droitePossible) return move(this, Const.DIR_RIGHT);
					else return false;
				}
			}
		}
		return false;
	}

	/**
	 * Methode de deplacement de l'IA 3
	 * @param direction
	 */
	public boolean inverseJoueur(int direction) {

		switch(direction) {
		// si le joueur se deplace a gauche...
		case Const.DIR_LEFT:
			if (this.posX<Const.NB_MAXTILES-1) { // ... l'IA se deplace a droite
				if (DeplacementsPossiblesIA3.droitePossible) {
					if (tiles[this.posY][this.posX+1].getOwner() == Const.C_NONE) {
						this.posX++;
						DeplacementsPossiblesIA3.gauchePossible = false;
						DeplacementsPossiblesIA3.hautPossible = true;
						DeplacementsPossiblesIA3.basPossible = true;
						return true;
					}
				}

				else {
					direction = randDirection.nextInt(4);
					return move(this, direction);
				}
			}	

			else return false;				

			// si le joueur se deplace a droite...
		case Const.DIR_RIGHT:				
			if (this.posX > 0){ // ... l'IA se deplace a gauche
				if (DeplacementsPossiblesIA3.gauchePossible) {
					if (tiles[this.posY][this.posX-1].getOwner() == Const.C_NONE) {
						this.posX--;
						DeplacementsPossiblesIA3.droitePossible = false;
						DeplacementsPossiblesIA3.basPossible = true;
						DeplacementsPossiblesIA3.hautPossible = true;
						return true;
					}
				}

				else {
					direction = randDirection.nextInt(4);
					return move(this, direction);
				}
			}			

			else return false;

			// si le joueur se deplace en haut...
		case Const.DIR_TOP:				
			if (this.posY<Const.NB_MAXTILES-1) { // ... l'IA se deplace en bas
				if (DeplacementsPossiblesIA3.basPossible) {
					if (tiles[this.posY+1][this.posX].getOwner() == Const.C_NONE) {
						this.posY++;
						DeplacementsPossiblesIA3.hautPossible = false;
						DeplacementsPossiblesIA3.gauchePossible = true;
						DeplacementsPossiblesIA3.droitePossible = true;
						return true;
					}
				}

				else {
					direction = randDirection.nextInt(4);
					return move(this, direction);
				}
			}

			else return false;

			// si le joueur se deplace en bas...
		case Const.DIR_BOTTOM:
			if (this.posY > 0){ // ... l'IA se deplace en haut
				if (DeplacementsPossiblesIA3.hautPossible) {
					if (tiles[this.posY-1][this.posX].getOwner() == Const.C_NONE) {
						this.posY--;
						DeplacementsPossiblesIA3.basPossible = false;
						DeplacementsPossiblesIA3.gauchePossible = true;
						DeplacementsPossiblesIA3.droitePossible = true;
						return true;
					}
				}

				else {
					direction = randDirection.nextInt(4);
					return move(this, direction);
				}
			}

			else return false;

		}


		return false;
	}

	/**
	 * Methode pour deplacer les IA
	 * @param entity
	 * @param direction
	 */
	@Override
	public boolean move(GameEntity entity, int direction) {
		// TODO Auto-generated method stub

		if(entity.getStatus() == Const.ENT_DEAD) return false;
		switch(entity.getOwnerCode()) {
		case Const.C_IA1: return deplacerSpirale();
		case Const.C_IA2: return deplacerAleatoire(direction);
		case Const.C_IA3: return inverseJoueur(direction);
		}

		return false;
	}

	/**
	 * Methode move dediee a l'IA qui doit faire les mouvements inverses du joueur
	 * @param entity
	 * @param direction
	 * @param playerStatus
	 */
	public boolean move(GameEntity entity, int direction, int playerStatus)
	{
		if(entity.getStatus() == Const.ENT_DEAD) return false;
		switch(entity.getOwnerCode()) {
			case Const.C_IA3: 
				//Si le joueur est mort, on laisse l'ia faire des mouvements aleatoires
				if(playerStatus == Const.ENT_DEAD)
				{
					return deplacerAleatoire(direction);
				}
				
				//Sinon elle fait l'inverse du joueur
				else
					return inverseJoueur(direction);
		}
		return false;
	}

	/**
	 * Methode move
	 * @param entity
	 */
	public boolean move(GameEntity entity) {
		switch(entity.getOwnerCode()) {
			case Const.C_IA1: return move(entity, 0);
	
			case Const.C_IA2: 
				if (compteur < 5) {				
					compteur++;
					return move(entity, direction);
				}
	
				else {
					compteur = 0;
					direction = randDirection.nextInt(4);
					return move(entity, direction);
				}
	
			case Const.C_IA3:
				return move(entity, gEngine.getPlayer().getCurrentDirection(), gEngine.getPlayer().getStatus());
	
			default: return false;
		}
	}

}
