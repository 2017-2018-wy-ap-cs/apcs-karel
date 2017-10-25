import kareltherobot.*;
import java.awt.Color;

public class DogBot extends UrRobot implements Workout {
  
  public DogBot(int r,int c, Direction d,int bbbb) { super(r,c,d,bbbb);}
  
  public void doWorkout()
  {
    move(); move(); move(); move(); move(); move(); move();
    turnLeft(); turnLeft();
    move(); move(); move(); move(); move(); move(); move();
    move(); move(); move(); move(); move(); move(); move();
    turnLeft(); turnLeft();
    move(); move(); move(); move(); move(); move(); move();
  }
}
