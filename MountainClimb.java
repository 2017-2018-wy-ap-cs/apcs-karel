import kareltherobot.*;
import java.awt.Color;

public class MountainClimb implements Directions {
  public static void main (String[] args)
  {
    World.readWorld("worlds/ch2ex6.kwld");
    World.setVisible(true);
    World.setDelay(25);
    //World.showSpeedControl(true);
    
    int row = 1; 
    int col = 2;
    UrRobot karen = new UrRobot(row, col,
                                North, 1);
    karen.move();
    karen.turnLeft();
    karen.turnLeft();
    karen.turnLeft();
    karen.move();
    karen.turnLeft();
    karen.move();
    karen.move();
    karen.turnLeft();
    karen.turnLeft();
    karen.turnLeft();
    karen.move();
    karen.turnLeft();
    karen.move();
    karen.move();
    karen.turnLeft();
    karen.turnLeft();
    karen.turnLeft();
    karen.move();
    karen.putBeeper();
    karen.move();
    
    karen.turnOff();
  }
  
}
