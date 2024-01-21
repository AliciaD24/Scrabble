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

    Board() {
        board = new SquareOnBoard[15][15];
        for (int col = 0; col < 15; col++){
            for (int row = 0; row < 15; row++){
                board[row][col] = new SquareOnBoard(null, 1, 1, row, col);
            }
        }

    }

    Board(SquareOnBoard[][] board){
        this.board = board;
    }

    public SquareOnBoard[][] getBoard() {
        return board;
    }

    public void setBoard(SquareOnBoard[][] board) {
        this.board = board;
    }  

    @Override
    public Board clone(){
        Board copy = new Board();
        copy.setBoard(board);
        return copy;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(board);
        return result;
    }

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

    private static int[][] tripleWordTiles = {{0, 0}, {7, 0}, {14, 0}, {0, 7}, {14, 7}, {0, 14}, {7, 14}, {14, 14}};
    private static int[][] doubleWordTiles = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {1, 13}, {2, 12}, {3, 11}, {4, 10}, {10, 4}, {11, 3}, {12, 2}, {13, 1}, {10, 10}, {11, 11}, {12, 12}, {13, 13}, {7, 7}};
    private static int[][] tripleLetterTiles = {{5, 1}, {9, 1}, {1, 5}, {5, 5}, {9, 5}, {13, 5}, {1, 9}, {5, 13}, {9, 13}, {5, 9}, {9, 9}, {13, 9}};
    private static int[][] doubleLetterTiles = {{3, 0}, {11, 0}, {6, 2}, {8, 2}, {0, 3}, {7, 3}, {14, 3}, {2, 6}, {6, 6}, {8, 6}, {12, 6}, {3, 7}, {11, 7}, {2, 8}, {6, 8}, {8, 8}, {12, 8}, {0, 11}, {7, 11}, {14, 11}, {6, 12}, {8, 12}, {3, 14}, {11, 14}};

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

    static Board initializeBoard(){
        Board newBoard = new Board();
        newBoard.addSpecialTiles(tripleWordTiles, 1, 3);
        newBoard.addSpecialTiles(doubleWordTiles, 1, 2);
        newBoard.addSpecialTiles(tripleLetterTiles, 3, 1);
        newBoard.addSpecialTiles(doubleLetterTiles, 2, 1);
        return newBoard;
    }


    //get letter and location

    static Board addTileToBoard(Board board, SquareOnBoard tile){
        int x = tile.getX();
        int y = tile.getY();
        board.board[x][y].setLetter(tile.getLetter());
        return board;
    }

    static Board addWordToBoard(Board board, ArrayList<SquareOnBoard> tiles){
        for (SquareOnBoard square : tiles){
            board = addTileToBoard(board, square);
        }
        return board;
    }

    static Board removeTileFromBoard(Board board, SquareOnBoard tile){
        int x = tile.getX();
        int y = tile.getY();
        board.board[x][y].setLetter(null);
        return board;
    }

    static Board removeWordFromBoard(Board board, ArrayList<SquareOnBoard> tiles){
        for (SquareOnBoard square : tiles){
            board = removeTileFromBoard(board, square);
        }
        return board;
    }

    // MAYBE IN ANOTHER CLASS

    //get whole word
    private static ArrayList<SquareOnBoard> getWholeWord(){
        ArrayList<SquareOnBoard> wordInSquares = new ArrayList<>();
        String letter = Player.getLetterFromPlayer(null);
        int[] coors = Player.getCoordinatesFromPlayer(null);
        Board blank = initializeBoard();
        while (TileLetter.pointLetterValuesHashMap().containsKey(letter)){
            TileLetter letterTile = new TileLetter(letter);
            SquareOnBoard onBlank = blank.board[coors[0]][coors[1]];
            SquareOnBoard square = new SquareOnBoard(letterTile, onBlank.getWordMultiplier(), onBlank.getLetterMultiplier(), coors[0], coors[1]);
            wordInSquares.add(square);
            letter = Player.getLetterFromPlayer(letter);
            coors = Player.getCoordinatesFromPlayer(coors);
        }
        return wordInSquares;
    }
   

    //check if touching at least one existing piece
    private static boolean checkRLUP(Board board, SquareOnBoard tile){
        for (int x = tile.getX() - 1; x <= tile.getX() + 1; x ++){
            for (int y = tile.getY() - 1; y <= tile.getY() + 1; y ++){
                if (board.board[x][y].letter != null){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkAllRLUP(Board board, ArrayList<SquareOnBoard> wordInSquares){
        for (SquareOnBoard tile : wordInSquares){
            if (checkRLUP(board, tile)){
                return true;
            }
        }
        return false;
    }

    //checks all letters in a row
    private static boolean checkLettersInRow(ArrayList<SquareOnBoard> wordInSquares){
        boolean horizontalOutput = true;
        boolean verticalOutput = true;
        for (int i = 0; i < wordInSquares.size() - 1; i ++){
            SquareOnBoard currentTile = wordInSquares.get(i);
            SquareOnBoard nextTile = wordInSquares.get(i + 1);
            if (nextTile.getX() != currentTile.getX() + 1){
                horizontalOutput = false;
            }
            if (nextTile.getY() != currentTile.getY() + 1){
                verticalOutput = false;
            }
        }
        if (horizontalOutput && verticalOutput){
            return false;
        }
        return horizontalOutput || verticalOutput;
    }

    // //check whole board for either single letter sequences or words in dictionary
    // private static boolean validBoard(Board board){
    //     ArrayList<SquareOnBoard> lineOfSquares = new ArrayList<>();
    //     for (int row = 0; row < 15; row ++){
    //         for (int col = 0; col < 15; col ++){
    //             lineOfSquares.add(board.board[row][col]);
    //         }
    //         if (!PlayingWords.checkForValidWords(PlayingWords.getAllWordsInLine(lineOfSquares))){
    //             return false;
    //         }
    //     }
    //     for (int col = 0; col < 15; col ++){
    //         for (int row = 0; row < 15; row ++){
    //             lineOfSquares.add(board.board[row][col]);
    //         }
    //         if (!PlayingWords.checkForValidWords(lineOfSquares)){
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    public static void main(String[] args) {
        Board newBoard = initializeBoard();
        System.out.println(newBoard);
        System.out.println(PlayingWordsTesting.testBoard());
    }
}
