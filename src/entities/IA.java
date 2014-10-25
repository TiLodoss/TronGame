package entities;

//import java.awt.Color;

import java.util.Random;

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
	private Tile[][] tiles;
	int compteur = 0; //compteur pour garder une direction
	Random randDirection = new Random();
	int direction = randDirection.nextInt(4);//direction choisie au hasard
	boolean gauchePossible, droitePossible, hautPossible, basPossible;
	
	public IA(GamePanel panel, int lvl, int ownerCodeIA, int x, int y)
	{
		gPanel = panel;
		tiles = gPanel.getTiles();
		ownerCode = ownerCodeIA;
		posX = x;
		posY = y;
		gauchePossible = true;
		droitePossible = true;
		hautPossible = true;
		basPossible = true;
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
					if (gauchePossible) {
						if (tiles[this.posY][this.posX-1].getOwner() == Const.C_NONE) {
							this.posX--;
							droitePossible = false;
							basPossible = true;
							hautPossible = true;
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
					if (droitePossible) {
						if (tiles[this.posY][this.posX+1].getOwner() == Const.C_NONE) {
							this.posX++;
							gauchePossible = false;
							hautPossible = true;
							basPossible = true;
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
					if (hautPossible) {
						if (tiles[this.posY-1][this.posX].getOwner() == Const.C_NONE) {
							this.posY--;
							basPossible = false;
							gauchePossible = true;
							droitePossible = true;
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
					if (basPossible) {
						if (tiles[this.posY+1][this.posX].getOwner() == Const.C_NONE) {
							this.posY++;
							hautPossible = false;
							gauchePossible = true;
							droitePossible = true;
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
	
	public boolean suivreJoueur() {
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
			case Const.C_IA3: return suivreJoueur();
		}
		
		return false;
	}
	
	public boolean move(GameEntity entity) {
		// TODO Auto-generated method stub
		if (compteur != 5) {
			compteur++;
			return move(entity, direction);
		}
		
		else {
			compteur = 0;
			direction = randDirection.nextInt(4);
			return move(entity, direction);
		}
	}

}
