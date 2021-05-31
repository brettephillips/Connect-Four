/*
 * @authors Tanner and Brett Phillips
 * @version 03/14/2016
 * Description: The MainGUI class creates the Connect 4 game board with the names entered in PlayerChoice. It then keeps
 *              track of wins, checks to make sure that player moves are valid and upon a win displays the winner to the
 *              user via a JOptionPane.
 * Course: ISTE-121
 */

//imports the necesary classes for GUI program.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/*
 * The MainGUI class creates the game board and loads the pieces into the JFrame. 
 *       Players will then be able to take turns placing their chips on 
 *       the game board by pressing the respective buttons 1-7.
 */
public class MainGUI
{
   /*
    * @param JFrame frame Creates a JFrame object that will hold all of the components of the GUI program. 
    * @param JMenuBar menuBar Creates a menu bar for the program which contains a file and help dropdown menu.
    * @param JMenu fileMenu Creates a file dropdown menu which contains a new game and an exit button that will terminate the program.
    * @param JMenu helpMenu Creates a help dropdown menu which contains an instruction button that will show a pop up dialog box with information about the game.
    * @param JMenuItem newGame Creates a menu item that will allow the user to start a new game when clicked.
    * @param JMenuItem exitMenu Creates a menu item that will allow the user to terminate the program when clicked.
    * @param JMenuItem instructions Creates a menu item that will show a pop up dialog box with information about how to play the game when it is clicked.
    * @param JPanel mainPanel Creates a panel where the JMenuBar, board, and buttons will be placed.
    * @param JPanel panel Creates a panel where the board is placed.
    * @param JPanel panelTwo Creates a panel where the player names are placed.
    * @param JPanel buttonPanel Creates a panel where the buttons 1-7 are placed, which on click, places the chips on the board.
    * @param JButton colButton1 Creates a button that will drop a chip in the first column when clicked.
    * @param JButton colButton2 Creates a button that will drop a chip in the second column when clicked.
    * @param JButton colButton3 Creates a button that will drop a chip in the third column when clicked.
    * @param JButton colButton4 Creates a button that will drop a chip in the fourth column when clicked.
    * @param JButton colButton5 Creates a button that will drop a chip in the fifth column when clicked.
    * @param JButton colButton6 Creates a button that will drop a chip in the sixth column when clicked.
    * @param JButton colButton7 Creates a button that will drop a chip in the seventh column when clicked.
    * @param JLabel playerOne Creates a label that will display the name of player one.
    * @param JLabel playerTwo Creates a label that will display the name of player two.
    * @param String finalPlayerOne Creates a String variable that will get the text that was entered in the player one textfield from the prior screen and place it on the window.
    * @param String finalPlayerTwo Creates a String variable that will get the text that was entered in the player two textfield from the prior screen and place it on the window.
    * @param int moveCount Creates an int variable that will keep count of the consecutive colored chips to see if there is a winner.
    * @param boolean isYellowChip Creates a boolean variable that will determine which color's turn it is.
    * @param boolean isRedChip Creates a boolean variable that will determine which color's turn it is.
    * @param int PANNEL_WIDTH Creates a constant that will keep the width of the frame. 
    * @param int PANNEL_HEIGHT Creates a constant that will keep the height of the frame. 
    * @param boolean foundWinner Creates a boolean variable that will see if someone won the game, if so, it will present a dialog box with the winning player.
    * @param Icon theBoard Creates an object for the image of the board, so that it can be placed in the frame.
    * @param Icon yellowChip Creates an object for the image of the yellow pieces, so that it can be placed in the frame.
    * @param Icon redChip Creates an object for the image of the red pieces, so that it can be placed in the frame.
    * @param int scorePlayerOne Creates an int variable that will keep the score for the number of wins that player one has.
    * @param int scorePlayerTwo Creates an int variable that will keep the score for the number of wins that player two has.
    * @param String gamePlay Creates a String 2D array that takes in an 'e' (empty) character at the start of the game. As each chip is placed it will change from an 'e' to an 'r' or 'y' depending on which colored chip is placed. Also used to help determine a winner.
    */
   private JFrame frame;
   private JMenuBar menuBar;
   private JMenu fileMenu;
   private JMenu helpMenu;
   private JMenuItem newGame;
   private JMenuItem exitMenu;
   private JMenuItem instructions;
   private JPanel mainPanel;
   private JPanel panel;
   private JPanel panelTwo;
   private JPanel buttonPanel;
   private JButton colButton1;
   private JButton colButton2;
   private JButton colButton3;
   private JButton colButton4;
   private JButton colButton5;
   private JButton colButton6;
   private JButton colButton7;
   private JLabel playerOne;
   private JLabel playerTwo;
   private static String finalPlayerOne = null;
   private static String finalPlayerTwo = null;
   private int moveCount = 0;
   private boolean isYellowChip = false; 
   private boolean isRedChip = false;  
   private static final int PANNEL_WIDTH = 700;
   private static final int PANNEL_HEIGHT = 690;
   private boolean foundWinner = false; 
   private Icon theBoard = new ImageIcon("grid.png");
   private Icon yellowChip = new ImageIcon("yellowpiece.png");
   private Icon redChip = new ImageIcon("redpiece.png");
   private int scorePlayerOne;
   private int scorePlayerTwo; 
   private String[][] gamePlay = new String[][]
      {{"e","e","e","e","e","e","e"},
       {"e","e","e","e","e","e","e"},
       {"e","e","e","e","e","e","e"},
       {"e","e","e","e","e","e","e"}, 
       {"e","e","e","e","e","e","e"},
       {"e","e","e","e","e","e","e"}};   
  
