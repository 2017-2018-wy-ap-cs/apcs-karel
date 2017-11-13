import kareltherobot.*;

public class StabStrategy implements Strategy
{
  
  public void doIt(UrRobot r)
  {
    MurderBot m = (MurderBot) r;
    
    m.stab();
    m.setStrategy(new FleeStrategy());
  }
}
