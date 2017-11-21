import kareltherobot.*;

public class HackBot extends Robot
{
  public HackBot(int r, int c, Direction d, int b) {
    super(r,c,d,b);
  }
  
  public static void main (String[] args)
  {
    HackBot h = new HackBot(6,3,East,10);
    
    int y = h.avenue();
    int x = h.street();
    System.out.println("Haha! I am at x="+x+", y="+y);
    h.move();
    System.out.println("Now I am at x="+x+", y="+y);
  }
  
}