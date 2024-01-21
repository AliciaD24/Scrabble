import java.util.ArrayList;

//   0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 
// 0     H
// 1     E L E G A N T
// 2     L
// 3     L
// 4     O C T O B E R
// 5                 A
// 6                 C
// 7                 I            T
// 8               K N I G H T    H
// 9                 G            I 
// 10        P A C T S            N
// 11              A              G
// 12        L     R T            S
// 13                  C G
// 14  T E S T  

class PlayingWordsTesting {

    private static Player player = new Player("Alicia", TileLetter.createFullBag());
    private static Board gameBoard = Board.initializeBoard();

    private static ArrayList<SquareOnBoard> HELLO = createTestWordInSquares("HELLO", 2, 0, false);
    private static ArrayList<SquareOnBoard> ELEGANT = createTestWordInSquares("ELEGANT", 2, 1, true);
    private static ArrayList<SquareOnBoard> OCTOBER = createTestWordInSquares("OCTOBER", 2, 4, true);
    private static ArrayList<SquareOnBoard> RACING = createTestWordInSquares("RACING", 8, 4, false);
    private static ArrayList<SquareOnBoard> KNIGHT = createTestWordInSquares("KNIGHT", 7, 8, true);
    private static ArrayList<SquareOnBoard> THINGS = createTestWordInSquares("THINGS", 13, 7, false);
    private static ArrayList<SquareOnBoard> PACTS = createTestWordInSquares("PACTS", 4, 10, true);
    private static ArrayList<SquareOnBoard> CAT = createCAT();
    private static ArrayList<SquareOnBoard> LRTCG = createLRTCG();
    private static ArrayList<SquareOnBoard> TEST = createTestWordInSquares("TEST", 1, 14, true);

    private static ArrayList<SquareOnBoard> createCAT(){
        ArrayList<SquareOnBoard> CAT = new ArrayList<>();

        SquareOnBoard c = new SquareOnBoard(new TileLetter("C"), 1, 1, 6, 10);
        SquareOnBoard a = new SquareOnBoard(new TileLetter("A"), 1, 2, 7, 11);
        SquareOnBoard t = new SquareOnBoard(new TileLetter("T"), 1, 2, 8, 12);
        CAT.add(c);
        CAT.add(a);
        CAT.add(t);
        return CAT;
    }

    private static ArrayList<SquareOnBoard> createLRTCG(){
        ArrayList<SquareOnBoard> LRTCG = new ArrayList<>();

        SquareOnBoard l = new SquareOnBoard(new TileLetter("L"), 0, 0, 4, 12);
        SquareOnBoard r = new SquareOnBoard(new TileLetter("R"), 0, 0, 7, 12);
        SquareOnBoard t = new SquareOnBoard(new TileLetter("T"), 0, 0, 8, 12);
        SquareOnBoard c = new SquareOnBoard(new TileLetter("C"), 0, 0, 9, 13);
        SquareOnBoard g = new SquareOnBoard(new TileLetter("G"), 0, 0, 10, 13);
        LRTCG.add(l);
        LRTCG.add(r);
        LRTCG.add(t);
        LRTCG.add(c);
        LRTCG.add(g);
        return LRTCG;
    }
    

    static PlayingWords hello = new PlayingWords(HELLO, gameBoard, player);
    static PlayingWords elegant = new PlayingWords(ELEGANT, gameBoard, player);
    static PlayingWords october = new PlayingWords(OCTOBER, gameBoard, player);
    static PlayingWords racing = new PlayingWords(RACING, gameBoard, player);
    static PlayingWords knight = new PlayingWords(KNIGHT, gameBoard, player);
    static PlayingWords things = new PlayingWords(THINGS, gameBoard, player);
    static PlayingWords pacts = new PlayingWords(PACTS, gameBoard, player);
    static PlayingWords cat = new PlayingWords(CAT, gameBoard, player);
    static PlayingWords lrtcg = new PlayingWords(LRTCG, gameBoard, player);
    static PlayingWords test = new PlayingWords(TEST, gameBoard, player);


    private static ArrayList<SquareOnBoard> createTestWordInSquares(String word, int startingX, int startingY, boolean right){ //if right is true the word moves from left to right instead of up to down
        Board blankBoard = Board.initializeBoard();
        int x = startingX;
        int y = startingY;
        int wordMult = 1;
        int letterMult = 1;
        ArrayList<SquareOnBoard> wordInSquares = new ArrayList<>();

        for (int i = 0; i < word.length(); i ++){
            wordMult = blankBoard.board[x][y].getWordMultiplier();
            letterMult = blankBoard.board[x][y].getLetterMultiplier();
            SquareOnBoard square = new SquareOnBoard(new TileLetter(word.substring(i, i+1)), wordMult, letterMult, x, y);
            wordInSquares.add(square);
            if (right)
                x ++;
            else {
                y ++;
            }
        }
        return wordInSquares;
    }

    private static void resetValidity(){
        hello.setValidity(true);
        elegant.setValidity(true);
        october.setValidity(true);
        racing.setValidity(true);
        knight.setValidity(true);
        things.setValidity(true);
        pacts.setValidity(true);
        cat.setValidity(true);
        lrtcg.setValidity(true);
        test.setValidity(true);
    }

