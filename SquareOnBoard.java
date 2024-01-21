import javax.swing.JButton;

class SquareOnBoard {
    TileLetter letter = null;
    int wordMultiplier = 1;
    int letterMultiplier = 1;
    int x;
    int y;
    
    public SquareOnBoard(TileLetter letter, int wordMultiplier, int letterMultiplier, int x, int y) {
        this.letter = letter;
        this.wordMultiplier = wordMultiplier;
        this.letterMultiplier = letterMultiplier;
        this.x = x;
        this.y = y;
    }

    public TileLetter getLetter() {
        return letter;
    }

    public void setLetter(TileLetter letter) {
        this.letter = letter;
    }

    public int getWordMultiplier() {
        return wordMultiplier;
    }

    public void setWordMultiplier(int wordMultiplier) {
        this.wordMultiplier = wordMultiplier;
    }

    public int getLetterMultiplier() {
        return letterMultiplier;
    }

    public void setLetterMultiplier(int letterMultiplier) {
        this.letterMultiplier = letterMultiplier;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

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
        return letter.toString();
    }
    public static void main(String[] args) {
        System.out.println(new SquareOnBoard(new TileLetter("A"), 0, 0, 0, 0));
    }
}
