/**
 * Language: JAVA 
 * IDE = NetBeans IDE 7.4
 * 
 * Description:
 * This is the computer player abstract class. It defines the basic computer player. This is abstract because 
 * any developer can extend it and can program various strategies. This game implements a strategy which is unbeatable.
 * 
 * Member variables: 
 * 1) rows: for making a grid which has the same rows as the original grid, so that computer can make 
 *          different moves to calculate the best one.
 * 2) cols: for making a grid which has the same columns as the original grid, so that computer can make 
 *          different moves to calculate the best one.
 * 3) cells: for making a grid which has the same cells as the original grid, so that computer can make 
 *          different moves to calculate the best one.
 * 4) myContent: To place its symbol on a cell and calculate the score.
 * 5) oppositionContent: To place the opposite player's symbol to mimic his behavior and come up with the best move
 * 
 * Methods:
 * 1) Constructor: initializes the cells
 * 2) setContent(): setting the symbol of player and computer
 * 3) move(): for which move to make.
 */

/**
 *
 * @author Rohan D. Shah
 */
public abstract class ComputerPlayer 
{
    protected int rows = Grid.ROWS;
    protected int cols = Grid.COLS;
    
    protected GridLocation[][] cells;
    
    Content myContent;
    Content oppositionContent;

    public ComputerPlayer(Grid myGrid) 
    {
        cells = myGrid.cell;
    }
    
    void setContent(Content symbol)
    {
        myContent = symbol;
        
        if(myContent == Content.CROSS)
            oppositionContent = Content.NOUGHT;
        else if(myContent == Content.NOUGHT)
            oppositionContent = Content.CROSS;
    }
    abstract int[] move();   
}
