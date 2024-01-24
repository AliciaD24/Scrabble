
class SquareOnBoard {
    TileLetter letter = null;
    int wordMultiplier = 1;
    int letterMultiplier = 1;
    int x;
    int y;
    
    /** Constructor for a SquareOnBoard made up of a letter, word multiplier, letter multiplier, x value and y value on the board.
     * @param letter represents the TileLetter on the square.
     * @param wordMultiplier represents the number by which every word on this square will be multiplied.
     * @param letterMultiplier represents the number by which every TileLetter on this square will be multiplied.
     * @param x represents the x value on the board.
     * @param y represents the y value on the board.
     */
    public SquareOnBoard(TileLetter letter, int wordMultiplier, int letterMultiplier, int x, int y) {
        this.letter = letter;
        this.wordMultiplier = wordMultiplier;
        this.letterMultiplier = letterMultiplier;
        this.x = x;
        this.y = y;
    }

    /** Gets the letter on the square.
     * @return returns the TileLetter.
     */
    public TileLetter getLetter() {
        return letter;
    }

    /** Sets the letter on the square to a new TileLetter.
     * @param letter represents the new TileLetter of the square.
     */
    public void setLetter(TileLetter letter) {
        this.letter = letter;
    }

    /** Gets the word multiplier of the square.
     * @return represents the new word multiplier of the square.
     */
    public int getWordMultiplier() {
        return wordMultiplier;
    }

    /** Sets the word multiplier of the square to a new value.
     * @param wordMultiplier represents the new value of the word multiplier.
     */
    public void setWordMultiplier(int wordMultiplier) {
        this.wordMultiplier = wordMultiplier;
    }

    /** Gets the letter multiplier of the square.
     * @return represents the new letter multiplier of the square.
     */
    public int getLetterMultiplier() {
        return letterMultiplier;
    }

    /** Sets the letter multiplier of the square to a new value.
     * @param letterMultiplier represents the new value of the letter multiplier.
     */
    public void setLetterMultiplier(int letterMultiplier) {
        this.letterMultiplier = letterMultiplier;
    }

    /** Gets the x value of the square on the board.
     * @return returns the x coordinate of the square.
     */
    public int getX() {
        return x;
    }

    /** Sets the x value of the square to a new value.
     * @param x represents the new x value of the square.
     */
    public void setX(int x) {
        this.x = x;
    }

    /** Gets the y value of the square on the board.
     * @return returns the y coordinate of the square.
     */
    public int getY() {
        return y;
    }

    /** Sets the y value of the square to a new value.
     * @param y represents the new y value of the square.
     */
    public void setY(int y) {
        this.y = y;
    }

    /** Creates hashCode for a SquareOnBoard.
     * @return returns the SquareOnBoard's hashCode as an int.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((letter == null) ? 0 : letter.hashCode());
        result = prime * result + wordMultiplier;
        result = prime * result + letterMultiplier;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    /** Checks if a SquareOnBoard is equal to another SquareOnBoard.
     * @param obj represents the other SquareOnBoard.
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
        SquareOnBoard other = (SquareOnBoard) obj;
        if (letter == null) {
            if (other.letter != null)
                return false;
        } else if (!letter.equals(other.letter))
            return false;
        if (wordMultiplier != other.wordMultiplier)
            return false;
        if (letterMultiplier != other.letterMultiplier)
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
    /** Returns the string value of a SquareOnBoard formatted to be easily legible.
     * @return String version of SquareOnBoard.
     */
    @Override
    public String toString() {
        if (letter == null){
            if (letterMultiplier == 1 && wordMultiplier == 1){
                    return "    ";
                }
                else if (letterMultiplier == 2){
                    return " DL ";
                }
                else if (letterMultiplier == 3){
                    return " TL ";
                }
                else if (wordMultiplier == 2){
                    return " DW ";
                }
                else if (wordMultiplier == 3){
                    return " TW ";
                }
        }
        return letter.toString() + "(" + getX() + ", " + getY() + ")";
    }
    public static void main(String[] args) {

    }
}
