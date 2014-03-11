/**
 *
 * Parses command line parameters and initializes the connect four game.
 *
 * @author Kevin Tierney
 * Code now follows standard Java conventions.
 * @version 28.2.2010
 * 
 *
 * @author Mai Ajspur
 * @version 1.2.2007
 *
 */


import javax.swing.*;
import java.net.URLClassLoader;
import java.lang.reflect.*;

public class ShowGame
{

    public static String HUMAN_CMD = "human";

    /**
     * Valid arguments: player1_logic player2_logic cols  rows
     * using the name "human" in one of the logics will activate the default human agent
     * standard values for cols and rows is 7x6
     */
    public static void main(String[] args)
    {
        IGameLogic player1 = null;
        IGameLogic player2 = null;
        int cols = 4;
        int rows = 6;
        
        boolean err = args.length < 2;
        String errMsg = "";

        if(args.length >= 2) {
            try {
                player1 = parseGameLogicParam(args[0]);
                player2 = parseGameLogicParam(args[1]);
            } catch(ClassNotFoundException cnf) {
                errMsg = cnf.toString();
                err = true;
            } catch(NoSuchMethodException nsme) {
                errMsg = "Your GameInstance had no constructor.";
                err = true;
            } catch(InstantiationException ie) {
                errMsg = "Your GameInstance could not be instantiated.";
                err = true;
            } catch(IllegalAccessException iae) {
                errMsg = "Your GameInstance caused an illegal access exception.";
                err = true;
            } catch(InvocationTargetException ite) {
                errMsg = "Your GameInstance constructor threw an exception: " + ite.toString();
                err = true;
            }

            if(!err && player1 == null && player2 == null) {
                errMsg = "Two human players not allowed.";
                err = true;
            }
        }
        if(args.length >= 3) {
            try {
                cols = Integer.parseInt(args[2]);
            } catch(NumberFormatException nfe) {
                errMsg = "Could not parse column value: " + args[2];
                err = true;
            }
        }
        if(args.length >= 4) {
            try {
                rows = Integer.parseInt(args[3]);
            } catch(NumberFormatException nfe) {
                errMsg = "Could not parse row value: " + args[2];
                err = true;
            }
        }
        
        if(err) {
            printHelp(errMsg);
            System.exit(1);
        }
        
        if(player1 != null) {
            player1.initializeGame(cols,rows,1);
        }
        if(player2 != null) {
            player2.initializeGame(cols,rows,2);
        }
        
        FourConnectGUI g = new FourConnectGUI(player1,player2,cols,rows);
        
        // Setup of the frame containing the game
        JFrame f = new JFrame();
        f.setSize(1000,800);
        f.setTitle("Four Connect");
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(g);    
        f.setVisible(true);
    }

    public static void printHelp(String errMsg) {
        if(!errMsg.equals("")) {
            System.err.println(errMsg);
        }
        System.err.println("Usage: java ShowGame GameLogic1 GameLogic2 [cols rows]");
        System.err.println("GameLogic{1,2} may be:");
        System.err.println("\t" + HUMAN_CMD  + "\t\t - Indicates a human will be playing.");
        System.err.println("\tGameLogic\t - Specifies a GameLogic class extending IGameLogic to use as a competitor.");
        System.err.println("\tcols/rows\t - Must be integers greater than 0 and default to 7 and 6 respectively.");
    }

    /*
     * @param cmdParam String from the command line that is either HUMAN_CMD or a path to a java class inheriting IGameLogic
     * @throws TBD
     * @returns IGameLogic if a class was specified, null if human was specified
     */
    public static IGameLogic parseGameLogicParam(String cmdParam) 
            throws ClassNotFoundException, NoSuchMethodException, 
                   InstantiationException, IllegalAccessException,
                   InvocationTargetException {
        IGameLogic retGL = null;
        if(!cmdParam.equalsIgnoreCase(HUMAN_CMD)) {
            retGL = (IGameLogic)Class.forName(cmdParam).getConstructor().newInstance();
        }
        return retGL;
    }

}
