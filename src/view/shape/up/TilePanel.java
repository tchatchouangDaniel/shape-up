package view.shape.up;

import game.shape.up.Positioning;

import javax.swing.*;

/**
 * This class represent a tile into our board on the graphic interface
 *
 * @author Daniel
 */
public class TilePanel  extends JPanel{
	private int id;
	private String curCardId = "erase";
	private Positioning position = new Positioning(-1,-1);

	/**
	 * A method to get the position of a tile
	 * @return position
	 */
	public Positioning getPosition() {
		return position;
	}

	/**
	 * A method to set the position of a tile
	 * @param position the position to set
	 */
	public void setPosition(Positioning position) {
		this.position = position;
	}

	/**
	 * The constructor of the tile panel
	 */
	public TilePanel() {
		super();
	}

	/**
	 * A method to set the id of the tile
	 * @param id the id of the panel
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * A method to get the id of the tile
	 * @return this.id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * A method to get the curCardId of the tile
	 * @return curCardId
	 */
	public String getCurCardId() {
		return curCardId;
	}

	/**
	 * A method to set the curCardId of the tile
	 * @param curCardId
	 */
	public void setCurCardId(String curCardId) {
		this.curCardId = curCardId;
	}
}
