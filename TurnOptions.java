import javax.swing.*;
import java.awt.*;

class TurnOptions extends JPanel{
    private static JButton[] optionButtons;

    TurnOptions(){
        super(new GridLayout(6, 1));
        optionButtons = makeTurnOptions();
        addButtonsToPanel();
        addActionListenersOptions();
        disableConfirmButton();
        disableEndButton();
        disableExchangeButton();
        disablePassButton();
        disablePlaceButton();
    }

    public static JButton[] getOptionButtons() {
        return optionButtons;
    }

    public static void setOptionButtons(JButton[] optionButtons) {
        TurnOptions.optionButtons = optionButtons;
    }

    static JButton[] makeTurnOptions(){
        JButton[] buttons = new JButton[6];
        buttons[0] = new JButton("Start Game");
        buttons[1] = new JButton("Place Letters");
        buttons[2] = new JButton("Confirm Letters");
        buttons[3] = new JButton("Exchange Tiles");
        buttons[4] = new JButton("Pass");
        buttons[5] = new JButton("End Game");
        return buttons;
    }

    private void addButtonsToPanel(){
        Font labelFont = new Font(Font.MONOSPACED, Font.PLAIN, 15);
        for (int i = 0; i < 6; i ++){
            add(optionButtons[i]);
            optionButtons[i].setFont(labelFont);
        }
    }

    void startGame(){
        ScrabbleGUI.startGame();
    }

    void placeLettersButtonAction(){
        ScrabbleGUI.resetLettersFromBadPlay();
        ScrabbleGUI.currentWordInSquares.clear();
        ScrabbleGUI.updateBoardGraphics(ScrabbleGUI.board, ScrabbleGUI.screen);
        disablePassButton();
        disableExchangeButton();
        disableEndButton();
        disableConfirmButton();
        RackOfButtons.enableRackOfButtons();
        optionButtons[1].setText("Reset Letters");
        BoardOfButtons.disableGridOfButtonsOnBoard();

    }

    void confirmPlacedLetters(){
        RackOfButtons.disableRackOfButtons();
        BoardOfButtons.disableGridOfButtonsOnBoard();
        ScrabbleGUI.validatePlayAndAddPoints();
        optionButtons[1].setText("Place Letters");
        ScrabbleGUI.currentPlayer.turns.add(1);
        disableConfirmButton();
        enableExchangeButton();
        enablePassButton();
        enableEndButton();
    }

    void exchangeCurrentPlayerTiles(){
        ScrabbleGUI.currentPlayer.exchangeTiles(ScrabbleGUI.gameBag);
        ScrabbleGUI.currentPlayer.turns.add(2);
        ScrabbleGUI.endTurn();
    }

    void passTurn(){
        ScrabbleGUI.currentPlayer.turns.add(3);
        ScrabbleGUI.endTurn();        
    }

    void endGame(){
        ScrabbleGUI.endGame();
    }

    void addActionListenersOptions(){
        optionButtons[0].addActionListener(e -> startGame());
        optionButtons[1].addActionListener(e -> placeLettersButtonAction());
        optionButtons[2].addActionListener(e -> confirmPlacedLetters());
        optionButtons[3].addActionListener(e -> exchangeCurrentPlayerTiles());
        optionButtons[4].addActionListener(e -> passTurn());
        optionButtons[5].addActionListener(e -> endGame());
    }

    static void enableStartButton(){
        optionButtons[0].setEnabled(true);
    }

    static void disableStartButton(){
        optionButtons[0].setEnabled(false);
    }

    static void enablePlaceButton(){
        optionButtons[1].setEnabled(true);
    }

    static void disablePlaceButton(){
        optionButtons[1].setEnabled(false);
    }

    static void enableConfirmButton(){
        optionButtons[2].setEnabled(true);
    }

    static void disableConfirmButton(){
        optionButtons[2].setEnabled(false);
    }

    static void enableExchangeButton(){
        optionButtons[3].setEnabled(true);
    }

    static void disableExchangeButton(){
        optionButtons[3].setEnabled(false);
    }

    static void enablePassButton(){
        optionButtons[4].setEnabled(true);
    }

    static void disablePassButton(){
        optionButtons[4].setEnabled(false);
    }

    static void enableEndButton(){
        optionButtons[5].setEnabled(true);
    }

    static void disableEndButton(){
        optionButtons[5].setEnabled(false);
    }

}