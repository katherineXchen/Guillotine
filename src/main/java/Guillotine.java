import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Card;
import model.Game;
import model.Player;
import model.action.AbstractAction;
import util.LLNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main class for the game and the game displays
 * @author Katherine Chen
 */
public class Guillotine extends Application {
    // move lead back 4 button
    private Button btnMoveLeadBack4;
    // move lead back 3 button
    private Button btnMoveLeadBack3;
    // move lead back 2 button
    private Button btnMoveLeadBack2;
    // move lead back 1 button
    private Button btnMoveLeadBack1;
    // reverse line button
    private Button btnReverseLine;
    // reverse first 5 button
    private Button btnReverseFirst5;
    // skip turn button
    private Button btnSkipTurn;
    // take first card button
    private Button btnTakeFirstCard;
    // move first to last button
    private Button btnMoveFirstToLast;
    // move last to first button
    private Button btnMoveLastToFirst;
    // Hbox to store game cards, first row
    private HBox gameCardsHb1;
    // Hbox to store game cards, second row
    private HBox gameCardsHb2;
    // Hbox to store player1's cards
    private HBox player1CardsHb;
    // Hbox to store player2's cards
    private HBox player2CardsHb;
    // label for current player
    private Label lbCurrentPlayer;
    // label for status
    private Label lbStatus;
    // label for point of player 1
    private Label lbPlayer1Points;
    // label for point of player 2
    private Label lbPlayer2Points;
    // game instance
    private Game game;
    // map for all action buttons
    private Map<String, Button> actionButtonMap;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * @param primaryStage the main window
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(initComponents());
        initGame();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Initialize game and insert the map for all action buttons to HashMap
     */
    private void initGame() {
        actionButtonMap = new HashMap<>();
        actionButtonMap.put("Move First to Last", btnMoveFirstToLast);
        actionButtonMap.put("Move Last to First", btnMoveLastToFirst);
        actionButtonMap.put("Move Lead Back 4", btnMoveLeadBack4);
        actionButtonMap.put("Move Lead Back 3", btnMoveLeadBack3);
        actionButtonMap.put("Move Lead Back 2", btnMoveLeadBack2);
        actionButtonMap.put("Move Lead Back 1", btnMoveLeadBack1);
        actionButtonMap.put("Reverse First 5", btnReverseFirst5);
        actionButtonMap.put("Reverse Line", btnReverseLine);
        actionButtonMap.put("Skip Turn", btnSkipTurn);
        actionButtonMap.put("Take First Card", btnTakeFirstCard);

        resetGame();
        resetGameCardBoxes();
    }

    /**
     * Initialize components in GUI
     * @return vBox
     */
    private Pane initComponents() {
        VBox vBox = new VBox();
        vBox.setPrefHeight(799);
        vBox.setPrefWidth(915);
        vBox.getChildren().clear();
        vBox.getChildren().addAll(createMenu(), createPane());

        return vBox;
    }

    /**
     * Create anchor pane
     * @return anchor pane
     */
    private AnchorPane createPane() {
        //create different font sizes
        Font font20 = Font.font(20);
        Font font24 = Font.font(24);
        Font font18 = Font.font(18);
        //creates a new anchor pane called anchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(799);
        anchorPane.setPrefWidth(915);
        //create a label for status positioned in the top left corner of the window
        lbStatus = new Label("");
        lbStatus.setFont(font20);
        setNodePos(lbStatus, 31, 27);
        //create a new HBox for the first row of cards
        gameCardsHb1 = new HBox();
        setNodePosAndSize(gameCardsHb1, 31, 64, 852, 126);
        //create a new HBox for the second row of cards
        gameCardsHb2 = new HBox();
        setNodePosAndSize(gameCardsHb2, 31, 190, 852, 126);
        //create a label for the current player
        lbCurrentPlayer = new Label("");
        lbCurrentPlayer.setFont(font20);
        //create a label for action button section
        Label actionLabel = new Label("Actions");
        actionLabel.setFont(font24);
        setNodePos(actionLabel, 31, 365);
        //create a label for the player stats section
        Label stats = new Label("Stats");
        stats.setFont(font24);
        setNodePos(stats, 31, 507);
        //create a label underneath the list of cards which provide tips for the game
        Label cardTips = new Label("The cards are ordered from top to bottom and left to right");
        cardTips.setTextFill(Color.web("#8d8a8a"));
        setNodePosAndSize(cardTips, 303, 324,  315,17);
        //create a separator that separates the cards and the action buttons
        Separator separator1 = new Separator();
        setNodePosAndSize(separator1, 31, 348, 852, 3);
        //create a separator that separates the action buttons and the player stats
        Separator separator2 = new Separator();
        setNodePosAndSize(separator2, 31, 497, 852, 3);
        //create a label for player 1 score
        Label score1 = new Label("Player1 Score: ");
        score1.setFont(font18);
        setNodePosAndSize(score1, 31, 543, 128, 26);
        //makes the player 1 score into a label
        lbPlayer1Points = new Label("100");
        lbPlayer1Points.setFont(font18);
        setNodePosAndSize(lbPlayer1Points, 159, 543, 108, 26);
        //create a label for player 2 score
        Label score2 = new Label("Player2 Score: ");
        score2.setFont(font18);
        setNodePosAndSize(score2, 31, 665, 128, 26);
        //makes the player 2 score into a label
        lbPlayer2Points = new Label("100");
        lbPlayer2Points.setFont(font18);
        setNodePosAndSize(lbPlayer2Points, 159, 665, 108, 26);
        //create a new HBox for the cards that player 1 collected
        player1CardsHb = new HBox();
        setNodePosAndSize(player1CardsHb, 31, 569, 852, 93);
        //create a new HBox for the cards that player 2 collected
        player2CardsHb = new HBox();
        setNodePosAndSize(player2CardsHb, 31, 691, 852, 93);
        //add everything created onto the anchor pane
        anchorPane.getChildren().addAll(lbStatus, gameCardsHb1, gameCardsHb2, actionLabel,
                cardTips, separator1, separator2, stats, score1, score2, lbPlayer1Points, lbPlayer2Points, player1CardsHb, player2CardsHb);
        //add the action buttons onto the anchor pane
        addActionButtons(anchorPane);

        return anchorPane;
    }

