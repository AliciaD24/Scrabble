import java.util.ArrayList;

class Player{
    String name;
    int points;
    ArrayList<TileLetter> rack;

    Player(String name, ArrayList<TileLetter> bag){
        this.name = name;
        this.points = 0;
        this.rack = generateFullRack(bag);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<TileLetter> getRack() {
        return rack;
    }

    public void setRack(ArrayList<TileLetter> rack) {
        this.rack = rack;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + points;
        result = prime * result + ((rack == null) ? 0 : rack.hashCode());
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

    @Override
    public String toString() {
        return "Player [name=" + name + ", points=" + points + ", rack=" + rack + "]";
    }

    private static ArrayList<TileLetter> generateFullRack(ArrayList<TileLetter> bag){
        int letterIndex = 0;
        ArrayList<TileLetter> rack = new ArrayList<>();
        while (rack.size() < 7){
            letterIndex = (int) (Math.random() * (bag.size() - 1) + 1);
            rack.add(bag.get(letterIndex));
            bag.remove(bag.get(letterIndex));
        }
        return rack;
    }

    static String getLetterFromPlayer(String letter){
        return letter;
    }

    static int[] getCoordinatesFromPlayer(int[] coordinates){
        return coordinates;
    }

    private static SquareOnBoard getLetterAndLocation(String getLetterFromPlayer, int[] getCoordinatesFromPlayer){
        TileLetter letter = new TileLetter(getLetterFromPlayer);
        Board gameBoard = Board.initializeBoard();
        int x = getCoordinatesFromPlayer[0];
        int y = getCoordinatesFromPlayer[1];
        SquareOnBoard square = gameBoard.board[x][y];
        int letterMult = square.getLetterMultiplier();
        int wordMult = square.getWordMultiplier();
        return new SquareOnBoard(letter, wordMult, letterMult, x, y);
    }

    private static void exchangeTiles(Player player, ArrayList<TileLetter> currentBag){
        ArrayList<TileLetter> newRack = generateFullRack(currentBag);
        for (TileLetter oldTile : player.getRack()){
            currentBag.add(oldTile);
            player.rack.remove(oldTile);
        }
        player.setRack(newRack);
    }

    public static void main(String[] args) {
        ArrayList<TileLetter> bag = TileLetter.createFullBag();
    }
}