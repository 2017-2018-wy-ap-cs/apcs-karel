import kareltherobot.*;

public class SwimAwayStrategy implements Strategy
{ 
  public void doIt (UrRobot r)
  {
    FishBot rfish = (FishBot) r;
    
    rfish.swim();
    rfish.putBeeper();
    rfish.swim();
  }
}
