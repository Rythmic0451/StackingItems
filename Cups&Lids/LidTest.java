import static org.junit.Assert.*;
import org.junit.Test;

public class LidTest {

    @Test
    public void shouldCreateLid(){

        Lid l = new Lid(3,5,200,200);

        assertEquals(3,l.getCupIndex());
    }

    @Test
    public void shouldHaveValidCup(){

        Lid l = new Lid(1,5,200,200);

        assertTrue(l.getCupIndex()>0);
    }
}