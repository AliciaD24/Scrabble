import javax.swing.*;
import java.awt.*;

class LeftSide extends JPanel{
    JLabel[] labels;

    LeftSide(){
        super(new GridLayout(14, 1));
        labels = makeLeftSide();
        addLabelsToPanel();
        labelFormatting(labels);
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    static JLabel[] makeLeftSide(){
        JLabel[] labels = new JLabel[14];
        labels[0] = new JLabel("Current Player: " + ScrabbleGUI.currentPlayer.getName(), SwingConstants.CENTER);
        labels[1] = new JLabel(ScrabbleGUI.player1.getName() + "'s points: " + ScrabbleGUI.player1.getPoints(), SwingConstants.CENTER);
        labels[2] = new JLabel(ScrabbleGUI.player2.getName() + "'s points: " + ScrabbleGUI.player2.getPoints(), SwingConstants.CENTER);
        labels[3] = new JLabel("Letters left in bag: " + ScrabbleGUI.gameBag.size(), SwingConstants.CENTER);
        labels[4] = new JLabel();
        labels[5] = new JLabel("Letter Point Values", SwingConstants.CENTER);
        labels[6] = new JLabel("1 Point: A E I O U L N S T R", SwingConstants.CENTER);
        labels[7] = new JLabel("2 Point: D G ", SwingConstants.CENTER);
        labels[8] = new JLabel("3 Point: B C M P ", SwingConstants.CENTER);
        labels[9] = new JLabel("4 Point: F H V W Y ", SwingConstants.CENTER);
        labels[10] = new JLabel("5 Point: K ", SwingConstants.CENTER);
        labels[11] = new JLabel("8 Point: J X ", SwingConstants.CENTER);
        labels[12] = new JLabel("10 Point: Q Z ", SwingConstants.CENTER);
        labels[13] = new JLabel();

        return labels;
    }

    static void labelFormatting(JLabel[] labels){
        Font labelFont = new Font(Font.MONOSPACED, Font.PLAIN, 15);
        Font boldFont = new Font(Font.SANS_SERIF, Font.BOLD,  20);

        for (int i = 0; i < labels.length; i ++){
            labels[i].setFont(labelFont);
        }
        labels[0].setFont(boldFont);
        labels[5].setFont(boldFont);

    }

    private void addLabelsToPanel(){
        for (int i = 0; i < labels.length; i ++){
            add(labels[i]);
        }
    }

}