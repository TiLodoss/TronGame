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
		if(size > Const.NB_MAXTILES)
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
	
	public Tile[][] getTiles() {
		return tiles;
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
				//On cree des JPanels pour représenter les tuiles de la carte
				JPanel tilePanel = new JPanel();
				tilePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				
				Tile tile = new Tile(i,j,tilePanel);
				tiles[i][j] = tile;
				this.add(tilePanel);
			}
		}
		
		//Test
		try
		{
			paintTile(0,10, 0);
			paintTile(1,10, 0);
			paintTile(2,10, 0);
			paintTile(3,10, 0);
			paintTile(4,10, 0);
			paintTile(0,50, 1);
			paintTile(0,20, 3);
		}
		
		catch(GameException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * Methode pour changer la couleur d'une tuile
	 * @param x
	 * @param y
	 * @param ownerColor
	 * @throws GameException
	 */
	public void paintTile(int x, int y, int ownerColor) throws GameException
	{
		//Ne colorier que les cases vides
		if(tiles[x][y].getOwner() == Const.C_NONE)
		{
			switch(ownerColor)
			{
				case Const.C_PLAYER:
						tiles[x][y].setOwner(Const.C_PLAYER);
						tiles[x][y].paintPanel(Color.RED);
					break;
					
				case Const.C_IA1:
						tiles[x][y].setOwner(Const.C_IA1);
						tiles[x][y].paintPanel(Color.BLUE);
					break;
					
				case Const.C_IA2:
					tiles[x][y].setOwner(Const.C_IA2);
					tiles[x][y].paintPanel(Color.GREEN);
					break;
					
				case Const.C_IA3:
					tiles[x][y].setOwner(Const.C_IA3);
					tiles[x][y].paintPanel(Color.BLACK);
					break;
				
				default:
					break;
			}
		}
		
		else 
		{
			throw new GameException("Case  ["+x+", "+y+"] deja occupee");
		}

		repaint(); //Actualiser l'affichage
		
	}
	
	/**
	 * Methode permettant de mettre a jour les tuiles nouvellement colorees
	 * @param tilesToUpdate
	 */
	public void updateData(ArrayList<Tile> tilesToUpdate)
	{
		for(int i=0;i<tilesToUpdate.size();i++)
		{
			try 
			{
				//TODO a ameliorer ?
				paintTile(tilesToUpdate.get(i).getX(),tilesToUpdate.get(i).getY(), tilesToUpdate.get(i).getOwner());
			} 
			catch (GameException e) 
			{
				e.printStackTrace();
			}
		}
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
				//Rectangle r = tiles[i][j].getRectangle();
				//Color col = tiles[i][j].getColor();

				//g2D.setColor(col);
				//g2D.fillRect(r.x, r.y, r.width, r.height);
				//this.add(r);
			}
		}
	}


}
