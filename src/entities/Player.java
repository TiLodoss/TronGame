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
				case Const.DIR_LEFT: //gauche
					if(tiles[this.posX-1][this.posY].getOwner() == Const.C_NONE) {
						this.posX--;
						this.currentDirection = Const.DIR_LEFT;
						return true;
					}
				
				case Const.DIR_RIGHT: //droite
					if(tiles[this.posX+1][this.posY].getOwner() == Const.C_NONE) {
						this.posX++;
						this.currentDirection = Const.DIR_RIGHT;
						return true;
					}
					
				case Const.DIR_BOTTOM: //bas
					if(tiles[this.posX][this.posY+1].getOwner() == Const.C_NONE) {
						this.posY++;
						this.currentDirection = Const.DIR_BOTTOM;
						return true;
					}
					
				case Const.DIR_TOP: //haut
					if(tiles[this.posX][this.posY-1].getOwner() == Const.C_NONE) {
						this.posY--;
						this.currentDirection = Const.DIR_TOP;
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
