import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;


class ScreenLayout extends JPanel{
    private Header header = new Header(); 
    BoardOfButtons grid = new BoardOfButtons();
    LeftSide left = new LeftSide();
    RackOfButtons rack = new RackOfButtons();
    private TurnOptions options = new TurnOptions();

    ScreenLayout(){
        super(new BorderLayout());
        Border rackBorder = BorderFactory.createEmptyBorder(20, 300, 20, 150);
        
        add(header, BorderLayout.PAGE_START);
        add(grid, BorderLayout.CENTER);
        add(left, BorderLayout.LINE_START);
        add(rack, BorderLayout.PAGE_END);
        add(options, BorderLayout.LINE_END);
        header.setPreferredSize(new Dimension(1350, 70));
        left.setPreferredSize(new Dimension(300, 0));
        rack.setPreferredSize(new Dimension(0, 120));
        rack.setBorder(rackBorder);
        options.setPreferredSize(new Dimension(150, 0));
        grid.setPreferredSize(new Dimension(900, 900));
    }


    public Header getHeader() {
        return header;
    }


    public void setHeader(Header header) {
        this.header = header;
    }


    public BoardOfButtons getGrid() {
        return grid;
    }


    public void setGrid(BoardOfButtons grid) {
        this.grid = grid;
    }


    public LeftSide getLeft() {
        return left;
    }


    public void setLeft(LeftSide left) {
        this.left = left;
    }


    public RackOfButtons getRack() {
        return rack;
    }


    public void setRack(RackOfButtons rack) {
        this.rack = rack;
    }


    public TurnOptions getOptions() {
        return options;
    }


    public void setOptions(TurnOptions options) {
        this.options = options;
    }
}
