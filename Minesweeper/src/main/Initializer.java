package main;

import java.awt.Toolkit;

public class Initializer {
	public static void main(String[] args) {

		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		boolean isFinished = false;
		boolean isWin = false;
		boolean isRestart = true;
		MainInterface gameWindow;
		ResultInterface resultWindow;

		while (isRestart) {
			isRestart = false;
			isFinished = false;
			gameWindow = new MainInterface(screenWidth, screenHeight);
			gameWindow.detectClick();
			while (!isFinished) {
				int remainField = gameWindow.getTotalField();
				int totalMarked = 0;
				for (int i = 0; i < gameWindow.getRow(); i++) {
					for (int j = 0; j < gameWindow.getColumn(); j++) {
						if (gameWindow.getMineList().getMineList()[i][j].isMarked())
							totalMarked++;
						if (gameWindow.getMineList().getMineList()[i][j].isSkip())
							remainField--;
					}
				}

				boolean isMatch = false;
				if (totalMarked == 10) {
					isMatch = true;
					for (int i = 0; i < gameWindow.getRow(); i++) {
						for (int j = 0; j < gameWindow.getColumn(); j++) {
							if (gameWindow.getMineList().getMineList()[i][j].isMarked()) {
								if (!gameWindow.getMineList().getMineList()[i][j].isMine())
									isMatch = false;
							}
						}
					}
				}

				if (isMatch && (remainField == 0)) {
					resultWindow = new ResultInterface((int) gameWindow.getFrame().getLocation().getX(),
							(int) gameWindow.getFrame().getLocation().getY(), true, gameWindow.getFrame());
					resultWindow.setVisible(true);
					isWin = true;
				}
				isFinished = gameWindow.getGameStat();
				System.out.println(isFinished);
			}

			resultWindow = new ResultInterface((int) gameWindow.getFrame().getLocation().getX(),
					(int) gameWindow.getFrame().getLocation().getY(), false, gameWindow.getFrame());
			resultWindow.setVisible(true);
			for (int i = 0; i < 40; i++) {
				try {
					Thread.sleep(250);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(resultWindow.getRestart())
					break;
			}

			isRestart = resultWindow.getRestart();
			if (isRestart) {
				gameWindow.close();
				resultWindow.close();
			}
		}
	}
}
