import kareltherobot.*;
import java.awt.Color;

public class FishBot extends StrategyLayer {
  public FishBot(int r, int c, Direction d, int beep, Strategy strat)
  {
    super(r,c,d,beep,strat);
  }

  public void swim()
  {
    move();
    move();
  }
  
  public static void main(String[] args)
  {
    World.setVisible(true);
    World.setDelay(75);
    
    Strategy flee = new SwimAwayStrategy();
    FishBot dory = new FishBot(4,1,East,10,flee);
    
    dory.doStrat();
    dory.turnOff();
  }
}
