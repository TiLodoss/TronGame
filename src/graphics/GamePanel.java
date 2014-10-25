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
	private GridLayout gridLayout;

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
		this.gridLayout = new GridLayout(size,size);
		this.setLayout(gridLayout);
		setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension(100,100));
		this.setFocusable(true);
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
				//On cree des JPanels pour représenter les tuiles de la carte
				JPanel tilePanel = new JPanel();
				tilePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				
				Tile tile = new Tile(i,j,tilePanel);
				tiles[i][j] = tile;
				this.add(tilePanel);
			}
		}
	}
	
	/**
	 * Methode qui reinitialise les tuiles de la grille
	 */
	public void cleanGrid()
	{
		for(int i=0;i<tiles.length;i++)
		{
			for(int j=0;j<tiles.length;j++)
			{
				tiles[i][j].setOwner(Const.C_NONE);
				tiles[i][j].paintPanel(Color.WHITE);
			}
		}
	}
	
	/**
	 * Methode pour changer la couleur / le proprietaire d'une tuile
	 * @param x
	 * @param y
	 * @param ownerColor
	 * @throws GameException
	 */
	public void paintTile(int x, int y, int ownerColor) throws GameException
	{
		if(x > tiles.length || x < 0 || y > tiles.length || y < 0)
		{
				throw new GameException("Coordonnees case invalides");
		}
		
		//Ne colorier que les cases vides
		if(tiles[y][x].getOwner() == Const.C_NONE)
		{
			switch(ownerColor)
			{
				case Const.C_PLAYER:
						tiles[y][x].setOwner(Const.C_PLAYER);
						tiles[y][x].paintPanel(Color.RED);

					break;
					
				case Const.C_IA1:
						tiles[y][x].setOwner(Const.C_IA1);
						tiles[y][x].paintPanel(Color.BLUE);
					break;
					
				case Const.C_IA2:
					tiles[y][x].setOwner(Const.C_IA2);
					tiles[y][x].paintPanel(Color.GREEN);
					break;
					
				case Const.C_IA3:
					tiles[y][x].setOwner(Const.C_IA3);
					tiles[y][x].paintPanel(Color.BLACK);
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
			System.out.println("tuile "+i);
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

	public GridLayout getGridLayout() {
		return gridLayout;
	}

	public void setGridLayout(GridLayout layout) {
		this.gridLayout = layout;
	}
	
	
	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	
	
	


}
