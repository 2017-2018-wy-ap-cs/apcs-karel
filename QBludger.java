import kareltherobot.*;
import java.util.*;

public class QBludger extends QuidditchBot
{
  
  public void doBludge() {
    move();
    
    if ( nextToARobot() ) {
      // knock the robot(s) back 2 spaces
      Strategy knockback = new QKnockBackStrat();
      
      // 4.7
      Enumeration<QuidditchBot> nbr = neighbors();
      
      while (nbr.hasMoreElements())
      {
        QuidditchBot oneNeighbor = nbr.nextElement();
        
        if (oneNeighbor.isPlayer()) {
          knockback.doIt(oneNeighbor);
        }
      }
    }
  }
  
  public boolean isPlayer() { return false; }
  
  public QBludger(int r, int c, Direction d, int b)
  {
    super(r,c,d,b);
  }
  
  
}