package view.shape.up;

import card.and.deck.shapeup.Card;
import card.and.deck.shapeup.Deck;
import controller.shape.up.GameViewController;
import gaem.area.shape.up.GameArea;
import game.shape.up.Positioning;
import game.shape.up.Variante;
import game.shape.up.VarianteBasic;
import model.shape.up.RoundsManager;
import observer.shape.up.Observable;
import observer.shape.up.Observer;
import player.shape.up.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * this class is where the graphic representation of our gameArea is build so that the players can play in it.
 *
 * @author Daniel
 */
public class BoardView implements Observer {

	protected GameViewController gvc;
	private JButton btnMove;
	private JButton btnPlayCard;
	private JFrame frame;
	private ArrayList<LinePanel> lines = new ArrayList<>();
	private ArrayList<ArrayList<TilePanel>> LinesTiles = new ArrayList<>();
	private JTextField x = new JTextField();
	private JTextField y = new JTextField();
	private JTextField cPlayed = new JTextField();
	private int Line = 0, Col = 0;
	private JPanel panel;
	private JLabel turn_winner_lbl;
	private JLabel hand;
	GridLayout gridBoard;
	GridLayout lineLayout;
	private JPanel deckPanel;
	private ArrayList<TilePanel> deckView = new ArrayList<>();
	private Card cardPlayed;
	private Positioning positionPlayed;
	private String cardMoved;
	private String extension;
	private String toRemove;
	private boolean moving = false;
	private JLabel name1;
	private JLabel name2;
	private JLabel name3;
	private JLabel score1;
	private JLabel score2;
	private JLabel score3;
	private JPanel hand_1;
	private JPanel hand_2;
	private JPanel hand_3;
	private JLabel victoryCard;
	private JLabel labelTurnName;
	private int numberLine = 0, numberCol = 0;
	private ArrayList<Player> players;
	private ArrayList<String> winners;
	private int currentPLayer;
	private boolean hasmoved;
	private boolean hasplayed;
	private Variante variante;
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardView window = BoardView.getUniqueInstance();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private BoardView() {
		initialize();
	}

	/**
	 * Method to get the main panel
	 * @return panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Method to get the lines of our gameboard
	 * @return lines
	 */
	public ArrayList<LinePanel> getLines() {
		return lines;
	}

	/**
	 * Inner class that is used to make sure we always have only one instance of this class
	 */
	private static class SingletonHelper{
		private static final BoardView BOARD_VIEW = new BoardView();
	}

	/**
	 * Method to get the unique instance of this class
	 * @return BOARD_VIEW
	 */
	public static BoardView getUniqueInstance(){
		return SingletonHelper.BOARD_VIEW;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		hasplayed = false;
		hasmoved = false;
		this.gvc = GameViewController.getUniqueInstance();
		GameArea.getUniqueGameArea().addObserver(this);
		RoundsManager.getUniqueRoundManager().addObserver(this);
		cardPlayed = null;
		cardMoved = null;
		positionPlayed = null;
		deckPanel = new JPanel();
		Line = 5;
		Col = 5;
		frame = new JFrame();
		frame.setTitle("Shape up");
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 893, 833);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel xlabel = new JLabel("x:");
		xlabel.setBounds(160, 120, 20, 20);
		frame.getContentPane().add(xlabel);
		x.setColumns(10);
		x.setBounds(180, 120, 60, 19);

		JLabel ylabel = new JLabel("y:");
		ylabel.setBounds(160, 145, 20, 20);
		frame.getContentPane().add(ylabel);
		y.setColumns(10);
		y.setBounds(180, 145, 60, 19);

		JLabel clabel = new JLabel("c:");
		clabel.setBounds(160, 170, 20, 20);
		frame.getContentPane().add(clabel);
		cPlayed.setColumns(10);
		cPlayed.setBounds(180, 170, 60, 19);
		y.setText("");
		cPlayed.setText("");
		x.setText("");

