import kareltherobot.*;

public class DartTurn implements Strategy
{
  public void doIt(UrRobot r)
  {
    r.turnLeft();
    r.move();
  }
}