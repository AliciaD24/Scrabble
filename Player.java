import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Player{
    String name;
    int points;
    ArrayList<TileLetter> rack;
    ArrayList<Integer> turns;

    /** Constructor for a Player using a name for the player and the gamebag from which it generates the rack.
     * @param name represents the name of the player.
     * @param bag represents the bag from which the player's rack is generated.
     */
    Player(String name, ArrayList<TileLetter> bag){
        this.name = name;
        this.points = 0;
        this.rack = generateFullRack(bag);
        this.turns = new ArrayList<>();
    }

    /** Constructor for a Player using a name for the player and the player's points.
     * @param name represents the name for the player.
     * @param points represents the player's points.
     */
    Player(String name, int points){
        this.name = name;
        this.points = points;
        this.rack = new ArrayList<>();
        this.turns = new ArrayList<>();
    }

    /**  Gets the name of the player.
     * @return returns the name of the player as a String.
     */
    public String getName() {
        return name;
    }

    /** Sets the name of the player to a new name.
     * @param name represents the new name for the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Gets the player's points.
     * @return returns the player's points.
     */
    public int getPoints() {
        return points;
    }

    /** Sets the player's points to a new value.
     * @param points represents the new point value for the player.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /** Get's the player's rack of TileLetters.
     * @return returns the player's rack as an ArrayList of TileLetters.
     */
    public ArrayList<TileLetter> getRack() {
        return rack;
    }

    /** Sets the player's rack to a new value.
     * @param rack represents the new ArrayList of TileLetters for the player's rack.
     */
    public void setRack(ArrayList<TileLetter> rack) {
        this.rack = rack;
    }

    /** Gets the player's list of turns.
     * @return returns the ArrayList of Integers of all the turns the player has made.
     */
    public ArrayList<Integer> getTurns() {
        return turns;
    }

    /** Sets the player's list of turns.
     * @param turns represents the ArrayList of Integers being set to the player's turns.
     */
    public void setTurns(ArrayList<Integer> turns) {
        this.turns = turns;
    }

    
    /** Creates hashCode for a Player.
     * @return returns the Player's hashCode as an int.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + points;
        result = prime * result + ((rack == null) ? 0 : rack.hashCode());
        return result;
    }
    
    /** Checks if a Player is equal to another Player.
     * @param obj represents the other Player.
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
        Player other = (Player) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (points != other.points)
            return false;
        if (rack == null) {
            if (other.rack != null)
                return false;
        } else if (!rack.equals(other.rack))
            return false;
        return true;
    }

    
    /** Returns the string value of a Player formatted to be easily legible.
     * @return String version of Player.
     */
    @Override
    public String toString() {
        return name + ": " + points + ", rack: " + rack;
    }

    /** Creates an ArrayList of 7 TileLetters to be used as a Player's rack, randomly generating each TileLetter from the bag of letters given. 
     * @param bag represents the bag from which the letters are being randomly picked.
     * @return returns an ArrayList of TileLetters of the randomly generated rack.
     */
    static ArrayList<TileLetter> generateFullRack(ArrayList<TileLetter> bag){
        int letterIndex = 0;
        ArrayList<TileLetter> rack = new ArrayList<>();
        while (rack.size() < 7){
            letterIndex = (int) (Math.random() * (bag.size() - 1) + 1);
            rack.add(bag.get(letterIndex));
            bag.remove(bag.get(letterIndex));
        }
        return rack;
    }

    /** Exchanges all tiles in a player's rack for random tiles from the specified bag, first generating a new rack with random letters and then adding the previous ones to the bag to minimize duplication of letters previously in the player's rack.
     * @param currentBag represents the bag from which the letters are being randomly picked and added back to.
     */
    void exchangeTiles(ArrayList<TileLetter> currentBag){
        ArrayList<TileLetter> newRack = generateFullRack(currentBag);
        for (TileLetter oldTile : this.getRack()){
            currentBag.add(oldTile);
        }
        this.setRack(newRack);
    }

    /** Refills the player's rack until the Player has 7 tiles in their rack.
     * @param currentBag represents the bag from which the letters are being randomly picked to refill the player's rack.
     */
    void refillTiles(ArrayList<TileLetter> currentBag){
        int letterIndex = 0;
        while (this.rack.size() < 7){
            letterIndex = (int) (Math.random() * (currentBag.size() - 1) + 1);
            this.rack.add(currentBag.get(letterIndex));
            currentBag.remove(currentBag.get(letterIndex));
        }
    }

    /** Removes the tiles being placed on the board from the player's rack.
     * @param wordInSquares represents the word being played onto the board therefore containing the tiles that are being used from the player's rack.
     */
    void removeTilesFromRack(ArrayList<SquareOnBoard> wordInSquares){
        ArrayList<TileLetter> wordInTiles = new ArrayList<>();
        for (SquareOnBoard square : wordInSquares){
            wordInTiles.add(square.getLetter());
        }
        for (TileLetter tile : wordInTiles){
            this.rack.remove(tile);
            if (!this.rack.contains(tile)){
                this.rack.remove(new TileLetter(" "));
            }
        }
    }

    /** Gets the list of high scores from the csv file and compiles them into an ArrayList of Players.
     * @return returns the values from the csv file in an ArrayList of Players.
     */
    static ArrayList<Player> getHighScoreList(){
        ArrayList<Player> highScoringPlayers = new ArrayList<>();
        try {
            File file = new File("Scrabble_High_Scores.csv");
            Scanner input = new Scanner(file);
            input.nextLine(); // header
            while (input.hasNextLine()){
                Scanner lineParser = new Scanner(input.nextLine());
                lineParser.next(); // player ranking
                String name = lineParser.next();
                int points = Integer.valueOf(lineParser.next());
                highScoringPlayers.add(new Player(name, points));
            }
            input.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return highScoringPlayers;
    }

    /** Gets the player with the highest number of points in an ArrayList of Players.
     * @param highScoreList represents the high score list from which the highest scoring player is being determined.
     * @return returns the Player with the highest score.
     */
    private static Player highestPoints(ArrayList<Player> highScoreList){
        Player highestRankedPlayer = highScoreList.get(0);
        for (Player player : highScoreList){
            if (player.points > highestRankedPlayer.points){
                highestRankedPlayer = player;
            }
        }
        return highestRankedPlayer;
    }

    /** Orders the high score list to be in order of highest scoring player to lowest scoring player.
     * @param highScoreList represents the high score list being ordered.
     * @param player represents the player being added to the list.
     * @return returns a new ArrayList of Players ordered from highest to lowest scores.
     */
    private static ArrayList<Player> adjustHighScoreList(ArrayList<Player> highScoreList, Player player){
        highScoreList.add(player);
        ArrayList<Player> sorted = new ArrayList<>();
        ArrayList<Player> highScoreListCopy = new ArrayList<>(highScoreList);
        while (sorted.size() < 10){
            sorted.add(highestPoints(highScoreListCopy));
            highScoreListCopy.remove(highestPoints(highScoreListCopy));
        }
        return sorted;
    }

    /** 
     * Creates/modifies a csv file containing the top 10 high scores obtained by players during the Scrabble game.
     */
    void updateHighScores(){
        ArrayList<Player> highScoreList = adjustHighScoreList(getHighScoreList(), this);
        try {
            File file = new File("Scrabble_High_Scores.csv");
            PrintWriter printer = new PrintWriter(file);
            printer.print("");
            printer.close();
            FileWriter writer = new FileWriter(file, true); // filename already exists will end up overwriting
            writer.write("Ranking\t\tName\tPoints\n");
            int ranking = 1;
            for (Player player : highScoreList){
                writer.write(String.format("%d.\t\t\t%s\t\t%d\n", ranking, player.getName(), player.getPoints()));
                ranking ++;
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        Player player = new Player("Josh", 2);
        player.updateHighScores();
    }
}