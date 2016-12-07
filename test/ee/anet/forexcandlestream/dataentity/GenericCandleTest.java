
package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andreyutkin
 */
public class GenericCandleTest {
    
    public GenericCandleTest() {
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        LocalDateTime candleDate = LocalDateTime.of(2016, 12, 1, 0, 1, 0);
        GenericCandle instance = new GenericCandle(candleDate, 1.306570, 1.306570, 1.306470, 1.306560);
        String expResult = "20161201 000100;1.306570;1.306570;1.306470;1.306560;0";
        String result = instance.toString();
        assertEquals(expResult, result);
        
        candleDate = LocalDateTime.of(2016, 12, 1, 18, 1, 0);
        instance = new GenericCandle(candleDate, 1.306570, 1.306570, 1.306470, 1.306560);
        expResult = "20161201 180100;1.306570;1.306570;1.306470;1.306560;0";
        result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
