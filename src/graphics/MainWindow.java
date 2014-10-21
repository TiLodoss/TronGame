package graphics;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import engine.GameEngine;
import exceptions.GameException;

/**
 * Classe MainWindow
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Fenetre principale du jeu
 *
 */
public class MainWindow extends JFrame {
	
	private JPanel mainPanel, middlePanel, bottomPanel; //conteneurs : principal, ecran de jeu, barre en bas (score)
	private GamePanel gamePanel; //grille de jeu
	private JLabel scoreTitle, scorePlayer, scoreIA1, scoreIA2, scoreIA3; //texte "score" et le score de chaque joueur
	private JLabel redIcon, blueIcon, greenIcon, blackIcon; //icones des joueurs
	private JButton play; //Bouton pour jouer un round
	
	private BorderLayout mainLayout; //layout principal
	private GridLayout bottomLayout; //layout de la barre du bas
	
	private GameEngine engine; //moteur qui gere la logique du jeu
	
	
	/**
	 * Constructeur de MainWindow
	 * @param title
	 * @param width (largeur fenêtre)
	 * @param height (hauteur fenêtre)
	 * @param size (taille de la grille de jeu)
	 */
	public MainWindow(String title, int width, int height, int size)
	{
		try 
		{
			this.gamePanel = new GamePanel(size);
		} 
		catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.engine = new GameEngine(this, gamePanel);
		
		this.setTitle(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Panels
		this.mainPanel = new JPanel();
		this.bottomPanel = new JPanel();
		
		
		
		//Layouts
		this.mainLayout = new BorderLayout();
		this.bottomLayout = new GridLayout(1,9);
		
		this.mainPanel.setLayout(mainLayout);
		this.bottomPanel.setLayout(bottomLayout);
		
		this.setContentPane(mainPanel);
		
		//Labels
		scoreTitle = new JLabel("Score");
		scorePlayer = new JLabel("0");
		scoreIA1 = new JLabel("0");
		scoreIA2 = new JLabel("0");
		scoreIA3 = new JLabel("0");
		
		//Icones des joueurs
		ImageIcon red= new ImageIcon(this.getClass().getResource("/images/ic_red.png"));
		redIcon = new JLabel(red);
		ImageIcon blue= new ImageIcon(this.getClass().getResource("/images/ic_blue.png"));
		blueIcon = new JLabel(blue);
		ImageIcon green= new ImageIcon(this.getClass().getResource("/images/ic_green.png"));
		greenIcon = new JLabel(green);
		ImageIcon black= new ImageIcon(this.getClass().getResource("/images/ic_black.png"));
		blackIcon = new JLabel(black);
		
		this.bottomPanel.add(scoreTitle);
		this.bottomPanel.add(redIcon);
		this.bottomPanel.add(scorePlayer);
		this.bottomPanel.add(blueIcon);
		this.bottomPanel.add(scoreIA1);
		this.bottomPanel.add(greenIcon);
		this.bottomPanel.add(scoreIA2);
		this.bottomPanel.add(blackIcon);
		this.bottomPanel.add(scoreIA3);
		this.bottomPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		//Boutons
		play = new JButton("Jouer");
		
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.add(gamePanel, BorderLayout.CENTER);
		mainPanel.add(play, BorderLayout.NORTH);
		
		
		
		//Listeners
		play.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				engine.play();
				
			}
			
		});
		
		this.setVisible(true);

	}


	public JPanel getMainPanel() {
		return mainPanel;
	}


	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}


	public JPanel getMiddlePanel() {
		return middlePanel;
	}


	public void setMiddlePanel(JPanel middlePanel) {
		this.middlePanel = middlePanel;
	}


	public JPanel getBottomPanel() {
		return bottomPanel;
	}


	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}


	public GamePanel getGamePanel() {
		return gamePanel;
	}


	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}


	public JLabel getScoreTitle() {
		return scoreTitle;
	}


	public void setScoreTitle(JLabel scoreTitle) {
		this.scoreTitle = scoreTitle;
	}


	public JLabel getScorePlayer() {
		return scorePlayer;
	}


	public void setScorePlayer(JLabel scorePlayer) {
		this.scorePlayer = scorePlayer;
	}


	public JLabel getScoreIA1() {
		return scoreIA1;
	}


	public void setScoreIA1(JLabel scoreIA1) {
		this.scoreIA1 = scoreIA1;
	}


	public JLabel getScoreIA2() {
		return scoreIA2;
	}


	public void setScoreIA2(JLabel scoreIA2) {
		this.scoreIA2 = scoreIA2;
	}


	public JLabel getScoreIA3() {
		return scoreIA3;
	}


	public void setScoreIA3(JLabel scoreIA3) {
		this.scoreIA3 = scoreIA3;
	}


	public JLabel getRedIcon() {
		return redIcon;
	}


	public void setRedIcon(JLabel redIcon) {
		this.redIcon = redIcon;
	}


	public JLabel getBlueIcon() {
		return blueIcon;
	}


	public void setBlueIcon(JLabel blueIcon) {
		this.blueIcon = blueIcon;
	}


	public JLabel getGreenIcon() {
		return greenIcon;
	}


	public void setGreenIcon(JLabel greenIcon) {
		this.greenIcon = greenIcon;
	}


	public JLabel getBlackIcon() {
		return blackIcon;
	}


	public void setBlackIcon(JLabel blackIcon) {
		this.blackIcon = blackIcon;
	}


	public JButton getPlay() {
		return play;
	}


	public void setPlay(JButton play) {
		this.play = play;
	}


	public BorderLayout getMainLayout() {
		return mainLayout;
	}


	public void setMainLayout(BorderLayout mainLayout) {
		this.mainLayout = mainLayout;
	}


	public GridLayout getBottomLayout() {
		return bottomLayout;
	}


	public void setBottomLayout(GridLayout bottomLayout) {
		this.bottomLayout = bottomLayout;
	}


	public GameEngine getEngine() {
		return engine;
	}

	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
}
