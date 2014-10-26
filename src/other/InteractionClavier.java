package other;
import entities.Player;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe InteractionClavier
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe permettant de gerer les entrees clavier
 *
 */
public class InteractionClavier implements KeyListener {
	private int keyPressedCode = 0;
	private Player player;
	private boolean keyIsPressed;
	
	/**
	 * Constructeur d'InteractionClavier
	 * @param p
	 */
	public InteractionClavier(Player p) {
		player = p;
		keyIsPressed = false;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		keyPressedCode = arg0.getKeyCode();
		keyIsPressed = true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public int getKeyPressedCode() {
		return keyPressedCode;
	}

	public boolean isKeyPressed() {
		return keyIsPressed;
	}

	
	
	

}
