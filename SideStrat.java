import kareltherobot.*;

public class SideStrat implements Strategy
{
  private int len;
  
  public SideStrat(int theLength)
  {
    len = theLength;
  }
  public String toString() {
    return "[SideStrat: put "+len+" beepers in a row]";
  }
  public void doIt(UrRobot r)
  {
    
    System.err.println("SideStrat for: "+len);
    for(int k=0;k<len;k += 1)
    {
      r.putBeeper();
      r.move();
    }
  }
}
