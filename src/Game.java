import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game implements ActionListener {  //checked is problem but not resetBooleans
	private Grid grid;
	private Button[][] buttons;
	private JFrame remote;
	private JButton up;
	private JButton left;
	private JButton right;
	private JButton down;

	public Game() {

		grid = new Grid();
		buttons = grid.getButtons();

		remote = new JFrame(); // sets the remote
		remote.setLayout(new GridLayout(3, 1));

		up = new JButton("UP");
		left = new JButton("LEFT");
		right = new JButton("RIGHT");
		down = new JButton("DOWN");
		
		up.addActionListener(this);
		left.addActionListener(this);
		right.addActionListener(this);
		down.addActionListener(this);

		remote.add(up);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(left);
		panel.add(right);
		remote.add(panel);
		remote.add(down);

		remote.setVisible(true);
		remote.setSize(400, 400);
		remote.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == up){
			shiftUp();
		}
		else if (e.getSource() == left){
			shiftLeft();
		}
		else if (e.getSource() == right){
			shiftRight();
		}
		else if (e.getSource() == down){
			shiftDown();
		}
		grid.addRandomButton();
		if(this.IsDone()){
			JOptionPane.showMessageDialog(null, "You Lose!", "Game Over", 1);		
		}
		if(this.won()){
			JOptionPane.showMessageDialog(null, "You Win!", "You got the 2048 Tile", 1);
		}

	}

	public void shiftUp() { 
		for(int c = 0; c < 4; c++){
			for (int r = 1; r < 4; r++) {
				int val = r;
				while(val <= r && val != 0){
					if(buttons[val-1][c].getTileNumber() == 0){
						buttons[val-1][c].setTileNumber(buttons[val][c].getTileNumber(),false);
						buttons[val][c].setTileNumber(0,false);
						--val;
					}
					else if(buttons[val][c].canAdd(buttons[val-1][c]) && !buttons[val-1][c].getBoolean()){
						buttons[val-1][c].setTileNumber(2*buttons[val][c].getTileNumber(),true);
						buttons[val][c].setTileNumber(0,false);
						--val;
					}else break;
				}
			}
		}
		this.resetBooleans(buttons);
	}

	private void shiftDown() {
		for(int c = 0; c < 4; c++){
			for(int r = 2; r >= 0; r--){
				int val = r;
				while(val >= r && val != 3){
					if(buttons[val+1][c].getTileNumber() == 0){
						buttons[val+1][c].setTileNumber(buttons[val][c].getTileNumber(),false);
						buttons[val][c].setTileNumber(0,false);
						++val;
					}
					else if(buttons[val][c].canAdd(buttons[val+1][c]) && !buttons[val+1][c].getBoolean()){
						buttons[val+1][c].setTileNumber(2*buttons[val][c].getTileNumber(),true);
						buttons[val][c].setTileNumber(0,false);
						++val;
					}else break;
				}
			}
		}
		this.resetBooleans(buttons);
	}

	private void shiftRight() {
		for(int r = 0; r < 4; r++){
			for (int c = 2; c >= 0; c--) {
				int val = c;
				while(val >= c && val != 3){
					if(buttons[r][val+1].getTileNumber() == 0){
						buttons[r][val+1].setTileNumber(buttons[r][val].getTileNumber(),false);
						buttons[r][val].setTileNumber(0,false);
						++val;
					}
					else if(buttons[r][val].canAdd(buttons[r][val+1]) && !buttons[r][val+1].getBoolean()){
						buttons[r][val+1].setTileNumber(2*buttons[r][val].getTileNumber(),true);
						buttons[r][val].setTileNumber(0,false);
						++val;
					}else break;
				}
			}
		}
		this.resetBooleans(buttons);
	}

	private void shiftLeft() {
		for(int r = 0; r < 4; r++){
			for (int c = 1; c < 4; c++) {
				int val = c;
				while(val <= c && val != 0){
					if(buttons[r][val-1].getTileNumber() == 0){
						buttons[r][val-1].setTileNumber(buttons[r][val].getTileNumber(),false);
						buttons[r][val].setTileNumber(0,false);
						--val;
					}
					else if(buttons[r][val].canAdd(buttons[r][val-1]) && !buttons[r][val-1].getBoolean()){
						buttons[r][val-1].setTileNumber(2*buttons[r][val].getTileNumber(),true);
						buttons[r][val].setTileNumber(0,false);
						--val;
					}else break;
				}
			}
		}
		this.resetBooleans(buttons);
	}
	public void resetBooleans(Button[][] b){
		for(Button[] x : b){
			for(Button y : x){
				y.setTileNumber(y.getTileNumber(), false);
			}
		}
	}
	public boolean won(){
		for(Button[] a : buttons){
			for(Button b : a){
				if(b.getTileNumber() == 2048){
					return true;
				}
			}
		}
		return false;
	}
	public boolean IsDone(){
		for(Button[] a : buttons){
			for(Button b : a){
				if(b.getTileNumber() == 0) 
					return false;
			}
		}
		return true;
	}
}

