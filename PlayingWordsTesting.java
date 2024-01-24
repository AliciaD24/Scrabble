import java.util.ArrayList;


//   0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 
// 0     H
// 1     E L E G A N T
// 2     L
// 3     L
// 4     O C T O B E R
// 5                 A
// 6     A T T     A C H
// 7                 I            T
// 8  B            K N I G H T    H
// 9  L              G            I 
// 10 A      P A C T S      E V A N S
// 11 N            A              G
// 12 _      L     R T            S
// 13                  C G
// 14  T E S T  

class PlayingWordsTesting {

    private static Player player = new Player("Testing", TileLetter.createFullBag());
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
    private static ArrayList<SquareOnBoard> LET = createLET();
    private static ArrayList<SquareOnBoard> NOW = createTestWordInSquares("NOW", 8, 14, true);
    private static ArrayList<SquareOnBoard> ATTACH = createATTACH();
    private static ArrayList<SquareOnBoard> EVANS = createTestWordInSquares("EVANS", 10, 10, true);
    private static ArrayList<SquareOnBoard> BLANK = createTestWordInSquares("BLANK", 0, 8, false);

    private static ArrayList<SquareOnBoard> createATTACH(){
        ArrayList<SquareOnBoard> ATTACH = new ArrayList<>();

        SquareOnBoard a = new SquareOnBoard(new TileLetter("A"), 1, 1, 4, 2);
        SquareOnBoard t = new SquareOnBoard(new TileLetter("T"), 1, 1, 4, 3);
        SquareOnBoard t2 = new SquareOnBoard(new TileLetter("T"), 1, 1,4, 4);
        SquareOnBoard a2 = new SquareOnBoard(new TileLetter("A"), 2, 1, 4, 7);
        SquareOnBoard h = new SquareOnBoard(new TileLetter("H"), 1, 2, 4, 8);

        ATTACH.add(a);
        ATTACH.add(t);
        ATTACH.add(t2);
        ATTACH.add(a2);
        ATTACH.add(h);
        return ATTACH;
    }

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

    private static ArrayList<SquareOnBoard> createLET(){
        ArrayList<SquareOnBoard> LET = new ArrayList<>();
        LET.add(new SquareOnBoard(new TileLetter("E"), 1, 1, 4, 13));
        return LET;
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
    static PlayingWords let = new PlayingWords(LET, gameBoard, player);
    static PlayingWords now = new PlayingWords(NOW, gameBoard, player);
    static PlayingWords attach = new PlayingWords(ATTACH, gameBoard, player);
    static PlayingWords evans = new PlayingWords(EVANS, gameBoard, player);
    static PlayingWords blank = new PlayingWords(BLANK, gameBoard, player);

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
        hello.setValid(true);
        elegant.setValid(true);
        october.setValid(true);
        racing.setValid(true);
        knight.setValid(true);
        things.setValid(true);
        pacts.setValid(true);
        cat.setValid(true);
        lrtcg.setValid(true);
        test.setValid(true);
        let.setValid(true);
        now.setValid(true);
        attach.setValid(true);
        evans.setValid(true);
    }

    static void testGenerateWordList(){
        System.out.println(PlayingWords.generateWordList()); // 25463 words
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
        rack.add(new TileLetter("B"));
        rack.add(new TileLetter("N"));
        rack.add(new TileLetter(" "));
        
        hello.checkRack();
        System.out.println(hello.getIsValid()); // true
        cat.checkRack();
        System.out.println(cat.getIsValid()); // true
        knight.checkRack();
        System.out.println(knight.getIsValid()); // false
        pacts.checkRack();
        System.out.println(pacts.getIsValid()); // true
        elegant.checkRack();
        System.out.println(elegant.getIsValid()); // false
        blank.checkRack();
        System.out.println(blank.getIsValid());
    }

    static void testCheckOnTopOfExisting(){
        Board testBoard = Board.initializeBoard();
        Board.addWordToBoard(testBoard, HELLO);
        Board.addWordToBoard(testBoard, OCTOBER);
        Board.addWordToBoard(testBoard, PACTS);
        Board.addWordToBoard(testBoard, CAT);
        Board.addWordToBoard(testBoard, LRTCG);

        test.setOldBoard(testBoard);
        test.checkOnTopOfExisting();
        System.out.println(test.getIsValid()); // true
        racing.setOldBoard(testBoard);
        racing.checkOnTopOfExisting();
        System.out.println(racing.getIsValid()); // false
        knight.setOldBoard(testBoard);
        knight.checkOnTopOfExisting();
        System.out.println(knight.getIsValid()); // true
        elegant.setOldBoard(testBoard);
        elegant.checkOnTopOfExisting(); 
        System.out.println(elegant.getIsValid()); // false
        things.setOldBoard(testBoard);
        things.checkOnTopOfExisting();
        System.out.println(things.getIsValid()); // true
        blank.setOldBoard(testBoard);
        blank.checkOnTopOfExisting();
        System.out.println(blank.getIsValid()); // true

    }

    static void testCheckAllRLUP(){
        resetValidity();
        Board testBoard = Board.initializeBoard();
        Board.addWordToBoard(testBoard, HELLO);
        Board.addWordToBoard(testBoard, OCTOBER);
        Board.addWordToBoard(testBoard, PACTS);
        Board.addWordToBoard(testBoard, CAT);
        Board.addWordToBoard(testBoard, LRTCG);

        test.setOldBoard(testBoard);
        test.checkAllRLUP();
        System.out.println(test.getIsValid()); // false
        racing.setOldBoard(testBoard);
        racing.checkAllRLUP();
        System.out.println(racing.getIsValid()); // true
        knight.setOldBoard(testBoard);
        knight.checkAllRLUP();
        System.out.println(knight.getIsValid()); // false
        elegant.setOldBoard(testBoard);
        elegant.checkAllRLUP(); 
        System.out.println(elegant.getIsValid()); // true
        things.setOldBoard(testBoard);
        things.checkAllRLUP();
        System.out.println(things.getIsValid()); // false
        blank.setOldBoard(testBoard);
        blank.checkAllRLUP();
        System.out.println(blank.getIsValid()); // false
    }

