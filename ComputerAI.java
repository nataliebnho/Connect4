 /*  Name: Natalie Ho
  *  PennKey: natabnho
  *  Recitation: 204
  *
  *  Creates the Computer AI playing against
  * the human player in One-Player mode
  *
  */
public class ComputerAI {
  private Board board;
  
  /**
  * Initialize the ComputerAI with a Board object
  */
  public ComputerAI(Board board) {
    this.board = board; 
  }
  
 /**
  * Draws a yellow disk (so ComputerAI always 
  * plays second) at the first allowed position
  */
  public void computerDrawDisk() {
    boolean draw = false;
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < board.getBoardArr().length; i++) {
      for (int j = 0; j < board.getBoardArr()[i].length; j++) {
        if (board.checkAllowDisk(i, j)) {
          Disk d = new Disk("YELLOW", board.getWidth() / 18, 
                            j + 0.5, 5 - i + 0.5);
          d.draw();
          board.getBoardArr()[i][j] = -1;
          draw = true;
          break;
        }
      }
      if (draw == true) {
        break;
      }
    }
  }
  
}