import kareltherobot.*;

public class ThreeBeeper implements Strategy
{
  public void doIt(UrRobot r)
  {
    r.putBeeper();
    r.putBeeper();
    r.putBeeper();
  }
}