   /*
    * MainGUI's default constructor creates a JFrame and adds a menu bar that contains
    *       menu items to the north of the frame. It creates a new board (inner class) object
    *       that places the image of the board to the center of the screen. Lastly, It creates
    *       JLabels on the south of the screen to show the players playing and the number of
    *       wins for each player.
    * @param String _playerOne Takes in the name of player one so that it can be placed on the bottom of the frame
    * @param String _playerTwo Takes in the name of player two so that it can be placed on the bottom of the frame
    */
   public MainGUI(String _playerOne, String _playerTwo)
   {
      //Assigns the String value of _playerOne to finalPlayerOne
      finalPlayerOne = _playerOne;
      //Assigns the String value of _playerTwo to finalPlayerTwo
      finalPlayerTwo = _playerTwo;
      //Creates a new JFrame object
      frame = new JFrame();
      //Sets the size of the frame using constant variables
      frame.setSize(PANNEL_WIDTH, PANNEL_HEIGHT);
      //Sets the title of the frame
      frame.setTitle("Connect 4");
      //Sets the layout of the frame to BorderLayout
      frame.setLayout(new BorderLayout());
      //Sets the frame so that the user cannot resize it     
      frame.setResizable(false);
      //Allows the user to close hte window and terminate the program by clicking the red X
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Sets the location of the frame to be in the middle of the page
      frame.setLocationRelativeTo(null);
   
      //Creates a new board object called al
      Board al = new Board();
   
      //Creates a new JMenuBar object called menuBar
      menuBar = new JMenuBar();
      //Creates a new JMenu object called fileMenu that takes in a String
      fileMenu = new JMenu("File");
      //Creates a new JMenuItem object called newGame that takes in a String
      newGame = new JMenuItem("New Game");
      //Adds an action to the newGame item
      newGame.addActionListener(al);
      //Creates a new JMenuItem object called exitMenu that takes in a String
      exitMenu = new JMenuItem("Exit");
      //Adds an action to the exitMenu item
      exitMenu.addActionListener(al);
      //Adds the JMenuItem to the file menu
      fileMenu.add(newGame);
      //Adds the JMenuItem to the file menu
      fileMenu.add(exitMenu);
   
      //Creates a new JMenu object called helpMenu that takes in a String
      helpMenu = new JMenu("Help");
      //Creates a new JMenuItem object called instructions that takes in a String
      instructions = new JMenuItem("Instructions");
      //Adds an action to the instructions item
      instructions.addActionListener(al);
      //Adds the JMenuItem to the help menu
      helpMenu.add(instructions);
      //Adds the file menu to the menubar
      menuBar.add(fileMenu);
      //Adds the help menu to the menubar
      menuBar.add(helpMenu);
        
      //Creates a panel object called buttonPanel, which will hold all of the buttons in GridLayout
      buttonPanel = new JPanel(new GridLayout(0,7));
      //Creates a JButton called colButton1 that takes in a number String for its repective column
      colButton1 = new JButton("1");
      //Creates a JButton called colButton2 that takes in a number String for its repective column
      colButton2 = new JButton("2");
      //Creates a JButton called colButton3 that takes in a number String for its repective column
      colButton3 = new JButton("3");
      //Creates a JButton called colButton4 that takes in a number String for its repective column
      colButton4 = new JButton("4");
      //Creates a JButton called colButton5 that takes in a number String for its repective column
      colButton5 = new JButton("5");
      //Creates a JButton called colButton6 that takes in a number String for its repective column
      colButton6 = new JButton("6");
      //Creates a JButton called colButton7 that takes in a number String for its repective column
      colButton7 = new JButton("7");
      //Adds an action to the button for column one
      colButton1.addActionListener(al);
      //Adds an action to the button for column two
      colButton2.addActionListener(al);
      //Adds an action to the button for column three
      colButton3.addActionListener(al);
      //Adds an action to the button for column four
      colButton4.addActionListener(al);
      //Adds an action to the button for column five
      colButton5.addActionListener(al);
      //Adds an action to the button for column six
      colButton6.addActionListener(al);
      //Adds an action to the button for column seven
      colButton7.addActionListener(al);
      //Adds the button to the panel
      buttonPanel.add(colButton1);
      //Adds the button to the panel
      buttonPanel.add(colButton2);
      //Adds the button to the panel
      buttonPanel.add(colButton3);
      //Adds the button to the panel
      buttonPanel.add(colButton4);
      //Adds the button to the panel
      buttonPanel.add(colButton5);
      //Adds the button to the panel
      buttonPanel.add(colButton6);
      //Adds the button to the panel
      buttonPanel.add(colButton7);
      //Creates a JPanel object called panel where the board will be placed and sets it to BorderLayout
      panel = new JPanel(new BorderLayout());  
      //Adds the board object to the panel in the center of the frame      
      panel.add(new Board(), BorderLayout.CENTER);      
      //Creates a JPanel object called panelTwo where the player names and score will be placed and sets it to GridLayout
      panelTwo = new JPanel(new GridLayout(1, 3));     
      //Creates a new JLabel that will take in the player one's name and score and center align it
      playerOne = new JLabel(finalPlayerOne, SwingConstants.CENTER );
      //Sets the label to be opaque so a color can be show in the background
      playerOne.setOpaque(true);
      //Sets the background color of player one to red to represent what color chip they are
      playerOne.setBackground(Color.RED);
      //Adds the label to the panel
      panelTwo.add(playerOne);
      //Creates a new JLabel that will take in the player two's name and score and center align it
      playerTwo = new JLabel(finalPlayerTwo, SwingConstants.CENTER );
      //Sets the label to be opaque so a color can be show in the background
      playerTwo.setOpaque(true);
      //Sets the background color of player two to yellow to represent what color chip they are
      playerTwo.setBackground(Color.YELLOW);
      //Adds the label to the panel
      panelTwo.add(playerTwo);
      //Creates a new JPanel that will take in the three panels, setting one to the north, one to the center, and the other in the south of the frame
      mainPanel = new JPanel(new BorderLayout());
      //Adds the buttonPanel to the mainPanel and puts it to the north of the frame
      mainPanel.add(buttonPanel, BorderLayout.NORTH);
      //Adds the panel to the mainPanel and puts it to the center of the frame
      mainPanel.add(panel,BorderLayout.CENTER);
      //Adds the panelTwo to the mainPanel and puts it to the south of the frame
      mainPanel.add(panelTwo,BorderLayout.SOUTH);
      //Adds the menubar to the north of the frame
      frame.add(menuBar, BorderLayout.NORTH);
      //Adds the mainPanel to the center of the frame
      frame.add(mainPanel, BorderLayout.CENTER);        
      //Sets the frame to be visible by the user
      frame.setVisible(true);      
   }
   
