import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

class RackOfButtons extends JPanel{
    JButton[] rackButtons;

    RackOfButtons(){
        super(new GridLayout(1, 7, 20, 0));
        rackButtons = makeRack();
        addButtonsToPanel();
        addActionListenersRack();
    }

    public JButton[] getRackButtons() {
        return rackButtons;
    }

    public void setRackButtons(JButton[] rackButtons) {
        this.rackButtons = rackButtons;
    }

    static JButton[] makeRack(){
        Font rackButtonFont = new Font(Font.SERIF, Font.BOLD, 30);
        Border buttonBorder = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED, Color.decode("#ffffff"), Color.decode("#ffffff"), Color.decode("#33312d"), Color.decode("#33312d"));
        JButton[] buttons = new JButton[7];
        for (int i = 0; i < 7; i ++){
            buttons[i] = new JButton();
            buttons[i].setFont(rackButtonFont);
            buttons[i].setBorder(buttonBorder);
            buttons[i].setOpaque(true);
            buttons[i].setBackground(Color.decode("#fffdf9"));
        }
        return buttons;
    }

    private void addButtonsToPanel(){
        for (int i = 0; i < 7; i ++){
            add(rackButtons[i]);
        }
    }

    void getLetter(int i){
        TileLetter tile = new TileLetter(rackButtons[i].getText());
        if (tile.getLetter().equals(" ")){
            while (tile.getLetter().equals(" ")){
                String blankTileLetter = JOptionPane.showInputDialog("What letter would you like your blank tile to represent?");
                try{
                    tile.setLetter(blankTileLetter);
                    if (!blankTileLetter.matches("[a-zA-Z]")){
                        throw new IllegalArgumentException();
                    }
                }
                catch (IllegalArgumentException e){
                    JOptionPane.showMessageDialog(ScrabbleGUI.screen, "Must be one letter only", "Blank Tile Error Message", 0);
                }
            }
        }
        ScrabbleGUI.currentWordInSquares.add(new SquareOnBoard(tile, 0, 0, 0, 0));
        RackOfButtons.disableRackOfButtons();    
        BoardOfButtons.enableGridOfButtonsOnBoard();    
    }

    void addActionListenersRack(){
        for (int i = 0; i < 7; i ++){
            int index = i;
            rackButtons[i].addActionListener(e -> getLetter(index));
        }
    }

    static void enableRackOfButtons(){
        for (int i = 0; i < 7; i ++){
            ScrabbleGUI.screen.rack.rackButtons[i].setEnabled(true);
        }
    }

    static void disableRackOfButtons(){
        for (int i = 0; i < 7; i ++){
            ScrabbleGUI.screen.rack.rackButtons[i].setEnabled(false);
        }
    }

}
