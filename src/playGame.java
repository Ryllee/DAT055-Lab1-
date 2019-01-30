

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
    public int gameSize;
    public int howmanymoves = 0;

    public playGame(int gameSize) {
        list = new ArrayList<>();
        makeFrame(gameSize);
        this.gameSize = gameSize;
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

            list.add(pos,new CellButton(i, pos,gameSize));
            list.get(pos).addActionListener(this);
            game.getContentPane().add(list.get(pos));
            if (i == 0) // Save "null" buttons position and Column/Row
            {
                setNullvalues(list.get(pos));
            }
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
        cell.setNumber(cell2.getNumber());
        cell2.setNumber(temp);
        //Save NULL button position
        if (cell.getNumber() == 0) // sätter colForNull och rowForNull efter cells värden
        {
           setNullvalues(cell);
        }
        else    // sätter colForNull och rowForNull efter cell2s värden
        {
            setNullvalues(cell2);
        }
        relabel(cell);
        relabel(cell2);
    }

    /**
     * relabel
     * Used to update the number on a specific cell(button)
     *
     * @param cell specify the specific cell
     */
    public void relabel(CellButton cell)
    {
        if(cell.getNumber() == 0)
        {
            cell.setText("");
        }
        else
        {
            cell.setText(String.valueOf(cell.getNumber()));
        }
    }

    /**
     * setNullvalues
     * puts a cell(button) information in three ints,
     * position -> posForNull
     * column -> colForNull
     * row -> rowForNull
     * @param cell specify the "Null" cell that should have its values saved.
     */
    public void setNullvalues(CellButton cell)
    {
        posForNull = cell.getPosition();
        colForNull = cell.getColumn();
        rowForNull = cell.getRow();
    }

    public boolean numRightorder()
    {
        boolean rightOrder = false;
        int lastnum = 0;

      for (CellButton cell : list)
      {
          if(lastnum < cell.getNumber() && cell.getNumber() != 0)
          {
              rightOrder = true;
              lastnum = cell.getNumber();
          }
          else if (cell.getNumber() == 0 && cell.getPosition() == ((gameSize * gameSize)-1))
          {
              rightOrder = true;
          }
          else
          {
              rightOrder = false;
              break;
          }


      }
      return rightOrder;
    }
   public void actionPerformed(ActionEvent ev) {


        CellButton tempCell = (CellButton)ev.getSource();


        if (((tempCell.getColumn()== (colForNull + 1)) || (tempCell.getColumn() == (colForNull - 1))) && tempCell.getRow() == rowForNull)
        {
            swapCellButton(tempCell, list.get(posForNull)); // byter trycktknapp och nullbuttons texter
        }
        else if ((tempCell.getRow() == (rowForNull + 1) || tempCell.getRow() == (rowForNull - 1)) && tempCell.getColumn() == colForNull)
        {
            swapCellButton(tempCell, list.get(posForNull)); // byter tycktknapp och nullbuttons texter
        }
        else
        {
            Toolkit.getDefaultToolkit().beep();
        }
        howmanymoves ++;
        if(numRightorder() == true)
        {
            JOptionPane.showMessageDialog(game,"You made " + howmanymoves + " moves to win");
        }
    }
}

