import kareltherobot.*;

public class MurderBot extends StrategyLayer
{
  public MurderBot (int r, int c, Direction d, int beep, Strategy s)
  {
    super(r,c,d,beep,s);
  }
  
  public void stab()
  {
    putBeeper();
    putBeeper();
  }
  
  public void flee()
  {
    move();
  }
  
}