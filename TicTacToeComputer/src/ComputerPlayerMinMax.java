
import java.util.ArrayList;
import java.util.List;

/**
 * Language: JAVA 
 * IDE = NetBeans IDE 7.4
 * 
 * Description:
 * This class defines and represents the characteristics of the computer player and comes up with its moves. 
 * This class implements an unbeatable strategy for the computer player.
 * It calculates where to place its symbol and tries :
 * - win the game
 * - make a fork when given a chance
 * - block the opposite player's fork
 * - stops the opposite player to win
 *
 * Methods:
 * 1) Constructor: initializes the super class grid so that it can calculate its moves on that grid.
 * 2) move(): calls the minmax() and returns the best moves in terms of an array which contains row and column position
 * 3) minMax(): This is the brain of the computer. In this method the computer looks ahead 3 levels and calculates
 *              the best possible move using a heuristic. It makes a decision tree not literally though.
 * 4) evaluateScore(): It evaluates score of the players if they place their symbols at a particular place. It is 
 *                     based on a heuristic. The computer's score has to be maximized and opposition's score should
 *                     be minimized. The move that gives the optimum score is taken.
 * 5) evaluateLines(): This method is a kind of helper method to the evaluateScore() as it calculates the score of 
 *                     each line in the grid. There are total 8 lines.
 * 6) generteMoves(): It generates all the possible moves that the player can take. It evaluates the empty spaces
 *                    and makes a list of the positions of those empty spaces and returns this list.
 * 7) hasWon(): This checks if the current player has won or not. 
 */

/**
 *
 * @author Rohan D. Shah
 */
public class ComputerPlayerMinMax extends ComputerPlayer
{
    public ComputerPlayerMinMax(Grid grid) 
    {
        super(grid);
    }
    

    @Override
    int[] move() 
    {
        // looking forward by only 3 levels.
        int [] result = minMax(2, myContent);
        return new int[]{result[1], result[2]};
    }
    
    private int[] minMax(int depth, Content player)
    {
        // generating possible moves.
        List<int[]> allMoves = generateMoves();
        
        // maximizing for computer and minimizing for opposition
        // hence for computer comparison has to be made with a min value and 
        // vice versa for opposition.
        int bestScore = (player == myContent)?Integer.MIN_VALUE:Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1, bestCol = -1;
        
        // evaluatiing the score after making all moves 
        if(allMoves.isEmpty() || depth == 0)
            bestScore = evaluateScore();
        else
        {
            // making moves and calculating the score and looking forward
            for(int[] moves : allMoves)
            {
                cells[moves[0]][moves[1]].seed = player;
                
                if(player == myContent)
                {
                    // maximizing computer's score
                    currentScore = minMax(depth-1, oppositionContent)[0];
                    if(currentScore > bestScore)
                    {
                        bestScore = currentScore;
                        bestRow = moves[0];
                        bestCol = moves[1];
                    }
                }
                else
                {
                    // minimizing oppositions score
                    currentScore = minMax(depth-1, myContent)[0];
                    if(currentScore < bestScore)
                    {
                        bestScore = currentScore;
                        bestRow = moves[0];
                        bestCol = moves[1];
                    }
                }
                // undo moves so that grid is clear.
                cells[moves[0]][moves[1]].clear();
            }
        }
        return new int[] {bestScore, bestRow, bestCol};
    }
    private int evaluateScore()
    {
        int score = 0;
        // evaluateLines(int row1, int col1, int row2, int col2, int row3, int col3)
        score+=evaluateLines(0,0,0,1,0,2); // first row
        score+=evaluateLines(1,0,1,1,1,2); // second row
        score+=evaluateLines(2,0,2,1,2,2); // third row
        score+=evaluateLines(0,0,1,0,2,0); // first column
        score+=evaluateLines(0,1,1,1,2,1); // second column
        score+=evaluateLines(0,2,1,2,2,2); // third column
        score+=evaluateLines(0,0,1,1,2,2); // \ diagonal
        score+=evaluateLines(0,2,1,1,2,0);// / diagonal
        
        return score;
    }
    
    /** The heuristic evaluation function for the given line of 3 cells
       @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
               -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
               0 otherwise */
   private int evaluateLines(int row1, int col1, int row2, int col2, int row3, int col3) 
   {
      int score = 0; 
      // First cell
      if (cells[row1][col1].seed == myContent) 
      {
         score = 1;
      } 
      else if (cells[row1][col1].seed == oppositionContent) 
      {
         score = -1;
      }
 
      // Second cell
      if (cells[row2][col2].seed == myContent) 
      {
         if (score == 1) 
         {   
            // cell1 is mySeed
            score = 10;
         } 
         else if (score == -1) 
         {  
            // cell1 is oppSeed
            return 0;
         } 
         else 
         {  
            // cell1 is empty
            score = 1;
         }
      } 
      else if (cells[row2][col2].seed == oppositionContent) 
      {
         if (score == -1) 
         { 
            // cell1 is oppSeed
            score = -10;
         } 
         else if (score == 1) 
         { 
            // cell1 is mySeed
            return 0;
         } 
         else 
         {  
            // cell1 is empty
            score = -1;
         }
      }
 
      // Third cell
      if (cells[row3][col3].seed == myContent) 
      {
         if (score > 0) 
         {  
            // cell1 and/or cell2 is mySeed
            score *= 10;
         } 
         else if (score < 0) 
         {  
            // cell1 and/or cell2 is oppSeed
            return 0;
         } 
         else 
         {  
            // cell1 and cell2 are empty
            score = 1;
         }
      } 
      else if (cells[row3][col3].seed == oppositionContent) 
      {
         if (score < 0) 
         {  
            // cell1 and/or cell2 is oppSeed
            score *= 10;
         } 
         else if (score > 1) 
         {  
            // cell1 and/or cell2 is mySeed
            return 0;
         } 
         else 
         {  
            // cell1 and cell2 are empty
            score = -1;
         }
      }
      return score;
   }
   // generate all possible moves
    private List<int[]> generateMoves()
    {
        List<int[]> possibleMoves = new ArrayList<int[]>();
        
        if(hasWon(myContent) || hasWon(oppositionContent))
            return possibleMoves; // empty list, because game is over
        else
        {
            for(int i=0 ; i<rows ; i++)
            {
                for(int j=0 ; j<cols ; j++)
                {
                    if(cells[i][j].seed == Content.EMPTY)
                        possibleMoves.add(new int[]{i,j});
                }
            }
            return possibleMoves;
        }
        
    }
    
    boolean hasWon(Content thisSeed)
    {              
        boolean forRows = false, forCols = false, forDiagonals = false;
        
        for(int i=0 ; i<rows ; i++)
        {
            // checking the rows for victory
                if(cells[i][0].seed == thisSeed &&
                   cells[i][1].seed == thisSeed &&
                   cells[i][2].seed == thisSeed)
                    forRows = true;
            // checking the columns for victory
                if(cells[0][i].seed == thisSeed &&
                   cells[1][i].seed == thisSeed &&
                   cells[2][i].seed == thisSeed)
                    forCols = true;
                
                if(forRows || forCols)
                    return true;
        }
        // checking the diagonal '\' for victory
        if((cells[0][0].seed == thisSeed &&
            cells[1][1].seed == thisSeed &&
            cells[2][2].seed == thisSeed)
            ||  // checking the diagonal '/' for victory
           (cells[0][2].seed == thisSeed &&
            cells[1][1].seed == thisSeed &&
            cells[2][0].seed == thisSeed))
            {
                forDiagonals = true;
                return forDiagonals;
            }
        return false;
    }
}
