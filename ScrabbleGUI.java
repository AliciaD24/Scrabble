import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ScrabbleGUI extends JFrame{
    private static Board board = Board.initializeBoard();
    private static ArrayList<TileLetter> gameBag = TileLetter.createFullBag();
    private static Player player1 = new Player(null, gameBag);
    private static Player player2 = new Player(null, gameBag);
    private ArrayList<SquareOnBoard> playedSquares = new ArrayList<>();
    private ArrayList<String> wordsOnBoard = new ArrayList<>();
    static ArrayList<SquareOnBoard> currentWordInSquares = new ArrayList<>();
    static ScreenLayout screen = new ScreenLayout();

    
    ScrabbleGUI(){
        super("Scrabble"); 
        add(screen);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    static void changeToPlayerRack(Player player, ScreenLayout screen){
        for (int i = 0; i < 7; i++){
            JButton button = screen.rack.buttons[i];
            button.setText(player.rack.get(i).getLetter());
        }
    }

    static void updateBoard(Board board, ScreenLayout screen){
        for (int y = 0; y < 15; y ++){
            for (int x = 0; x < 15; x ++){
                SquareOnBoard current = board.board[x][y];
                JButton gridButton = screen.grid.gridButtons[x][y];
                if (current.getLetter() != null){
                    gridButton.setText(current.getLetter().getLetter());
                    gridButton.setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    public static void main(String[] args) {
        new ScrabbleGUI();
        changeToPlayerRack(player1, screen);
        //updateBoard(PlayingWordsTesting.testBoard(), screen);
    }
}

class ScreenLayout extends JPanel{
    private Header header = new Header(); 
    BoardOfButtons grid = new BoardOfButtons();
    LeftSide left = new LeftSide();
    RackOfButtons rack = new RackOfButtons();
    private TurnOptions options = new TurnOptions();


    ScreenLayout(){
        super(new BorderLayout());
        
        add(header, BorderLayout.PAGE_START);
        add(grid, BorderLayout.CENTER);
        add(left, BorderLayout.LINE_START);
        add(rack, BorderLayout.PAGE_END);
        add(options, BorderLayout.LINE_END);
    }
}

class Header extends JPanel{
    private JLabel[] labels;

    Header(){
        super(new GridLayout(1, 7));
        labels = makeHeader();
        addLabelsToPanel();
    }

    static JLabel[] makeHeader(){
        JLabel[] labels = new JLabel[1];
        labels[0] = new JLabel("Welcome to Scrabble!");
        return labels;
    }

    private void addLabelsToPanel(){
        for (int i = 0; i < labels.length; i ++){
            add(labels[i]);
        }
    }
}

class BoardOfButtons extends JPanel{
    JButton[][] gridButtons;
    Board initialized = Board.initializeBoard();

    BoardOfButtons(){
        super(new GridLayout(15, 15));

        gridButtons = makeButtons(initialized);
        addButtonsToPanel();
    }

    static JButton[][] makeButtons(Board board){
        JButton[][] gridButtons = new JButton[15][15];
        for (int y = 0; y < 15; y ++){
            for (int x = 0; x < 15; x ++){
                SquareOnBoard current = board.board[x][y];
                if (current.getLetterMultiplier() == 1 && current.getWordMultiplier() == 1){
                    gridButtons[x][y] = new JButton(" ");
                }
                else if (current.getLetterMultiplier() == 2){
                    gridButtons[x][y] = new JButton("DL");
                    gridButtons[x][y].setBackground(Color.decode("#b5e3d8"));
                }
                else if (current.getLetterMultiplier() == 3){
                    gridButtons[x][y] = new JButton("TL");
                    gridButtons[x][y].setBackground(Color.decode("#4ca6ff"));
                }
                else if (current.getWordMultiplier() == 2){
                    gridButtons[x][y] = new JButton("DW");
                    gridButtons[x][y].setBackground(Color.decode("#f9cb9c"));
                }
                else if (current.getWordMultiplier() == 3){
                    gridButtons[x][y] = new JButton("TW");
                    gridButtons[x][y].setBackground(Color.decode("#ea9999"));
                }
                gridButtons[x][y].setOpaque(true);
                gridButtons[x][y].setBorderPainted(false);;
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
        int index = ScrabbleGUI.currentWordInSquares.size() - 1;
        SquareOnBoard newSquare = ScrabbleGUI.currentWordInSquares.get(index);
        newSquare.setX(row);
        newSquare.setY(col);
        ScrabbleGUI.currentWordInSquares.set(index, newSquare);
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

}

class LeftSide extends JPanel{
    JLabel[] labels;

    LeftSide(){
        super(new GridLayout(1, 7));
        labels = makeBag();
        addLabelsToPanel();
    }

    static JLabel[] makeBag(){
        JLabel[] labels = new JLabel[1];
        labels[0] = new JLabel("   ");
        return labels;
    }

    private void addLabelsToPanel(){
        for (int i = 0; i < labels.length; i ++){
            add(labels[i]);
        }
    }
}

class RackOfButtons extends JPanel{
    JButton[] buttons;

    RackOfButtons(){
        super(new GridLayout(1, 7));
        buttons = makeRack();
        addButtonsToPanel();
        addActionListenersRack();
    }

    static JButton[] makeRack(){
        JButton[] buttons = new JButton[7];
        for (int i = 0; i < 7; i ++){
            buttons[i] = new JButton("   ");
        }
        return buttons;
    }

    private void addButtonsToPanel(){
        for (int i = 0; i < 7; i ++){
            add(buttons[i]);
        }
    }

    void getLetter(int i){
        TileLetter tile = new TileLetter(buttons[i].getText());
        System.out.println(tile);
        ScrabbleGUI.currentWordInSquares.add(new SquareOnBoard(tile, 0, 0, 0, 0));
        System.out.println(ScrabbleGUI.currentWordInSquares);
    }

    void addActionListenersRack(){

        for (int i = 0; i < 7; i ++){
            int index = i;
            buttons[i].addActionListener(e -> getLetter(index));
        }
    }
}

class TurnOptions extends JPanel{
    private JLabel[] labels;

    TurnOptions(){
        super(new GridLayout(1, 7));
        labels = makeTurnOptions();
        addLabelsToPanel();
    }

    static JLabel[] makeTurnOptions(){
        JLabel[] labels = new JLabel[1];
        labels[0] = new JLabel("Turn options");
        return labels;
    }

    private void addLabelsToPanel(){
        for (int i = 0; i < labels.length; i ++){
            add(labels[i]);
        }
    }
}

