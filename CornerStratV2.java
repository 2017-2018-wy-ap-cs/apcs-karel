import kareltherobot.*;

public class CornerStratV2 extends CornerStrat
{
  public CornerStratV2(int beeperCount)
  {
    super(beeperCount);
  }
  
  public void doIt(UrRobot r)
  {
    PrettyBoxBot rpretty = (PrettyBoxBot) r;
    
    super.doIt(rpretty);
    rpretty.swapStrat();
  }
}