   /*
    * setColorChip() method that is to determine which color's turn it is, so that
    * the user or player can place their respective chip.
    */    
   public void setColorChip()
   {
      /* If isRedChip currently equals true and isYellowChip currently equals false, 
       * then it will set isRedChip to false and isYellowChip to true showing that it is yellow's turn 
       */
      if(isRedChip == true && isYellowChip == false)
      {
         isRedChip = false;
         isYellowChip = true;
      }
      
      /* If isRedChip currently equals false and isYellowChip currently equals true, 
       * then it will set isRedChip to true and isYellowChip to false showing that it is red's turn 
       */      
      else if( isYellowChip == true && isRedChip == false)
      {
         isRedChip = true;
         isYellowChip = false;
      }
      
      //If both chips are true, then it will return an error saying that both chips are active
      else if(isYellowChip == true && isRedChip == true)
      {
         System.err.println("Both chips are active.");
      }
      //If both chips are false, then it will set redChip to true and yellowChip to false so that red can start the game off
      else if(isYellowChip == false && isRedChip == false)
      {
         isRedChip = true;
         isYellowChip = false;
      }
   }
   
   //MainGUI's main method
   public static void main(String[] args)
   {
      MainGUI game = new MainGUI(finalPlayerOne, finalPlayerTwo);
   }

