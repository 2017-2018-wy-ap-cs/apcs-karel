import kareltherobot.*;
import java.awt.Color;

public class StrategyLayer extends UrRobot {
  private Strategy myStrategy = null;
  public StrategyLayer(int r, int c, Direction d, int beep, Strategy strat)
  {
    super(r,c,d,beep);
    myStrategy = strat;
  }
  
  public void doStrat()
  {
    myStrategy.doIt(this);
  }
  
  public void setStrategy (Strategy s) 
  {
    myStrategy = s;
  }
  
  public static void main (String[] args)
  {
    World.readWorld("worlds/fig1-2a.kwld");
    World.setVisible(true);
    World.setDelay(100);
    
    StrategyLayer kilo = 
      new StrategyLayer(4,1,East,5, new TwoBeeper());
    
    kilo.doStrat();
    kilo.move();
    kilo.doStrat();
    kilo.move();
    
    
    kilo.turnOff();
    
  }
  
}
