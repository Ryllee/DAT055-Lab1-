import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CellButton extends JButton
{
    int column,row,position,number;

    public CellButton(int number,int position)
    {
        this.position = position;
        this.number = number;
        column = position % 4;
        row = position / 4;
        row = row + 1;
        if (position % 4 == 0)
        {
            row = position/ 4;
        }

        if(number == 0)
        {
            setText("");
        }
        else
        {
            setText(String.valueOf(number)+ "," + String.valueOf(column) + "," + String.valueOf((row)));
        }
    }

    public void setNumber(int num)
    {
        number = num;
    }

    public int getRow()
    {
        return row;
    }
    public int getColumn()
    {
        return column;
    }
    public int getPosition()
    {
        return position;
    }
    public int getNumber()
    {
        return number;
    }


}
