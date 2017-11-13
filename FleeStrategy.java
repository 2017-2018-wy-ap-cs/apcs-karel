import kareltherobot.*;

public class FleeStrategy implements Strategy
{
  
  public void doIt(UrRobot r)
  {
    MurderBot m = (MurderBot) r;
    
    m.flee();
  }
}
