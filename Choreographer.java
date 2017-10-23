import kareltherobot.*;
import java.awt.Color;

public class Choreographer extends UrRobot {
  
  private UrRobot a,b,c;
  
  public Choreographer(int row, int col, Direction d, int beep)
  {
    super(row,col,d,beep);
    a = new UrRobot(row+1,col-1,d,beep);
    b = new UrRobot(row+2,col-2,d,beep);
    c = new UrRobot(row+3,col-3,d,beep);
  }
  
  @Override
  public void move()
  {
    super.move();
    a.move();
    b.move();
    c.move();
  }
  
  // This class is incomplete you need to finish it.
  
  public static void main (String[] args)
  {
    World.readWorld("worlds/fig3-5.kwld");
    World.setVisible(true);
    World.setDelay(20);
    Choreographer boss = new Choreographer(5,10,West,10);
    boss.move();
  }
  
}
