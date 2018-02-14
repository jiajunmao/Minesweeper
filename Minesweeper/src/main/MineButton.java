package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MineButton extends JButton {
	private boolean isMine;
	private int xPos;
	private int yPos;
	private int row;
	private int column;
	private boolean isSkip;
	private boolean isMarked;

	public MineButton(int x, int y, int row, int column, boolean isMine) {
		this.xPos = x;
		this.yPos = y;
		this.isMine = isMine;
		this.row = row;
		this.column = column;
		this.isMarked = false;
	}

	public int getXPos() {
		return this.xPos;
	}

	public int getYPos() {
		return this.yPos;
	}

	public boolean isMine() {
		return isMine;
	}

	public boolean isSkip() {
		return isSkip;
	}

	public void setSkip(boolean isSkip) {
		this.isSkip = isSkip;
	}

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}

	public MineButton getUp() {
		JPanel tempPanel = (JPanel) this.getParent();
		return (MineButton) tempPanel.getComponent((tempPanel.getComponentZOrder(this) - 1));
	}

	public MineButton getDown() {
		JPanel tempPanel = (JPanel) this.getParent();
		return (MineButton) tempPanel.getComponent((tempPanel.getComponentZOrder(this) + 1));
	}

	public MineButton getRight() {
		JPanel tempPanel = (JPanel) this.getParent();
		return (MineButton) tempPanel.getComponent((tempPanel.getComponentZOrder(this) + this.column));
	}

	public MineButton getLeft() {
		JPanel tempPanel = (JPanel) this.getParent();
		return (MineButton) tempPanel.getComponent((tempPanel.getComponentZOrder(this) - this.column));
	}

	public MineButton getUpperLeft() {
		JPanel tempPanel = (JPanel) this.getParent();
		return (MineButton) tempPanel.getComponent((tempPanel.getComponentZOrder(this) - this.column - 1));
	}

	public MineButton getLowerLeft() {
		JPanel tempPanel = (JPanel) this.getParent();
		return (MineButton) tempPanel.getComponent((tempPanel.getComponentZOrder(this) - this.column + 1));
	}

	public MineButton getUpperRight() {
		JPanel tempPanel = (JPanel) this.getParent();
		return (MineButton) tempPanel.getComponent((tempPanel.getComponentZOrder(this) + this.column - 1));
	}

	public MineButton getLowerRight() {
		JPanel tempPanel = (JPanel) this.getParent();
		return (MineButton) tempPanel.getComponent((tempPanel.getComponentZOrder(this) + this.column + 1));
	}

	public boolean isTop() {
		if ((((JPanel) this.getParent()).getComponentZOrder(this)) % column == 0)
			return true;
		else
			return false;
	}

	public boolean isBottom() {
		if (((((JPanel) this.getParent()).getComponentZOrder(this)) + 1) % column == 0)
			return true;
		else
			return false;
	}

	public boolean isOutOfBound(String direction) {
		switch (direction) {
		case "UpperLeft":
			if (((JPanel) this.getParent()).getComponentZOrder(this) - column - 1 < 0)
				return true;
			break;
		case "Left":
			if (((JPanel) this.getParent()).getComponentZOrder(this) - column < 0)
				return true;
			break;
		case "LowerLeft":
			if (((JPanel) this.getParent()).getComponentZOrder(this) - column + 1 < 0)
				return true;
			break;
		case "Up":
			if (((JPanel) this.getParent()).getComponentZOrder(this) - 1 < 0)
				return true;
			break;
		case "Down":
			if (((JPanel) this.getParent()).getComponentZOrder(this) + 1 > 80)
				return true;
			break;
		case "UpperRight":
			if (((JPanel) this.getParent()).getComponentZOrder(this) + column - 1 > 80)
				return true;
			break;
		case "Right":
			if (((JPanel) this.getParent()).getComponentZOrder(this) + column > 80)
				return true;
			break;
		case "LowerRight":
			if (((JPanel) this.getParent()).getComponentZOrder(this) + column + 1 > 80)
				return true;
			break;
		default:
			return false;
		}
		return false;
	}

	public void detectRange() {
		JPanel tempPanel = (JPanel) this.getParent();

		Integer count = new Integer(0);
		if (!this.isOutOfBound("UpperLeft")) {
			if (this.getUpperLeft() != null && this.getUpperLeft().isMine && !this.getUpperLeft().isBottom())
				count++;
		}
		if (!this.isOutOfBound("Left")) {
			if (this.getLeft() != null && this.getLeft().isMine)
				count++;
		}
		if (!this.isOutOfBound("LowerLeft")) {
			if (this.getLowerLeft() != null && this.getLowerLeft().isMine && !this.getLowerLeft().isTop())
				count++;
		}
		if (!this.isOutOfBound("Up")) {
			if (this.getUp() != null && this.getUp().isMine && !this.getUp().isBottom())
				count++;
		}
		if (!this.isOutOfBound("Down")) {
			if (this.getDown() != null && this.getDown().isMine && !this.getDown().isTop())
				count++;
		}
		if (!this.isOutOfBound("UpperRight")) {
			if (this.getUpperRight() != null && this.getUpperRight().isMine && !this.getUpperRight().isBottom())
				count++;
		}
		if (!this.isOutOfBound("Right")) {
			if (this.getRight() != null && this.getRight().isMine)
				count++;
		}
		if (!this.isOutOfBound("LowerRight")) {
			if (this.getLowerRight() != null && this.getLowerRight().isMine && !this.getLowerRight().isTop())
				count++;
		}

		if (count != 0) {
			this.setBackground(Color.BLUE);
			this.setFont(new Font("Consolas", Font.PLAIN, 40));
			this.setText(count.toString());
			this.setForeground(Color.WHITE);
			this.isSkip = true;
		} else {
			this.setBackground(Color.GREEN);
			this.isSkip = true;
			if (!this.isOutOfBound("UpperLeft")) {
				if (!this.getUpperLeft().isMine && !this.getUpperLeft().isSkip && !this.getUpperLeft().isBottom()) {
					this.getUpperLeft().detectRange();
					// System.out.println("getUpperLeft");
				}
			}
			if (!this.isOutOfBound("Left")) {
				if (!this.getLeft().isMine && !this.getLeft().isSkip) {
					this.getLeft().detectRange();
					// System.out.println("getUpperLeft");
				}
			}
			if (!this.isOutOfBound("LowerLeft")) {
				if (!this.getLowerLeft().isMine && !this.getLowerLeft().isSkip && !this.getLowerLeft().isTop()) {
					this.getLowerLeft().detectRange();
					// System.out.println("getLowerLeft");
				}
			}
			if (!this.isOutOfBound("Up")) {
				if (!this.getUp().isMine && !this.getUp().isSkip && !this.getUp().isBottom()) {
					this.getUp().detectRange();
					// System.out.println("getUp");
				}
			}
			if (!this.isOutOfBound("Down")) {
				if (!this.getDown().isMine && !this.getDown().isSkip && !this.getDown().isTop()) {
					this.getDown().detectRange();
					// System.out.println("getDown");
				}
			}
			if (!this.isOutOfBound("UpperRight")) {
				if (!this.getUpperRight().isMine && !this.getUpperRight().isSkip && !this.getUpperRight().isBottom()) {
					this.getUpperRight().detectRange();
					// System.out.println("getUpperRight");
				}
			}
			if (!this.isOutOfBound("Right")) {
				if (!this.getRight().isMine && !this.getRight().isSkip) {
					this.getRight().detectRange();
					// System.out.println("getRight");
				}
			}
			if (!this.isOutOfBound("LowerRight")) {
				if (!this.getLowerRight().isMine && !this.getLowerRight().isSkip && !this.getLowerRight().isTop()) {
					this.getLowerRight().detectRange();
					// System.out.println("getLowerRight");
				}
			}
		}
	}
}
