
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

        if (hasWon(1, board)) return Winner.PLAYER1;
        if (hasWon(2, board)) return Winner.PLAYER2;
        return Winner.NOT_FINISHED;

        //return checkWin(1);

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
       // System.out.println("my decision value is:"+minimax_decision(board));
        return minimax_decision(board);

    }


    private int minimax_decision(int[][] board) {
        int[] result = min_value(board);
        System.out.println("min value is: "+result[0]);
        return result[1];


    }

    private int[] max_value(int[][] board) {
        int v, savedValue, freeSpot;
        int[] state_values=new int[]{-999,0}; // 0-utility value  1 - path
        int[] new_state_values;
        v = utility(board);
        if (v != -999) return new int[]{v,0}; //if state is terminal

        for (int col = 0; col < board.length; col++) {
            freeSpot = freeSpot(board, col);
            if (freeSpot != -1) {
                savedValue = board[col][freeSpot];
                board[col][freeSpot]=1;

                new_state_values = min_value(board);

                if(state_values[0]< new_state_values[0])
                state_values=new_state_values;

                state_values[1]=col;

                board[col][freeSpot]=savedValue;
            }
        }
        return state_values;
    }

    private int[] min_value(int[][] board) {
        int v, savedValue, freeSpot;
        int[] state_values=new int[]{999,0}; // 0-utility value  1 - path
        int[] new_state_values;

        v = utility(board);
        if (v != -999) return new int[]{v,0}; //if state is terminal
        for (int col = 0; col < board.length; col++) {
            freeSpot = freeSpot(board, col);
            if (freeSpot != -1) {
                //System.out.println("placing a coin to: "+col);
                savedValue = board[col][freeSpot];
                board[col][freeSpot]=2;

                new_state_values = min_value(board);

                if(state_values[0]> new_state_values[0])
                    state_values=new_state_values;

                state_values[1]=col;

                board[col][freeSpot]=savedValue;
            }
        }
        return state_values;
    }


    // 100 - win, -100 - lose , 0 - draw , -999 -default| no utility value so far
    private int utility(int[][] board) {

        if (hasWon(1, board)) return 100;
        if (hasWon(2, board)) return -100;
        if (isTie(board)) return 0;

        return -999;
    }



    /* Support Methods */


    private boolean columnIsFull(int[][] ar, int col) {
        if (ar[col][ar[col].length - 1] != 0) return true;
        return false;
    }

    private boolean isTie(int[][] gameBoard) {
        boolean tiegame = true;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[col][row] == 0) tiegame = false;
            }
        }
        return tiegame;
    }


    // true is returned if playerID has won. false - if not, IT DOES NOT MEAN AUTOMATICALLY THAT HE LOST. it means that
    // current positions of coins don't make playerID a winner.
    private boolean hasWon(int playerID, int[][] board) {


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
                //System.out.println("column: "+i+"v_count: "+v_count);
                //System.out.println("h_count j: "+j+" "+h_count[j]);
                if (v_count >= 4 || h_count[j] >= 4) return true;
            }
        }

// diagonal lower left to upper right
        for (int col = 0; col < this.cols - 3; col++)
            for (int row = 0; row < this.rows - 3; row++)
                if (board[col][row] > 0
                        && board[col][row] == board[col + 1][row + 1]
                        && board[col][row] == board[col + 2][row + 2]
                        && board[col][row] == board[col + 3][row + 3])
                    return true;

// diagonal upper left to lower right
        for (int row = this.rows - 1; row >= 3; row--)
            for (int col = 0; col < this.cols - 3; col++)
                if (board[col][row] > 0
                        && board[col][row] == board[col + 1][row - 1]
                        && board[col][row] == board[col + 2][row - 2]
                        && board[col][row] == board[col + 3][row - 3])
                    return true;

        return false;
    }

    private int freeSpot(int[][] ar, int column) {

        for (int i = 0; i < ar[column].length; i++) {
            if (ar[column][i] == 0) return i;
        }

        return -1; // no spots
    }

}



