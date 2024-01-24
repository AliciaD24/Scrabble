import java.util.ArrayList;
import java.util.HashMap;

class TileLetter {
    int pointValue;
    String letter;

    /** Constructor for a Tile Letter made up of a String value being the letter of the tile.
     * @param letter represents the Letter of the TileLetter.
     */
    TileLetter(String letter) {
        this.pointValue = pointLetterValuesHashMap().get(letter);
        this.letter = letter;
    }

    /**  Gets the point value of the TileLetter.
     * @return returns the point value of the TileLetter.
     */
    public int getPointValue() {
        return pointValue;
    }

    /** Sets the point value of a TileLetter to a new value.
     * @param pointValue represents the new values of the TileLetter's point value.
     */
    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    /**  Gets the letter of the TileLetter.
     * @return returns the String value of the letter in the TileLetter.
     */
    public String getLetter() {
        return letter;
    }

    /** Sets the letter of the TileLetter to a new letter validating the input and regardless of capitalization.
     * @param letter represents the string value of the letter being made the letter of the TileLetter.
     * @throws IllegalArgumentException throws an exception if the letter trying to be set as the TileLetter's letter is longer than one character.
     */
    public void setLetter(String letter) throws IllegalArgumentException{
        if (letter.length() != 1){
            throw new IllegalArgumentException("Must be one letter only");
        }
        this.letter = letter.toUpperCase();
    }

    /** Creates hashCode for a TileLetter.
     * @return returns the TileLetter's hashCode as an int.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pointValue;
        result = prime * result + ((letter == null) ? 0 : letter.hashCode());
        return result;
    }

    /** Checks if a TileLetter is equal to another TileLetter.
     * @param obj represents the other TileLetter.
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
        TileLetter other = (TileLetter) obj;
        if (pointValue != other.pointValue)
            return false;
        if (letter == null) {
            if (other.letter != null)
                return false;
        } else if (!letter.equals(other.letter))
            return false;
        return true;
    }

    /** Returns the string value of a TileLetter formatted to be easily legible.
     * @return String version of TileLetter.
     */
    @Override
    public String toString() {
        return "" + letter + "";
    }

    /** Creates a HashMap of each letter in the alphabet as the key and it's point value as the value.
     * @return returns a HashMap with each letter and its respective point value.
     */
    static HashMap<String, Integer> pointLetterValuesHashMap(){
        HashMap<String, Integer> letterPointValues = new HashMap<>();
        String onePointLetters = "AEIOULNSTR";
        String twoPointLetters = "DG";
        String threePointLetters = "BCMP";
        String fourPointLetters = "FHVWY";
        String fivePointLetters = "K";
        String eightPointLetters = "JX";
        String tenPointLetters = "QZ";
        addLettersToHashMap(letterPointValues, onePointLetters, 1);
        addLettersToHashMap(letterPointValues, twoPointLetters, 2);
        addLettersToHashMap(letterPointValues, threePointLetters, 3);
        addLettersToHashMap(letterPointValues, fourPointLetters, 4);
        addLettersToHashMap(letterPointValues, fivePointLetters, 5);
        addLettersToHashMap(letterPointValues, eightPointLetters, 8);
        addLettersToHashMap(letterPointValues, tenPointLetters, 10);
        letterPointValues.put(" ", 0);
        return letterPointValues;
    }

    /** Adds a set of letters with the same point value to a HashMap.
     * @param map represents the HashMap to which the letters and point values are being added to.
     * @param letters represents the letters being added to the HashMap.
     * @param pointValue represents the point value of the letters being added to the HashMap.
     */
    static void addLettersToHashMap(HashMap<String, Integer> map, String letters, int pointValue){
        for (int i = 0; i < letters.length(); i ++){
            map.put(letters.substring(i, i+1), pointValue);
        }
    }

    /** Creates the required number of TileLetters for a specific letter and adds them to a bag.
     * @param bag represents the bag to which the letters are being added.
     * @param numberOfLetter represents the number of the specified letter that is being created.
     * @param letter represents the letter.
     * @return returns the bag with the specified number of specified letters added.
     */
    private static ArrayList<TileLetter> createLetters(ArrayList<TileLetter> bag, int numberOfLetter, String letter){
        for (int i = 0; i < numberOfLetter; i++){
            TileLetter newTile = new TileLetter(letter);
            bag.add(newTile);
        }
        return bag;
    }

    /** Creates a full gamebag containing all the required tiles for a scrabble game.
     * @return returns a ArrayList of TileLetters with 100 tiles inside for a game of scrabble.
     */
    static ArrayList<TileLetter> createFullBag(){
        ArrayList<TileLetter> bag = new ArrayList<>();
        createLetters(bag, 12, "E");
        createLetters(bag, 9, "A");
        createLetters(bag, 9, "I");
        createLetters(bag, 8, "O");
        createLetters(bag, 6, "N");
        createLetters(bag, 6, "R");
        createLetters(bag, 6, "T");
        createLetters(bag, 4, "L");
        createLetters(bag, 4, "S");
        createLetters(bag, 4, "U");
        createLetters(bag, 4, "D");
        createLetters(bag, 3, "G");
        createLetters(bag, 2, "B");
        createLetters(bag, 2, "C");
        createLetters(bag, 2, "M");
        createLetters(bag, 2, "P");
        createLetters(bag, 2, "F");
        createLetters(bag, 2, "H");
        createLetters(bag, 2, "V");
        createLetters(bag, 2, "W");
        createLetters(bag, 2, "Y");
        createLetters(bag, 1, "K");
        createLetters(bag, 1, "J");
        createLetters(bag, 1, "X");
        createLetters(bag, 1, "Q");
        createLetters(bag, 1, "Z");
        createLetters(bag, 2, " ");
        return bag;
    }

    public static void main(String[] args) {

    }
}
