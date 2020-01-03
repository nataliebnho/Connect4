 /*  Name: Natalie Ho
  *  PennKey: natabnho
  *  Recitation: 204
  *
  *  A class representing the board of the Connect
  * 4 Game. Can put in disks, implements the
  * Computer AI, and checks if the red or
  * yellow player has won.
  *
  */

public class Board {
  
   /**
  * Board fields: width and height are dimensions
  * of the board, numPlayers denotes one or two player
  * mode, and winner returns if there is a winner 
  * (initialized to false) and winnerIs says who is the 
  * winner. 
  */
  private static int[][] board;
  private boolean mouseListeningMode; 
  private double width, height;
  private boolean playerRed, playerYellow;
  private boolean winner;
  private String winnerIs;
  private int numPlayers; 
  
 /**
  * Initialize the board's member variables
  * with the same names as the inputs to those values.
  */
  public Board(double width, double height, int[][] board) {
    this.width = width;
    this.height = height;
    this.board = board;
    
    this.mouseListeningMode = true;
    
    PennDraw.setXscale(0, width);
    PennDraw.setYscale(0, height);
    
    this.playerRed = true;
    this.winner = false;
  }
  
 /**
  * Draws an empty board
  */
  public void draw() {
    PennDraw.clear(PennDraw.BLUE); 
    PennDraw.setPenColor(PennDraw.WHITE);
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 6; j++) {
        PennDraw.filledCircle(i + 0.5, j + 0.5, width / 16);
      }
    }
  }
  
 /**
  * Switches whose turn it is to play
  * betewen yellow player and red player
  */
  public void switchPlayers() {
    playerRed = !playerRed; 
  }
  
 /**
  * Checks if a position is allowed to be filled 
  * with a disk. If there is nothing directly below
  * the position or if there is already another disk 
  * in that position, returns false. 
  * Inputs: "row" and "column" positions
  * Outputs: returns true or false depending on whether
  * the position is valid to be filled
  */
  public boolean checkAllowDisk(int i, int j) {
    if (i == 5 && board[i][j] == 0) {
      return true;
    }
    else if (board[i][j] != 0 || board[i + 1][j] == 0) {
      return false;
    } 
    return true;
  }
  
  /**
  * Checks if red or yellow player has won horizontally
  * Inputs: a 2D array board 
  * Outputs: true or false whether a player has won
  */
  public boolean checkHorizontal(int[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length - 3; j++) {
        if (board[i][j] != 0 && board[i][j] == board[i][j + 1] && 
            board[i][j] == board[i][j + 2] && board[i][j] == 
              board[i][j + 3]) {
          if (board[i][j] > 0) {
            winnerIs = "Red Player"; 
          }
          if (board[i][j] < 0) {
            winnerIs = "Yellow Player";
          }
          return true;
        }
      }
    }
    return false;
  }
  
  /**
  * Checks if red or yellow player has won vertically
  * Inputs: a 2D array board 
  * Outputs: true or false whether a player has won
  */
  public boolean checkVertical(int[][] board) {
    for (int i = 0; i < board.length - 3; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != 0 && board[i][j] == board[i + 1][j] &&
               board[i][j] == board[i + 2][j] &&
               board[i][j] == board[i + 3][j]) {
          if (board[i][j] > 0) {
            winnerIs = "Red Player"; 
          }
          if (board[i][j] < 0) {
            winnerIs = "Yellow Player";
          }
          return true;
        }
      }
    }
    return false;
  }
 
 /**
  * Checks if red or yellow player has won diagaonlly
  * Inputs: a 2D array board 
  * Outputs: true or false whether a player has won
  */
  public boolean checkDiagonal(int[][] board) {
    for (int i = 0; i < board.length - 3; i++) {
      for (int j = 0; j < board[i].length - 3; j++) {
        if (board[i][j] != 0 && board[i][j] == board[i + 1][j + 1] && 
              board[i][j] == board[i + 2][j + 2] && 
              board[i][j] == board[i + 3][j + 3]) {
          if (board[i][j] > 0) {
            winnerIs = "Red Player"; 
          }
          if (board[i][j] < 0) {
            winnerIs = "Yellow Player";
          }
          return true;
        }
      }
    }
    
    for (int i = board.length - 1; i > 3; i--) {
      for (int j = 0; j < board[i].length - 3; j++) {
        if (board[i][j] != 0 && board[i][j] == board[i - 1][j + 1] && 
            board[i][j] == board[i - 2][j + 2] &&
            board[i][j] == board[i - 3][j + 3]) {
          
          if (board[i][j] > 0) {
            winnerIs = "Red Player"; 
          }
          if (board[i][j] < 0) {
            winnerIs = "Yellow Player";
          }
          return true;
        }
      }
    }
    return false;
  }
  