		frame.getContentPane().add(x);
		frame.getContentPane().add(y);
		frame.getContentPane().add(cPlayed);
		panel = new JPanel();
		gridBoard = new GridLayout(Line,0);
		lineLayout = new GridLayout(0,Col);
		panel.setBounds(73, 210, 600, 600);
		frame.getContentPane().add(panel);
		panel.setLayout(gridBoard);
		gridBoard.setVgap(2);
		lineLayout.setHgap(2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		panel_1.setBounds(73, 116, 74, 74);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		JLabel deckLabel = new JLabel("DECK");
		panel_1.setLayout(new BorderLayout());
		panel_1.add(deckLabel,BorderLayout.CENTER);
		panel_1.revalidate();

		hand_1 = new JPanel();
		hand_1.setBackground(Color.pink);
		hand_1.setBounds(500, 116, 74, 74);
		frame.getContentPane().add(hand_1);

		hand_2 = new JPanel();
		hand_2.setBackground(Color.pink);
		hand_2.setBounds(600, 116, 74, 74);
		frame.getContentPane().add(hand_2);

		hand_3 = new JPanel();
		hand_3.setBackground(Color.pink);
		hand_3.setBounds(700, 116, 74, 74);
		frame.getContentPane().add(hand_3);

		btnPlayCard = new JButton("play a card");
		btnPlayCard.setForeground(new Color(255, 255, 255));
		btnPlayCard.setBackground(new Color(128, 128, 128));
		
		btnPlayCard.setBounds(380, 124, 107, 28);
		frame.getContentPane().add(btnPlayCard);

		btnPlayCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!x.getText().equals("") && !y.getText().equals("") && !cPlayed.getText().equals("")){
					int xAxis = Integer.parseInt(x.getText());
					int yAxis = Integer.parseInt(y.getText());
					Positioning position = new Positioning(xAxis,yAxis);
					String card = cPlayed.getText();
					if(card.equals(players.get(currentPLayer).getHand().get(0).getId())){ // jouer sur le terrain
						if(RoundsManager.getUniqueRoundManager().checkPosBound(position)){
							if(!hasplayed){
								RoundsManager.getUniqueRoundManager().playCardBasicView(position);
								hasplayed = true;
							}
						}
					}else if(GameArea.getUniqueGameArea().isCardOnField(card)){ // deplacer une carte
						if(RoundsManager.getUniqueRoundManager().checkPosBound(position)){
							if(!hasmoved){

								hasmoved = true;
							}
						}
					}
				}
			}
		});

		btnMove = new JButton("move a card");
		
		btnMove.setForeground(new Color(255, 255, 255));
		btnMove.setBackground(new Color(128, 128, 128));
		btnMove.setBounds(380, 162, 107, 28);
		frame.getContentPane().add(btnMove);

		labelTurnName = new JLabel("player");
		labelTurnName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelTurnName.setBounds(250, 147, 110, 13);
		frame.getContentPane().add(labelTurnName);

		turn_winner_lbl = new JLabel("TURN");
		turn_winner_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		turn_winner_lbl.setBounds(254, 177, 55, 13);
		frame.getContentPane().add(turn_winner_lbl);
		
		JLabel lblNewLabel_1 = new JLabel("Players");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(551, 10, 74, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		name1 = new JLabel("name1");
		name1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		name1.setBounds(551, 33, 85, 13);
		frame.getContentPane().add(name1);
		
		name2 = new JLabel("name2");
		name2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		name2.setBounds(551, 56, 85, 13);
		frame.getContentPane().add(name2);
		
		name3 = new JLabel("name3");
		name3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		name3.setBounds(551, 77, 85, 13);
		frame.getContentPane().add(name3);
		
		JLabel lblNewLabel_3 = new JLabel("Scores");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(710, 10, 74, 13);
		frame.getContentPane().add(lblNewLabel_3);

		score1 = new JLabel("-");
		score1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		score1.setBounds(760, 34, 45, 13);
		frame.getContentPane().add(score1);
		
		score2 = new JLabel("-");
		score2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		score2.setBounds(760, 57, 45, 13);
		frame.getContentPane().add(score2);
		
		score3 = new JLabel("-");
		score3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		score3.setBounds(760, 78, 45, 13);
		frame.getContentPane().add(score3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(636, 44, 93, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(636, 67, 93, 2);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(636, 88, 93, 2);
		frame.getContentPane().add(separator_1_1);
		
		hand = new JLabel("HAND");
		hand.setForeground(new Color(255, 255, 255));
		hand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		hand.setBounds(509, 103, 45, 13);
		frame.getContentPane().add(hand);
		
		JButton newGameBtn = new JButton("New Game");
		newGameBtn.setForeground(new Color(255, 255, 255));
		newGameBtn.setBackground(new Color(128, 128, 128));
		newGameBtn.setBounds(76, 8, 107, 21);
		frame.getContentPane().add(newGameBtn);

		JButton quitBtn = new JButton("Quit");
		quitBtn.setForeground(new Color(255, 255, 255));
		quitBtn.setBackground(new Color(128, 128, 128));
		quitBtn.setBounds(205, 8, 85, 21);
		frame.getContentPane().add(quitBtn);
		quitBtn.addActionListener(gvc.getQuit());
		
		
		for(int i = 0; i < 5; i++) {
			LinePanel line = new LinePanel();
			line.setLineId(i);
			line.setLayout(lineLayout);
			lines.add(line);
			panel.add(lines.get(i));
		}
		
		for(int i = 0; i < 5; i++) {
			ArrayList<TilePanel> tiles = new ArrayList<>();
			for(int j = 0; j < 5; j++) {
				TilePanel tile = new TilePanel();
				tile.setId(i);
				tiles.add(tile);
			tile.setBackground(Color.WHITE);
				lines.get(i).add(tile);
			}
			LinesTiles.add(tiles);
		}

		setPositions();
		
	}

	/**
	 * Set the position of each tile on the board
	 */
	private void setPositions(){
		for (int i = 0; i < LinesTiles.size(); i++) {
			for (int j = 0; j < LinesTiles.get(i).size(); j++) {
				LinesTiles.get(i).get(j).setPosition(new Positioning(i,j));
			}
		}
	}

	/**
	 * Method to add the player's name into the game interface
	 * @param players Arraylist of all the players
	 */
	private void addPlayersName(ArrayList<Player> players){
		for (int i = 0; i < players.size(); i++) {
			if(i == 0){
				name1.setText(players.get(i).getName());
			}else if(i == 1){
				name2.setText(players.get(i).getName());
			}else if(i == 2){
				name3.setText(players.get(i).getName());
			}
		}

		if(players.size() == 2){
			name3.setText("---");
		}
	}

	/**
	 * Add  a line at the top of our board
	 */
	private void addTopLine() { //Ok
		LinePanel  line = new LinePanel();
		line.setLineId(-1);
		lineLayout = new GridLayout(0,Col);
		line.setLayout(lineLayout);
		lines.add(0,line);
		ArrayList<TilePanel> tiles = new ArrayList<>();
		
		for(int i = 0; i < Col; i++) {
			TilePanel tile = new TilePanel();
			tile.setId(i);
			tiles.add(tile);
			tile.setBackground(Color.WHITE);
			lines.get(0).add(tile);
		}
		LinesTiles.add(0,tiles);
		Line++;
		gridBoard = new GridLayout(Line, 0);
		panel.removeAll();
		panel.setLayout(gridBoard);
		for(int i = 0; i < Line; i++) {
			panel.add(lines.get(i));
		}
		gridBoard.setVgap(2);
		lineLayout.setHgap(2);
		panel.revalidate();
		
		refreshLineId();
		setPositions();
	}
	/**
	 * Add  a line at the bottom of our board
	 */
	private void addBottomLine() { //Ok
		LinePanel  line = new LinePanel();
		line.setLineId(-1);
		lineLayout = new GridLayout(0,Col);
		line.setLayout(lineLayout);
		lines.add(line);
		ArrayList<TilePanel> tiles = new ArrayList<>();
		
		for(int i = 0; i < Col; i++) {
			TilePanel tile = new TilePanel();
			tile.setId(i);
			tiles.add(tile);
			tile.setBackground(Color.WHITE);
			lines.get((lines.size()-1)).add(tile);
		}
		LinesTiles.add(tiles);
		Line++;
		gridBoard = new GridLayout(Line, 0);
		panel.removeAll();
		panel.setLayout(gridBoard);
		for(int i = 0; i < Line; i++) {
			panel.add(lines.get(i));
		}
		gridBoard.setVgap(2);
		lineLayout.setHgap(2);
		panel.revalidate();
		
		refreshLineId();
		setPositions();
	}
	/**
	 * Add  a line at the left of our board
	 */
	private void addColBegin() {
		Col++;
		lineLayout = new GridLayout(0,Col);
		gridBoard.setVgap(2);
		lineLayout.setHgap(2);
		//setLayout on line
		for(int i = 0; i < lines.size(); i++) {
			TilePanel tile = new TilePanel();
			tile.setId(-1);
			tile.setBackground(Color.WHITE);
			LinesTiles.get(i).add(0,tile);
			lines.get(i).removeAll();
			lines.get(i).setLayout(lineLayout);
			for(int j = 0; j < LinesTiles.get(i).size(); j++) {
				lines.get(i).add(LinesTiles.get(i).get(j));
			}
			lines.get(i).revalidate();
		}
		
		refreshAllTilesId();
		setPositions();
	}


	/**
	 * Add  a line at the right of our board
	 */
	private void addColEnd() {
		Col++;
		lineLayout = new GridLayout(0,Col);
		gridBoard.setVgap(2);
		lineLayout.setHgap(2);
		//setLayout on line
		for(int i = 0; i < lines.size(); i++) {
			TilePanel tile = new TilePanel();
			tile.setId(-1);
			tile.setBackground(Color.WHITE);
			LinesTiles.get(i).add(tile);
			lines.get(i).removeAll();
			lines.get(i).setLayout(lineLayout);
			for(int j = 0; j < LinesTiles.get(i).size(); j++) {
				lines.get(i).add(LinesTiles.get(i).get(j));
			}
			lines.get(i).revalidate();
		}
		refreshAllTilesId();
		setPositions();
	}

	/**
	 * set the id of the lines on the board
	 */
	private void refreshLineId() {
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setLineId(i);
		}
	}
	/**
	 * set the id of the tiles on the board
	 */
	private void refreshAllTilesId() { 
		for(int i = 0; i < LinesTiles.size(); i++) {
			for(int j = 0; j < LinesTiles.get(i).size(); j++) {
				LinesTiles.get(i).get(j).setId(j);
			}
		}
	}

	/**
	 * Get the panel representating the hand of a player
	 * @return JPanel of the card in hand
	 */
	public JPanel getHand_1() {
		return hand_1;
	}

	/**
	 * A method to get the width of a tile
	 * @return The width of a tile
	 */
	public int getWidth(){
		return LinesTiles.get(0).get(0).getWidth();

	}
	/**
	 * A method to get the height of a tile
	 * @return The height of a tile
	 */
	public int getHeight(){
		return LinesTiles.get(0).get(0).getHeight();
	}

	/**
	 * A method that stacks cards on the deck
	 */
	public void createDeckStackPanelTest(){
		Deck.getUniqueDeck().initDeck();
		ArrayList<Card> deck = Deck.getUniqueDeck().getDeck();
		for (Card card : deck) {
			TilePanel tileDeck = new TilePanel();
			Draw drawDeck = new Draw();
			BorderLayout bly = new BorderLayout();
			tileDeck.setCurCardId(card.getId());
			tileDeck.setLayout(bly);
			tileDeck.add(drawDeck, BorderLayout.CENTER);
			tileDeck.setBackground(Color.PINK);
			drawDeck.setCardToDraw(tileDeck.getCurCardId());
			drawDeck.drawing();
			tileDeck.revalidate();
			deckView.add(tileDeck);
			deckPanel.setLayout(new BorderLayout());
			deckPanel.add(tileDeck,BorderLayout.CENTER);
		}


	}

	/**
	 * get the x field
	 * @return x
	 */
	public JTextField getX() {
		return x;
	}

	/**
	 * get the y field
	 * @return y
	 */
	public JTextField getY() {
		return y;
	}

	/**
	 * get the c field
	 * @return c
	 */
	public JTextField getcPlayed() {
		return cPlayed;
	}

	/**
	 * Format some panels on the field depending of the variant  chosen by the player
	 */
	public void formatVariant(){

		if (variante instanceof VarianteBasic){
			hand_1.setBackground(Color.pink);
			hand_2.setBackground(Color.lightGray);

			DrawNext figure2 = new DrawNext();
			figure2.setCardToDraw(players.get(currentPLayer).getVictoryCard().getId());
			figure2.setBackground(Color.WHITE);
			hand_2.setLayout(new BorderLayout());
			hand_2.removeAll();
			hand_2.revalidate();
			hand_2.repaint();
			hand_2.add(figure2,BorderLayout.CENTER);
			figure2.drawing();
			hand_2.revalidate();

			victoryCard = new JLabel("");
			victoryCard.setForeground(new Color(255, 255, 255));
			victoryCard.setFont(new Font("Tahoma", Font.PLAIN, 14));
			victoryCard.setBounds(600, 103, 55, 13);
			frame.getContentPane().add(victoryCard);

			hand_3.setBackground(new Color(192, 192, 192));
			frame.revalidate();
			frame.repaint();

		}else{
			hand_1.setBackground(Color.pink);
			hand_2.setBackground(Color.pink);
			hand_3.setBackground(Color.pink);
		}
	}

	/**
	 * Clear the different fields after the end of a turn to display the different players
	 * and their victory cards but also
	 */
	public void clearFieldAtEnd(){
		labelTurnName.setText("");
		labelTurnName.repaint();
		labelTurnName.revalidate();
		victoryCard.setText("");
		victoryCard.revalidate();
		victoryCard.repaint();
		frame.getContentPane().remove(victoryCard);
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		hand_1.removeAll();
		hand_1.revalidate();
		hand_1.repaint();

		hand_2.removeAll();
		hand_2.setBounds(620, 116, 74, 74);
		hand_2.revalidate();
		hand_2.repaint();

		hand_3.removeAll();
		hand_3.setBounds(720, 116, 74, 74);
		hand_3.revalidate();
		hand_3.repaint();

		hand.setText("");
		JLabel victoryCard1 = new JLabel("VC-1");
		victoryCard1.setForeground(new Color(255, 255, 255));
		victoryCard1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		victoryCard1.setBounds(500, 103, 150, 13);
		frame.getContentPane().add(victoryCard1);

		DrawNext figure1 = new DrawNext();
		figure1.setCardToDraw(players.get(0).getVictoryCard().getId());
		figure1.setBackground(Color.WHITE);
		hand_1.setLayout(new BorderLayout());
		hand_1.add(figure1, BorderLayout.CENTER);
		figure1.drawing();
		hand_1.revalidate();

		JLabel victoryCard2 = new JLabel("VC-2");
		victoryCard2.setForeground(new Color(255, 255, 255));
		victoryCard2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		victoryCard2.setBounds(620, 103, 150, 13);
		frame.getContentPane().add(victoryCard2);

		DrawNext figure2 = new DrawNext();
		figure2.setCardToDraw(players.get(1).getVictoryCard().getId());
		figure2.setBackground(Color.WHITE);
		hand_2.setLayout(new BorderLayout());
		hand_2.add(figure2, BorderLayout.CENTER);
		figure2.drawing();
		hand_2.revalidate();

		if(players.size() == 3) {
			JLabel victoryCard3 = new JLabel("VC-3");
			victoryCard3.setForeground(new Color(255, 255, 255));
			victoryCard3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			victoryCard3.setBounds(720, 103, 150, 13);
			frame.getContentPane().add(victoryCard3);

			DrawNext figure3 = new DrawNext();
			figure3.setCardToDraw(players.get(2).getVictoryCard().getId());
			figure3.setBackground(Color.WHITE);
			hand_3.setLayout(new BorderLayout());
			hand_3.add(figure3, BorderLayout.CENTER);
			figure3.drawing();
			hand_3.revalidate();
		}else{
			frame.getContentPane().remove(hand_3);
			hand_3.setBackground(new Color(192, 192, 192));

		}

		if (winners.size() == players.size()){
			turn_winner_lbl.setText("DRAW!");
		}else {
			turn_winner_lbl.setText("WINNER");
			labelTurnName.setText("");

			for (int i = 0; i < winners.size(); i++) {
				if(i == 0){
					JLabel label0 = new JLabel(winners.get(0));
					label0.setFont(new Font("Tahoma", Font.PLAIN, 14));
					label0.setBounds(250, 147, 110, 13);
					label0.revalidate();
					label0.repaint();
					frame.getContentPane().add(label0);
				}else if(i == 1){
					JLabel label1 = new JLabel(winners.get(1));
					label1.setFont(new Font("Tahoma", Font.PLAIN, 14));
					label1.setBounds(250, 190, 110, 13);
					label1.revalidate();
					label1.repaint();
					frame.getContentPane().add(label1);
				}
			}
		}


		frame.revalidate();
		frame.repaint();
	}

	/**
	 * Show the card picked by the player in the basic variant
	 */
	public void showCardPickedBasic(){
		if(variante instanceof VarianteBasic) {
			DrawNext figure = new DrawNext();
			figure.setCardToDraw(players.get(currentPLayer).getHand().get(0).getId());
			figure.setBackground(Color.WHITE);
			hand_1.setLayout(new BorderLayout());
			hand_1.removeAll();
			hand_1.revalidate();
			hand_1.repaint();
			hand_1.add(figure, BorderLayout.CENTER);
			figure.drawing();
			hand_1.revalidate();
		}
	}
	/**
	 * That method is used to place a card on the grid board
	 */
	public void placeCardOnBoard(Card card, Positioning position){
		Draw figure = new Draw();
		figure.setCardToDraw(card.getId());
		figure.setBackground(Color.WHITE);
		figure.drawing();
		BorderLayout innerLay = new BorderLayout();
		LinesTiles.get(position.getX()).get(position.getY()).removeAll();
		LinesTiles.get(position.getX()).get(position.getY()).setLayout(innerLay);
		LinesTiles.get(position.getX()).get(position.getY()).setCurCardId(card.getId());
		LinesTiles.get(position.getX()).get(position.getY()).add(figure,BorderLayout.CENTER);
		LinesTiles.get(position.getX()).get(position.getY()).revalidate();
		LinesTiles.get(position.getX()).get(position.getY()).repaint();
	}

	/**
	 * that method is used to visually move a card from one panel to another on the game field
	 * once an card has been moved the panel she was sitting on is emptied
	 * @param cardId the id of the card to move
	 * @param position the new position of the card to move
	 */
	public void moveCardOnBoard(String cardId, Positioning position){
		Draw figure = new Draw();
		Draw eraseFigure = new Draw();
		figure.setCardToDraw(cardId);
		eraseFigure.setCardToDraw("erase");
		eraseFigure.setBackground(Color.WHITE);
		eraseFigure.drawing();
		BorderLayout innerLay = new BorderLayout();

		for (int i = 0; i < LinesTiles.size(); i++) {
			boolean found = false;
			for (int j = 0; j < LinesTiles.get(i).size(); j++) {
				if(LinesTiles.get(i).get(j).getCurCardId().equals(cardId)){
					LinesTiles.get(i).get(j).removeAll();
					LinesTiles.get(i).get(j).setCurCardId("erase");
					LinesTiles.get(i).get(j).setLayout(new BorderLayout());
					LinesTiles.get(i).get(j).add(eraseFigure,BorderLayout.CENTER);
					LinesTiles.get(i).get(j).revalidate();
					LinesTiles.get(i).get(j).repaint();
					found = true;
					break;
				}
			}
			if(found) break;
		}
		LinesTiles.get(position.getX()).get(position.getY()).setCurCardId(cardId);
		LinesTiles.get(position.getX()).get(position.getY()).setLayout(innerLay);
		LinesTiles.get(position.getX()).get(position.getY()).add(figure,BorderLayout.CENTER);
		figure.setBackground(Color.WHITE);
		figure.drawing();
		LinesTiles.get(position.getX()).get(position.getY()).revalidate();
		LinesTiles.get(position.getX()).get(position.getY()).repaint();
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * That method display the final score on our graphical interface
	 * @param score is the score array with the score of the different players
	 */
	public void displayScores(int[] score){
		for (int i = 0; i < score.length; i++) {
			if(i == 0){
				score1.setText(score[i] +"");
				score1.revalidate();
				score1.repaint();
			} else if(i == 1){
				score2.setText(score[i] +"");
				score2.revalidate();
				score2.repaint();
			} else if(i == 2){
				score3.setText(score[i] +"");
				score3.revalidate();
				score3.repaint();
			}
		}
	}

	/**
	 * that method is used to count the number of lines on our graphic grid
	 * @return the number of lines on our graphic grid
	 */
	public int checkLine(){

		int lines = 0;
		for (int j = 0; j < LinesTiles.size(); j++) {
			for (int k = 0; k < LinesTiles.get(0).size(); k++) {
				if(!LinesTiles.get(j).get(k).getCurCardId().equals("erase")){
					lines++;
					break;
				}
			}
		}
		return lines;
	}

	/**
	 * that method is used to count the number of columns on our graphic grid
	 * @return the number of column on our graphic grid
	 */
	public int checkCol(){
		int cols = 0;
		for (int j = 0; j < LinesTiles.get(0).size(); j++) {
			for (int k = 0; k < LinesTiles.size(); k++) {
				if(!LinesTiles.get(k).get(j).getCurCardId().equals("erase")){
					cols++;
					break;
				}
			}
		}

		return cols;
	}

	/**
	 *That method is used to remove additional lines and columns on the board that are not userful
	 */
	public void simplifyBoard(){
		int firstLine = 0;
		int firstColumn = 0;
		JPanel newPanel = new JPanel();
		newPanel.setBounds(73, 210, 400, 400);
		ArrayList<LinePanel> lines = new ArrayList<>();
		GridLayout lineLayout;
		numberLine = checkLine();
		numberCol = checkCol();
		GridLayout grid = new GridLayout(numberLine,0);
		newPanel.setLayout(grid);
		lineLayout = new GridLayout(0,numberCol);
		lineLayout.setHgap(2);
		grid.setVgap(2);
		if(numberCol == 3){
			numberLine = 5;
		}else if(numberLine == 5){
			numberCol = 3;
		}
		for (int j = 0; j < LinesTiles.size(); j++) {
			boolean found = false;
			for (int k = 0; k < LinesTiles.get(0).size(); k++) {
				if(!LinesTiles.get(j).get(k).getCurCardId().equals("erase")){
					firstLine = j;
					found = true;
					break;
				}
			}
			if(found)
				break;
		}

		for (int j = 0; j < LinesTiles.get(0).size(); j++) {
			boolean found = false;
			for (int k = 0; k < LinesTiles.size(); k++) {
				if(!LinesTiles.get(k).get(j).getCurCardId().equals("erase")){
					firstColumn = j;
					found = true;
					break;
				}
			}
			if(found)
				break;
		}

		for(int i = 0; i < numberLine; i++) {
			LinePanel line = new LinePanel();
			line.setLineId(i);
			line.setLayout(lineLayout);
			lines.add(line);
			newPanel.add(lines.get(i));
		}

		for (int i = 0; i < numberLine; i++) {
			for (int j = 0; j < numberCol; j++) {
				lines.get(i).add(new TilePanel());
			}
		}

		System.out.println(firstLine);
		System.out.println(firstColumn);
		int i2 = 0;
		for (int i = firstLine; i < (firstLine+numberLine); i++) {
			for (int j = firstColumn; j < (firstColumn+numberCol); j++) {
				lines.get(i2).remove(j);
				lines.get(i2).add(LinesTiles.get(i).get(j),j);
			}
			i2++;
		}

		frame.getContentPane().remove(panel);
		frame.getContentPane().add(newPanel);
		frame.revalidate();

	}

	/**
	 * There is a bug when placing card on the board because certain cards aren't positioned at the right place.
	 * This method loop throup every panel and make sure the corresponding card is in it.
	 * If not it draw the card in it.
	 */
	public void remakeCardPanels(){
		for (int i = 0; i < LinesTiles.size(); i++) {
			for (int j = 0; j < LinesTiles.get(0).size(); j++) {
				Draw figure = new Draw();
				figure.setCardToDraw(LinesTiles.get(i).get(j).getCurCardId());
				figure.setBackground(Color.WHITE);
				figure.drawing();
				BorderLayout innerLay = new BorderLayout();
				LinesTiles.get(i).get(j).removeAll();
				LinesTiles.get(i).get(j).setLayout(innerLay);
				LinesTiles.get(i).get(j).add(figure,BorderLayout.CENTER);
				LinesTiles.get(i).get(j).revalidate();
				LinesTiles.get(i).get(j).repaint();
			}
		}
	}

	/**
	 * React to the call of notifyObserver() method in the different Observable objects
	 * @param s
	 * @param o
	 */
	@Override
	public void update(Observable s, Object o) {
		if(s instanceof RoundsManager){
			if(o instanceof String){
				if(((String) o).equals("player created")){
					players = RoundsManager.getUniqueRoundManager().getPlayers();
					addPlayersName(players);
					int player = RoundsManager.getUniqueRoundManager().getCurrentPlayerInd();
					labelTurnName.setText(players.get(player).getName()); //todo change
					variante = RoundsManager.getUniqueRoundManager().getVariante();
					currentPLayer = RoundsManager.getUniqueRoundManager().getCurrentPlayerInd();
					formatVariant();
				}else if(((String) o).equals("card picked")){
					showCardPickedBasic();
				}else if(((String) o).equals("card picked next")){
					showCardPickedBasic();
				}else if(((String) o).equals("game end")){
					winners = RoundsManager.getUniqueRoundManager().getWinners();
					clearFieldAtEnd();
					displayScores(RoundsManager.getUniqueRoundManager().getScore());
					remakeCardPanels();
				}
			}

			if(o instanceof Boolean){
				if(((boolean) o)){
					displayScores(RoundsManager.getUniqueRoundManager().getScore());
				}
			}

		} else if(s instanceof GameArea){
			System.out.println("waq here 2");
			if(o instanceof String){
				extension = (String) o;
				switch (extension) {
					case "top" -> {
						addTopLine();
						System.out.println("added top");
					}
					case "bottom" -> {
						addBottomLine();
						System.out.println("add bottom");
					}
					case "left" -> {
						addColBegin();
						System.out.println("add left");
					}
					case "Right" -> {
						addColEnd();
						System.out.println("add right");
					}
					default -> {
						cardMoved = extension;
					}
				}
			}
			if (o instanceof Positioning) {
				positionPlayed = (Positioning) o;
			}else if(o instanceof Card){
				cardPlayed = (Card) o;
			}
			if (o instanceof Boolean){
				moving = (boolean) o;
			}
			if(positionPlayed != null && cardPlayed != null && !moving){
				System.out.println("placed");
				placeCardOnBoard(cardPlayed,positionPlayed);
				positionPlayed = null;
				cardPlayed = null;
			}
			if(positionPlayed != null && cardMoved != null && moving){
				System.out.println("moved");
				moveCardOnBoard(cardMoved,positionPlayed);
				moving = false;
				cardMoved = null;
				positionPlayed = null;
			}
		}
	}
}
