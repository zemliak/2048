import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Better implements KeyListener{

    private JFrame grid;
    private Button[][] buttons;
    private boolean flag = false;
    public static void main(String [] args){
        Better b = new Better();
    }
    public Better() {

        buttons = new Button[4][4];

        grid = new JFrame();
        grid.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button b = new Button("",false);
                buttons[i][j] = b;
                grid.add(b);
            }
        }

        int randomRow1 = (int) (Math.random() * 4);
        int randomCol1 = (int) (Math.random() * 4);

        int randomRow2 = (int) (Math.random() * 4);
        int randomCol2 = (int) (Math.random() * 4);
        while (randomRow1 == randomRow2 && randomCol1 == randomCol2) {
            randomRow2 = (int) (Math.random() * 4);
            randomCol2 = (int) (Math.random() * 4);
        }

        buttons[randomRow1][randomCol1].setTileNumber(2,false);
        buttons[randomRow2][randomCol2].setTileNumber(2,false);
        for(int r = 0; r < 4; r++){
		    for(int c = 0; c < 4; c++){
		          buttons[r][c].changeTileColor();
		      }
		}
        grid.addKeyListener(this);
        grid.setFocusable(true);
        grid.setVisible(true);
        grid.setSize(400, 400);
        grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void keyReleased(KeyEvent e){}
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP){
			shiftUp();
		}
		else if(key == KeyEvent.VK_LEFT){
			shiftLeft();
		}
		else if(key == KeyEvent.VK_RIGHT){
			shiftRight();
		}
		else if(key == KeyEvent.VK_DOWN){
			shiftDown();
		}
		else if(key == KeyEvent.VK_N){
			grid.setVisible(false);
			grid.dispose();
			Better x = new Better();
		}
		if(!(this.isFull()) && flag){
		      addRandomButton();
		      flag = false;
		  }
		for(int r = 0; r < 4; r++){
		    for(int c = 0; c < 4; c++){
		          buttons[r][c].changeTileColor();
		      }
		}
		if(this.IsDone()){
			int reply = JOptionPane.showConfirmDialog(null, "You Lose! \nPress Yes for a new game", "Game Over", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				grid.setVisible(false);
				grid.dispose();
				Better x = new Better();
		        }
		}
		if(this.won()){
			JOptionPane.showMessageDialog(null, "You Win!", "You got the 2048 Tile", 1);
		}
    }
    public void addRandomButton() {
        ArrayList<Button> selection = new ArrayList<Button>();
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (buttons[r][c].getTileNumber() == 0){
                    selection.add(buttons[r][c]); 
                }
            }
        }
        int randomButtonIndex = (int) (Math.random() * selection.size());
        if(Math.random() < 0.5){
            selection.get(randomButtonIndex).setTileNumber(2,false);
        }else{
            selection.get(randomButtonIndex).setTileNumber(4,false);
        }
        
    }

    public Button[][] getButtons() {
        return buttons;
    }
    public void shiftUp() { 
		for(int c = 0; c < 4; c++){
			for (int r = 1; r < 4; r++) {
				int val = r;
				while(val <= r && val != 0){
					if(buttons[val-1][c].getTileNumber() == 0 && buttons[val][c].getTileNumber() != 0){
						buttons[val-1][c].setTileNumber(buttons[val][c].getTileNumber(),false);
						buttons[val][c].setTileNumber(0,false);
						--val;
						flag = true;
					}
					else if(buttons[val][c].canAdd(buttons[val-1][c]) && !(buttons[val-1][c].getBoolean()) && !(buttons[val][c].getBoolean())){
						buttons[val-1][c].setTileNumber(2*buttons[val][c].getTileNumber(),true);
						buttons[val][c].setTileNumber(0,false);
						--val;
						flag = true;
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
					if(buttons[val+1][c].getTileNumber() == 0 && buttons[val][c].getTileNumber() != 0){
						buttons[val+1][c].setTileNumber(buttons[val][c].getTileNumber(),false);
						buttons[val][c].setTileNumber(0,false);
						++val;
						flag = true;
					}
					else if(buttons[val][c].canAdd(buttons[val+1][c]) && !(buttons[val+1][c].getBoolean()) && !(buttons[val][c].getBoolean())){
						buttons[val+1][c].setTileNumber(2*buttons[val][c].getTileNumber(),true);
						buttons[val][c].setTileNumber(0,false);
						++val;
						flag = true;
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
					if(buttons[r][val+1].getTileNumber() == 0 && buttons[r][val].getTileNumber() != 0){
						buttons[r][val+1].setTileNumber(buttons[r][val].getTileNumber(),false);
						buttons[r][val].setTileNumber(0,false);
						++val;
						flag = true;
					}
					else if(buttons[r][val].canAdd(buttons[r][val+1]) && !(buttons[r][val+1].getBoolean()) && !(buttons[r][val].getBoolean())){
						buttons[r][val+1].setTileNumber(2*buttons[r][val].getTileNumber(),true);
						buttons[r][val].setTileNumber(0,false);
						++val;
						flag = true;
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
					if(buttons[r][val-1].getTileNumber() == 0 && buttons[r][val].getTileNumber() != 0){
						buttons[r][val-1].setTileNumber(buttons[r][val].getTileNumber(),false);
						buttons[r][val].setTileNumber(0,false);
						--val;
						flag = true;
					}
					else if(buttons[r][val].canAdd(buttons[r][val-1]) && !(buttons[r][val-1].getBoolean()) && !(buttons[r][val].getBoolean())){
						buttons[r][val-1].setTileNumber(2*buttons[r][val].getTileNumber(),true);
						buttons[r][val].setTileNumber(0,false);
						--val;
						flag = true;
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
	public boolean isFull(){
	       for(int r = 0; r < 4; r++){
			for (int c = 0; c < 4; c++) {
			    if(buttons[r][c].getTileNumber() == 0)
			         return false;
			 }}
			 return true;
	   }
	public boolean IsDone(){
		for(int r = 0; r < 4; r++){
			for (int c = 0; c < 4; c++) {
			     if(buttons[r][c].getTileNumber() == 0)
			         return false;
			     if(r > 1 && buttons[r-1][c].getTileNumber() == buttons[r][c].getTileNumber())
			         return false;
			     if(r < 3 && buttons[r+1][c].getTileNumber() == buttons[r][c].getTileNumber())
			         return false;
			     if(c > 1 && buttons[r][c-1].getTileNumber() == buttons[r][c].getTileNumber())
			         return false;
			     if(c < 3 && buttons[r][c+1].getTileNumber() == buttons[r][c].getTileNumber())
			         return false;
			 }
        }
		return true;
	}
}