import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Header extends JPanel{
    JButton highscores = new JButton("High Scores");
    JLabel label = new JLabel("Welcome to Scrabble!", SwingConstants.CENTER);
    JButton rules = new JButton("Rules");
    JButton instructions = new JButton("Instructions");

    Header(){
        super(new FlowLayout());
        addComponentsToPanel();
        addActionListenersToMenu();
    }

    private void addComponentsToPanel(){
        Font labelFont = new Font(Font.MONOSPACED, Font.PLAIN, 15);
        highscores.setFont(labelFont);
        add(highscores);
        add(new JLabel("                                      "));
        Font mono = new Font(Font.MONOSPACED, Font.BOLD, 40);
        label.setFont(mono);
        add(label);
        rules.setFont(labelFont);
        add(new JLabel("                                  "));
        add(rules);
        instructions.setFont(labelFont);
        add(instructions);
    }

    void addActionListenersToMenu(){
        highscores.addActionListener(e -> openHighScores());
        rules.addActionListener(e -> openRules());
        instructions.addActionListener(e -> openInstructions());
    }

    void openHighScores(){
        ArrayList<Player> highScoreListFromCSV = Player.getHighScoreList();
        String highScoresString = "HIGH SCORES\n\n";
        int i = 1;
        for (Player player : highScoreListFromCSV){
            highScoresString += i+". ";
            highScoresString += player.getName() + ": ";
            highScoresString += player.getPoints() + " points\n";
            i ++;
        }
        JOptionPane.showMessageDialog(ScrabbleGUI.screen, highScoresString, "High Scores", 1);
    }

    void openRules(){
        String rules = "RULES\n\nWhen the game begins, the first player will place their word on the tile in the centre of the board.\nThe star is a double square and will offer a double word score.\nAll players following will build their words off of this word, extending the game to other squares on the board.\nOnce tiles are played on the board, players will draw new tiles to replace those.\nPlayers will always have seven tiles during the game. This will happen automatically.\nExciting rewards can come when players use all seven tiles to create a word on the board.\nWhen this happens, players will receive a 50 point bonus, in addition to the value of the word.\nOnce all tiles are gone from the bag, the game will end and the player with the highest score wins.\n The game can also end if a player passes twice in a row, or exchanges their letters twice in a row.\nHappy Scrabbling!";
        JOptionPane.showMessageDialog(ScrabbleGUI.screen, rules, "Rules", 1);
    }

    void openInstructions(){
        String instructions = "INSTRUCTIONS\n\nTo start the game click 'Start Game' at the top right and enter a name for player 1 and 2 when prompted.\nWhen placing letters click 'Place Letters' which will enable your rack at the bottom of the screen.\nSelect the letter you would like to play and then select the coordinate on the grid you would like to play it to.\nRepeat selecting a letter followed by a square on the grid until you have placed all the letters you want on the board, then click 'Confirm Letters'.\nIf your play is valid, your turn will be over.\nOtherwise, click 'Place Letters' and try again or Exchange your tiles/pass your turn.\nExchanging your tiles will exchange all 7 of your tiles for new ones.\nPassing your turn will simply switch to the next player's rack and it will be their turn.\nThe current player is always visible at the top left with each player's points right underneath.\nTo end the game before the bag has run out of letters, click 'End Game' to see the winner.\nThe scores will be added to the high score list (if they're high enough) and the high score list will be updated.\nHappy Scrabbling!";
        JOptionPane.showMessageDialog(ScrabbleGUI.screen, instructions, "Instructions", 1);
    }


}
