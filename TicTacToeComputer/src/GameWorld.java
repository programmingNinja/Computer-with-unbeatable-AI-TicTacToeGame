/**
 * Language: JAVA 
 * IDE = NetBeans IDE 7.4
 * 
 * Description:
 * This is the world in which the game is played. It 
 * - maintains and update the states
 * - updates the grid
 * - switches the player according to their turns.
 * - handles the moves of the players
 * 
 * Member variables(Data type):
 * 1) gameGrid (Grid) = The grid on which the game will be played
 * 2) currentState (GameState) = The current state of the game after every move
 * 3) currentPlayer (Content) = The current player's symbol
 * 4) input (Scanner) = For taking the input from the player about their moves.
 * 
 * Methods:
 * 1) Constructor
 * 2) playerMove(Content object)
 * 3) worldInit()
 * 4) updateGameState(Content object)
 * 
 */
import java.util.Scanner;
/**
 *
 * @author Rohan D. Shah
 */
public class GameWorld 
{
    private Grid gameGrid;
    private GameState currentState;
    private Content currentPlayer;
    private Scanner input = new Scanner(System.in);
    
    GameWorld()
    {
        gameGrid = new Grid();
        ComputerPlayerMinMax computer = new ComputerPlayerMinMax(gameGrid);
        computer.setContent(Content.CROSS);
        worldInit();
        do
        {
            playerMove(currentPlayer, computer);
            gameGrid.drawGrid();
            updateGameState(currentPlayer);
             
            if(currentState == GameState.CROSS_WON)
            {
                System.out.println("Player X Won the game");
                System.out.println("=========================");
            }
            else if(currentState == GameState.NOUGHT_WON)
            {
                System.out.println("Player O Won the game");
                System.out.println("=========================");
            }
            else if(currentState == GameState.DRAW)
            {
                System.out.println("Game Drawn. Bye!");
                System.out.println("=========================");
            }
            
            if(currentPlayer == Content.CROSS)
                currentPlayer = Content.NOUGHT;
            else if(currentPlayer == Content.NOUGHT)
                currentPlayer = Content.CROSS;
        }
        // keep on playing until one player wins or the game is drawn
        while(currentState == GameState.PLAYING);
    }
    
    GameState getState()
    {
        return this.currentState;
    }
    // Handles the moves of the player, takes the input as the symbol of the current player
    void playerMove(Content thisSeed, ComputerPlayerMinMax comp)
    {
        
        boolean validInput = true;
        do
        {
            int row = -1, col = -1;
            
            if(thisSeed == Content.CROSS)
            {
                /*System.out.println("Player X please enter the location where you want to place your "+thisSeed+"\n"
                + "The input should be (row[1-3] , column[1-3]) WITHOUT commas, and ONLY SPACES between two digits");*/
                int [] computerMoves = comp.move();
                row = computerMoves[0];
                col = computerMoves[1];
                System.out.println("");
                System.out.println("Computer placed its "+thisSeed+" at "+(row+1)+" "+(col+1));
            }
            else if(thisSeed == Content.NOUGHT) 
            {
                System.out.println("");
                System.out.println("Player O please enter the location where you want to place your "+thisSeed+"\n"
                + "The input should be (row[1-3] , column[1-3]) WITHOUT commas, and ONLY SPACES between two digits\n"+
                "entering alphabets as input will crash the game");
              
               try
                {
                    row = input.nextInt()-1;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid row input");
                }
                col = input.nextInt()-1;
                System.out.println("");
            }
            // checking the correctness of the input and seeing whether that cell to be filled is empty.
            if(row>=0 && row<3 && col>=0 && col<3 && gameGrid.cell[row][col].seed == Content.EMPTY)
            {
                gameGrid.cell[row][col].seed = thisSeed;
                gameGrid.currentRow = row;
                gameGrid.currentCol = col;
                gameGrid.emptySpacesRemaining--;
                validInput = true;
            }
            else
            {
                System.out.println("");
                System.out.println(" The entered input is incorrect please enter it again");
                validInput = false;
            }
        }
        while(!validInput);
    }
    
    void worldInit()
    {
        gameGrid.init();
        currentState = GameState.PLAYING;
        currentPlayer = Content.NOUGHT;        
    }
    
    void updateGameState(Content thisSeed)
    {
        if(gameGrid.hasWon(thisSeed))
        {
            if(thisSeed == Content.CROSS)
                currentState = GameState.CROSS_WON;
             if(thisSeed == Content.NOUGHT)
                currentState = GameState.NOUGHT_WON;
        }
        else if(gameGrid.isDraw())
            currentState = GameState.DRAW;
    }
        
}
