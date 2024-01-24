import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class PlayingWords {

    private ArrayList<SquareOnBoard> wordInSquares;
    private Board oldBoard;
    private Board newBoard;
    private Player player;
    private boolean isValid;

    private static ArrayList<String> wordList = generateWordList();
    
    /** Constructor for PlayingWords using the ArrayList of SquareOnBoards being played, the Board it is being played to, and the Player making the play.
     * @param wordInSquares represents the ArrayList of SquareOnBoards.
     * @param board represents the Board.
     * @param player represents the Player.
     */
    public PlayingWords(ArrayList<SquareOnBoard> wordInSquares, Board board, Player player) {
        this.wordInSquares = wordInSquares;
        this.oldBoard = board;
        this.newBoard = Board.addWordToBoard(board.clone(), wordInSquares);
        this.player = player;
        this.isValid = true;
    }

    /** Gets the word being played to the board by the player.
     * @return returns the ArrayList of SquareOnBoards that is the word.
     */
    public ArrayList<SquareOnBoard> getWordInSquares() {
        return wordInSquares;
    }
    
    /** Sets the word being played to the board by the player to a new ArrayList of SquareOnBoards.
     * @param wordInSquares represents the new ArrayList or SquareOnBoards.
     */
    public void setWordInSquares(ArrayList<SquareOnBoard> wordInSquares) {
        this.wordInSquares = wordInSquares;
    }

    /** Gets the newBoard which is the board to which the word is being played after the word has been added.
     * @return returns the Board of the old board with the word added.
     */
    public Board getNewBoard() {
        return newBoard;
    }

    /** Sets the newBoard to a new value.
     * @param board represents the board to which the newBoard is being set.
     */
    public void setNewBoard(Board board) {
        this.newBoard = board;
    }

    /** Gets the oldBoard which is the board to which the word is being played before the word has been added.
     * @return returns the Board of the old board.
     */
    public Board getOldBoard() {
        return oldBoard;
    }

    /** Sets the oldBoard to a new value.
     * @param board represents the board to which the oldBoard is being set.
     */
    public void setOldBoard(Board board) {
        this.oldBoard = board;
    }

    /** Gets the Player that is playing the word.
     * @return returns the Player value.
     */
    public Player getPlayer() {
        return player;
    }

    /** Sets the Player that is playing the word.
     * @param player represents the Player that is being set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /** Gets the validity of the word.
     * @return returns true if the word is valid and false if it is not.
     */
    public boolean getIsValid() {
        return isValid;
    }

    /** Sets the validity of the word.
     * @param validity represents the new value of the word's validity.
     */
    public void setValid(boolean validity) {
        this.isValid = validity;
    }

    /** Gets the word list.
     * @return returns the word list as an ArrayList of Strings.
     */
    public static ArrayList<String> getWordList() {
        return wordList;
    }

    /** Sets the word list.
     * @param wordList represents the word list being set.
     */
    public static void setWordList(ArrayList<String> wordList) {
        PlayingWords.wordList = wordList;
    }

    /** Creates hashCode for PlayingWords.
     * @return returns PlayingWords's hashCode as an int.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((wordInSquares == null) ? 0 : wordInSquares.hashCode());
        result = prime * result + ((oldBoard == null) ? 0 : oldBoard.hashCode());
        result = prime * result + ((newBoard == null) ? 0 : newBoard.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + (isValid ? 1231 : 1237);
        return result;
    }

    /** Checks if PlayingWords is equal to another PlayingWords.
     * @param obj represents the other PlayingWords.
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
        PlayingWords other = (PlayingWords) obj;
        if (wordInSquares == null) {
            if (other.wordInSquares != null)
                return false;
        } else if (!wordInSquares.equals(other.wordInSquares))
            return false;
        if (oldBoard == null) {
            if (other.oldBoard != null)
                return false;
        } else if (!oldBoard.equals(other.oldBoard))
            return false;
        if (newBoard == null) {
            if (other.newBoard != null)
                return false;
        } else if (!newBoard.equals(other.newBoard))
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        if (isValid != other.isValid)
            return false;
        return true;
    }

    /** Returns the string value of PlayingWords formatted to be easily legible.
     * @return String version of PlayingWords.
     */
    @Override
    public String toString() {
        return "PlayingWords [wordInSquares=" + wordInSquares + ", oldBoard=" + oldBoard + ", newBoard=" + newBoard
                + ", player=" + player + ", isValid=" + isValid + "]";
    }

    /** Reads the words.txt file and compiles all the words to an ArrayList of Strings.
     * @return returns the ArrayList of Strings.
     */
    static ArrayList<String> generateWordList(){
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

    /**
     * Checks if the word being played is placing letters on any squares that already contain letters. Sets word validity to false if letters are being played on other letters.
     */
    void checkOnTopOfExisting(){
        for (SquareOnBoard square : this.wordInSquares){
            if (this.oldBoard.board[square.getX()][square.getY()].getLetter() != null){
                this.setValid(false);
            }
        }
    }

    /**
     * Checks the player's rack for the letters being played. Sets word validity to false if the player is trying to play a letter that is not on their rack.
     */
    void checkRack(){
        ArrayList<TileLetter> playerRackCopy = new ArrayList<>(this.player.getRack());
        ArrayList<TileLetter> wordInTiles = new ArrayList<>();
        TileLetter blank = new TileLetter(" ");
        for (SquareOnBoard square : this.wordInSquares){
            wordInTiles.add(square.getLetter());
        }
        for (TileLetter tile : wordInTiles){
            if (!playerRackCopy.contains(tile)){
                if (playerRackCopy.contains(blank)){
                    playerRackCopy.remove(blank);
                }
                else{
                    setValid(false);
                }
            }
            else {
                playerRackCopy.remove(tile);
            }
        }
    }

    /** Checks the tiles surrounding a tile on the edges of the board (does not check indicies that don't exist such as -1 and 15.)
     * @param board represents the board being checked.
     * @param tile represents the square being checked for contact with other tiles.
     * @return true if the tile has at least one neighbouring square with a letter already on it.
     */
    private static boolean checkOutsideEdges(Board board, SquareOnBoard tile){
        if (tile.getX() == 0){
            for (int x = tile.getX(); x <= tile.getX() + 1; x ++){
                for (int y = tile.getY() - 1; y <= tile.getY() + 1; y ++){
                    if (y == 15){
                        continue;
                    }
                    else if (board.board[x][y].letter != null){
                        return true;
                    }
                }
            }
        }
        if (tile.getY() == 0){
            for (int y = tile.getY(); y <= tile.getY() + 1; y ++){
                for (int x = tile.getX() - 1; x <= tile.getX() + 1; x ++){
                    if (x == 15){
                        continue;
                    }
                    else if (board.board[x][y].letter != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Checks a square on the board for at least one neighbouring square that contains a letter.
     * @param board represents the board being checked.
     * @param tile represents the tile being checked for surrounding contact.
     * @return returns true if at least one neighbouring square contains a letter.
     */
    private static boolean checkRLUP(Board board, SquareOnBoard tile){
        if (tile.getX() == 0 || tile.getY() == 0) {
            return checkOutsideEdges(board, tile);
        }
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

    /**
     * Checks each tile being played by the player for contact with at least one tile already on the board. Sets word validity to false if no neighbouring tiles with letters found.
     */
    void checkAllRLUP(){
        boolean checker = false;
        for (SquareOnBoard tile : this.wordInSquares){
            if (checkRLUP(this.oldBoard, tile)){
                checker = true;
            }
        }
        setValid(checker);
    }

    /**
     * Checks if any of the letters in the word touch the center tile (starting tile). Sets word validity to false if they dont.
     */
    void checkTouchingStartTile(){
        boolean checker = false;
        for (SquareOnBoard tile : this.wordInSquares){
            if (tile.getX() == 7 && tile.getY() == 7){
                checker = true;
            }
        }
        setValid(checker);
    }

    /**
     * Checks if all letters being played are in one line with no spaces that aren't filled by tiles already on the board. Sets word validity to false if any gaps or changes in lines found.
     */
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
        if (!horizontalOutput && !verticalOutput){
            setValid(false);
        }
        else{
            if (this.wordInSquares.size() != 1){
                if (horizontalOutput){
                    setValid(noSpacesBetweenTiles(this, firstX, true));
                }
                else {

                    setValid(noSpacesBetweenTiles(this, firstY, false));
                }
            }
        }
        
    }

    /** Checks if there are any spaces between tiles that are not filled by tiles already on the board.
     * @param word represents the word being played.
     * @param constantValue represents the integer value (x value or y value) that remains constant.
     * @param isHorizontal represents the orientation of the word on the board.
     * @return returns true if no spaces are found.
     */
    private static boolean noSpacesBetweenTiles(PlayingWords word, int constantValue, boolean isHorizontal){
        ArrayList<SquareOnBoard> lineOfSquares = new ArrayList<>();
        if (isHorizontal){
            int x = constantValue;
            for (int col = 0; col < 15; col ++){
                lineOfSquares.add(word.newBoard.board[x][col]);
            }
        }
        else {
            int y = constantValue;
            for (int row = 0; row < 15; row ++){
                lineOfSquares.add(word.newBoard.board[row][y]);
            }
        }
        ArrayList<ArrayList<SquareOnBoard>> wordsInSquaresList = getSquaresPerWordInLine(lineOfSquares);
        for (int i = 0; i < wordsInSquaresList.size(); i ++){
            if (wordsInSquaresList.get(i).contains(word.getWordInSquares().get(0))){
                return allPlayedMatchWord(wordsInSquaresList.get(i), word);
            }
        }
        return false;
    }

    /** Checks if all the letters that were played belong to the same word.
     * @param wordOnBoard represents the whole word on the board including letters added by the player and letters previously on the board.
     * @param word represents the word played.
     * @return returns true if all the letter played by the player are in the word on the board.
     */
    private static boolean allPlayedMatchWord(ArrayList<SquareOnBoard> wordOnBoard, PlayingWords word){
        for (SquareOnBoard square : word.getWordInSquares()){
            if (!wordOnBoard.contains(square)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks every row and column on the board for valid words of single letter. Sets validity to false if any sequence of letters greater than one is nota valid word in the words.txt file.
     */
    void validateBoard(){
        ArrayList<SquareOnBoard> lineOfSquares = new ArrayList<>();
        ArrayList<String> lineOfWords = new ArrayList<>();
        for (int row = 0; row < 15; row ++){
            lineOfSquares.clear();
            lineOfWords.clear();
            for (int col = 0; col < 15; col ++){
                lineOfSquares.add(this.newBoard.board[row][col]);
            }
            lineOfWords = getAllWordsInLine(lineOfSquares);
            if (!lineOfWords.isEmpty() && !checkForValidWords(lineOfWords)){
                setValid(false);
            }

        }
        for (int col = 0; col < 15; col ++){
            lineOfSquares.clear();
            lineOfWords.clear();
            for (int row = 0; row < 15; row ++){
                lineOfSquares.add(this.newBoard.board[row][col]);
            }
            lineOfWords = getAllWordsInLine(lineOfSquares);
            if (!lineOfWords.isEmpty() && !checkForValidWords(lineOfWords)){
                setValid(false);
            }
        }
    }

    /** Checks is a word is a valid word in the words.txt file.
     * @param lineOfWords represents the word being checked.
     * @return returns true if the word is in the words.txt file.
     */
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

    /** Gets all the words as String that are in a row or column from a line of SquareOnBoards.
     * @param lineOfSquares represents the ArrayList of SquareOnBoards.
     * @return returns all words in a line.
     */
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
            if (word.length() > 1){
                listOfWords.add(word);
            }
        }
        wordParser.close();
        return listOfWords;
    }

    /** Gets each word in a row/column and adds it to an ArrayList of ArrayLists of SquareOnBoards.
     * @param lineOfSquares represents the row/column that is being split into words.
     * @return returns an ArrayList of ArrayLists of SquareOnBoards.
     */
    private static ArrayList<ArrayList<SquareOnBoard>> getSquaresPerWordInLine(ArrayList<SquareOnBoard> lineOfSquares){
        ArrayList<ArrayList<SquareOnBoard>> wordsInLine = new ArrayList<>();
        ArrayList<SquareOnBoard> wordInSquares = new ArrayList<>();
        for (SquareOnBoard square : lineOfSquares){
            if (square.getLetter() != null){
                wordInSquares.add(square);
                if (lineOfSquares.get(14).equals(square)){
                    wordsInLine.add(wordInSquares);
                }
            }
            else {
                if (wordInSquares.size() > 1){
                    try{
                        ArrayList<SquareOnBoard> copy = clone(wordInSquares);
                        wordsInLine.add(copy);

                    }
                    catch (ClassCastException e) {
                        System.out.println(e.getMessage());
                    }
                }
                wordInSquares.clear();
            }
        }
        return wordsInLine;
    }

    static ArrayList<SquareOnBoard> clone(ArrayList<SquareOnBoard> list){
        ArrayList<SquareOnBoard> copy = new ArrayList<>();
        for (SquareOnBoard square : list){
            copy.add(square);
        }
        return copy;
    }

    /** Adds all words on a board to a HashMap where the key is the String value of the word and the value is an ArrayList of SquareOnBoards containing all the squares that the word consists of.
     * @param board represents the board from which the words are being collected.
     * @return returns the HashMap.
     */
    private static HashMap<String, ArrayList<SquareOnBoard>> getAllWordsFromBoard(Board board){
        HashMap<String, ArrayList<SquareOnBoard>> wordsOnBoard = new HashMap<>();
        addWordsPerLineToHashMap(wordsOnBoard, board, true);
        addWordsPerLineToHashMap(wordsOnBoard, board, false);
        return wordsOnBoard;
    }

    /** Adds every word in a row/column to a HashMap where the key is the String value of the word and the value is an ArrayList of SquareOnBoards containing all the squares that the word consists of.
     * @param wordsOnBoard represents the HashMap.
     * @param board represents the board from which all the words are being collected.
     * @param isRow represents the orientation of the line.
     * @return returns the HashMap.
     */
    private static HashMap<String, ArrayList<SquareOnBoard>> addWordsPerLineToHashMap(HashMap<String, ArrayList<SquareOnBoard>> wordsOnBoard, Board board, boolean isRow) {
        ArrayList<SquareOnBoard> lineOfSquares = new ArrayList<>();
        ArrayList<String> lineOfWords = new ArrayList<>();
        ArrayList<ArrayList<SquareOnBoard>> lineOfWordsAsSquares = new ArrayList<>();
        for (int row = 0; row < 15; row ++){
            lineOfSquares.clear();
            lineOfWords.clear();
            for (int col = 0; col < 15; col ++){
                if (isRow){
                    lineOfSquares.add(board.board[row][col]);
                }
                else{
                    lineOfSquares.add(board.board[col][row]);
                }
            }
            lineOfWords = getAllWordsInLine(lineOfSquares);
            lineOfWordsAsSquares = getSquaresPerWordInLine(lineOfSquares);
            for (int i = 0; i < lineOfWords.size(); i ++){
                String key = lineOfWords.get(i);
                if (wordsOnBoard.keySet().contains(key)){
                    key += "+";
                }
                wordsOnBoard.put(key, lineOfWordsAsSquares.get(i));
            }
        }
        return wordsOnBoard;
    }

    /** Creates a HashMap of words unique to the newBoard HashMap where the key is the String value of the word and the value is an ArrayList of SquareOnBoards containing all the squares that the word consists of.
     * @param oldBoard represents the HashMap of words on the board before the word the player played was added.
     * @param newBoard represents the HashMap of word on the board after the word the player played was added.
     * @return returns the new HashMap or words unique to the newBoard HashMap.
     */
    private static HashMap<String, ArrayList<SquareOnBoard>> newWordsFromBoards(Board oldBoard, Board newBoard){
        HashMap<String, ArrayList<SquareOnBoard>> oldBoardWords = getAllWordsFromBoard(oldBoard);
        HashMap<String, ArrayList<SquareOnBoard>> newBoardWords = getAllWordsFromBoard(newBoard);
        HashMap<String, ArrayList<SquareOnBoard>> newWords = new HashMap<>();
        for (String word : newBoardWords.keySet()){
            if (!oldBoardWords.keySet().contains(word)){
                newWords.put(word, newBoardWords.get(word));
            }
        }
        return newWords;
    }


    /** Counts the points acquired by playing a word.
     * @return returns the int value of the number of points a word scores including all multipliers and 50 point bonus for using all 7 words.
     */
    int countPoints(){
        HashMap<String, ArrayList<SquareOnBoard>> newWords = newWordsFromBoards(this.oldBoard, this.newBoard);
        int wordTotal = 0;
        int wordMultTotal = 1;
        int pointTotal = 0;
        for (String word : newWords.keySet()){
            wordTotal = 0;
            wordMultTotal = 1;
            for (SquareOnBoard square : newWords.get(word)){
                if (this.wordInSquares.contains(square)){
                    wordTotal += square.getLetterMultiplier() * square.getLetter().getPointValue();
                    wordMultTotal *= square.getWordMultiplier();
                }
                else {
                    wordTotal += square.getLetter().getPointValue();
                }
                
            }
            pointTotal += wordTotal * wordMultTotal;
            if (this.wordInSquares.size() == 7){
                pointTotal += 50;
            }
        }
        return pointTotal;
    }

    public static void main(String[] args) {
    }
    
}
