import kareltherobot.*;
import java.awt.Color;

public class MultiThreadBot extends Robot {
  public MultiThreadBot (int r, int c, Direction d, int b)
  {
    super(r,c,d,b);
    World.setupThread(this); // each robot in its own thread
  }
  
  // this method always gets called to move the robot
  public void run()
  {
    move();
    turnLeft();
    move();
    turnLeft();
    turnLeft();
    turnLeft();
    move();
  }
  
  public static void main (String[] args)
  {
    World.setVisible(true);
    World.setDelay(50);
  
    MultiBot a = new MultiBot(1,6,North,10);
    MultiBot b = new MultiBot(2,1,East,8);
   
    World.startThreads();
    
  }
  
}
