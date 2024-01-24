import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ScrabbleGUI extends JFrame{
    static Board board = Board.initializeBoard();
    static ArrayList<TileLetter> gameBag = TileLetter.createFullBag();
    static Player player1 = new Player("Player 1", gameBag);
    static Player player2 = new Player("Player 2", gameBag);
    static Player currentPlayer = player1;
    static ArrayList<SquareOnBoard> currentWordInSquares = new ArrayList<>();
    static ScreenLayout screen = new ScreenLayout();
    
    ScrabbleGUI(){
        super("Scrabble"); 
        add(screen);
        updateBoardGraphics(board, screen);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        ScrabbleGUI.board = board;
    }

    public ArrayList<TileLetter> getGameBag() {
        return gameBag;
    }

    public void setGameBag(ArrayList<TileLetter> gameBag) {
        ScrabbleGUI.gameBag = gameBag;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        ScrabbleGUI.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        ScrabbleGUI.player2 = player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        ScrabbleGUI.currentPlayer = currentPlayer;
    }

    public ArrayList<SquareOnBoard> getCurrentWordInSquares() {
        return currentWordInSquares;
    }

    public void setCurrentWordInSquares(ArrayList<SquareOnBoard> currentWordInSquares) {
        ScrabbleGUI.currentWordInSquares = currentWordInSquares;
    }

    public ScreenLayout getScreen() {
        return screen;
    }

    public void setScreen(ScreenLayout screen) {
        ScrabbleGUI.screen = screen;
    }

    static void displayErrorMessage(String message){
        JOptionPane.showMessageDialog(screen, message, "Error Message", 0);
    }

    static void displayWinnerMessage(Player player){
        JOptionPane.showMessageDialog(screen, player.getName() + " is the winner with " + player.getPoints() + " points!", "Winner", 1);
    }

    static void updatePlayerRackGraphics(Player player, ScreenLayout screen){
        for (int i = 0; i < 7; i++){
            JButton button = screen.rack.rackButtons[i];
            button.setText(player.rack.get(i).getLetter());
        }
    }

    static void updateBoardGraphics(Board board, ScreenLayout screen){
        for (int y = 0; y < 15; y ++){
            for (int x = 0; x < 15; x ++){
                SquareOnBoard current = board.board[x][y];
                JButton gridButton = screen.grid.gridButtons[x][y];
                if (current.getLetter() != null){
                    gridButton.setText(current.getLetter().getLetter());
                    gridButton.setBackground(Color.LIGHT_GRAY);
                }
                else {
                    if (current.getLetterMultiplier() == 1 && current.getWordMultiplier() == 1){
                        gridButton.setText(" ");
                        gridButton.setBackground(Color.WHITE);
                    }
                    else if (current.getLetterMultiplier() == 2){
                        gridButton.setText("DL");
                        gridButton.setBackground(Color.decode("#b5e3d8"));
                    }
                    else if (current.getLetterMultiplier() == 3){
                        gridButton.setText("TL");
                        gridButton.setBackground(Color.decode("#4ca6ff"));
                    }
                    else if (current.getWordMultiplier() == 2){
                        gridButton.setText("DW");
                        gridButton.setBackground(Color.decode("#f9cb9c"));
                    }
                    else if (current.getWordMultiplier() == 3){
                        gridButton.setText("TW");
                        gridButton.setBackground(Color.decode("#ea9999"));
                    }
                }
            }
        }
    }

    static void updateLeftSideGraphics(Player currentPlayer, ScreenLayout screen){
        screen.left.labels[0].setText("Current Player: " + currentPlayer.getName());
        screen.left.labels[1].setText(player1.getName() + "'s points: " + player1.getPoints());
        screen.left.labels[2].setText(player2.getName() + "'s points: " + player2.getPoints());
        screen.left.labels[3].setText("Letters left in bag: " + ScrabbleGUI.gameBag.size());
    }

    static void resetLettersFromBadPlay(){
        for (SquareOnBoard square : currentWordInSquares){
            screen.grid.gridButtons[square.getX()][square.getY()].setText(Board.initializeBoard().board[square.getX()][square.getY()].toString());
        }
    }

    static void endTurn(){
        if (gameIsOver()){
            endGame();
        }
        if (currentPlayer.equals(player1)){
            currentPlayer = player2;
        }
        else {
            currentPlayer = player1;
        }
        updatePlayerRackGraphics(currentPlayer, screen);
        updateBoardGraphics(board, screen);
        updateLeftSideGraphics(currentPlayer, screen);
    }

    static boolean gameIsOver(){
        if (gameBag.isEmpty()){
            return true;
        }
        if (currentPlayer.turns.size() > 1){
            int lastTurn = currentPlayer.turns.size() - 2;
            int currentTurn = currentPlayer.turns.size() - 1;
            if ((currentPlayer.turns.get(currentTurn) == 2 || currentPlayer.turns.get(currentTurn) == 3) && currentPlayer.turns.get(currentTurn) == currentPlayer.turns.get(lastTurn)){
                return true;
            }
        }
        return false;
    }

    static void endGame(){
        TurnOptions.disableExchangeButton();
        TurnOptions.disableExchangeButton();
        TurnOptions.disablePassButton();
        TurnOptions.disablePlaceButton();
        if (player1.points > player2.points){
            displayWinnerMessage(player1);
        }
        else {
            displayWinnerMessage(player2);
        }
        player1.updateHighScores();
        player2.updateHighScores();
        board.setBoard(Board.initializeBoard().board);
        TurnOptions.enableStartButton();
    }

    static void startGame(){
        String player1 = JOptionPane.showInputDialog("Enter a name for Player 1:");
        ScrabbleGUI.player1.setName(player1);
        String player2 = JOptionPane.showInputDialog("Enter a name for Player 2:");
        ScrabbleGUI.player2.setName(player2);
        updateLeftSideGraphics(currentPlayer, screen);
        updatePlayerRackGraphics(currentPlayer, screen);
        board.setBoard(Board.initializeBoard().board);
        updateBoardGraphics(board, screen);
        TurnOptions.disableStartButton();
        TurnOptions.enablePlaceButton();
        TurnOptions.enableEndButton();
        TurnOptions.enableExchangeButton();
        TurnOptions.enablePassButton();
    }

    static void addPointsAndRefillRack(PlayingWords word){
        currentPlayer.points += word.countPoints();
        Board.addWordToBoard(board, currentWordInSquares);
        currentPlayer.removeTilesFromRack(currentWordInSquares);
        if (gameBag.size() > currentWordInSquares.size()){
            currentPlayer.refillTiles(gameBag);
        }
        else {
            gameBag.clear();
        }
        endTurn();
    }

    static void validatePlayAndAddPoints(){
        PlayingWords play = new PlayingWords(currentWordInSquares, board, currentPlayer);
        play.checkAllRLUP();
        if (board.equals(Board.initializeBoard())){
            play.checkTouchingStartTile();
        }
        if (play.getIsValid()){
            play.checkOnTopOfExisting();
            if (play.getIsValid()){
                play.checkLettersInRow();
                if (play.getIsValid()){
                    play.checkRack();
                    if (play.getIsValid()){
                        play.validateBoard();
                        if (play.getIsValid()){
                            addPointsAndRefillRack(play);
                        }
                        else{
                            displayErrorMessage("The letters you've tried to play create an invalid word.");
                        }
                    }
                    else{
                        displayErrorMessage("You can only play a letter from your rack once.");
                    }
                }
                else{
                    displayErrorMessage("The letters played must be on one line with no spaces.");
                }
            }
            else{
                displayErrorMessage("You cannot play letters ontop of letters already on the board.");
            }
        }
        else{
            displayErrorMessage("The letters played must be touching at least one existing tile on the board.");
        }
        if (!play.getIsValid()){
            resetLettersFromBadPlay();
        }
    }
    

    public static void main(String[] args) {
        new ScrabbleGUI();
        BoardOfButtons.disableGridOfButtonsOnBoard();
        RackOfButtons.disableRackOfButtons();
    }

}



