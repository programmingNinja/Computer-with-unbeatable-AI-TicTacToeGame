/**
 * Language: JAVA 
 * IDE = NetBeans IDE 7.4
 * 
 * Description:
 * This class stores the grid on which the game is being played. The grid is made up of 9 grid cells. These cells are 
 * stored in an array containing the GridLocation objects.
 * 
 * Member Variables(datatypes):
 * 1) ROWS(int) = Maximum row of the grid
 * 2) COLS(int) = Maximum column of the grid
 * 3) emptySpacesRemaining(int) = counts the empty cells remaining in the grid
 * 4) currentRow(int) = row been worked on currently
 * 5) currentCol(int) = column been worked on currently
 * 6) GridLocation[][] cell (GridLocation) = stores the cells objects
 * 
 * Member Methods:
 * 1) Constructor
 * 2) init()
 * 3) drawGrid()
 * 4) isDraw()
 * 5) hasWon(Content object)
 */

/**
 *
 * @author Rohan D. Shah
 */
public class Grid 
{
    public static final int ROWS = 3;
    public static final int COLS = 3;
    int emptySpacesRemaining;
    int currentRow, currentCol;
    GridLocation[][] cell;
  
    Grid()
    {
        cell = new GridLocation[ROWS][COLS];
        emptySpacesRemaining = ROWS * COLS;
        for(int i = 0 ; i<ROWS ; i++)
        {
            for(int j=0 ; j<COLS ; j++)
            {
                cell[i][j] = new GridLocation(i, j);
            }
        }
    }
   
    public void init() 
    {
      for (int row = 0; row < ROWS; ++row) 
      {
         for (int col = 0; col < COLS; ++col) 
         {
            cell[row][col].clear();  
         }
      }
    }
    
    void drawGrid()
    {
        for(int i=0 ; i<ROWS ; i++)
        {
            for(int j=0 ; j<COLS ; j++)
            {
                cell[i][j].drawCell();
                 if (j < COLS - 1) System.out.print("|");
            }
            System.out.println();
             if (i < ROWS - 1) 
            {
                System.out.println("-------");
            }
        }
    }
    
    boolean isDraw()
    {
        // the grid does not have any empty space hence the game is drawn
        if(emptySpacesRemaining == 0)
            return true;
        else return false;
    }
    
    boolean hasWon(Content thisSeed)
    {               // checking rows for victory
        return ( (cell[currentRow][0].seed == thisSeed &&
                  cell[currentRow][1].seed == thisSeed &&
                  cell[currentRow][2].seed == thisSeed) 
                ||  // checking columns for victory
                 (cell[0][currentCol].seed == thisSeed &&
                  cell[1][currentCol].seed == thisSeed &&
                  cell[2][currentCol].seed == thisSeed)
                ||  // checking the diagonal '\' for victory
                  (currentRow == currentCol   &&
                  cell[0][0].seed == thisSeed &&
                  cell[1][1].seed == thisSeed &&
                  cell[2][2].seed == thisSeed)
                ||  // checking the diagonal '/' for victory
                  (currentRow + currentCol == 2 &&
                   cell[0][2].seed == thisSeed &&
                   cell[1][1].seed == thisSeed &&
                   cell[2][0].seed == thisSeed));
    }
    
}

