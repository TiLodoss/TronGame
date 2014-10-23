package entities;

//import java.awt.Color;

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
	//private int diffLvl; //niveau de difficultï¿½
	private GamePanel gPanel;
	
	public IA(GamePanel panel, int lvl, int ownerCodeIA, int x, int y)
	{
		gPanel = panel;
		//diffLvl = lvl;
		ownerCode = ownerCodeIA;
		posX = x;
		posY = y;
	}
	
	public boolean deplacerSpirale() {
		Tile[][] tiles = gPanel.getTiles();

		//déplacement à droite
		if (posX<Const.NB_MAXTILES-1 && posY == 0) {
			if (tiles[this.posX+1][this.posY].getOwner() == Const.C_NONE) {
				this.posX++;
				return true;
			}
		}
		
		//déplacement en bas
		else if (posX == Const.NB_MAXTILES-1 && posY<Const.NB_MAXTILES-1) {
			if (tiles[this.posX][this.posY+1].getOwner() == Const.C_NONE) {
				this.posY++;
				return true;
			}
		}
		
		//déplacement à gauche
		else if (posX > 0 && posY == Const.NB_MAXTILES-1){
			if (tiles[this.posX-1][this.posY].getOwner() == Const.C_NONE) {
				this.posX--;
				return true;
			}
		}
		
		//déplacement en haut
		else if (posY > 1 & posX == 0){
			if (tiles[this.posX][this.posY-1].getOwner() == Const.C_NONE) {
				this.posY--;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean deplacerAleatoire() {
		return false;
	}
	
	public boolean suivreJoueur() {
		return false;
	}


	@Override
	public boolean collides() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean move(GameEntity entity, int direction) {
		// TODO Auto-generated method stub
		switch(entity.getOwnerCode()) {
			case Const.C_IA1: return deplacerSpirale();
		}
		
		return false;
	}
	
	public boolean move(GameEntity entity) {
		// TODO Auto-generated method stub
		return move(entity, 0);
	}

}
