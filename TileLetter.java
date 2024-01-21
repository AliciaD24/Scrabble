import java.util.ArrayList;
import java.util.HashMap;

class TileLetter {
    int pointValue;
    String letter;

    TileLetter(String letter) {
        this.pointValue = pointLetterValuesHashMap().get(letter);
        this.letter = letter;
    }

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

    static void addLettersToHashMap(HashMap<String, Integer> map, String letters, int pointValue){
        for (int i = 0; i < letters.length(); i ++){
            map.put(letters.substring(i, i+1), pointValue);
        }
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pointValue;
        result = prime * result + ((letter == null) ? 0 : letter.hashCode());
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

    @Override
    public String toString() {
        return "" + letter + "";
    }

    private static ArrayList<TileLetter> createLetters(ArrayList<TileLetter> bag, int numberOfLetter, String letter){
        for (int i = 0; i < numberOfLetter; i++){
            TileLetter newTile = new TileLetter(letter);
            bag.add(newTile);
        }
        return bag;
    }

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
        System.out.println(createFullBag());
    }
}
