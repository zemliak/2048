import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Grid {

	private JFrame grid;
	private Button[][] buttons;

	public Grid() {

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
		;

		grid.setVisible(true);
		grid.setSize(400, 400);
		grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void addRandomButton() {
		ArrayList<Button> selection = new ArrayList<Button>();
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (buttons[r][c].getTileNumber() == 0)
					selection.add(buttons[r][c]);
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

}