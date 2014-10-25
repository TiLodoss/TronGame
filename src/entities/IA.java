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
		
		/*switch(this.currentDirection)
		{
			case Const.DIR_RIGHT:
				if (posX<Const.NB_MAXTILES-1) {
					if (tiles[this.posX+1][this.posY].getOwner() == Const.C_NONE) {
						this.posX++;
						this.currentDirection = Const.DIR_RIGHT;
						return true;
					}
					
					//Si a droite c'est bloque, on descend
					else
					{
						this.currentDirection = Const.DIR_BOTTOM;
					}
				}
				
				else
				{
					this.currentDirection = Const.DIR_BOTTOM;
				}
				break;
				
			case Const.DIR_LEFT:
				if (posX > 0){
					if (tiles[this.posX-1][this.posY].getOwner() == Const.C_NONE) {
						this.posX--;
						this.currentDirection = Const.DIR_LEFT;
						return true;
					}
					
					//Si a gauche c'est bloque, on monte
					else
					{
						this.currentDirection = Const.DIR_TOP;
					}
				}
				else
				{
					this.currentDirection = Const.DIR_TOP;
				}
				break;
				
			case Const.DIR_TOP:
				if (posY > 1 & posX == 0){
					if (tiles[this.posX][this.posY-1].getOwner() == Const.C_NONE) {
						this.posY--;
						this.currentDirection = Const.DIR_TOP;
						return true;
					}
					//Si en haut c'est bloque, on va a droite
					else
					{
						this.currentDirection = Const.DIR_RIGHT;
					}
				}
				else
				{
					this.currentDirection = Const.DIR_RIGHT;
				}
				break;
				
			case Const.DIR_BOTTOM:
				if (posY<Const.NB_MAXTILES-1 ) {
					if (tiles[this.posX][this.posY+1].getOwner() == Const.C_NONE) {
						this.posY++;
						this.currentDirection = Const.DIR_BOTTOM;
						return true;
					}
					
					//Si en bas c'est bloque, on va a gauche
					else
					{
						this.currentDirection = Const.DIR_LEFT;
					}
				}
				else
				{
					System.out.println("Peux plus aller en bas (limite atteinte)");
					this.currentDirection = Const.DIR_LEFT;
				}
				break;
				
			default:
				break;
				
		}
		*/
		
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
		}
		
		return false;
	}
	
	public boolean move(GameEntity entity) {
		// TODO Auto-generated method stub
		return move(entity, 0);
	}

}