    //TESTBOARD SHOULD BE DELETED WHEN IM FINISHED
    static Board testBoard(){
        Board newBoard = Board.initializeBoard();
        Board.addWordToBoard(newBoard, HELLO);
        Board.addWordToBoard(newBoard, ELEGANT);
        Board.addWordToBoard(newBoard, OCTOBER);
        Board.addWordToBoard(newBoard, RACING);
        Board.addWordToBoard(newBoard, KNIGHT);
        Board.addWordToBoard(newBoard, THINGS);
        Board.addWordToBoard(newBoard, PACTS);
        Board.addWordToBoard(newBoard, CAT);
        Board.addWordToBoard(newBoard, LRTCG);
        Board.addWordToBoard(newBoard, TEST);

        return newBoard;
    }
    
    static void testCheckRack(){
        resetValidity();
        ArrayList<TileLetter> rack = new ArrayList<>();
        player.setRack(rack);
        rack.add(new TileLetter("H"));
        rack.add(new TileLetter("E"));
        rack.add(new TileLetter("L"));
        rack.add(new TileLetter("L"));
        rack.add(new TileLetter("O"));
        rack.add(new TileLetter("P"));
        rack.add(new TileLetter("T"));
        rack.add(new TileLetter("C"));
        rack.add(new TileLetter("A"));
        rack.add(new TileLetter("S"));
        
        hello.checkRack();
        System.out.println(hello.getValidity()); // true
        cat.checkRack();
        System.out.println(cat.getValidity()); // true
        test.checkRack();
        System.out.println(test.getValidity()); // false
        pacts.checkRack();
        System.out.println(pacts.getValidity()); // true
        elegant.checkRack();
        System.out.println(elegant.getValidity()); // false
    }

    static void testCheckAllRLUP(){
        resetValidity();
        Board testBoard = Board.initializeBoard();
        Board.addWordToBoard(testBoard, HELLO);
        Board.addWordToBoard(testBoard, OCTOBER);
        Board.addWordToBoard(testBoard, PACTS);
        Board.addWordToBoard(testBoard, CAT);
        Board.addWordToBoard(testBoard, LRTCG);

        test.setWordBoard(testBoard);
        test.checkAllRLUP();
        System.out.println(test.getValidity()); // false
        racing.setWordBoard(testBoard);
        racing.checkAllRLUP();
        System.out.println(racing.getValidity()); // true
        knight.setWordBoard(testBoard);
        knight.checkAllRLUP();
        System.out.println(knight.getValidity()); // false
        elegant.setWordBoard(testBoard);
        elegant.checkAllRLUP(); 
        System.out.println(elegant.getValidity()); // true
        things.setWordBoard(testBoard);
        things.checkAllRLUP();
        System.out.println(things.getValidity()); // false
    }

    static void testCheckLettersInRow(){
        resetValidity();
        racing.checkLettersInRow();
        System.out.println(racing.getValidity()); // true
        things.checkLettersInRow();
        System.out.println(things.getValidity()); // true
        cat.checkLettersInRow();
        System.out.println(cat.getValidity()); // false
        hello.checkLettersInRow();
        System.out.println(hello.getValidity()); // true
        lrtcg.checkLettersInRow();
        System.out.println(lrtcg.getValidity()); // false
    }

    static void testValidateBoard(){
        resetValidity();
        Board newBoard = Board.initializeBoard();
        Board.addWordToBoard(newBoard, HELLO);
        hello.setWordBoard(newBoard);
        hello.validateBoard();
        System.out.println(hello.getValidity()); // true

        Board.addWordToBoard(newBoard, ELEGANT);
        elegant.setWordBoard(newBoard);
        elegant.validateBoard();
        System.out.println(elegant.getValidity()); // true

        Board.addWordToBoard(newBoard, OCTOBER);
        october.setWordBoard(newBoard);
        october.validateBoard();
        System.out.println(october.getValidity()); // true

        Board falseBoard = newBoard.clone();
        System.out.println(falseBoard.equals(newBoard) + "hrejwkjhrwkh");

        Board.addWordToBoard(falseBoard, RACING); 
        racing.setWordBoard(falseBoard);
        racing.validateBoard();
        System.out.println(racing.getValidity()); // false (racing is not in the words txt file)
        
        //Board.removeWordFromBoard(newBoard, RACING);
        System.out.println(newBoard);
        Board.addWordToBoard(newBoard, KNIGHT);
        knight.setWordBoard(newBoard);
        knight.validateBoard();
        System.out.println(knight.getValidity()); // true

        Board.addWordToBoard(newBoard, THINGS);
        things.setWordBoard(newBoard);
        things.validateBoard();
        System.out.println(things.getValidity()); // true

        Board.addWordToBoard(newBoard, PACTS);
        pacts.setWordBoard(newBoard);
        pacts.validateBoard();
        System.out.println(pacts.getValidity()); // true

        Board.addWordToBoard(newBoard, CAT);
        cat.setWordBoard(newBoard);
        cat.validateBoard();
        System.out.println(cat.getValidity()); // true

        Board.addWordToBoard(newBoard, LRTCG);
        lrtcg.setWordBoard(newBoard);
        lrtcg.validateBoard();
        System.out.println(lrtcg.getValidity()); // true

        Board.addWordToBoard(newBoard, TEST);
        test.setWordBoard(newBoard);
        test.validateBoard();
        System.out.println(test.getValidity()); // true
    }

    public static void main(String[] args) {
        //testCheckRack();
        //testCheckAllRLUP();
        //testCheckLettersInRow();
        testValidateBoard();
    }
}
