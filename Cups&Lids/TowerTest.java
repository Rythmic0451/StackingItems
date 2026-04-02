import static org.junit.Assert.*;
import org.junit.Test;

public class TowerTest {

    @Test
    public void shouldPushCup(){

        Tower t = new Tower(10,100);

        t.pushCup(3);

        assertTrue(t.height()>0);
    }

    @Test
    public void shouldPopCup(){

        Tower t = new Tower(10,100);

        t.pushCup(3);
        t.popCup();

        assertEquals(0,t.height());
    }

    @Test
    public void shouldReverseTower(){

        Tower t = new Tower(10,100);

        t.pushCup(1);
        t.pushCup(2);

        t.reverseTower();

        assertTrue(t.height()>0);
    }
}