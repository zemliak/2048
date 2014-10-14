import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Button extends JButton {
	private int tileNumber;
	private boolean added; // true if already added button
	private final Color[] COLORS = { Color.YELLOW, Color.RED,
			Color.PINK, Color.GREEN, Color.BLUE, Color.GRAY };

	/**
	 * precondition String s must be "" if tileNumber is zero
	 * 
	 * @param s
	 */
	public Button(String s, boolean b) {
		super(s);
		added = b;
		if (s.length() == 0 || s == "0")
			tileNumber = 0;
		else
			tileNumber = Integer.parseInt(s);

	}

	public boolean getBoolean() {
		return added;
	}

	public void setTileNumber(int i, boolean b) {
		tileNumber = i;
		added = b;
		//this.changeTileColor();
		if (i == 0)
			this.setText("");
		else
			this.setText(i + "");

	}

	public void changeTileColor() {
		if((this.getText().equals("")))
                this.setBackground(Color.WHITE);
            else{
            if(Integer.parseInt(this.getText()) == 2)
                this.setBackground(Color.LIGHT_GRAY);
            if(Integer.parseInt(this.getText()) == 4)
                this.setBackground(Color.DARK_GRAY);
            if(Integer.parseInt(this.getText()) == 8)
                this.setBackground(new Color(233, 150, 122));
            if(Integer.parseInt(this.getText()) == 16)
                this.setBackground(new Color(255,99, 71));
            if(Integer.parseInt(this.getText()) == 32)
                this.setBackground(new Color(255,69, 0));
            if(Integer.parseInt(this.getText()) == 64)
                this.setBackground(Color.RED);
            if(Integer.parseInt(this.getText()) == 128)
                this.setBackground(new Color(238, 232, 170));
            if(Integer.parseInt(this.getText()) == 256)
                this.setBackground(new Color(255, 255, 0));
            if(Integer.parseInt(this.getText()) == 512)
                this.setBackground(new Color(255, 215, 0));
            if(Integer.parseInt(this.getText()) == 1024)
                this.setBackground(new Color(218, 165, 32));
            if(Integer.parseInt(this.getText()) == 2048)
                this.setBackground(new Color(184, 134, 11));
            }
	}
	public int getTileNumber() {
		return tileNumber;
	}

	public boolean canAdd(Button b) {
		if (this.getTileNumber() == b.getTileNumber())
			return true;
		return false;
	}
}
