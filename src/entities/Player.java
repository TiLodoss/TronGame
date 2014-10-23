package entities;

import graphics.GamePanel;
import graphics.Tile;
import other.Const;

/**
 * Classe Player
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe representant le joueur
 *
 */
public class Player extends GameEntity
{
	private GamePanel gamePanel;
	
	/**
	 * Constructeur de Player
	 */
	public Player(GamePanel gPanel, int x, int y)
	{
		super.setOwnerCode(Const.C_PLAYER);
		gamePanel = gPanel;
		this.posX = x;
		this.posY = y;
	}
	
	@Override
	public boolean move(GameEntity entity, int direction) {
		// TODO Auto-generated method stub
		Tile[][] tiles = gamePanel.getTiles();
		
		if (entity.getOwnerCode() == Const.C_PLAYER) {
			switch (direction) {
				case 37: //gauche
					if(tiles[this.posX-1][this.posY].getOwner() == Const.C_NONE) {
						this.posX--;
						return true;
					}
				
				case 39: //droite
					if(tiles[this.posX+1][this.posY].getOwner() == Const.C_NONE) {
						this.posX++;
						return true;
					}
					
				case 40: //bas
					if(tiles[this.posX][this.posY+1].getOwner() == Const.C_NONE) {
						this.posY++;
						return true;
					}
					
				case 38: //haut
					if(tiles[this.posX][this.posY-1].getOwner() == Const.C_NONE) {
						this.posY--;
						return true;
					}
				default: return false;
				
			}
		}
		return false;
	}
	
	@Override
	public boolean move(GameEntity entity) {
		// TODO Auto-generated method stub
		return move(entity, -1);
	}

	@Override
	public boolean collides() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