   /*
    * exitProgram() method terminates the program
    */
   public void exitProgram()
   {
      System.exit(0);
   }
 
   /*
    * The Board Inner class that extends JPanel and implements action listener. Its function is to listen to the buttons
    *       and, if they are pressed, place the chip in its respective column.
    */
   class Board extends JPanel implements ActionListener
   {
      /*
       * @param boolean KeepGoing Creates a boolean attribute that will remain true until the winner is found.
       * @param String INVALID_MOVE_MESSAGE Creates a constant variable that will present a dialog box letting the user know that the move they mad was invalid.
       * @param int indexCounterCol1 Creates an int variable that will keep track of how many spots are available in column one for the user to move into. If no spots are left, it is an invalid move.
       * @param int indexCounterCol2 Creates an int variable that will keep track of how many spots are available in column two for the user to move into. If no spots are left, it is an invalid move.
       * @param int indexCounterCol3 Creates an int variable that will keep track of how many spots are available in column three for the user to move into. If no spots are left, it is an invalid move.
       * @param int indexCounterCol4 Creates an int variable that will keep track of how many spots are available in column four for the user to move into. If no spots are left, it is an invalid move.
       * @param int indexCounterCol5 Creates an int variable that will keep track of how many spots are available in column five for the user to move into. If no spots are left, it is an invalid move.
       * @param String instructionsDialog Creates the text that will explain the directions for the game and be put in a dialog box. 
       * @param int currentRow Creates an int variable that will help determine if there is a winner by checking the rows to see if colors are repeating. 
       * @param int currentColumn Creates an int variable that will help determine if there is a winner by checking the columns to see if colors are repeating. 
       */
      private boolean keepGoing = true;
      private String INVALID_MOVE_MESSAGE = "You made an invalid move.";
      private int indexCounterCol1 = 5;
      private int indexCounterCol2 = 5;
      private int indexCounterCol3 = 5;
      private int indexCounterCol4 = 5;
      private int indexCounterCol5 = 5;
      private int indexCounterCol6 = 5;
      private int indexCounterCol7 = 5;
      private String instructionsDialog = "Game Description:\nConnect 4 is a two player game in which players attempt to align their colored chips four in a row.\nPlayers place their chips into the top of the board, which then fall to the lowest available spot in that column.\nPlayers place one chip per turn. The goal of the game is to align four of your chips in either horizontal, diagonal, or vertical alignment.\nThe first person to align their chips in the ways described above, is the winner.\n\nThe design of this game is close to that of the traditional version of this game.\n\n\tGame URL: https://en.wikipedia.org/wiki/Connect_Four";
      private int currentRow = 0;
      private int currentColumn = 0;
      
