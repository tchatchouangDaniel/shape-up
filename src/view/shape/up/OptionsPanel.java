package view.shape.up;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that represent the option panel in our game with all the configurations available
 *
 * @author Daniel
 */
public class OptionsPanel extends JPanel {
	private JTextField P1_name = new JTextField();
	private JTextField P2_name = new JTextField();
	private JTextField P3_name = new JTextField();
	private JRadioButton basicVarRadio = new JRadioButton();
	private JRadioButton rdbtnShapeUpAdvance = new JRadioButton();
	private JRadioButton rdBtnRect = new JRadioButton();
	private JRadioButton rdBtnSqr = new JRadioButton();
	private JComboBox VP1_level = new JComboBox();
	private JComboBox VP2_level= new JComboBox();
	private JComboBox VP3_level= new JComboBox();
	private JComboBox RPlayerNb= new JComboBox();
	private JComboBox playerNb= new JComboBox();

	/**
	 * Create the panel.
	 */
	public OptionsPanel() {
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
		
		basicVarRadio = new JRadioButton("Shape Up Basic");
		basicVarRadio.setSelected(true);
		basicVarRadio.setBackground(new Color(211, 211, 211));
		basicVarRadio.setBounds(455, 134, 103, 21);
		add(basicVarRadio);
		
		rdbtnShapeUpAdvance = new JRadioButton("Shape Up Advance");
		rdbtnShapeUpAdvance.setBackground(new Color(211, 211, 211));
		rdbtnShapeUpAdvance.setBounds(455, 164, 147, 21);
		add(rdbtnShapeUpAdvance);
		
		ButtonGroup groupeVariante = new ButtonGroup();
		groupeVariante.add(basicVarRadio);
		groupeVariante.add(rdbtnShapeUpAdvance);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 128));
		separator.setBounds(173, 205, 665, 2);
		add(separator);
		
		JLabel lblCarpetShape = new JLabel("Choose a carpet Shape");
		lblCarpetShape.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblCarpetShape.setBounds(423, 217, 204, 21);
		add(lblCarpetShape);

		rdBtnRect = new JRadioButton("Rectangle");
		rdBtnRect.setSelected(true);
		rdBtnRect.setBackground(new Color(211, 211, 211));
		rdBtnRect.setBounds(458, 244, 100, 21);
		add(rdBtnRect);

		rdBtnSqr = new JRadioButton("Square");
		rdBtnSqr.setBackground(new Color(211, 211, 211));
		rdBtnSqr.setBounds(458, 267, 100, 21);
		add(rdBtnSqr);
		
		ButtonGroup groupeCarpetShape = new ButtonGroup();
		groupeCarpetShape.add(rdBtnRect);
		groupeCarpetShape.add(rdBtnSqr);
		
		JPanel Np1Label = new JPanel();
		Np1Label.setLayout(null);
		Np1Label.setBackground(Color.PINK);
		Np1Label.setBounds(173, 400, 223, 100);
		add(Np1Label);
		//PLayer Name Panel//
		
		
		JLabel name1 = new JLabel("name P1");
		name1.setForeground(Color.WHITE);
		name1.setBounds(10, 10, 58, 13);
		Np1Label.add(name1);
		

		P1_name.setEnabled(false);
		name1.setLabelFor(P1_name);
		P1_name.setText("player1");
		P1_name.setColumns(10);
		P1_name.setBounds(78, 7, 96, 19);
		Np1Label.add(P1_name);
		
		JLabel Np2Label = new JLabel("name P2");
		Np2Label.setForeground(Color.WHITE);
		Np2Label.setBounds(10, 43, 58, 13);
		Np1Label.add(Np2Label);
		

		Np2Label.setLabelFor(P2_name);
		P2_name.setEnabled(false);
		P2_name.setText("player2");
		P2_name.setColumns(10);
		P2_name.setBounds(78, 40, 96, 19);
		Np1Label.add(P2_name);
		

		P3_name.setEnabled(false);
		P3_name.setText("player3");
		P3_name.setColumns(10);
		P3_name.setBounds(78, 69, 96, 19);
		Np1Label.add(P3_name);
		
		JLabel Np3Label = new JLabel("name P3");
		Np3Label.setLabelFor(P3_name);
		Np3Label.setForeground(Color.WHITE);
		Np3Label.setBounds(10, 72, 58, 13);
		Np1Label.add(Np3Label);
		
		
		//End PLayer Name Panel//
		
		
		//Begin Vp level//
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(Color.PINK);
		panel_2_1.setBounds(634, 400, 204, 100);
		add(panel_2_1);
				
		JLabel levelVp1 = new JLabel("level VP1");
		levelVp1.setForeground(Color.WHITE);
		levelVp1.setBounds(10, 10, 60, 13);
		panel_2_1.add(levelVp1);
				
		JLabel levelVp2 = new JLabel("level VP2");
		levelVp2.setForeground(Color.WHITE);
		levelVp2.setBounds(10, 43, 60, 13);
		panel_2_1.add(levelVp2);
				
		JLabel levelVp3 = new JLabel("level VP3");
		levelVp3.setForeground(Color.WHITE);
		levelVp3.setBounds(10, 72, 60, 13);
		panel_2_1.add(levelVp3);
				

		VP1_level.setEnabled(false);
		VP1_level.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		VP1_level.setMaximumRowCount(3);
		VP1_level.setBounds(104, 6, 45, 21);
		panel_2_1.add(VP1_level);
				

		VP2_level.setEnabled(false);
		VP2_level.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		VP2_level.setMaximumRowCount(3);
		VP2_level.setBounds(104, 39, 45, 21);
		panel_2_1.add(VP2_level);
				

		VP3_level.setEnabled(false);
		VP3_level.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		VP3_level.setMaximumRowCount(3);
		VP3_level.setBounds(104, 68, 45, 21);
		panel_2_1.add(VP3_level);
		//End Vp Level//
				
				
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.PINK);
		panel_1.setBounds(387, 315, 249, 52);
		add(panel_1);
		
		JLabel lblNewLabel = new JLabel("virtuals are set automatically if needed ...");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(13, 19, 248, 13);
		panel_1.add(lblNewLabel);
		
		JLabel lblSelectPlayerNumber = new JLabel("select player number");
		lblSelectPlayerNumber.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblSelectPlayerNumber.setBounds(173, 315, 204, 21);
		add(lblSelectPlayerNumber);
		

		RPlayerNb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				int RplayerNber = Integer.parseInt((String)cb.getSelectedItem());
				switch(RplayerNber) {
				case 1:
					P1_name.setEnabled(true);
					P2_name.setEnabled(false);
					P3_name.setEnabled(false);
					VP1_level.setEnabled(false);
					VP2_level.setEnabled(true);
					if(cb.getItemCount() == 4) {
						VP3_level.setEnabled(true);
					}
					break;
				case 2:
					P1_name.setEnabled(true);
					P2_name.setEnabled(true);
					P3_name.setEnabled(false);
					VP1_level.setEnabled(false);
					VP2_level.setEnabled(false);
					if(cb.getItemCount() == 4) {
						VP3_level.setEnabled(true);
					}
					break;
				case 3:
					VP1_level.setEnabled(false);
					VP2_level.setEnabled(false);
					VP3_level.setEnabled(false);
					P1_name.setEnabled(true);
					P2_name.setEnabled(true);
					P3_name.setEnabled(true);
					break;
					default:
					P1_name.setEnabled(false);
					P2_name.setEnabled(false);
					P3_name.setEnabled(false);
					VP1_level.setEnabled(true);
					VP2_level.setEnabled(true);
					if(cb.getItemCount() == 4) {
						VP3_level.setEnabled(true);
					}
				}
				
				
			}
		});
		RPlayerNb.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2"}));
		RPlayerNb.setSelectedIndex(0);
		RPlayerNb.setMaximumRowCount(3);
		RPlayerNb.setBounds(691, 346, 147, 21);
		add(RPlayerNb);
		

		playerNb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				int playerNb = Integer.parseInt((String)cb.getSelectedItem());
				if(playerNb == 2) {
					RPlayerNb.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2"}));
					RPlayerNb.setSelectedIndex(0);
					VP1_level.setEnabled(true);
					VP2_level.setEnabled(true);
					VP3_level.setEnabled(false);
				}else {
					RPlayerNb.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2","3"}));
					RPlayerNb.setSelectedIndex(0);
					VP1_level.setEnabled(true);
					VP2_level.setEnabled(true);
					VP3_level.setEnabled(true);
				}
			}
		});
		playerNb.setModel(new DefaultComboBoxModel(new String[] {"2", "3"}));
		playerNb.setSelectedIndex(0);
		playerNb.setMaximumRowCount(3);
		playerNb.setBounds(173, 346, 147, 21);
		add(playerNb);
		
		JLabel lblRealPlayerNumber = new JLabel("Real Player number");
		lblRealPlayerNumber.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblRealPlayerNumber.setBounds(691, 315, 195, 21);
		add(lblRealPlayerNumber);
		
		
		JLabel lblOptions = new JLabel("MainMenu > Options ");
		lblOptions.setForeground(new Color(0, 0, 128));
		lblOptions.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblOptions.setBounds(173, 62, 204, 21);
		add(lblOptions);

		JLabel copy = new JLabel("2020 - Developed by Daniel");
		copy.setBounds(443, 593, 249, 13);
		add(copy);
		
		JLabel lblChooseAVariant = new JLabel("Choose a variant");
		lblChooseAVariant.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblChooseAVariant.setBounds(444, 96, 204, 13);
		add(lblChooseAVariant);

	}

	public JTextField getP1_name() {
		return P1_name;
	}

	public JTextField getP2_name() {
		return P2_name;
	}

	public JTextField getP3_name() {
		return P3_name;
	}

	public JRadioButton getBasicVarRadio() {
		return basicVarRadio;
	}

	public JRadioButton getRdBtnRect() {
		return rdBtnRect;
	}

	public JRadioButton getRdBtnSqr() {
		return rdBtnSqr;
	}

	public JComboBox getVP1_level() {
		return VP1_level;
	}

	public JComboBox getVP2_level() {
		return VP2_level;
	}

	public JComboBox getVP3_level() {
		return VP3_level;
	}

	public JComboBox getRPlayerNb() {
		return RPlayerNb;
	}

	public JComboBox getPlayerNb() {
		return playerNb;
	}
}
