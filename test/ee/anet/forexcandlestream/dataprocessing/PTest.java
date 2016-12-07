/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.anet.forexcandlestream.dataprocessing;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.Tick;
import ee.anet.forexcandlestream.dataentity.TruncTick;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andreyutkin
 */
public class PTest {
    
    public PTest() {
    }
    
    

    /**
     * Test of ticksToTrucatedTicks method, of class P.
     */
    @Test
    public void testTicksToTrucatedTicks() {
        System.out.println("ticksToTrucatedTicks");
        List<Tick> ticksList = new ArrayList<Tick>();
        ticksList.add(new Tick("20161001 000003660",1.306600,1.306770));
        ticksList.add(new Tick("20161001 000003973",1.306580,1.306750));
        ChronoUnit chronoUnit = ChronoUnit.MINUTES;
        List<TruncTick> expResult = new ArrayList<TruncTick>();
        expResult.add(new TruncTick("20161001 000003660","20161001 000000000", 1.306600, 1.306770, chronoUnit));
        expResult.add(new TruncTick("20161001 000003660","20161001 000000000", 1.306580, 1.306750, chronoUnit));
        List<TruncTick> result = P.ticksToTruncatedTicks(ticksList, chronoUnit);
        
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.get(0).dateTimeStampTruncated, result.get(0).dateTimeStampTruncated);
        assertEquals(expResult.get(1).dateTimeStampTruncated, result.get(1).dateTimeStampTruncated);
    }

    

    @Test
    public void testTicksToMinTicksStream() {
        System.out.println("ticksToMinTicksStream");
        List<TruncTick> ticks = null;
        Comparator<TruncTick> comparator = null;
        Stream<TruncTick> expResult = null;
        Stream<TruncTick> result = P.ticksToMinTicksStream(ticks, comparator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testTicksToMaxTicksStream() {
        System.out.println("ticksToMaxTicksStream");
        List<TruncTick> ticks = null;
        Comparator<TruncTick> comparator = null;
        Stream<TruncTick> expResult = null;
        Stream<TruncTick> result = P.ticksToMaxTicksStream(ticks, comparator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testStreamToMap() {
        System.out.println("streamToMap");
        Stream<TruncTick> inStream = null;
        Map<LocalDateTime, TruncTick> expResult = null;
        Map<LocalDateTime, TruncTick> result = P.streamToMap(inStream);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testTicksToCandles() {
        System.out.println("ticksToCandles");
        Map<LocalDateTime, TruncTick> openTicks = null;
        Map<LocalDateTime, TruncTick> closeTicks = null;
        Map<LocalDateTime, TruncTick> highTicks = null;
        Map<LocalDateTime, TruncTick> lowTicks = null;
        ArrayList<Candle> expResult = null;
        ArrayList<Candle> result = P.ticksToCandles(openTicks, closeTicks, highTicks, lowTicks);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    


    
}