    /**
     * Add action buttons
     * @param anchorPane root pane
     */
    private void addActionButtons(AnchorPane anchorPane) {
        btnMoveLeadBack4 = new Button("Move Lead Back 4");
        btnMoveLeadBack4.setOnAction(event -> actionMoveLeadBack4());
        btnMoveLeadBack3 = new Button("Move Lead Back 3");
        btnMoveLeadBack3.setOnAction(event -> actionMoveLeadBack3());
        btnMoveLeadBack2 = new Button("Move Lead Back 2");
        btnMoveLeadBack2.setOnAction(event -> actionMoveLeadBack2());
        btnMoveLeadBack1 = new Button("Move Lead Back 1");
        btnMoveLeadBack1.setOnAction(event -> actionMoveLeadBack1());
        btnReverseLine = new Button("Reverse Line");
        btnReverseLine.setOnAction(event -> actionReverseLine());
        btnReverseFirst5 = new Button("Reverse First 5");
        btnReverseFirst5.setOnAction(event -> actionReverseFirst5());
        btnMoveFirstToLast = new Button("Move First To Last");
        btnMoveFirstToLast.setOnAction(event -> actionMoveFirstToLast());
        btnSkipTurn = new Button("Skip Turn");
        btnSkipTurn.setOnAction(event -> actionSkipTurn());
        btnMoveLastToFirst = new Button("Move Last to First");
        btnMoveLastToFirst.setOnAction(event -> actionMoveLastToFirst());
        btnTakeFirstCard = new Button("Take First Card");
        btnTakeFirstCard.setOnAction(event -> actionTakeFirstCard());

        setButtonFontSize();
        setButtonPosAndSize();

        anchorPane.getChildren().addAll(btnMoveLeadBack4, btnMoveLeadBack3, btnMoveLeadBack2, btnMoveLeadBack1, btnMoveFirstToLast,
                btnMoveLastToFirst, btnTakeFirstCard, btnSkipTurn, btnReverseFirst5, btnReverseLine);
    }

    /**
     * Set button position and size
     */
    private void setButtonPosAndSize() {
        setNodePosAndSize(btnMoveLeadBack4, 31, 401, 162, 38);
        setNodePosAndSize(btnMoveLeadBack3, 207, 401, 162, 38);
        setNodePosAndSize(btnMoveLeadBack2, 379, 401, 162, 38);
        setNodePosAndSize(btnMoveLeadBack1, 551, 401, 162, 38);
        setNodePosAndSize(btnReverseLine, 31, 449, 162, 38);
        setNodePosAndSize(btnReverseFirst5, 207, 449, 162, 38);
        setNodePosAndSize(btnMoveFirstToLast, 721, 401, 162, 38);
        setNodePosAndSize(btnSkipTurn, 379, 449, 162, 38);
        setNodePosAndSize(btnMoveLastToFirst, 721, 449, 162, 38);
        setNodePosAndSize(btnTakeFirstCard, 551, 449, 162, 38);
    }

