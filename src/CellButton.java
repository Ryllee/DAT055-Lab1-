import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CellButton extends JButton
{
    private int column,row,position,number;

    public CellButton(int number,int position, int gameSize)
    {
        this.position = position;
        this.number = number;
        row = position/gameSize + 1;
        column = position%gameSize + 1;
        if(number == 0)
        {
            setText("");
        }
        else
        {
            setText(String.valueOf(number));
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
