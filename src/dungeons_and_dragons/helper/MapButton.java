/**
 * 
 */
package dungeons_and_dragons.helper;

import javax.swing.JButton;

/**
 * @author User
 *
 */
public class MapButton extends JButton {
	
	private String button_type = Game_constants.GRID_BUTTON_TYPE;

	private int xPos;
	
	private int yPos;
	
	//0 if it is wall and 2 if it is an enemy
	private int pointValue = 1;
	
	private int dirty_flag = 0;
	
	

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}	
	
	public String getButton_type() {
		return button_type;
	}

	public void setButton_type(String button_type) {
		this.button_type = button_type;
	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public int getDirty_flag() {
		return dirty_flag;
	}

	public void setDirty_flag(int dirty_flag) {
		this.dirty_flag = dirty_flag;
	}
}