    /**
     * Set font of buttons
     */
    private void setButtonFontSize() {
        Font font = Font.font(16);
        btnMoveLeadBack4.setFont(font);
        btnMoveLeadBack3.setFont(font);
        btnMoveLeadBack2.setFont(font);
        btnMoveLeadBack1.setFont(font);
        btnMoveFirstToLast.setFont(font);
        btnMoveLastToFirst.setFont(font);
        btnReverseLine.setFont(font);
        btnReverseFirst5.setFont(font);
        btnSkipTurn.setFont(font);
        btnTakeFirstCard.setFont(font);
    }

    /**
     * Set node position
     * @param node inputted node
     * @param x horizontal position
     * @param y vertical position
     */
    private void setNodePos(Region node, int x, int y) {
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    /**
     * Set node position
     * @param node inputted generic node
     * @param x horizontal position
     * @param y vertical position
     * @param width horizontal size of the node
     * @param height vertical size of the node
     */
    private void setNodePosAndSize(Region node, int x, int y, int width, int height) {
        node.setLayoutX(x);
        node.setLayoutY(y);
        node.setPrefWidth(width);
        node.setPrefHeight(height);
    }


    /**
     * Create a menu bar
     * @return menu bar
     */
    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(event -> newGame());
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(event -> System.exit(0));
        menuFile.getItems().addAll(newGame, separator, quit);

        Menu menuHelp = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction(event -> showAbout());
        menuHelp.getItems().add(about);

        menuBar.getMenus().addAll(menuFile, menuHelp);

        return menuBar;
    }

    /**
     * Create a new game
     */
    public void newGame() {
        resetGame();
        resetGameCardBoxes();
        resetPlayerCardBoxes();
        //undisable all bactionuttons when the game resets
        for (Button button: actionButtonMap.values()) {
            button.setDisable(false);
        }
    }

    /**
     * Reset HBoxes of game cards
     */
    private void resetGameCardBoxes() {
        gameCardsHb1.getChildren().clear();
        gameCardsHb2.getChildren().clear();

        LLNode<Card> curr = game.getHead();
        curr = addCardToBox(curr, gameCardsHb1);
        addCardToBox(curr, gameCardsHb2);
    }

    /**
     * reset HBoxes of player cards
     */
    private void resetPlayerCardBoxes() {
        player1CardsHb.getChildren().clear();
        player2CardsHb.getChildren().clear();

        List<Player> playerList = game.getPlayerList();
        resetPlayerCardBox(playerList.get(0), player1CardsHb);
        resetPlayerCardBox(playerList.get(1), player2CardsHb);
    }

    /**
     * Reset one player cards HBox
     * @param player target player
     * @param box HBox of player cards
     */
    private void resetPlayerCardBox(Player player, HBox box) {
        // traverse player cards and add to HBox
        for (Card card: player.getCardList()) {
            box.getChildren().add(createCardButton(card, true));
        }
    }

    /**
     * Add card to HBox
     * @param curr current node in list
     * @param box HBox
     * @return current node
     */
    private LLNode<Card> addCardToBox(LLNode<Card> curr, HBox box) {
        if (curr != null) {
            // each hbox of game cards can have at most 10 cards
            for (int i = 0; i < 10; i++) {
                Button btn = createCardButton(curr.getElement(), false);
                box.getChildren().add(btn);
                curr = curr.getNext();
                if (curr == null) {
                    break;
                }
            }
        }

        return curr;
    }

    /**
     * Set action button status, some button can only be clicked once for one player
     */
    private void setActionButtonStatus() {
        Player player = game.getCurrentPlayer();
        Map<String, AbstractAction> playerActionMap = player.getActionMap();
        // traverse button map, if player has clicked, then disable it
        for (Map.Entry<String, Button> entry: actionButtonMap.entrySet()) {
            if (!playerActionMap.containsKey(entry.getKey())) {
                entry.getValue().setDisable(true);
            } else {
                entry.getValue().setDisable(false);
            }
        }
    }

    /**
     * Get card group images
     * @param card the card
     * @return ImageView
     */
    private ImageView getImageView(Card card) {
        Image img;
        switch (card.getGroup()) {
            case CIVIC:
                img = new Image("civic.png");
                break;
            case ROYAL:
                img = new Image("royal.png");
                break;
            case CHURCH:
                img = new Image("church.png");
                break;
            case COMMONER:
                img = new Image("commoner.png");
                break;
            case MILITARY:
                img = new Image("military.png");
                break;
            default:
                throw new IllegalStateException("Unknown card");
        }

        return new ImageView(img);
    }