      /*
       * resetBoard() method resets the entire board and start a new game.
       */
      public void resetBoard()
      {
         //For loop that will set the entire 2D array back to an empty board with 'e' (empty) characters.
         for( int i = 0; i<=5; i++)
         {
            for(int j = 0; j<=6; j++)
            {
               gamePlay[i][j] = "e";
            }      
         }
         
         //Sets all of the counters for each column back to zero
         indexCounterCol1 = 5;
         indexCounterCol2 = 5;
         indexCounterCol3 = 5;
         indexCounterCol4 = 5;
         indexCounterCol5 = 5;
         indexCounterCol6 = 5;
         indexCounterCol7 = 5;
         SwingUtilities.invokeLater(
               new Runnable(){
                  public void run(){
                     repaint();             
                  }}); 
      }
          
      /*
       * The actionPerformed() method that is to listen to the JButtons, then in return, perform
       * a certain action.
       * @param ActionEvent ae Allows the action command to be performed 
       */          
      public void actionPerformed(ActionEvent e)
      {  
         //Assigns the action command to a String variable called actionString    
         String actionString = e.getActionCommand();
         
         //If new game is pressed then it will reset the board by using the resetBoard method and set the buttons to be enabled
         if (actionString.equals("New Game"))
         {
            resetBoard();
            
            colButton1.setEnabled(true);
            colButton2.setEnabled(true);
            colButton3.setEnabled(true);
            colButton4.setEnabled(true);
            colButton5.setEnabled(true);
            colButton6.setEnabled(true);
            colButton7.setEnabled(true); 
                        
            //Decides who will end up going first based off of the last move 
            if(isRedChip)
            {
               JOptionPane.showMessageDialog(null, finalPlayerTwo +", you will go first");
            }
            else
            {
               JOptionPane.showMessageDialog(null, finalPlayerOne +", you will go first");
            }
            
            
         }
         //If exit is pressed then it will terminate the program using the exitProgram method.
         else if (actionString.equals("Exit"))
         {
            exitProgram();
         }
         //If instructions is pressed then it will show a dialog box with the instructions on how to play the game.
         else if (actionString.equals("Instructions"))
         {
            JOptionPane.showMessageDialog(frame, instructionsDialog);
         }
         else
         {
            //If the 1 button is pressed it will do the following actions
            if(actionString.equals("1"))
            {
               //Takes in the counter for column one that starts at 5
               switch(indexCounterCol1)
               {
                  //If it is -1, then it presents a dialog box saying it is an invalid move
                  case -1: 
                     JOptionPane.showMessageDialog(null, INVALID_MOVE_MESSAGE);
                     break;
               
                  //If the counter is between 0-5, then it will set the color of the chip and place it in the 2D array
                  case 5:
                  case 4:
                  case 3:
                  case 2:
                  case 1:
                  case 0:
                     setColorChip();
                     if(isYellowChip == true)
                     {
                        //Places a 'y' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol1][0] = "y";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol1--;
                     }
                     else if(isRedChip == true)
                     {
                        //Places an 'r' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol1][0] = "r";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol1--;
                     }
               }
               //Increments moveCount
               moveCount++;
            }
            //If the 2 button is pressed it will do the following actions
            if(actionString.equals("2"))
            {
               //Takes in the counter for column two that starts at 5
               switch(indexCounterCol2)
               {
                  //If it is -1, then it presents a dialog box saying it is an invalid move
                  case -1: 
                     JOptionPane.showMessageDialog(null, INVALID_MOVE_MESSAGE);
                     break;
               
                  //If the counter is between 0-5, then it will set the color of the chip and place it in the 2D array
                  case 5:
                  case 4:
                  case 3:
                  case 2:
                  case 1:
                  case 0:
                     setColorChip();
                     if(isYellowChip == true)
                     {
                        //Places a 'y' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol2][1] = "y";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol2--;
                     }
                     else if(isRedChip == true)
                     {
                        //Places an 'r' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol2][1] = "r";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol2--;
                     }
               }
               //Increments moveCounter
               moveCount++;
            }
            //If the 3 button is pressed it will do the following actions
            if(actionString.equals("3"))
            {
               //Takes in the counter for column three that starts at 5
               switch(indexCounterCol3)
               {
                  //If it is -1, then it presents a dialog box saying it is an invalid move
                  case -1: 
                     JOptionPane.showMessageDialog(null, INVALID_MOVE_MESSAGE);
                     break;
               
                  //If the counter is between 0-5, then it will set the color of the chip and place it in the 2D array
                  case 5:
                  case 4:
                  case 3:
                  case 2:
                  case 1:
                  case 0:
                     setColorChip();
                     if(isYellowChip == true)
                     {
                        //Places a 'y' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol3][2] = "y";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol3--;
                     }
                     else if(isRedChip == true)
                     {
                        //Places an 'r' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol3][2] = "r";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol3--;
                     }
               } 
               //Increments moveCounter
               moveCount++;
            }
            //If the 4 button is pressed it will do the following actions
            if(actionString.equals("4"))
            {
               //Takes in the counter for column four that starts at 5
               switch(indexCounterCol4)
               {
                  //If it is -1, then it presents a dialog box saying it is an invalid move
                  case -1: 
                     JOptionPane.showMessageDialog(null, INVALID_MOVE_MESSAGE);
                     break;
               
                  //If the counter is between 0-5, then it will set the color of the chip and place it in the 2D array
                  case 5:
                  case 4:
                  case 3:
                  case 2:
                  case 1:
                  case 0:
                     setColorChip();
                     if(isYellowChip)
                     {
                        //Places a 'y' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol4][3] = "y";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol4--;
                     }
                     else if(isRedChip)
                     {
                        //Places an 'r' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol4][3] = "r";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol4--;
                     }
               } 
               //Increments moveCounter
               moveCount++;
            }
            //If the 5 button is pressed it will do the following actions
            if(actionString.equals("5"))
            {
               //Takes in the counter for column five that starts at 5
               switch(indexCounterCol5)
               {
                  //If it is -1, then it presents a dialog box saying it is an invalid move
                  case -1: 
                     JOptionPane.showMessageDialog(null, INVALID_MOVE_MESSAGE);
                     break;
               
                  //If the counter is between 0-5, then it will set the color of the chip and place it in the 2D array
                  case 5:
                  case 4:
                  case 3:
                  case 2:
                  case 1:
                  case 0:
                     setColorChip();
                     if(isYellowChip == true)
                     {
                        //Places a 'y' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol5][4] = "y";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol5--;
                     }
                     else if(isRedChip == true)
                     {
                        //Places an 'r' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol5][4] = "r";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol5--;
                     }
               } 
               //Increments moveCounter
               moveCount++;
            }
            //If the 6 button is pressed it will do the following actions
            if(actionString.equals("6"))
            {
               //Takes in the counter for column six that starts at 5
               switch(indexCounterCol6)
               {
                  //If it is -1, then it presents a dialog box saying it is an invalid move
                  case -1: 
                     JOptionPane.showMessageDialog(null, INVALID_MOVE_MESSAGE);
                     break;
               
                  //If the counter is between 0-5, then it will set the color of the chip and place it in the 2D array
                  case 5:
                  case 4:
                  case 3:
                  case 2:
                  case 1:
                  case 0:
                     setColorChip();
                     if(isYellowChip == true)
                     {
                        //Places a 'y' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol6][5] = "y";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol6--;
                     }
                     else if(isRedChip == true)
                     {
                        //Places an 'r' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol6][5] = "r";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol6--;
                     }
               } 
               //Increments moveCount
               moveCount++;
            }
            //If the 7 button is pressed it will do the following actions
            if(actionString.equals("7"))
            {
               //Takes in the counter for column sevem that starts at 5
               switch(indexCounterCol7)
               {
                  //If it is -1, then it presents a dialog box saying it is an invalid move
                  case -1: 
                     JOptionPane.showMessageDialog(null, INVALID_MOVE_MESSAGE);
                     break;
               
                  //If the counter is between 0-5, then it will set the color of the chip and place it in the 2D array
                  case 5:
                  case 4:
                  case 3:
                  case 2:
                  case 1:
                  case 0:
                     setColorChip();
                     if(isYellowChip == true)
                     {
                        //Places a 'y' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol7][6] = "y";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol7--;
                     }
                     else if(isRedChip == true)
                     {
                        //Places an 'r' in the 2D array to represent which color was placed where
                        gamePlay[indexCounterCol7][6] = "r";
                        //Decrements counter so it will know how many available spots are left to move into
                        indexCounterCol7--;
                     }
               }
               //Increments moveCount
               moveCount++;
            }
         }
      }
    
