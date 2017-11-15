import kareltherobot.*;

public class GetBeeperStrat implements Strategy
{
  public void doIt (UrRobot r)
  {
    r.pickBeeper();
    System.out.println("Student: Thanks for the help.");
    r.move();
    r.move();
  }
}