/**
  * Returns whether a player has won or not
  */
  public boolean didPlayerWin() {
    return winner;
  }
  
/**
  * Draws a disk where the player has clicked.
  * If game is in one player mode then the ComputerAI 
  * will go next. If game is in two player mode 
  * then disks switch off between red and yellow. 
  * Exits when there is a winner. 
  */
  public void drawDisks() {
    while (mouseListeningMode) {
      if (PennDraw.mousePressed()) {
        
        double x = PennDraw.mouseX();
        double y = PennDraw.mouseY();
        
        if (playerRed == true) {
          Disk d = new Disk("RED", width / 18, 
                            (int) x + 0.5, (int) y + 0.5); 
          if (checkAllowDisk(5 - (int) y, (int) x)) {
            d.draw();
            board[5 - (int) y][(int) x] = 1;
            if (numPlayers == 2) {
              switchPlayers(); 
            }
            else if (numPlayers == 1) {
              ComputerAI computerAI = new ComputerAI(this);
              if (checkHorizontal(board)) {
                mouseListeningMode = false;
                winner = checkHorizontal(board);
              }
              else if (checkVertical(board)) {
                mouseListeningMode = false;
                winner = checkVertical(board);
              }
              else if (checkDiagonal(board)) {
                mouseListeningMode = false;
                winner = checkDiagonal(board);
              }
              else {
                computerAI.computerDrawDisk();
              }
            }
          }
        }
        else {
          Disk d = new Disk("YELLOW", width / 18, 
                            (int) x + 0.5, (int) y + 0.5); 
          if (checkAllowDisk(5 - (int) y, (int) x)) {
            d.draw();
            board[5 - (int) y][(int) x] = -1; 
            if (numPlayers == 2) {
              switchPlayers(); 
            }
          }
        }
        
      }
      if (checkHorizontal(board)) {
        mouseListeningMode = false;
        winner = checkHorizontal(board);
      }
      if (checkVertical(board)) {
        mouseListeningMode = false;
        winner = checkVertical(board);
      }
      if (checkDiagonal(board)) {
        mouseListeningMode = false;
        winner = checkDiagonal(board);
      } 
      if (checkTie(board)) {
        mouseListeningMode = false;
        winner = checkTie(board); 
      }
    }
    
  }
  
/**
  * Checks there has been a tie
  * Inputs: a 2D array board 
  * Outputs: true or false whether there is a tie
  */
  public boolean checkTie(int[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 0) {
          return false;
        }
      }
    }
    winnerIs = "tie";
    return true;
  }
  
/**
  * Draws the game is over screen after
  * a slight delay
  */
  public void drawGameCompleteScreen() {
    
    try {
      Thread.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    PennDraw.clear();
    PennDraw.setPenColor(PennDraw.BLACK); 
    if (winnerIs.equals("Red Player")) {
      PennDraw.text(3.75, 3, "Red Player Wins!");
    }
    if (winnerIs.equals("Yellow Player")) {
      PennDraw.text(3.75, 3, "Yellow Player Wins!");
    }
    if (winnerIs.equals("tie")) {
      PennDraw.text(3.75, 3, "It's a tie!");
    }
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
/**
  * Draws the menu screen so players can 
  * choose between one and two player mode
  */
  public void drawMenuScreen() {
    PennDraw.text(3.75, 4, "Select a Game Mode:");
    PennDraw.text(3.75, 3, "Press " + 1 + " for One Player");
    PennDraw.text(3.75, 2, "Press " + 2 + " for Two Players");
  }
  
/**
  * Simple setter method: 
  * sets number of players 
  */
  public void setNumPlayers(int num) {
    this.numPlayers = num;
  }
  
/**
  * Simple getter method:
  * gets the width of the board
  */
  public double getWidth() {
    return width;
  }
  
/**
  * Simple getter method:
  * gets the board 2D array
  */
  public int[][] getBoardArr() {
    return board;
  }
  
}
