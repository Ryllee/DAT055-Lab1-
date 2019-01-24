import java.awt.event.*;
import java.awt.*;
import java.util.Iterator;
import javax.swing.*;

public class playGame implements ActionListener
{
    GameGenerator generator;
    JFrame game;
    public int posForNull = 0;
    public int rowForNull;
    public int colForNull;

    public playGame(int gameSize)
    {

        makeFrame(gameSize);
    }

    public void makeFrame( int gameSize)
    {
        generator = new GameGenerator(gameSize);
        game = new JFrame("The 15 game");
        game.setLayout(new GridLayout(4, 4, 3, 3));
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


    public void actionPerformed(ActionEvent ev)
    {
        Iterator<Integer> genIt =  generator.iterator();

        CellButton tempCell = (CellButton)ev.getSource();
        if(tempCell.getColumn() == colForNull + 1 || colForNull - 1)


    }


}
