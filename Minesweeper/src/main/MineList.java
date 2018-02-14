package main;

import java.util.ArrayList;

public class MineList {
	private int row;
	private int column;

	private int mineNum;

	private MineButton[][] mineList;

	public MineList(int row, int column, int mineNum) {
		this.row = row;
		this.column = column;
		this.mineNum = mineNum;
		mineList = new MineButton[row][column];
		populateMineList(0, row, 0, column, mineNum);
	}

	public MineButton[][] getMineList() {
		return mineList;
	}

	public void populateMineList(int xMin, int xMax, int yMin, int yMax, int num) {
		int numCount = 0;
		
		while (numCount < num) {
			boolean use = true;

			int xTemp = (int) (Math.random() * xMax + xMin);
			int yTemp = (int) (Math.random() * yMax + yMin);
			for (int j = 0; j < xMax; j++) {
				for (int k = 0; k < yMax; k++) {
					if (mineList[j][k] != null && mineList[j][k].getXPos() == xTemp && mineList[j][k].getYPos() == yTemp) {
						use = false;
					}
				}
			}
			if (use) {
				mineList[xTemp][yTemp] = new MineButton(xTemp, yTemp, row, column, true);
				numCount++;
			}
		}

		for (int i = 0; i < xMax; i++) {
			for (int j = 0; j < yMax; j++) {
				if (mineList[i][j] == null) {
					mineList[i][j] = new MineButton(i, j, row, column, false);
				}
			}
		}
	}
}
