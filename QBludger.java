import kareltherobot.*;
import java.util.*;

public class QBludger extends Robot implements Quidditch
{
  
  public void doBludge() {
    move();
    
    if ( nextToARobot() ) {
      // knock the robot(s) back 2 spaces
      Strategy knockback = new QKnockBackStrat();
      
      // 4.7
      Enumeration<UrRobot> nbr = neighbors();
      
      while (nbr.hasMoreElements())
      {
        UrRobot oneNeighbor = nbr.nextElement();
        
        knockback.doIt(oneNeighbor);
      }
    }
  }
  
  public boolean isBludger() { return true; }
  public boolean isPlayer() { return false; }
  
  public QBludger(int r, int c, Direction d, int b)
  {
    super(r,c,d,b);
  }
  
  
}