     /* 
      * The findWinner() method that checks all of the possible win cases on the piece that was placed.
      * @param currentRow are the current pieces position that will be checked to see if there is four in a row.
      * @param currentCol are the current pieces position that will be checked to see if there is four in a row.
      */
      public boolean findWinner(int currentRow, int currentCol)
      {
         //Assigns the 2D array to a String called currentPiece, so that it can test to see if there is a winner
         String currentPiece = gamePlay[currentRow][currentCol];
        
         //If the currentPiece equals 'e', then it is empty and will return false since there is no winner
         if(currentPiece.equals("e"))
         {
            return false;
         }         
         try
         {
            int matchFound = 1;               
         
            //The first piece is included in the four count so this is how i account for it. 
            //Checks the vertical positions above it
            for(int i = currentRow+1; i <= currentRow+3; i++)
            {
               if(i>5){
                  break;
               }
               //It will compare the currentPieces as it goes through the vertical positions, incrementing matchFound each time if they are the same color 
               if(currentPiece.equals(gamePlay[i][currentCol]))
               {
                  matchFound++;                     
               }
            }
            //If matchFound equals four, then some player got four in a row and it will return foundWinner to be true
            if(matchFound == 4)
            {
               matchFound = 1;
               foundWinner = true;
               return true;
            }
            //If it did not find four in a row, then it will set matchFound back to 1
            else
            {
               matchFound = 1;              
            }
               
            //The first piece is included in the four count so this is how i account for it.              
            //Checks the horizontal positions to the right
            for(int j = currentCol+1; j<=currentCol+3; j++)
            {  
               if(j>6){
                  break;
               }
               //It will compare the currentPieces as it goes through the horizontal positions, incrementing matchFound each time if they are the same color 
               if(currentPiece.equals(gamePlay[currentRow][j]))
               {
                  matchFound++;
               }
            }
            //If matchFound equals four, then some player got four in a row and it will return foundWinner to be true
            if(matchFound == 4)
            {
               matchFound = 1;
               foundWinner = true;
               return true;
            }
            //If it did not find four in a row, then it will set matchFound back to 1
            else
            {
               matchFound = 1;              
            }
            //The first piece is included in the four count so this is how i account for it.               
            //Checks the horizontal positions to the left
            for(int i = currentCol-1; i>=currentCol-3; i--)
            {
               if(i<0){
                  break;
               }
               //It will compare the currentPieces as it goes through the horizontal positions, incrementing matchFound each time if they are the same color 
               if(currentPiece.equals(gamePlay[currentRow][i]))
               {
                  matchFound++;
               }
            }
            //If matchFound equals four, then some player got four in a row and it will return foundWinner to be true
            if(matchFound == 4)
            {
               matchFound = 1;
               return true;
            }
            //If it did not find four in a row, then it will set matchFound back to 1
            else
            {
               matchFound = 1;              
            }
            
            //The first piece is included in the four count so this is how i account for it.               
            //Checks the diagonal positions up and to the right            
            for(int i = 1; i<=3; i++){
               if(currentRow+i>5 || currentCol-i<0){
                    break;
                 }
               if(currentPiece.equals(gamePlay[currentRow+i][currentCol-i])){
                 
                 matchFound++;
               }
            }
               
            //If matchFound equals four, then some player got four in a row and it will return foundWinner to be true
            if(matchFound == 4)
            {
               matchFound = 1;
               return true;
            }
            //If it did not find four in a row, then it will set matchFound back to 1
            else
            {
               matchFound = 1;              
            }
            
            //The first piece is included in the four count so this is how i account for it.               
            //Checks the diagonal positions up and to the left                 
            for(int i = 1; i<=3; i++){
               if(currentRow+i>5 || currentCol+i>6){
                    break;
                 }
               if(currentPiece.equals(gamePlay[currentRow+i][currentCol+i])){
                 
                 matchFound++;
               }
            }
            //If matchFound equals four, then some player got four in a row and it will return foundWinner to be true
            if(matchFound == 4)
            {
               matchFound = 1;
               return true;
            }
            //If it did not find four in a row, then it will set matchFound back to 1
            else
            {
               matchFound = 1;              
            }  
                                                 
         }                       
         //Catches an ArrayIndexOutOfBoundsException
         catch(ArrayIndexOutOfBoundsException e){
         }       
         return foundWinner;
         
      }
      
