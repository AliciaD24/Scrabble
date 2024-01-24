import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

class BoardOfButtons extends JPanel{
    JButton[][] gridButtons;
    Board initialized = Board.initializeBoard();

    BoardOfButtons(){
        super(new GridLayout(15, 15));

        gridButtons = makeButtons(initialized);
        addButtonsToPanel();
        addActionListenersGrid();
    }

    public JButton[][] getGridButtons() {
        return gridButtons;
    }

    public void setGridButtons(JButton[][] gridButtons) {
        this.gridButtons = gridButtons;
    }

    public Board getInitialized() {
        return initialized;
    }

    public void setInitialized(Board initialized) {
        this.initialized = initialized;
    }

    static JButton[][] makeButtons(Board board){
        JButton[][] gridButtons = new JButton[15][15];
        Font gridButtonFont = new Font(Font.SERIF, Font.BOLD, 20);
        for (int y = 0; y < 15; y ++){
            for (int x = 0; x < 15; x ++){
                gridButtons[x][y] = new JButton(" ");
                gridButtons[x][y].setOpaque(true);
                Border buttonBorder = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED);
                gridButtons[x][y].setBorder(buttonBorder);
                gridButtons[x][y].setFont(gridButtonFont);
            }
        }
        return gridButtons;
    }

    private void addButtonsToPanel(){
        for (int y = 0; y < 15; y ++){
            for (int x = 0; x < 15; x ++){
                add(gridButtons[x][y]);
            }
        }
    }

    void getCoordinates(int row, int col){
        Board blank = Board.initializeBoard();
        int index = ScrabbleGUI.currentWordInSquares.size() - 1;
        SquareOnBoard newSquare = ScrabbleGUI.currentWordInSquares.get(index);
        newSquare.setX(row);
        newSquare.setY(col);
        newSquare.setLetterMultiplier(blank.board[newSquare.getX()][newSquare.getY()].getLetterMultiplier());
        newSquare.setWordMultiplier(blank.board[newSquare.getX()][newSquare.getY()].getWordMultiplier());
        ScrabbleGUI.currentWordInSquares.set(index, newSquare);
        ScrabbleGUI.screen.grid.gridButtons[row][col].setText(ScrabbleGUI.currentWordInSquares.get(index).getLetter().getLetter());
        RackOfButtons.enableRackOfButtons();
        TurnOptions.enableConfirmButton();
        BoardOfButtons.disableGridOfButtonsOnBoard();
    }

    void addActionListenersGrid(){
        for (int y = 0; y < 15; y ++){
            for (int x = 0; x < 15; x ++){
                int row = x;
                int col = y;
                gridButtons[x][y].addActionListener(e -> getCoordinates(row, col));
            }
        }
    }

    static void enableGridOfButtonsOnBoard(){
        for (int y = 0; y < 15; y ++){
            for (int x = 0; x < 15; x ++){
                ScrabbleGUI.screen.grid.gridButtons[x][y].setEnabled(true);
            }
        }
    }

    static void disableGridOfButtonsOnBoard(){
        for (int y = 0; y < 15; y ++){
            for (int x = 0; x < 15; x ++){
                ScrabbleGUI.screen.grid.gridButtons[x][y].setEnabled(false);
            }
        }
    }

}