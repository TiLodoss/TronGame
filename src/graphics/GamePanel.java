package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import other.Const;
import exceptions.GameException;

/**
 * Classe GamePanel
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Composant qui affiche la grille de jeu et actualise les affichages
 *
 */
public class GamePanel extends JPanel{

	private Tile[][] tiles; //"carres" de la grille de jeu
	private GridLayout layout;

	/**
	 * Constructeur de GamePanel
	 * @param size (taille de la grille)
	 * @throws GameException 
	 */
	public GamePanel(int size) throws GameException
	{
		if(size > 100)
		{
			throw new GameException("Dimensions trop grandes !");
		}

		this.tiles = new Tile[size][size];
		this.layout = new GridLayout(size,size);
		this.setLayout(layout);
		setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension(100,100));
		initGrid(size);
	}

	/**
	 * Méthode de création de la grille de jeu vide
	 */
	public void initGrid(int size)
	{
		int width = getWidth()/size, height = getHeight()/size;

		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				Rectangle rect = new Rectangle(5,5);
				
				//On cree des JPanels pour représenter les tuiles de la carte
				JPanel tilePanel = new JPanel();
				tilePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				
				Tile tile = new Tile(i,j,tilePanel);
				tiles[i][j] = tile;
				this.add(tilePanel);
			}
		}
		
		paintTile(0,10, 0);
		paintTile(0,50, 0);
		paintTile(0,20, 0);
		
	}
	
	/**
	 * Methode pour changer la couleur d'une tuile
	 * @param x
	 * @param y
	 * @param color
	 * @throws GameException
	 */
	public void paintTile(int x, int y, int color)
	{
		switch(color)
		{
		//TODO
			case Const.C_PLAYER:
				if(tiles[x][y].getOwner() == Const.C_NONE);
				{
					tiles[x][y].setOwner(Const.C_PLAYER);
					tiles[x][y].setColor(Color.red);
				}
				break;
				
			case Const.C_IA1:
				
				break;
				
			case Const.C_IA2:
				break;
				
			case Const.C_IA3:
				break;
			
			default:
				break;
		}
		
		
		repaint();
		
	}

	/**
	 * Méthode painComponent (affichage a l'ecran)
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		//drawGrid(g);
	}

	/**
	 * Methode de rafraichissement de la grille
	 * @param g
	 */
	public void drawGrid(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;

		for(int i=0;i<tiles.length;i++)
		{
			for(int j=0;j<tiles[i].length;j++)
			{
				Rectangle r = tiles[i][j].getRectangle();
				Color col = tiles[i][j].getColor();

				g2D.setColor(col);
				g2D.fillRect(r.x, r.y, r.width, r.height);
				//this.add(r);
			}
		}
	}


}
