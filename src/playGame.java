import javafx.scene.control.Cell;

import java.awt.event.*;
import java.awt.*;
import java.util.Iterator;
import javax.swing.*;

public class playGame implements ActionListener
{
    GameGenerator generator;
    JFrame game;
    public int posForNull;
    public int rowForNull;
    public int colForNull;

    public playGame(int gameSize)
    {

        makeFrame(gameSize);
    }
    /**
     * makeFrame create a Jframe with the right amount of buttons
     *
     * @param gameSize puts the game size for the game:
     *                 example 4 if playfield is 16(4x4)
     *
     */
    public void makeFrame( int gameSize)
    {
        generator = new GameGenerator(gameSize);
        game = new JFrame("The 15 game");
        game.setLayout(new GridLayout(gameSize, gameSize, 3, 3));
        game.setSize(400, 400);
        int pos = 1;

        for (int i : generator)
        {
            if(i == 0) // Save "null" buttons position
            {
                posForNull = pos;
                colForNull = pos % 4;
                if (colForNull == 0)
                {
                    colForNull = 4;
                }
                rowForNull = pos / 4;
                rowForNull = rowForNull + 1;
                if (pos % 4 == 0)
                {
                    rowForNull = pos/ 4;
                }

            }
            CellButton button = new CellButton(i, pos);
            button.addCellButton(game);
            pos++;
        }

        game.setVisible(true);
        game.addWindowListener(new WindowAdapter()
        { public void windowClosing(WindowEvent e)
        { System.exit(0); }
        });
    }

    /**
     * swapCellButton swaps the cells text with another cells text
     *
     * @param cell  Cell nr 1
     * @param cell2 Cell nr 2
     *
     */
    public void swapCellButton(CellButton cell,CellButton cell2)
    {
        CellButton temp = cell;
        cell.setText(cell2.getText());
        cell2.setText(temp.getText());
        //Save NULL button position
        if(cell.getText().equals("")) // sätter colForNull och rowForNull efter cells värden
        {
            colForNull = cell.getColumn();
            rowForNull = cell.getRow();
        }
        else    // sätter colForNull och rowForNull efter cell2s värden
        {
            colForNull = cell2.getColumn();
            rowForNull = cell2.getRow();
        }
    }
    /*
    public CellButton NullButtonSearch()
    {
        Iterator<Integer> genIt =  generator.iterator();

    }*/

    public void actionPerformed(ActionEvent ev)
    {


        CellButton tempCell = (CellButton)ev.getSource();
        if(((tempCell.getColumn() == colForNull + 1) || (tempCell.getColumn() == colForNull -1)) && (tempCell.getRow() == rowForNull) )
        {
            swapCellButton(tempCell,tempCell); // byt sista tempcell till NULL button
        }
        else if(((tempCell.getRow() == rowForNull + 1)|| (tempCell.getRow() == rowForNull -1)) && (tempCell.getColumn() == colForNull))
        {
            swapCellButton(tempCell,tempCell); // byt sista tempcell till NULL button
        }
        else
        {
            Toolkit.getDefaultToolkit().beep();
        }
