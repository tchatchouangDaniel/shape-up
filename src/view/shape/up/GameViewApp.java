package view.shape.up;

import controller.shape.up.GameViewController;
import model.shape.up.GameManager;
import model.shape.up.OptionData;
import model.shape.up.RoundsManager;
import observer.shape.up.Observable;
import observer.shape.up.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * this class communicate with the game manager class to make sure we have the right render at all moments.
 * The connexion is made through the observer design pattern so this class is notified each time the game state is being change.
 *
 * @author Daniel
 * @see GameManager
 */
public class GameViewApp extends JFrame implements Observer {
	private Dimension size;
    private JPanel container = new JPanel(new CardLayout());
    protected GameViewController gvc;
    
	private String menuPName = "menu panel";
	private String optPName = "option panel";
	private JPanel panelMenu = new MainMenuPanel();
	private JPanel panelOption = new OptionsPanel();
	private JButton playBtn = new JButton("Play");
	private JButton rulesBtn = new JButton("rules");
	private JButton backBtn = new JButton("<");
	private JButton beginBtn = new JButton("Begin !");
	private CardLayout cl;

	/**
	 * Method to get the container of our GameViewApp class
	 * @return container
	 */
	public JPanel getContainer() {
		return container;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameViewApp window = GameViewApp.getUniqueInstance();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * The constructor of the GameViewApp class
	 * @param gvc the controller of the program
	 */
    private GameViewApp(GameViewController gvc) {
		GameManager.getUniqueGm().addObserver(this);
		this.gvc = gvc;
    	this.setTitle("Shape up Game");
    	this.setSize(1024,645);
    	this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.size = new Dimension(this.getWidth(), this.getHeight());
        this.container.setPreferredSize(this.size);

        //CODE BOUTON JOUER
		playBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		playBtn.setBackground(Color.LIGHT_GRAY);
		playBtn.setBounds(435, 214, 150, 35);

		//CODE BOUTON REGLE
		rulesBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rulesBtn.setBackground(Color.LIGHT_GRAY);
		rulesBtn.setBounds(435, 296, 150, 35);

		//CODE BOUTON BACK
		backBtn.setBounds(173, 566, 85, 21);
		//CODE BOUTON BEGIN
		beginBtn.setBounds(753, 566, 85, 21);

        panelMenu.add(playBtn);
        panelMenu.add(rulesBtn);
        panelOption.add(backBtn);
        panelOption.add(beginBtn);

        this.container.setBackground(Color.white);
        this.container.setLayout(new CardLayout());
        container.add(panelMenu, menuPName);
		container.add(panelOption, optPName);
		this.add(container);
		cl = (CardLayout) (container.getLayout());
		cl.show(container,menuPName);



		this.playBtn.addActionListener(gvc.getPlay());
		this.backBtn.addActionListener(gvc.getPrevious());
		this.beginBtn.addActionListener(gvc.getBegin());

    }

	/**
	 * Inner class to make sure we only have one instance of the GameViewApp class
	 */
	private static class SingletonHelper{
		private static final GameViewApp gva = new GameViewApp(GameViewController.getUniqueInstance());
	}

	/**
	 * Get the unique instance of the GameViewApp class
	 * @return SingletonHelper.gva
	 */
	public static GameViewApp getUniqueInstance(){
		return SingletonHelper.gva;
	}

	/**
	 * A method to show a panel that is contained in the card layout
	 * @param panelNameID the id of the panel to show
	 */
	public void showPanel(String panelNameID){
		cl.show(container,panelNameID);
	}

	/**
	 * The method to initialise container panel and add them to the card layput
	 */
    public void initOptions() {
    	CardLayout cl = (CardLayout) (container.getLayout());
		cl.show(container,optPName);
    }

	/**
	 * A method to get all the options from option panel and share send them to the model
	 */
	public void shareConfigOnBegin(){
		//Some basic preconsidaration
		OptionsPanel optPanel = (OptionsPanel) panelOption;
		int nbPlayer = Integer.parseInt((String) optPanel.getPlayerNb().getSelectedItem());
		int nbRealPlayer = Integer.parseInt((String) optPanel.getRPlayerNb().getSelectedItem());
		int variant = -1;
		int carpet = -1;
		if(optPanel.getBasicVarRadio().isSelected()){
			variant = 1;
		}else{
			variant = 2;
		}
		OptionData.setVariant(variant);

		if(optPanel.getRdBtnRect().isSelected()){
			carpet = 1;
		}else if(optPanel.getRdBtnSqr().isSelected()){
			carpet = 2;
		}
		OptionData.setCarpetShape(carpet);
		//build the arraylist of players name: clear it before doing anything
		OptionData.setNbPlayer(nbPlayer);
		OptionData.setNbVirtualPlayer(nbPlayer - nbRealPlayer);
		OptionData.setNbRealPlayer(nbRealPlayer);
		//virtual player
			for (int i = 0; i < (nbPlayer - nbRealPlayer); i++) {
				int level = -1;
				if(i == 0){
					try{
						level = Integer.parseInt((String) optPanel.getVP1_level().getSelectedItem());
					}catch (NullPointerException e){
						e.printStackTrace();
					}
				}else if(i==1){
					try{
						level = Integer.parseInt((String) optPanel.getVP2_level().getSelectedItem());
					}catch (NullPointerException e){
						e.printStackTrace();
					}
				}else if(i==2){
					try{
						level = Integer.parseInt((String) optPanel.getVP3_level().getSelectedItem());
					}catch (NullPointerException e){
						e.printStackTrace();
					}
				}
				OptionData.getLevels()[i] = level;
			}

			OptionData.getPlayersName().clear();
			for(int i = 0; i < nbRealPlayer; i++){
				String name = null;
				if(i == 0){
					name = optPanel.getP1_name().getText();
				}else if(i==1){
					name = optPanel.getP2_name().getText();
				}else if(i==2){
					name = optPanel.getP3_name().getText();
				}
				OptionData.getPlayersName().add(name);
			}

		System.out.println(nbPlayer + " " + nbRealPlayer + " " + variant + " " + carpet + " " + OptionData.getPlayersName() + " " + Arrays.toString(OptionData.getLevels()));
	}

	/**
	 * Where we initialize the others parameters of the game
	 * depending on what game manager give back to us
	 * @param s
	 * @param o
	 */
	@Override
	public void update(Observable s, Object o) {
		if(s instanceof GameManager){
			switch (((GameManager) s).getGameState()){
				case "mainMenu" :
					showPanel(menuPName);
					//System.out.println("Update Menu");

					break;
				case "started" :
					this.setBounds(100, 100, 893, 833);
					BoardView board = BoardView.getUniqueInstance();
					RoundsManager.getUniqueRoundManager().addObserver(board);
					board.getFrame().setVisible(true);
					this.setVisible(false);
					//System.out.println("update show newGame");
					break;
				case "options" :
					showPanel(optPName);
					//System.out.println("update options");

					break;
				case "exit" :
					System.exit(0);
					break;
			}
		}
	}
}
