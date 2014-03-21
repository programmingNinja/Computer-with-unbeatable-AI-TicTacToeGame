/**
 * Language: JAVA 
 * IDE = NetBeans IDE 7.4
 * Description:
 * 
 * This is a list of constants that stores various values of the game state.
 * PLAYING = The game is being played by the two players and in motion
 * DRAW = All the cells have been filled and no player has won. Hence the game is drawn.
 * NOUGHT_WON = The player with the NOUGHT symbol has won the game.
 * CROSS_WON = The player with the CROSS symbol has won the game.
 * 
 * This is a better way to store the states as a integer values for each respective states.
 */

/**
 *
 * @author Rohan D. Shah
 */
public enum GameState 
{
    PLAYING, DRAW, NOUGHT_WON, CROSS_WON;
}
