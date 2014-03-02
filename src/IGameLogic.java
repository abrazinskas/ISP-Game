/**
 *
 * @author Kevin Tierney
 * @version 28.2.2010
 *
 * @author Mai Ajspur
 * @version 1.2.2007
 *
 */
public interface IGameLogic {

    public enum Winner {PLAYER1, PLAYER2, TIE, NOT_FINISHED}

    /**
     * Creates a new empty game board of the specified dimensions
     * and indicates the ID of the player. 
     * This method will be called from the main function. 
     * @param columns The number of columns in the game board
     * @param rows The number of rows in the game board
     * @param playerID 1 = blue (player1), 2 = red (player2)
     */
    void initializeGame(int columns, int rows, int player);
	
		
    /**
     * Notifies that a token/coin is put in the specified column of 
     * the game board. 
     * @param column The column where the coin is inserted.      
     * @param playerID The ID of the current player.
     */
    void insertCoin(int column, int playerID);

    /**
     * Calculates the next move  This is where you should 
     * implement/call your heuristic evaluation functions etc. 
     */
    int decideNextMove();
	
	
    /**
     * Checks if any of the two players have 4-in-a-row.
     * @return Winner enum
     */
    Winner gameFinished();
}
