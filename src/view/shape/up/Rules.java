package view.shape.up;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The class for the panel containing ou game rule. Not yet working
 */
public class Rules extends JPanel {

	/**
	 * the constructor for the class
	 */
	public Rules() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Daniel\\Desktop\\ShapeUpCorrection\\ShapeUp2\\pictures\\Other\\rules.JPG"));
		lblNewLabel.setBounds(10, 10, 1327, 809);
		add(lblNewLabel);
		
		JButton btnBack = new JButton("Back ");
		btnBack.setBounds(1341, 777, 99, 42);
		add(btnBack);

	}

}
