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
	
	public IA(GamePanel panel, int lvl, int ownerCodeIA, int x, int y)
	{
		gPanel = panel;
		tiles = gPanel.getTiles();
		ownerCode = ownerCodeIA;
		posX = x;
		posY = y;
	}
	
	public boolean deplacerSpirale() {	
		//d�placement � droite
		if (this.posX<Const.NB_MAXTILES-1 && this.posY == 0) {
			if (tiles[this.posX+1][this.posY].getOwner() == Const.C_NONE) {
				this.posX++;
				return true;
			}
		}
		
		//d�placement en bas
		else if (this.posX == Const.NB_MAXTILES-1 && this.posY<Const.NB_MAXTILES-1) {
			if (tiles[this.posX][this.posY+1].getOwner() == Const.C_NONE) {
				this.posY++;
				return true;
			}
		}
		
		//d�placement � gauche
		else if (this.posX > 0 && this.posY == Const.NB_MAXTILES-1){
			this.posX--;
			if (tiles[this.posX][this.posY].getOwner() == Const.C_NONE) {
				return true;
			}
		}
		
		//d�placement en haut
		else if (this.posY > 0 && this.posX == 0){
			this.posY--;
			if (tiles[this.posX][this.posY].getOwner() == Const.C_NONE) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean deplacerAleatoire() {
		Random randDirection = new Random();
		int direction = randDirection.nextInt(4);
		
		switch(direction) {
			// deplacement gauche
			case 0: 
				if (this.posX > 0 /*&& this.posY == Const.NB_MAXTILES-1*/){
					this.posX--;
					if (tiles[this.posX][this.posY].getOwner() == Const.C_NONE) {
						return true;
					}
				}
				
				else return false;
			
			// deplacement droite
			case 1: 
				if (this.posX<Const.NB_MAXTILES-1 /*&& this.posY == 0*/) {
					if (tiles[this.posX+1][this.posY].getOwner() == Const.C_NONE) {
						this.posX++;
						return true;
					}
				}
				
				else return false;
			
			// deplacement haut
			case 2: 
				if (this.posY > 0 /*&& this.posX == 0*/){
					this.posY--;
					if (tiles[this.posX][this.posY].getOwner() == Const.C_NONE) {
						return true;
					}
				}
				
				else return false;
			
			// deplacement bas
			case 3: 				
				if (/*this.posX == Const.NB_MAXTILES-1 &&*/ this.posY<Const.NB_MAXTILES-1) {
					if (tiles[this.posX][this.posY+1].getOwner() == Const.C_NONE) {
						this.posY++;
						return true;
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
		else if ((tiles[this.posX][this.posY].getOwner() != Const.C_NONE))
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
			case Const.C_IA2: return deplacerAleatoire();
			case Const.C_IA3: return suivreJoueur();
		}
		
		return false;
	}
	
	public boolean move(GameEntity entity) {
		// TODO Auto-generated method stub
		return move(entity, 0);
	}

}
