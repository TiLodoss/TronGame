package graphics;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import other.Const;
import engine.GameEngine;
import entities.GameEntity;
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
	private JDialog loadingDialog, gameOverDialog;
	
	
	/**
	 * Constructeur de MainWindow
	 * @param title
	 * @param width (largeur fenêtre)
	 * @param height (hauteur fenêtre)
	 * @param size (taille de la grille de jeu)
	 */
	public MainWindow(String title, int width, int height, int size)
	{
		displayLoadingDialog();
		
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
		play.setEnabled(false); //bouton inactif au début
		
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.add(gamePanel, BorderLayout.CENTER);
		mainPanel.add(play, BorderLayout.NORTH);
		
		
		
		//Listeners
		play.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				engine.startRound();	
				gamePanel.requestFocusInWindow();
				disablePlayButton();
			}
			
		});
		
		this.setVisible(true);
		
		//Supprimer le dialog quand la fenêtre principale est correctement affichée
		if(this.isDisplayable())
		{
			killLoadingDialog();
			play.setEnabled(true); //bouton cliquable apres le chargement
		}

	}
	
	/**
	 * Methode pour rendre le bouton play inactif
	 */
	public void disablePlayButton()
	{
		if(play.isEnabled())
			play.setEnabled(false);
	}
	
	/**
	 * Methode pour rendre le bouton play actif
	 */
	public void enablePlayButton()
	{
		if(!play.isEnabled())
			play.setEnabled(true);
	}
	
	/**
	 * Methode de mise a jour du score affiche pour un joueur
	 */
	public void updateEntityScore(int ownerCode, int score)
	{
		switch(ownerCode)
		{
			case Const.C_PLAYER:
				scorePlayer.setText(score+"");
				break;
				
			case Const.C_IA1:
				scoreIA1.setText(score+"");
				break;
				
			case Const.C_IA2:
				scoreIA2.setText(score+"");
				break;
				
			case Const.C_IA3:
				scoreIA3.setText(score+"");
				break;
		}
	}
	
	/**
	 * Methode qui affiche un dialogue de chargement
	 */
	public void displayLoadingDialog()
	{
		JLabel text = new JLabel("Veuillez patienter");
		text.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel dPanel = new JPanel();
		this.loadingDialog = new JDialog();
		loadingDialog.setTitle("Chargement");
		loadingDialog.setLocationRelativeTo(this);
		loadingDialog.setResizable(false);
		loadingDialog.setAlwaysOnTop(true);
		loadingDialog.setSize(new Dimension(250,100));
		loadingDialog.setUndecorated(true);

		
		dPanel.setLayout(new BorderLayout());
		dPanel.add(text, BorderLayout.CENTER);
		loadingDialog.setContentPane(dPanel);
		loadingDialog.show();	
	}
	
	/**
	 * Methode qui detruit le dialogue de chargement
	 */
	public void killLoadingDialog()
	{
		if(loadingDialog.isActive())
		{
			loadingDialog.dispose();
		}
	}
	
	/**
	 * Methode d'affichage du dialogue affichant le score final
	 */
	public void displayGameOverDialog(ArrayList<GameEntity> entities)
	{
		System.out.println("aaa");
		JButton quit = new JButton("Quitter");
		JButton restart = new JButton("Rejouer");
		JPanel panel = new JPanel(), scorePanel = new JPanel(), buttonsPanel = new JPanel();
		
		this.gameOverDialog = new JDialog();
		gameOverDialog.setTitle("Score final");
		gameOverDialog.setLocationRelativeTo(null);
		gameOverDialog.setResizable(false);
		gameOverDialog.setAlwaysOnTop(true);
		gameOverDialog.setSize(new Dimension(250,250));
		gameOverDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		panel.setLayout(new BorderLayout());
		scorePanel.setLayout(new GridLayout(entities.size(),2)); // tableau des scores
		buttonsPanel.setLayout(new FlowLayout());
		
		for(GameEntity e : entities)
		{
			scorePanel.add(new JLabel(e.getClass().getSimpleName()));
			scorePanel.add(new JLabel(e.getScore()+""));
		}
		
		/* Listeners des boutons */
		
		//Bouton quitter
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainWindow.this.dispose();
				System.exit(0);
			}
			
		});
		
		buttonsPanel.add(restart);
		buttonsPanel.add(quit);
		panel.add(scorePanel, BorderLayout.CENTER);
		panel.add(buttonsPanel,BorderLayout.SOUTH);
		
		gameOverDialog.setContentPane(panel);
		gameOverDialog.pack();
		gameOverDialog.show();
	}
	
	/**
	 * Methode qui detruit le dialogue de score
	 */
	public void killGameOverDialog()
	{
		if(gameOverDialog.isActive())
		{
			gameOverDialog.dispose();
		}
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
