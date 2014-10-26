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
	//private int diffLvl; //niveau de difficult�
	private GamePanel gPanel;
	private GameEngine gEngine;
	private Tile[][] tiles;
	private int compteur = 0; //compteur pour garder une direction
	private Random randDirection = new Random();
	private int direction = randDirection.nextInt(4);//direction choisie au hasard
	
	
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
	
	public IA(GamePanel panel, GameEngine engine, int lvl, int ownerCodeIA, int x, int y)
	{
		gPanel = panel;
		gEngine = engine;
		tiles = gPanel.getTiles();
		ownerCode = ownerCodeIA;
		posX = x;
		posY = y;
	}
	
	public boolean deplacerSpirale() {	
		//d�placement � droite
		if (this.posX<Const.NB_MAXTILES-1 && this.posY == 0) {
			if (tiles[this.posY][this.posX+1].getOwner() == Const.C_NONE) {
				this.posX++;
				return true;
			}
		}
		
		//d�placement en bas
		else if (this.posX == Const.NB_MAXTILES-1 && this.posY<Const.NB_MAXTILES-1) {
			if (tiles[this.posY+1][this.posX].getOwner() == Const.C_NONE) {
				this.posY++;
				return true;
			}
		}
		
		//d�placement � gauche
		else if (this.posX > 0 && this.posY == Const.NB_MAXTILES-1){
			this.posX--;
			if (tiles[this.posY][this.posX].getOwner() == Const.C_NONE) {
				return true;
			}
		}
		
		//d�placement en haut
		else if (this.posY > 0 && this.posX == 0){
			this.posY--;
			if (tiles[this.posY][this.posX].getOwner() == Const.C_NONE) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean deplacerAleatoire(int direction) {
		Random randDirection = new Random();
		
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
						direction = randDirection.nextInt(4);
						move(this, direction);
					}
				}			
				
				else return false;
			
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
						direction = randDirection.nextInt(4);
						move(this, direction);
					}
				}	
				
				else return false;
			
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
						direction = randDirection.nextInt(4);
						move(this, direction);
					}
				}
				
				else return false;
			
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
						direction = randDirection.nextInt(4);
						move(this, direction);
					}
				}
				
				else return false;
		}
		return false;
	}
	
	public boolean suivreJoueur(int direction) {
		switch(direction) {
			case Const.DIR_LEFT:
				if (this.posX > 0){
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
						move(this, direction);
					}
				}			
				
				else return false;
				
			case Const.DIR_RIGHT:				
				if (this.posX<Const.NB_MAXTILES-1) {
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
						move(this, direction);
					}
				}	
				
				else return false;
			
			// deplacement haut
			case Const.DIR_TOP:				
				if (this.posY > 0){
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
						move(this, direction);
					}
				}
				
				else return false;
			
			// deplacement bas
			case Const.DIR_BOTTOM:				
				if (this.posY<Const.NB_MAXTILES-1) {
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
						move(this, direction);
					}
				}
				
				else return false;
		}
		
		return false;
	}


	@Override
	public boolean collides() {
		// TODO Auto-generated method stub
		if (this.posX == 0 && this.posY == 0) return false;
		else if ((tiles[this.posY][this.posX].getOwner() != Const.C_NONE))
		{
			this.setStatus(Const.ENT_DEAD);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean move(GameEntity entity, int direction) {
		// TODO Auto-generated method stub
		
		if(entity.getStatus() == Const.ENT_DEAD) return false;
		switch(entity.getOwnerCode()) {
			case Const.C_IA1: return deplacerSpirale();
			case Const.C_IA2: return deplacerAleatoire(direction);
			case Const.C_IA3: return suivreJoueur(direction);
		}
		
		return false;
	}
	
	public boolean move(GameEntity entity) {
		// TODO Auto-generated method stub
		switch(entity.getOwnerCode()) {
			case Const.C_IA1: return move(entity, 0);
			
			case Const.C_IA2: 
				if (compteur != 5) {				
					compteur++;
					return move(entity, direction);
				}
				
				else {
					compteur = 0;
					direction = randDirection.nextInt(4);
					return move(entity, direction);
				}
			
			case Const.C_IA3:
				return move(entity, gEngine.getPlayer().getCurrentDirection());
			
			default: return false;
		}
	}

}
