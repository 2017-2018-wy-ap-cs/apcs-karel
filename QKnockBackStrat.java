import kareltherobot.*;

public class QKnockBackStrat implements Strategy
{
  public void doIt(UrRobot r)
  {
    r.turnLeft();
    r.turnLeft();
    r.move();
    r.move();
    r.turnLeft();
    r.turnLeft();
  }
}
