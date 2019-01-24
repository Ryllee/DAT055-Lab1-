import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CellButton extends JButton
{
    int column,row;

    public CellButton(int number,int position)
    {
        column = position % 4;
        if (column == 0)
        {
            column = 4;
        }
        row = position / 4;
        row = row + 1;
        if (position % 4 == 0)
        {
            row = position/ 4;
        }


        if(number == 0)
        {
            setText("" );
        }
        else
        {
            setText(String.valueOf(number));
        }



    }


    public void addCellButton(JFrame frame)
    {
        frame.getContentPane().add(this);

    }

    public int getRow()
    {
        return row;
    }
    public int getColumn()
    {
        return column;
    }

    public void setValue(int value)
    {
        if( value == 0)
        {
            setText("");
        }
        else
        setText(String.valueOf(value));
    }
}
