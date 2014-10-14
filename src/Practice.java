import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Practice implements KeyListener {
	JTextArea inputText;
	JTextArea feedbackText;

	public Practice() {
		JFrame guiFrame = new JFrame();

		// make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Creating a Table Example");
		guiFrame.setSize(700, 200);

		// This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);

		feedbackText = new JTextArea();
		JScrollPane scrollText = new JScrollPane(feedbackText);

		inputText = new JTextArea();

		// place keylistener code here

		guiFrame.add(inputText, BorderLayout.NORTH);
		guiFrame.add(scrollText, BorderLayout.CENTER);
		guiFrame.setVisible(true);
	}

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Practice();
			}
		});
	}*/

	@Override
	public void keyPressed(KeyEvent e) {
		feedbackText.append("Key Pressed: " + e.getKeyChar() + "\n");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		feedbackText.append("Key Released: " + e.getKeyChar() + "\n");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		feedbackText.append("Key Typed: " + e.getKeyChar() + " "
				+ KeyEvent.getKeyModifiersText(e.getModifiers()) + "\n");
	}
}
