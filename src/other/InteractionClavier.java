package other;
import entities.Player;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InteractionClavier implements KeyListener {
	private int keyPressedCode = 0;
	private Player player;
	
	public InteractionClavier(Player p) {
		player = p;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		keyPressedCode = arg0.getKeyCode();
		System.out.println(keyPressedCode);
		player.move(player, keyPressedCode);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public int getKeyPressedCode() {
		return keyPressedCode;
	}

}