      /*
       * The paintPieces() method that will paint the pieces onto the board after the user
       *       pushes the button for the respective column.
       */
      public void paintPieces(Graphics g)
      {
         //For loop that will go through the rows and columns to find the position where the user wants to place the chip
         for(int rows = 0; rows < 6; rows++)
         {
            for(int columns = 0; columns < 7; columns++)
            {
               //If the String value of the 2D array equals 'r', then it will print a red chip in the respective spot 
               if(gamePlay[rows][columns].equals("r"))
               {
                  redChip.paintIcon(this, g , columns*100, rows*100);
               }
               //If the String value of the 2D array equals 'y', then it will print a red chip in the respective spot 
               else if(gamePlay[rows][columns].equals("y"))
               {
                  yellowChip.paintIcon(this, g, columns*100, rows*100);
               }
               //If it finds a winner then the method will be called and keepGoing will be set to false
               if(findWinner(rows,columns))
               {
                  keepGoing = false;
                  foundWinner = true;
               }
               SwingUtilities.invokeLater(
                     new Runnable(){
                        public void run(){
                           repaint();
                        }});     
            }
         }
      }  
      
      /*
       * The paintComponent() method that will paint the board on the screen and print the dialog box 
       *       when a winner is found.
       */
      protected void paintComponent(Graphics g) 
      {             
         super.paintComponent(g);      
         //If keep going equals true, then it will keep the board painted on the screen and continue to paint pieces as they are called on
         if(keepGoing)
         { 
            theBoard.paintIcon(this, g, 0, 0);
         
            paintPieces(g);
                              
            //If found winner is true, then it will print a dialog box for whichever player won the game.
            if(foundWinner)
            {
               if(moveCount%2==0)
               {
                  JOptionPane.showMessageDialog(null, finalPlayerTwo+" you won!\n\nTo play a new game:\nFile -> New Game");
                  //Increments the counter to show how many wins player two has
                  scorePlayerTwo++;
               }
               else
               {
                  JOptionPane.showMessageDialog(null, finalPlayerOne+" you won!\n\nTo play a new game:\nFile -> New Game");
                  //Increments the counter to show how many wins player one has
                  scorePlayerOne++;
               }
               
               foundWinner = false;
               
               //Prints the score of the game
               JOptionPane.showMessageDialog(null, "SCORE:\n\n" + finalPlayerOne+" = " + scorePlayerOne + "\n" + finalPlayerTwo + " = " + scorePlayerTwo);
            
               //Sets the buttons to false, so the players can start a new game
               colButton1.setEnabled(false);
               colButton2.setEnabled(false);
               colButton3.setEnabled(false);
               colButton4.setEnabled(false);
               colButton5.setEnabled(false);
               colButton6.setEnabled(false);
               colButton7.setEnabled(false);
               //resets the board
               resetBoard();
            }
         }
         //stops the game
         keepGoing = true;
      }          
   }
}
