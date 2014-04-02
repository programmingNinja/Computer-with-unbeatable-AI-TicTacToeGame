
import java.util.Scanner;

/**
 * Language: JAVA 
 * IDE = NetBeans IDE 7.4
 * 
 * Description:
 * This is a tic-tac-toe game. The main method initializes the game world in which the entire game is played.
 * The main method creates the instance of the game world, and the constructor of the game world runs the entire game.
 */

/**
 *
 * @author Rohan D. Shah
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    static void showMenu()
    {
        System.out.println("MENU:");
        System.out.println("--------------------");
        System.out.println("1) About");
        System.out.println("2) Intructions");
        System.out.println("3) Play Game");
        System.out.println("4) Exit");
        System.out.println("--------------------");
    }
    
    static void showAbout()
    {
        System.out.println(" About:"
                + "\n ========================"
                + "\n This is a Tic-Tac-Toe game. It is developed in JAVA using the netbeans IDE 7.4."
                + "\n You will be playing against the computer. I challenge you to win against it "
                + "\n The developer of this game is Rohan D. Shah\n");
    }
    
    static void showInstructions()
    {
        System.out.println(" Instructions:"
                + "\n ======================"
                + "\n This is a Tic-Tac-Toe game. Your will be the first turn to play and you symbol is a"
                + "\n NOUGHT (o) and computer's symbol is a CROSS (X). You have to beat the computer."
                + "\n You have to enter the row and column number of the position you want to place"
                + "\n your symbol on the grid, with space between the two numbers. Please DON'T"
                + "\n separate the numbers with a comma."
                + "\n All the best. You will need luck to beat the computer player \n");
    }
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        GameState continueGame = null ;
        int choice=0; 
        do{
            showMenu();            
            try
            {
               choice = input.nextInt();
            }
            catch(Exception e)
            {
                System.out.println("invalid input");
            }
            while (choice <= 0 || choice > 4) 
            {
               System.out.println("Please enter number between 1 to 4!");
               while (!input.hasNextInt()) 
               {
                   System.out.println("That's not a number!");
                   input.next(); // this is important!
               }
                choice = input.nextInt();
            } 
            
            switch(choice)
            {
                case 1:
                    showAbout();
                    break;

                case 2:
                    showInstructions();
                    break;

                case 3:
                    GameWorld newGame = new GameWorld();
                    continueGame = newGame.getState();
                    break;

                case 4:
                    break;
                    
                default: 
                    System.out.println("Please enter a valid input");
                    choice = 0;
            }
        }
        while(choice >= 0 && choice<4 || continueGame != null);
        
    }
    
}