    /**
     * Create a button for the card
     * @param card the card
     * @param isPlayerCard it means it's a player card else it's a game card
     * @return the button
     */
    private Button createCardButton(Card card, boolean isPlayerCard) {
        VBox vBox = new VBox(3);
        //apply images to the game cards
        ImageView view = getImageView(card);
        if (isPlayerCard) {
            view.setFitHeight(40);
            view.setFitWidth(50);
        } else {
            view.setFitHeight(60);
            view.setFitWidth(90);
        }
        view.setPreserveRatio(true);
        vBox.getChildren().add(view);
        //create a label for the card name
        Label lbName = new Label(card.getName());
        lbName.setFont(Font.font(18));
        if (isPlayerCard) {
            lbName.setFont(Font.font(16));
        }
        //make the point value of the card into a String
        String point = card.getPoint() == 0 ? "Point *": "Point " + card.getPoint();
        //create a label for the card's point value
        Label lbPoint = new Label(point);
        lbPoint.setFont(Font.font(14));
        if (isPlayerCard) {
            lbPoint.setFont(Font.font(12));
        }
        vBox.getChildren().add(lbName);
        vBox.getChildren().add(lbPoint);
        //create a button for the card
        Button btn = new Button();
        if (isPlayerCard) {
            btn.setPrefWidth(50);
            btn.setPrefHeight(80);
        } else {
            btn.setPrefWidth(86);
            btn.setPrefHeight(131);
        }
        btn.setGraphic(vBox);

        return btn;
    }

    /**
     * Reset the game state
     */
    public void resetGame() {
        game = new Game();
        lbCurrentPlayer.setText(game.getCurrentPlayer().getName());
        lbPlayer1Points.setText("0");
        lbPlayer2Points.setText("0");
        lbStatus.setText("Now is " + game.getCurrentPlayer().getName() + "'s turn!");
    }

    /**
     * Quit game
    */
    public void quit() {
        System.exit(0);
    }

    /**
     * Execute one action by action name
     * @param actionName the name of the target action
     */
    private void executeAction(String actionName) {
        game.executeAction(actionName);
        resetGameCardBoxes();
        setActionButtonStatus();
        //create a player list
        List<Player> playerList = game.getPlayerList();
        lbPlayer1Points.setText(String.valueOf(playerList.get(0).getPoint()));
        lbPlayer2Points.setText(String.valueOf(playerList.get(1).getPoint()));
        lbStatus.setText("Now is " + game.getCurrentPlayer().getName() + "'s turn!");

        resetPlayerCardBoxes();

        if (game.isGameOver()) {
            // if game is over, disable all buttons
            for (Button button: actionButtonMap.values()) {
                button.setDisable(true);
            }

            // show status message and alert message
            Player winner = game.getWinPlayer();
            if (winner != null) {
                lbStatus.setText("Game over! Winner is " + winner.getName() + "!");
            } else {
                lbStatus.setText("Game over! Tied !");
            }

            alertInfo("Game is over, select new game menu to start a new round.");
        }
    }

    /**
     * Action when clicked on move lead back 4 button
     */
    public void actionMoveLeadBack4() {
        executeAction("Move Lead Back 4");
    }

    /**
     * Action when clicked on move lead back 3 button
     */
    public void actionMoveLeadBack3() {
        executeAction("Move Lead Back 3");
    }

    /**
     * Action when clicked on move lead back 2 button
     */
    public void actionMoveLeadBack2() {
        executeAction("Move Lead Back 2");
    }

    /**
     * Action when clicked on move lead back 1 button
     */
    public void actionMoveLeadBack1() {
        executeAction("Move Lead Back 1");
    }

    /**
     * Action when clicked on move first to last button
     */
    public void actionMoveFirstToLast() {
        executeAction("Move First to Last");
    }

    /**
     * Action when clicked on move last to first button
     */
    public void actionMoveLastToFirst() {
        executeAction("Move Last to First");
    }

    /**
     * Action when clicked on reverse line button
     */
    public void actionReverseLine() {
        executeAction("Reverse Line");
    }

    /**
     * Action when clicked on reverse first 5 button
     */
    public void actionReverseFirst5() {
        executeAction("Reverse First 5");
    }

    /**
     * Action when clicked on skip turn button
     */
    public void actionSkipTurn() {
        executeAction("Skip Turn");
    }

    /**
     * Action when clicked on take first card button
     */
    public void actionTakeFirstCard() {
        executeAction("Take First Card");
    }

    /**
     * Show about info
     */
    public void showAbout() {
        alertInfo("Guillotine is a card game!");
    }

    /**
     * Alert info message
     * @param message info message
     */
    private void alertInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.show();
    }

    /**
     * The main method launches the application
     * @param args the command line arguments to pass to the GUI
     */
    public static void main(String[] args) {
        launch(args);
    }
}
