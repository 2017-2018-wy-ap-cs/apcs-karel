import kareltherobot.*;

public class QDemo implements Directions {
  public static void main(String[]args)
  {
    World.setVisible(true);
    World.setDelay(50);
    
    QBludger r = new QBludger(9,4,South,0);
    UrRobot victim = new UrRobot(5,4,East,0);
    
    r.doBludge();
    r.doBludge();
    r.doBludge();
    r.doBludge();
    r.doBludge();
  }
}