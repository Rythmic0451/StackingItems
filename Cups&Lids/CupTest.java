import static org.junit.Assert.*;
import org.junit.Test;

public class CupTest {

    @Test
    public void shouldCreateCup(){

        Cup c = new Cup(3,200,200);

        assertEquals(5,c.height());
    }

    @Test
    public void shouldNotHaveNegativeHeight(){

        Cup c = new Cup(1,200,200);

        assertTrue(c.height()>0);
    }
}