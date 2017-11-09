import kareltherobot.*;
import java.awt.Color;

public class PrettyBoxBot extends StrategyLayer {
  private Strategy s1 = null;
  private Strategy s2 = null;
  
  public PrettyBoxBot(int r, int c, Direction d, int beep, 
                      Strategy ss1, Strategy ss2)
  {
    super(r,c,d,beep,ss1);
    s1=ss1;
    s2=ss2;
  }
  
  public void doSideCorner()
  {
    setStrategy(s1);
    doStrat();
    setStrategy(s2);
    doStrat();
  }
  
  public void makeBox()
  {
    doSideCorner();
    doSideCorner();
    doSideCorner();
    doSideCorner();
    move();
  }
  
  public void makeBoxV2()
  {
    for(int k=0; k<8; k++) {
      doStrat();
    }
  }   
  
  public void swapStrat()
  {
    Strategy tempHelper = s1;
    s1 = s2;
    s2 = tempHelper;
    setStrategy(s1);
    System.err.println("Current strategy: "+s1);
  }
  
  public static void main(String[] args)
  {
    World.setVisible(true);
    World.setDelay(10);
    
    PrettyBoxBot betty = new PrettyBoxBot(2,1,East,99,
                                          new SideStratV2(6),
                                          new CornerStratV2(4));
    betty.makeBoxV2();
    betty.move();
  }
  
}
