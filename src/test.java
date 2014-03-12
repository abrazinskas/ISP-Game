//import java.util.*;
//
//
//public class Minimax extends Thread {
//
//    private static boolean DEBUG = false;
//    private static int limit;
//    private static int moveValue[];
//
//    //gameBoard square values
//    private static int PLAYER1_MOVE = 1;  //black player
//    private static int PLAYER2_MOVE = -1; //red player
//    private static int OPEN_SQUARE = 0;
//
//    private static int ROW_SIZE = 6;
//    private static int COL_SIZE = 7;
//
//    public int test = 4;
//    public int rowNextMove;
//    public int colNextMove;
//    public boolean minFlag;
//
//    private int[][] gameBoard;
//    private int num;
//    private int mychip;
//
//    private Random gen = new Random();
//
//    public void run() {
//        //get the game board
//        gameBoard = Connect4Controller.getBoard();
//
//        rowNextMove = -1;
//        colNextMove = -1;
//
//
//        try {
//            limit=0;
//            int alpha=Integer.MIN_VALUE;
//            int beta=Integer.MAX_VALUE;
//
//            //first set a move in case of a quick timeout
//            //moveValue = justSetNext(gameBoard, currentPosition, minFlag);
//            //rowNextMove=moveValue[0];
//            //colNextMove=moveValue[1];
//
//            if (minFlag) mychip = PLAYER2_MOVE;
//            else mychip = PLAYER1_MOVE;
//            //If a move to i,j causes the computer to win
//            //choose that spot as your move
//            for (int i=0; i<ROW_SIZE; i++) {
//                for (int j=0; j<COL_SIZE; j++) {
//                    if (Connect4Controller.isLegalMove(i,j)) {
//                        gameBoard[i][j] = mychip;
//                        if (isWin()) {
//                            rowNextMove = i;
//                            colNextMove = j;
//                            //System.out.println("win!");
//                            return;
//                        }
//                        gameBoard[i][j] = OPEN_SQUARE;
//                    }
//                }
//            }
//
//
//            //If a move to i,j causes the computer to lose,
//            //choose that spot as your move
//            for (int i=0; i<ROW_SIZE; i++) {
//                for (int j=0; j<COL_SIZE; j++) {
//                    if (Connect4Controller.isLegalMove(i,j)) {
//                        gameBoard[i][j] = -mychip;
//                        if (isWin()) {
//                            rowNextMove = i;
//                            colNextMove = j;
//                            //System.out.println("block!");
//                            return;
//                        }
//                        gameBoard[i][j] = OPEN_SQUARE;
//                    }
//                }
//            }
//
//            //run miniMax to find optimal nextMove
//            while(!isInterrupted()) {
//                moveValue = miniMax(gameBoard,0,limit,alpha,beta,minFlag);
//                rowNextMove=moveValue[0];
//                colNextMove=moveValue[1];
//                if (DEBUG) {
//                    System.out.println("*****************");
//                    System.out.println("limit="+limit);
//                    System.out.println("rowNext="+rowNextMove);
//                    System.out.println("colNext="+colNextMove);
//                    System.out.println("*****************");
//                }
//                limit++;
//            }
//        } catch (Exception e) {
//            System.out.println("Player interrupted");
//
//        }
//        if (DEBUG) {
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^done at run method.");
//        }
//    }
//
//
//
//    private int[] miniMax(int[][] gameBoard, int depth, int limit, int alpha, int beta, boolean myMinFlag) {
//        int oldv, v, savedValue;
//        int[] testPos=new int[] {0,0};
//        int[] nextMove=new int[] {0,0};
//
//        if (isInterrupted()) {
////  	    nextMove[0] = rowNextMove;
////  	    nextMove[1] = colNextMove;
//            return new int[] {rowNextMove,colNextMove,0};
//        }
//
//        if (isTie(gameBoard)) {
//            return utility(gameBoard);
//        }
//
//        //depth limiter
//        if (depth == limit) {
//            return staticBoardEvaluator(gameBoard);
//        }
//
//        depth = depth + 1;
//        if (myMinFlag) {
//            //min player
//            v = Integer.MAX_VALUE;
//            for (int i=0; i<=ROW_SIZE; i++) {
//                for (int j=0; j<=COL_SIZE; j++) {
//                    if (isLegalMove(gameBoard, i, j)) {
//                        oldv = v;
//                        //testPos[0] = i;
//                        //testPos[1] = j;
//                        savedValue = gameBoard[i][j];
//                        gameBoard[i][j] = PLAYER2_MOVE;
//                        v = java.lang.Math.min(v, (int) miniMax(gameBoard, depth, limit, alpha, beta, ! myMinFlag)[2]);
//                        gameBoard[i][j] = savedValue;
//                        //beta = v;
//                        //if (beta <= alpha) {
//                        //    return new int[] {nextMove[0],nextMove[1],alpha};
//                        //}
//                        if ((depth == 1) && (v < oldv)) {
//                            nextMove[0] = i;
//                            nextMove[1] = j;
//                        }
//                    }//end if
//                }
//            }
//            //return new int[] {nextMove[0],nextMove[1],beta};
//            return new int[] {nextMove[0],nextMove[1],v};
//        }//end if
//        else {
//
//            //red player
//            //max player
//            v = Integer.MIN_VALUE;
//            for (int i=0; i<=ROW_SIZE; i++) {
//                for (int j=0; j<=COL_SIZE; j++) {
//                    if (isLegalMove(gameBoard, i, j)) {
//                        oldv = v;
//                        //testPos[0] = i;
//                        //testPos[1] = j;
//                        savedValue = gameBoard[i][j];
//                        gameBoard[i][j] = PLAYER1_MOVE;
//                        v = java.lang.Math.max(v, (int) miniMax(gameBoard, depth, limit, alpha, beta, ! myMinFlag)[2]);
//                        gameBoard[i][j] = savedValue;
//                        //alpha = v;
//                        //if (beta <= alpha) {
//                        //    return new int[] {nextMove[0],nextMove[1],beta};
//                        //}
//                        if ((depth == 1) && (v > oldv)) {
//                            nextMove[0] = i;
//                            nextMove[1] = j;
//                        }
//                    }//end if
//                }
//            }
//            //return new int[] {nextMove[0],nextMove[1],alpha};
//            return new int[] {nextMove[0],nextMove[1],v};
//        }//end else
//
//        //return new int[] {nextMove[0], nextMove[1], v};
//    }
//
//    public boolean isLegalMove(int[][] gB, int row, int col) {
//        //for legal move, must satisfy the following
//        if (row < ROW_SIZE && col < COL_SIZE) {
//            if (row >= 0 && col >= 0) {
//                if (gB[row][col] == OPEN_SQUARE) {
//                    //needs to be placed at the bottom
//                    if (row == ROW_SIZE-1) return true;
//                    //or needs a piece below it
//                    if (gB[row+1][col] != OPEN_SQUARE) return true;
//                }
//            }
//        }
//        return false;
//    }//end isLegalMove
//
//    private int[] utility(int[][] boardState) {
//        int score = 0;
//
//
//        //check for win horizontally
//        for (int row=0; row<ROW_SIZE; row++) {
//            for (int col=0; col<COL_SIZE-3; col++) { //0 to 3
//                if (gameBoard[row][col] == gameBoard[row][col+1] &&
//                        gameBoard[row][col] == gameBoard[row][col+2] &&
//                        gameBoard[row][col] == gameBoard[row][col+3] &&
//                        gameBoard[row][col] != OPEN_SQUARE) {
//                    if (gameBoard[row][col] == PLAYER1_MOVE) score = score + 100;
//                    //if (gameBoard[row][col] == PLAYER2_MOVE) score = score - 100;
//                }
//            }
//        }
//        //check for win vertically
//        for (int row=0; row<ROW_SIZE-3; row++) { //0 to 2
//            for (int col=0; col<COL_SIZE; col++) {
//                if (gameBoard[row][col] == gameBoard[row+1][col] &&
//                        gameBoard[row][col] == gameBoard[row+2][col] &&
//                        gameBoard[row][col] == gameBoard[row+3][col] &&
//                        gameBoard[row][col] != OPEN_SQUARE) {
//                    if (gameBoard[row][col] == PLAYER1_MOVE) score = score + 100;
//                    //if (gameBoard[row][col] == PLAYER2_MOVE) score = score - 100;
//                }
//            }
//        }
//        //check for win diagonally (upper left to lower right)
//        for (int row=0; row<ROW_SIZE-3; row++) { //0 to 2
//            for (int col=0; col<COL_SIZE-3; col++) { //0 to 3
//                if (gameBoard[row][col] == gameBoard[row+1][col+1] &&
//                        gameBoard[row][col] == gameBoard[row+2][col+2] &&
//                        gameBoard[row][col] == gameBoard[row+3][col+3] &&
//                        gameBoard[row][col] != OPEN_SQUARE) {
//                    if (gameBoard[row][col] == PLAYER1_MOVE) score = score + 100;
//                    //if (gameBoard[row][col] == PLAYER2_MOVE) score = score - 100;
//                }
//            }
//        }
//        //check for win diagonally (lower left to upper right)
//        for (int row=3; row<ROW_SIZE; row++) { //3 to 5
//            for (int col=0; col<COL_SIZE-3; col++) { //0 to 3
//                if (gameBoard[row][col] == gameBoard[row-1][col+1] &&
//                        gameBoard[row][col] == gameBoard[row-2][col+2] &&
//                        gameBoard[row][col] == gameBoard[row-3][col+3] &&
//                        gameBoard[row][col] != OPEN_SQUARE) {
//                    if (gameBoard[row][col] == PLAYER1_MOVE) score = score + 100;
//                    //if (gameBoard[row][col] == PLAYER2_MOVE) score = score - 100;
//                }
//            }
//        }
//
//
//        return new int[] {0,0,score};
//    }
//
//    private int[] staticBoardEvaluator(int[][] BoardState) {
//        int value;
//        value = utility(BoardState)[2];
//
//
//
//        return new int[] {0,0,value};
//    }
//
//    private boolean isTie(int[][] gameBoard) {
//        boolean tiegame = true;
//        for (int row=0; row<ROW_SIZE; row++) {
//            for (int col=0; col<COL_SIZE; col++) {
//                if (gameBoard[row][col] == 0) tiegame = false;
//            }
//        }
//        return tiegame;
//    }
//
//
//    private boolean isWin() {
//        boolean win = false;
//
//        //check for win horizontally
//        for (int row=0; row<ROW_SIZE; row++) {
//            for (int col=0; col<COL_SIZE-3; col++) { //0 to 3
//                if (gameBoard[row][col] == gameBoard[row][col+1] &&
//                        gameBoard[row][col] == gameBoard[row][col+2] &&
//                        gameBoard[row][col] == gameBoard[row][col+3] &&
//                        gameBoard[row][col] != OPEN_SQUARE) {
//                    win = true;
//                }
//            }
//        }
//        //check for win vertically
//        for (int row=0; row<ROW_SIZE-3; row++) { //0 to 2
//            for (int col=0; col<COL_SIZE; col++) {
//                if (gameBoard[row][col] == gameBoard[row+1][col] &&
//                        gameBoard[row][col] == gameBoard[row+2][col] &&
//                        gameBoard[row][col] == gameBoard[row+3][col] &&
//                        gameBoard[row][col] != OPEN_SQUARE) {
//                    win = true;
//                }
//            }
//        }
//        //check for win diagonally (upper left to lower right)
//        for (int row=0; row<ROW_SIZE-3; row++) { //0 to 2
//            for (int col=0; col<COL_SIZE-3; col++) { //0 to 3
//                if (gameBoard[row][col] == gameBoard[row+1][col+1] &&
//                        gameBoard[row][col] == gameBoard[row+2][col+2] &&
//                        gameBoard[row][col] == gameBoard[row+3][col+3] &&
//                        gameBoard[row][col] != OPEN_SQUARE) {
//                    win = true;
//                }
//            }
//        }
//        //check for win diagonally (lower left to upper right)
//        for (int row=3; row<ROW_SIZE; row++) { //3 to 5
//            for (int col=0; col<COL_SIZE-3; col++) { //0 to 3
//                if (gameBoard[row][col] == gameBoard[row-1][col+1] &&
//                        gameBoard[row][col] == gameBoard[row-2][col+2] &&
//                        gameBoard[row][col] == gameBoard[row-3][col+3] &&
//                        gameBoard[row][col] != OPEN_SQUARE) {
//                    win = true;
//                }
//            }
//        }
//
//        return win;
//
//    }//end checkWinner
//}
