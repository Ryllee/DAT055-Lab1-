import javafx.scene.control.Cell;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

public class playGame implements ActionListener {
    GameGenerator generator;
    JFrame game;
    ArrayList<CellButton> list;

    public int posForNull;
    public int rowForNull;
    public int colForNull;

    public playGame(int gameSize) {
        list = new ArrayList<>();
        makeFrame(gameSize);

    }

    /**
     * makeFrame create a Jframe with the right amount of buttons
     *
     * @param gameSize puts the game size for the game:
     *                 example 4 if playfield is 16(4x4)
     */
    public void makeFrame(int gameSize) {
        generator = new GameGenerator(gameSize);
        game = new JFrame("The 15 game");
        game.setLayout(new GridLayout(gameSize, gameSize, 3, 3));
        game.setSize(400, 400);
        int pos = 0;

        for (int i : generator)
        {
            if (i == 0) // Save "null" buttons position and Column/Row
            {
                posForNull = pos;
                colForNull = pos % 4;
                if (colForNull == 0) {
                    colForNull = 4;
                }
                rowForNull = pos / 4;
                rowForNull = rowForNull + 1;
                if (pos % 4 == 0) {
                    rowForNull = pos / 4;
                }

            }
            list.add(pos,new CellButton(i, pos));
            list.get(pos).addActionListener(this);
            game.getContentPane().add(list.get(pos));

            pos++;
        }

        game.setVisible(true);

        game.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * swapCellButton swaps the cells text with another cells text
     *
     * @param cell  Cell nr 1
     * @param cell2 Cell nr 2
     */
    public void swapCellButton(CellButton cell, CellButton cell2)
    {
        int temp = cell.getNumber();
        System.out.println("test2");
        cell.setNumber(cell2.getNumber());
        System.out.println("test3");
        cell2.setNumber(temp);
        //Save NULL button position
        if (cell.getNumber() == 0) // sätter colForNull och rowForNull efter cells värden
        {
            posForNull = cell.getPosition();
            colForNull = cell.getColumn();
            rowForNull = cell.getRow();
        }
        else    // sätter colForNull och rowForNull efter cell2s värden
        {
            posForNull = cell2.getPosition();
            colForNull = cell2.getColumn();
            rowForNull = cell2.getRow();
        }
        relabel(cell);
        relabel(cell2);
        System.out.println("test4");
    }

    public void relabel(CellButton cell)
    {
        if(cell.getNumber() == 0)
        {
            System.out.println("test1");
            cell.setText("");
        }
        else
        {
            System.out.println("test");
            cell.setText(String.valueOf(cell.getNumber()));
        }
    }

   public void actionPerformed(ActionEvent ev) {


        CellButton tempCell = (CellButton)ev.getSource();


        if (((tempCell.getColumn()== (colForNull + 1)) || (tempCell.getColumn() == (colForNull - 1))) && (tempCell.getRow() == rowForNull))
        {
            swapCellButton(tempCell, list.get(posForNull)); // byter trycktknapp och nullbuttons texter
        }
        else if (((tempCell.getRow() == (rowForNull + 1)) || (tempCell.getRow() == (rowForNull - 1)) && (tempCell.getColumn() == colForNull)))
        {
            swapCellButton(tempCell, list.get(posForNull)); // byter tycktknapp och nullbuttons texter
        }
        else
            {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}

