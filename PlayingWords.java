import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class PlayingWords {

    private ArrayList<SquareOnBoard> wordInSquares;
    private Board board;
    private Player player;
    private boolean validity;

    private static ArrayList<String> wordList = generateWordList();
    
    public PlayingWords(ArrayList<SquareOnBoard> wordInSquares, Board board, Player player) {
        this.wordInSquares = wordInSquares;
        this.board = board;
        this.player = player;
        this.validity = true;
    }

    public ArrayList<SquareOnBoard> getWordInSquares() {
        return wordInSquares;
    }

    public void setWordInSquares(ArrayList<SquareOnBoard> wordInSquares) {
        this.wordInSquares = wordInSquares;
    }

    public Board getBoard() {
        return board;
    }

    public void setWordBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean getValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public static ArrayList<String> getWordList() {
        return wordList;
    }

    public static void setWordList(ArrayList<String> wordList) {
        PlayingWords.wordList = wordList;
    }


    private static ArrayList<String> generateWordList(){
        ArrayList<String> words = new ArrayList<>();
        try {
            File file = new File("words.txt");
            Scanner input = new Scanner(file);
            while (input.hasNextLine()){
                words.add(input.nextLine());
            }
            input.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return words;
    }

    //validate the word

    //check the rack 
    void checkRack(){
        ArrayList<TileLetter> playerRackCopy = new ArrayList<>(this.player.getRack());
        ArrayList<TileLetter> wordInTiles = new ArrayList<>();
        for (SquareOnBoard square : this.wordInSquares){
            wordInTiles.add(square.getLetter());
        }
        for (TileLetter tile : wordInTiles){
            if (!playerRackCopy.contains(tile)){
                setValidity(false);
            }
            playerRackCopy.remove(tile);
        }
    }

    //check if touching at least one existing piece
    private static boolean checkRLUP(Board board, SquareOnBoard tile){
        for (int x = tile.getX() - 1; x <= tile.getX() + 1; x ++){
            for (int y = tile.getY() - 1; y <= tile.getY() + 1; y ++){
                if (x == 15 || y == 15){
                    continue;
                }
                else if (board.board[x][y].letter != null){
                    return true;
                }
            }
        }
        return false;
    }

    void checkAllRLUP(){
        boolean checker = false;
        for (SquareOnBoard tile : this.wordInSquares){
            if (checkRLUP(this.board, tile)){
                checker = true;
            }
        }
        setValidity(checker);
    }

    //checks all letters in a row
    void checkLettersInRow(){
        boolean horizontalOutput = true;
        boolean verticalOutput = true;
        int firstX = this.wordInSquares.get(0).getX();
        int firstY = this.wordInSquares.get(0).getY();
        for (SquareOnBoard square : wordInSquares){
            if (square.getX() != firstX){
                horizontalOutput = false;
            }
            if (square.getY() != firstY){
                verticalOutput = false;
            }
        }
        if (horizontalOutput && verticalOutput || !horizontalOutput && !horizontalOutput){
            setValidity(false);
        }
    }

    //check whole board for either single letter sequences or words in dictionary
    void validateBoard(){
        ArrayList<SquareOnBoard> lineOfSquares = new ArrayList<>();
        ArrayList<String> lineOfWords = new ArrayList<>();
        for (int row = 0; row < 15; row ++){
            lineOfSquares.clear();
            lineOfWords.clear();
            for (int col = 0; col < 15; col ++){
                lineOfSquares.add(this.board.board[row][col]);
            }
            lineOfWords = getAllWordsInLine(lineOfSquares);
            if (!lineOfWords.isEmpty() && !checkForValidWords(lineOfWords)){
                setValidity(false);
            }

        }
        for (int col = 0; col < 15; col ++){
            lineOfSquares.clear();
            lineOfWords.clear();
            for (int row = 0; row < 15; row ++){
                lineOfSquares.add(this.board.board[row][col]);
            }
            lineOfWords = getAllWordsInLine(lineOfSquares);
            if (!lineOfWords.isEmpty() && !checkForValidWords(lineOfWords)){
                setValidity(false);
            }
        }
    }

    private static boolean checkForValidWords(ArrayList<String> lineOfWords){
        for (String word : lineOfWords){
            if (word.length() > 1){
                if (!wordList.contains(word.toLowerCase())){
                    return false;
                }
            }
        }
        return true;
    }

    private static ArrayList<String> getAllWordsInLine(ArrayList<SquareOnBoard> lineOfSquares){
        ArrayList<String> listOfWords = new ArrayList<>();
        String line = "";
        for (SquareOnBoard square : lineOfSquares){
            String letter = "";
            if (square.getLetter() == null){
                letter = " ";
            }
            else {
                letter = square.getLetter().getLetter();
            }
            line += letter;
        }
        Scanner wordParser = new Scanner(line);
        while (wordParser.hasNext()){
            String word = wordParser.next();
            listOfWords.add(word);
        }
        wordParser.close();
        return listOfWords;
    }



    private static int countPoints(ArrayList<SquareOnBoard> validWord){
        int wordTotal = 0;
        int wordMultTotal = 1;
        for (SquareOnBoard tile : validWord){
            wordTotal += tile.letterMultiplier * tile.getLetter().getPointValue();
            wordMultTotal *= tile.getWordMultiplier();
        }
        return wordTotal * wordMultTotal;
    }

    private void playWordValidateAndAddPointsToPlayer(){
        if (this.validity){
            player.points += countPoints(wordInSquares);
        }
    }

    private Board addWordToBoard(){
        for (SquareOnBoard square : this.wordInSquares){
            this.board = Board.addTileToBoard(this.board, square);
        }
        return board;
    }

    public static void main(String[] args) {
        ArrayList<SquareOnBoard> tiles = new ArrayList<>();
    }
    
}
