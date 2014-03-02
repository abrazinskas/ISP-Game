
public class GameLogic implements IGameLogic {
    private int cols;
    private int rows;
    private int playerID;

    private int[][] board;  // matrix for coins coordinates // values are id's of players
    private int[] currentSize; // the number of coins in each column

    public GameLogic() {
        //TODO Write your implementation for this method
    }

    public void initializeGame(int x, int y, int playerID) {
        //TODO Write your implementation for this method
        cols = x;
        rows = y;
        playerID = playerID;

        board = new int[cols][rows];
        currentSize = new int[rows];

        System.out.println("Game initialized");

    }

    public Winner gameFinished() {
        //TODO Write your implementation for this method
        return Winner.NOT_FINISHED;
    }


    public void insertCoin(int column, int playerID) {
        //TODO Write your implementation for this method

        board[column][currentSize[column]] = playerID;
        currentSize[column]++;

        System.out.println("Coin inserted in column " + column);
    }


    public int decideNextMove() {
        //TODO Write your implementation for this method
        return 2;
    }

    /* Support Methods */

    private boolean columnIsFull(int[][] ar, int col) {
        if (ar[col][ar[col].length - 1] != 0) return true;
        return false;
    }

}




