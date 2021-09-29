package view.shape.up;

import javax.swing.JPanel;

/**
 * A class that represent a panel for a line on the gameboard
 */
public class LinePanel extends JPanel{
	private int lineId;

	/**
	 * The constructor of the class
	 */
	public LinePanel() {
		super();
	}

	/**
	 * A method to set the id of the lien
	 * @param id the id of the line
	 */
	public void setLineId(int id) {
		this.lineId = id;
	}

	/**
	 * A method to get the id of the line
	 * @return lineId
	 */
	public int getLineId() {
		return this.lineId;
	}
}
