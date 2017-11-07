import kareltherobot.*;

public class TwoBeeper implements Strategy
{
  public void doIt(UrRobot r)
  {
    r.putBeeper();
    r.putBeeper();
  }
}