package view.shape.up;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that represent the main menu of the game
 */
public class MainMenuPanel extends JPanel  {

	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {
		setBackground(new Color(211, 211, 211));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		panel.setBounds(0, 0, 1020, 56);
		add(panel);
		
		JLabel title = new JLabel("Shape Up");
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		title.setBounds(467, 10, 151, 36);
		panel.add(title);
		
		JLabel mainMenuLabel = new JLabel("Main Menu");
		mainMenuLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		mainMenuLabel.setBounds(463, 125, 150, 25);
		add(mainMenuLabel);

		
		JButton quitBtn = new JButton("Quit");
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				// replace with the choice that exit the console in console menu 
			}
		});
		quitBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		quitBtn.setBackground(Color.LIGHT_GRAY);
		quitBtn.setBounds(435, 377, 150, 35);
		add(quitBtn);
		
		JLabel copy = new JLabel("2020 - Developed by Daniel");
		copy.setBounds(447, 596, 238, 13);
		add(copy);

	}

}
