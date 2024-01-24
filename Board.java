//   0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 
// 0 T     d       T         d       T
// 1   D       t       t           D
// 2     D       d   d          D
// 3 d     D       d         D       d
// 4         D            D
// 5   t       t        t          t
// 6     d       d    d         d
// 7 T     d        D         d     T
// 8     d       d    d         d
// 9   t        t       t          t
// 10         D           D
// 11 d     D        d      D       d
// 12     D        d  d       D  
// 13   D        t      t       D
// 14 T     d       T       d       T

import java.util.Arrays;
import java.util.ArrayList;

class Board {
    SquareOnBoard[][] board;

    private static int[][] tripleWordTiles = {{0, 0}, {7, 0}, {14, 0}, {0, 7}, {14, 7}, {0, 14}, {7, 14}, {14, 14}};
    private static int[][] doubleWordTiles = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {1, 13}, {2, 12}, {3, 11}, {4, 10}, {10, 4}, {11, 3}, {12, 2}, {13, 1}, {10, 10}, {11, 11}, {12, 12}, {13, 13}, {7, 7}};
    private static int[][] tripleLetterTiles = {{5, 1}, {9, 1}, {1, 5}, {5, 5}, {9, 5}, {13, 5}, {1, 9}, {5, 13}, {9, 13}, {5, 9}, {9, 9}, {13, 9}};
    private static int[][] doubleLetterTiles = {{3, 0}, {11, 0}, {6, 2}, {8, 2}, {0, 3}, {7, 3}, {14, 3}, {2, 6}, {6, 6}, {8, 6}, {12, 6}, {3, 7}, {11, 7}, {2, 8}, {6, 8}, {8, 8}, {12, 8}, {0, 11}, {7, 11}, {14, 11}, {6, 12}, {8, 12}, {3, 14}, {11, 14}};

    /**
     * Constructor for a Board. 
     */
    Board() {
        board = new SquareOnBoard[15][15];
        for (int col = 0; col < 15; col++){
            for (int row = 0; row < 15; row++){
                board[row][col] = new SquareOnBoard(null, 1, 1, row, col);
            }
        }
    }

    /** Constructor for a Board made up of a specified 2 dimensional Array of SquareOnBoards.
     * @param board represents the 2D SquareOnBoards value.
     */
    Board(SquareOnBoard[][] board){
        this.board = board;
    }

    /** Gets the 2D Array of SquareOnBoards.
     * @return returns the 2D Array of SquareOnBoards.
     */
    public SquareOnBoard[][] getBoard() {
        return board;
    }

    /** Sets the 2D Array of SquareOnBoards to a new value.
     * @param board represents the new value of the 2D Array of SquareOnBoards.
     */
    public void setBoard(SquareOnBoard[][] board) {
        this.board = board;
    }  
    
    /** Clones a Board into a new Board object.
     * @return Board with identical values to the Board it was called on.
     */
    @Override
    public Board clone(){
        Board copy = new Board();
        for (int col = 0; col < 15; col++){
            for (int row = 0; row < 15; row++){
                SquareOnBoard old = this.board[row][col];
                copy.board[row][col] = new SquareOnBoard(old.getLetter(), old.getWordMultiplier(), old.getLetterMultiplier(), old.getX(), old.getY());
            }
        }
        return copy;
    }
    
    /** Creates hashCode for a Board.
     * @return returns the Board's hashCode as an int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(board);
        return result;
    }
    
    /** Checks if a Board is equal to another Board.
     * @param obj represents the other Board
     * @return boolean 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Board other = (Board) obj;
        if (!Arrays.deepEquals(board, other.board))
            return false;
        return true;
    }
    
    /**  Returns the string value of a Board formatted to be easily legible.
     * @return String version of Board.
     */
    @Override
    public String toString() {
        String row = "";
        for (int i = 0; i < 15; i++){
            row = "";
            for (int j = 0; j < 15; j++){
                row +=(board[j][i]);
                row += "|";
            }
            System.out.println(row);
            System.out.println("---------------------------------------------------------------------------");
        }
        return "";
    }
    
    /** Adds word and letter multipliers to the SquareOnBoards on the board.
     * @param tileCoors represents the tiles to which the specified multipliers are being added.
     * @param letterMultiplier represents the letter multiplier value.
     * @param wordMultiplier represents the word multiplier value.
     */
    private void addSpecialTiles(int[][] tileCoors, int letterMultiplier, int wordMultiplier){
        int xInt = 0;
        int yInt = 0;
        for (int[] coors : tileCoors){
            xInt = coors[0];
            yInt = coors[1];
            SquareOnBoard tile = board[xInt][yInt];
            tile.setLetterMultiplier(letterMultiplier);
            tile.setWordMultiplier(wordMultiplier);
        }
    }

    /** Creates a blank board with no letters where each square has its correct multipliers added .
     * @return returns a new Board fully set up ready for play.
     */
    static Board initializeBoard(){
        Board newBoard = new Board();
        newBoard.addSpecialTiles(tripleWordTiles, 1, 3);
        newBoard.addSpecialTiles(doubleWordTiles, 1, 2);
        newBoard.addSpecialTiles(tripleLetterTiles, 3, 1);
        newBoard.addSpecialTiles(doubleLetterTiles, 2, 1);
        return newBoard;
    }

    /** Adds a specified tile to the specified board.
     * @param board represents the board to which the tile is being added.
     * @param tile represents the tile being added to the board.
     * @return returns the modified board.
     */
    static Board addTileToBoard(Board board, SquareOnBoard tile){
        int x = tile.getX();
        int y = tile.getY();
        board.board[x][y].setLetter(tile.getLetter());
        return board;
    }

    /** Adds each tile in a specified ArrayList of SquareOnBoards to the specified board.
     * @param board represents the board to which the tile is being added.
     * @param tiles represents the ArrayList of SquareOnBoards being added to the board.
     * @return returns the modified board.
     */
    static Board addWordToBoard(Board board, ArrayList<SquareOnBoard> tiles){
        for (SquareOnBoard square : tiles){
            board = addTileToBoard(board, square);
        }
        return board;
    }

    public static void main(String[] args) {
    
    }
}
