import kareltherobot.*;

public class CornerStrat implements Strategy
{
  private int count;
  
  public CornerStrat(int beeperCount)
  {
    count=beeperCount;
  }
  public String toString() {
    return "[CornerStrat: put "+count+" beepers on a corner]";
  }
  public void doIt(UrRobot r)
  {
    System.err.println("CornerStrat for: "+count);
    for(int k=0;k<count;k += 1)
    {
      r.putBeeper();
    }
    r.turnLeft();
  }
}
