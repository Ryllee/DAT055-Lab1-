import javafx.scene.control.Cell;

import java.awt.event.*;
import java.awt.*;
import java.util.Iterator;
import javax.swing.*;

public class playGame implements ActionListener {
    GameGenerator generator;
    JFrame game;
    CellButton[] button = new CellButton[20];

    public int posForNull;
    public int rowForNull;
    public int colForNull;

    public playGame(int gameSize) {

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
        int pos = 1;

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
            button[pos] = new CellButton(i, pos);
            game.getContentPane().add(button[pos]);
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
    public void swapCellButton(CellButton cell, CellButton cell2) {
        CellButton temp = cell;
        cell.setText(cell2.getText());
        cell2.setText(temp.getText());
        //Save NULL button position
        if (cell.getText().equals("")) // sätter colForNull och rowForNull efter cells värden
        {
            posForNull = cell.getPosition();
            colForNull = cell.getColumn();
            rowForNull = cell.getRow();
        } else    // sätter colForNull och rowForNull efter cell2s värden
        {
            posForNull = cell2.getPosition();
            colForNull = cell2.getColumn();
            rowForNull = cell2.getRow();
        }
    }

   public void actionPerformed(ActionEvent ev) {


        CellButton tempCell = (CellButton) ev.getSource();
        int posForPressed = tempCell.getPosition();

        if (((button[posForPressed].getColumn() == colForNull + 1) || (button[posForPressed].getColumn() == colForNull - 1)) && (button[posForPressed].getRow() == rowForNull))
        {
            swapCellButton(button[posForPressed], button[posForNull]); // byter trycktknapp och nullbuttons texter
        }
        else if (((button[posForPressed].getRow() == rowForNull + 1) || (button[posForPressed].getRow() == rowForNull - 1)) && (button[posForPressed].getColumn() == colForNull))
        {
            swapCellButton(button[posForPressed], button[posForNull]); // byter tycktknapp och nullbuttons texter
        }
        else
            {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}

