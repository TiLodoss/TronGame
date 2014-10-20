package entities;

import java.awt.Color;

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
	private int diffLvl; //niveau de difficult�
	private int posX, posY; //position en x et en y
	private GamePanel gPanel;
	
	public IA(GamePanel panel, int lvl, int ownerCodeIA, int x, int y)
	{
		gPanel = panel;
		diffLvl = lvl;
		ownerCode = ownerCodeIA;
		posX = x;
		posY = y;
	}
	
	/**
	 * M�thode d�clenchant le mouvement des IA selon son niveau
	 * @param lvl Niveau de difficult� de l'IA
	 * @return TRUE si le d�placement est possible, FALSE sinon
	 */
	public boolean moveIA(int lvl) {
		switch(lvl) {
			case Const.IA_LVL0:
				return deplacerSpirale();

			case Const.IA_LVL1:
				return deplacerAleatoire();
			
			case Const.IA_LVL2:
				return suivreJoueur();
		}
		
		return false;
	}
	
	public boolean deplacerSpirale() {
		Tile[][] tiles = gPanel.getTiles();
		
		if (posX == 0 && posY == 0) {
			while (tiles[posX][posY++].getColor() == Color.LIGHT_GRAY) {
				move(Const.DIR_RIGHT);
			}			
		}
		
		else if (posY == Const.NB_MAXTILES && posX == 0) {
			while (tiles[posX++][posY].getColor() == Color.LIGHT_GRAY) {
				move(Const.DIR_BOTTOM);
			}
		}
		
		else if (posX == Const.NB_MAXTILES && posY == Const.NB_MAXTILES) {
			while (tiles[posX--][posY].getColor() == Color.LIGHT_GRAY) {
				move(Const.DIR_LEFT);
			}
		}
		
		else if (posX == 0 && posY == Const.NB_MAXTILES) {
			while (tiles[posX][posY--].getColor() == Color.LIGHT_GRAY) {
				move(Const.DIR_TOP);
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
	public void move(int direction) {
		// TODO Auto-generated method stub
		switch(direction) {
			case Const.DIR_LEFT: posX--;
			break;
			
			case Const.DIR_RIGHT: posX++;
			break;
			
			case Const.DIR_BOTTOM: posY--;
			break;
			
			case Const.DIR_TOP: posY++;
			break;
			
			//default:
		}
	}

	@Override
	public boolean collides() {
		// TODO Auto-generated method stub
		return false;
	}

}
