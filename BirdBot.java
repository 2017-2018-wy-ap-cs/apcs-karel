import kareltherobot.*;
import java.awt.Color;

public class BirdBot extends StrategyLayer {
  public BirdBot(int r, int c, Direction d, int beep, Strategy strat)
  {
    super(r,c,d,beep,strat);
  }

  public void fly()
  {
    move();
    move();
    move();
  }
}