    static void testCheckLettersInRow(){
        resetValidity();
        racing.checkLettersInRow();
        System.out.println(racing.getIsValid()); // true
        things.checkLettersInRow();
        System.out.println(things.getIsValid()); // true
        cat.checkLettersInRow();
        System.out.println(cat.getIsValid()); // false
        hello.checkLettersInRow();
        System.out.println(hello.getIsValid()); // true
        lrtcg.checkLettersInRow();
        System.out.println(lrtcg.getIsValid()); // false
        let.checkLettersInRow();
        System.out.println(let.getIsValid()); // true
        attach.checkLettersInRow();
        System.out.println(attach.getIsValid()); // false (Same row but not in consecutive order)
        evans.checkLettersInRow();
        System.out.println(evans.getIsValid()); // true


    }

    static void testValidateBoard(){
        resetValidity();
        Board newBoard = Board.initializeBoard();
        Board.addWordToBoard(newBoard, HELLO);
        hello.setNewBoard(newBoard);
        hello.validateBoard();
        System.out.println(hello.getIsValid()); // true

        Board.addWordToBoard(newBoard, ELEGANT);
        elegant.setNewBoard(newBoard);
        elegant.validateBoard();
        System.out.println(elegant.getIsValid()); // true

        Board.addWordToBoard(newBoard, OCTOBER);
        october.setNewBoard(newBoard);
        october.validateBoard();
        System.out.println(october.getIsValid()); // true

        Board falseBoard = newBoard.clone();
        Board.addWordToBoard(falseBoard, RACING); 
        racing.setNewBoard(falseBoard);
        racing.validateBoard();
        System.out.println(racing.getIsValid()); // false (racing is not in the words.txt file)
        
        Board.addWordToBoard(newBoard, KNIGHT);
        knight.setNewBoard(newBoard);
        knight.validateBoard();
        System.out.println(knight.getIsValid()); // true

        falseBoard = newBoard.clone();
        Board.addWordToBoard(falseBoard, THINGS);
        things.setNewBoard(falseBoard);
        things.validateBoard();
        System.out.println(things.getIsValid()); // false (things is not in the words.txt file)

        falseBoard = newBoard.clone();
        Board.addWordToBoard(falseBoard, PACTS);
        pacts.setNewBoard(falseBoard);
        pacts.validateBoard();
        System.out.println(pacts.getIsValid()); // false (pacts is not in the words.txt file)

        Board.addWordToBoard(newBoard, CAT);
        cat.setNewBoard(newBoard);
        cat.validateBoard();
        System.out.println(cat.getIsValid()); // true

        falseBoard = newBoard.clone();
        Board.addWordToBoard(falseBoard, LRTCG);
        lrtcg.setNewBoard(falseBoard);
        lrtcg.validateBoard();
        System.out.println(lrtcg.getIsValid()); // false (Split on multiple lines and not a word)

        Board.addWordToBoard(newBoard, TEST);
        test.setNewBoard(newBoard);
        test.validateBoard();
        System.out.println(test.getIsValid()); // true

        Board.addWordToBoard(newBoard, NOW);
        now.setNewBoard(newBoard);
        now.validateBoard();
        System.out.println(now.getIsValid()); // true
    }

    static void testCountPoints(){
        resetValidity();
        Board newBoard = Board.initializeBoard();
        Board oldBoard = newBoard.clone();
        Board.addWordToBoard(newBoard, HELLO);
        hello.setNewBoard(newBoard);
        hello.setOldBoard(oldBoard);
        System.out.println(hello.countPoints()); // 16

        oldBoard = newBoard.clone();
        Board.addWordToBoard(newBoard, OCTOBER);
        october.setNewBoard(newBoard);
        october.setOldBoard(oldBoard);
        System.out.println(october.countPoints()); // 72 (With 50 point bonus for all 7 letters)

        oldBoard = newBoard.clone();
        Board.addWordToBoard(newBoard, RACING);
        racing.setNewBoard(newBoard);
        racing.setOldBoard(oldBoard);
        System.out.println(racing.countPoints()); // 13

        oldBoard = newBoard.clone();
        Board.addWordToBoard(newBoard, PACTS);
        pacts.setNewBoard(newBoard);
        pacts.setOldBoard(oldBoard);
        System.out.println(pacts.countPoints()); // 28

        oldBoard = newBoard.clone();
        Board.addWordToBoard(newBoard, THINGS);
        things.setNewBoard(newBoard);
        things.setOldBoard(oldBoard);
        System.out.println(things.countPoints()); // 12

        oldBoard = newBoard.clone();
        Board.addWordToBoard(newBoard, KNIGHT);
        knight.setNewBoard(newBoard);
        knight.setOldBoard(oldBoard);
        System.out.println(knight.countPoints()); // 20

        oldBoard = newBoard.clone();
        Board.addWordToBoard(newBoard, NOW);
        now.setNewBoard(newBoard);
        now.setOldBoard(oldBoard);
        System.out.println(now.countPoints()); // 6
    }

    public static void main(String[] args) {
        testGenerateWordList();
        testCheckOnTopOfExisting();
        testCheckRack();
        testCheckAllRLUP();
        testCheckLettersInRow();
        testValidateBoard();
        testCountPoints();
    }
}
