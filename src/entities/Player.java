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
					if(this.posX>0)
					{
						if(tiles[this.posY][this.posX-1].getOwner() == Const.C_NONE) {
							this.posX--;
							this.currentDirection = Const.DIR_LEFT;
							return true;
						}
					}
					
					//Si on va a gauche mais qu'on est sur la limite gauche
					else if (this.posX == 0)
					{
						this.setStatus(Const.ENT_DEAD);
					}
					
				
				case Const.DIR_RIGHT: //droite
					if(this.posX<Const.NB_MAXTILES-1)
					{
						if(tiles[this.posY][this.posX+1].getOwner() == Const.C_NONE) {
							this.posX++;
							this.currentDirection = Const.DIR_RIGHT;
							return true;
						}
					}
					
					//Si on va a droite mais qu'on est sur la limite droite
					else if (this.posX == Const.NB_MAXTILES-1)
					{
						this.setStatus(Const.ENT_DEAD);
					}
					
					
				case Const.DIR_BOTTOM: //bas
					if(this.posY<Const.NB_MAXTILES-1)
					{
						if(tiles[this.posY+1][this.posX].getOwner() == Const.C_NONE) {
							this.posY++;
							this.currentDirection = Const.DIR_BOTTOM;
							return true;
						}
					}
					
					//Si on va en bas mais qu'on est sur la limite inferieure
					else if (this.posY == Const.NB_MAXTILES-1)
					{
						this.setStatus(Const.ENT_DEAD);
					}
					
					
				case Const.DIR_TOP: //haut
					if(this.posY>0)
					{
						if(tiles[this.posY-1][this.posX].getOwner() == Const.C_NONE) {
							this.posY--;
							this.currentDirection = Const.DIR_TOP;
							return true;
						}
					}
					
					//Si on va en haut mais qu'on est sur la limite superieur
					else if (this.posY == 0)
					{
						this.setStatus(Const.ENT_DEAD);
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
