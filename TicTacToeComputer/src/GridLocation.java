/**
 * Language: JAVA 
 * IDE = NetBeans IDE 7.4
 * 
 * Description:
 * This class represent the cell object on the grid. There are 9 cells in the tic-tac-toe game grid.
 * 
 * Member Variables(data type):
 * 1) seed (Content) = Stores the symbol of the cell, initially it is an empty space.
 * 2) row(int) = The horizontal location of the cell in the grid.
 * 3) col(int) = The vertical location of the cell in the grid.
 * 
 * Member Methods:
 * 1) Constructor
 * 2) clear()
 * 3) drawCell()
 */

/**
 *
 * @author Rohan D. Shah
 */
public class GridLocation 
{
    Content seed;
    int row, col;
    
    GridLocation(int locationRow, int locationCol)
    {
        row = locationRow;
        col = locationCol;
        clear();
    }
    
    void clear()
    {
        seed = Content.EMPTY;
    }
    
    
    void drawCell()
    {
        switch(seed)
        {
            case CROSS : System.out.print("X");
                break;
            case NOUGHT : System.out.print("O");
                break;
            case EMPTY : System.out.print(" ");
                break;
        }
    }
}
