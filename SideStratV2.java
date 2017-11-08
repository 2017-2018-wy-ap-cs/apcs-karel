import kareltherobot.*;

public class SideStratV2 extends SideStrat
{
  public SideStratV2(int n)
  {
    super(n);
  }
  
  public void doIt(UrRobot r)
  {
    PrettyBoxBot rpretty = (PrettyBoxBot) r;
    
    super.doIt(rpretty);
    rpretty.swapStrat();
  }
}
