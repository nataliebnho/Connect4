 /*  Name: Natalie Ho
  *  PennKey: natabnho
  *  Recitation: 204
  *
  *  Represents the Connect Four game. Makes an
  * empty board and allows players to choose between
  * One Player and Two player mode. Resets game once
  * a player has won, or there is a tie. 
  *
  */
public class ConnectFour {
  
  public static void main(String[] args) {
    boolean keepRunning = true;
    
    PennDraw.setCanvasSize(700, 600);
    
    //Always resets game, never ends
    while (true) {
      int[][] testBoardArr = new int[6][7];
      Board testBoard = new Board(7.0, 6.0, testBoardArr);
      
      PennDraw.clear();
      keepRunning = true;
      testBoard.drawMenuScreen();
      
      //exits after someone has won
      while (keepRunning) {
        
        if (PennDraw.hasNextKeyTyped()) {
          char c = PennDraw.nextKeyTyped();
          if (c == '2') {
            PennDraw.clear();
            testBoard.setNumPlayers(2);
            while (!testBoard.didPlayerWin()) {
              testBoard.draw();
              testBoard.drawDisks();
            }
            keepRunning = false;
          }
          
          if (c == '1') {
            PennDraw.clear(); 
            testBoard.setNumPlayers(1);
            while (!testBoard.didPlayerWin()) {
              testBoard.draw();
              testBoard.drawDisks();
            }
            keepRunning = false;
          }
        }
        
      }
      testBoard.drawGameCompleteScreen(); 
     
    }
    
  }
  
}