import kareltherobot.*;

public abstract class QuidditchBot extends Robot implements Quidditch
{
  public abstract boolean isPlayer();
  
  public boolean isBludger() {
    return ! isPlayer();
  }

  public QuidditchBot(int r, int c, Direction d, int b)
  {
    super(r,c,d,b);
  }
}