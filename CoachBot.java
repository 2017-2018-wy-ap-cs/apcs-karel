import kareltherobot.*;
import java.awt.Color;

public class CoachBot extends UrRobot {
  
  private Workout myTrainee;
  
  public CoachBot(Workout trainee)
  {
    super(1,1,East,0);
    myTrainee = trainee;
  }
  
  public void leadPractice()
  {
    myTrainee.doWorkout();
    turnLeft();turnLeft();turnLeft();turnLeft();
    turnLeft();turnLeft();turnLeft();turnLeft();
    myTrainee.doWorkout();
  }
  
  public static void main(String[] args)
  {
    World.setVisible(true);
    
    DogBot fido = new DogBot(4,6,East,0);
    CoachBot geiger = new CoachBot(fido);
    geiger.leadPractice();
  }
  
}
