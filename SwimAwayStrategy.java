import kareltherobot.*;

public class SwimAwayStrategy implements Strategy
{ 
  private int numswim;
  
  public SwimAwayStrategy (int ntimes)
  {
    numswim = ntimes;
  }
  
  public void doIt (UrRobot r)
  {
    FishBot rfish = (FishBot) r;
    
    for(int k=0; k<numswim; k++) {
      rfish.swim();
    }
    rfish.putBeeper();
    rfish.swim();
  }
}
