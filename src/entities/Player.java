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
	 * @param gPanel
	 * @param x
	 * @param y
	 */
	public Player(GamePanel gPanel, int x, int y)
	{
		super.setOwnerCode(Const.C_PLAYER);
		gamePanel = gPanel;
		this.posX = x;
		this.posY = y;
	}

	/**
	 * Methode de déplacement du joueur
	 * @param entity
	 * @param direction
	 */
	@Override
	public boolean move(GameEntity entity, int direction) {
		// TODO Auto-generated method stub
		Tile[][] tiles = gamePanel.getTiles();

		if (entity.getOwnerCode() == Const.C_PLAYER) {
			switch (direction) {

			case Const.DIR_LEFT: //gauche
				if(currentDirection != Const.DIR_RIGHT)
				{
					if(this.posX>0)
					{
						if(tiles[this.posY][this.posX-1].getOwner() == Const.C_NONE) {
							this.posX--;
							this.currentDirection = Const.DIR_LEFT;
							return true;
						}
						else
						{
							this.setStatus(Const.ENT_DEAD);
							return false;
						}
					}

					//Si on va a gauche mais qu'on est sur la limite gauche
					else if (this.posX == 0)
					{
						this.setStatus(Const.ENT_DEAD);
						return false;
					}
				}


			case Const.DIR_RIGHT: //droite
				if(currentDirection != Const.DIR_LEFT)
				{
					if(this.posX<Const.NB_MAXTILES-1)
					{
						if(tiles[this.posY][this.posX+1].getOwner() == Const.C_NONE) {
							this.posX++;
							this.currentDirection = Const.DIR_RIGHT;
							return true;
						}
						else
						{
							this.setStatus(Const.ENT_DEAD);
							return false;
						}
					}

					//Si on va a droite mais qu'on est sur la limite droite
					else if (this.posX == Const.NB_MAXTILES-1)
					{
						this.setStatus(Const.ENT_DEAD);
						return false;
					}	
				}



			case Const.DIR_BOTTOM: //bas
				if(currentDirection != Const.DIR_TOP)
				{
					if(this.posY<Const.NB_MAXTILES-1)
					{
						if(tiles[this.posY+1][this.posX].getOwner() == Const.C_NONE) {
							this.posY++;
							this.currentDirection = Const.DIR_BOTTOM;
							return true;
						}
						else
						{
							this.setStatus(Const.ENT_DEAD);
							return false;
						}
					}

					//Si on va en bas mais qu'on est sur la limite inferieure
					else if (this.posY == Const.NB_MAXTILES-1)
					{
						this.setStatus(Const.ENT_DEAD);
					}
				}

			case Const.DIR_TOP: //haut
				if(currentDirection != Const.DIR_BOTTOM)
				{
					if(this.posY>0)
					{
						if(tiles[this.posY-1][this.posX].getOwner() == Const.C_NONE) {
							this.posY--;
							this.currentDirection = Const.DIR_TOP;
							return true;
						}
						else
						{
							this.setStatus(Const.ENT_DEAD);
							return false;
						}
					}

					//Si on va en haut mais qu'on est sur la limite superieur
					else if (this.posY == 0)
					{
						this.setStatus(Const.ENT_DEAD);
					}
				}
			default: return false;

			}
		}
		return false;
	}

	/**
	 * Methode move
	 */
	@Override
	public boolean move(GameEntity entity) {
		// TODO Auto-generated method stub
		return move(entity, -1);
	}
}
