
public class GameLogic implements IGameLogic {
    private int cols;
    private int rows;
    private int playerID;

    // player blue - 1 player red - 2(AI)

    private int[][] board;  // matrix for coins coordinates // values are id's of players

    public GameLogic() {
        //TODO Write your implementation for this method
    }

    public void initializeGame(int x, int y, int playerID) {
        //TODO Write your implementation for this method
        cols = x;
        rows = y;
        playerID = playerID;
        board = new int[cols][rows];

        System.out.println(playerID);
        System.out.println("Game initialized");

    }

    public Winner gameFinished() {
        //TODO Write your implementation for this method


        //for player 1 and player 2


//        System.out.println("playerid "+playerID);

//        if(playerID==1) return checkWin(Winner.PLAYER1, 1);
//        else return checkWin(Winner.PLAYER2, 2);


        // return checkWin(Winner.PLAYER2, 2);
        return checkWin(Winner.PLAYER1, 1);

        //  return Winner.NOT_FINISHED;

    }


    public void insertCoin(int column, int playerID) {
        //TODO Write your implementation for this method

        System.out.println("insert coin");
        int spot = freeSpot(board, column);

        System.out.println("spot is:" + spot);
        if (spot != -1) {
            board[column][spot] = playerID;

            System.out.println("player id" + playerID);

            System.out.println("Coin inserted in column " + column);

        } else System.out.println("no free spots");


    }


    public int decideNextMove() {
        //TODO Write your implementation for this method


        return minimax_decision(board);
    }


    private int minimax_decision(int[][] ar) {

        return 0;
    }

    private int max_value(int[][] ar) {

        return 0;
    }

    private int min_value(int[][] ar) {
        return 0;
    }

    private boolean terminalState() {

        return true;
    }


    // 1 - win, -1 - lose , 0 - draw
    private int utility() {

        return 1;
    }



    /* Support Methods */

    //TODO insert a coin into last free column cell in a board array


    private boolean columnIsFull(int[][] ar, int col) {
        if (ar[col][ar[col].length - 1] != 0) return true;
        return false;
    }

    private Winner checkWin(Winner player, int playerID) {


        int[] h_count = new int[board[0].length];
        // check vertically and horizontal
        for (int i = 0; i < board.length; i++) {
            int v_count = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == playerID) {
                    h_count[j]++;
                    v_count++;
                } else {
                    v_count = 0;
                    h_count[j] = 0;
                }
                if (v_count >= 4 ||h_count[j]>=4) return player;
            }
        }

// diagonal lower left to upper right
        for (int col = 0; col < this.cols - 3; col++)
            for (int row = 0; row < this.rows - 3; row++)

                if (board[col][row] > 0
                                && board[col][row] == board[col + 1][row + 1]
                                && board[col][row] == board[col + 2][row + 2]
                                && board[col][row] == board[col + 3][row + 3])
                    return player;

// diagonal upper left to lower right
        for (int row = this.rows - 1; row >= 3; row--)
            for (int col = 0; col < this.cols - 3; col++)
                if (board[col][row] > 0
                        && board[col][row] == board[col + 1][row - 1]
                        && board[col][row] == board[col + 2][row - 2]
                        && board[col][row] == board[col + 3][row - 3])
                    return player;

        return Winner.NOT_FINISHED;
    }

    private int freeSpot(int[][] ar, int column) {

        for (int i = 0; i < ar[column].length - 1; i++) {
            if (ar[column][i] == 0) return i;
        }

        return -1; // no spots
    }

}



