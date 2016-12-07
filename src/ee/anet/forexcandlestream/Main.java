
package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.Tick;
import ee.anet.forexcandlestream.dataentity.TruncTick;
import ee.anet.forexcandlestream.dataprocessing.DataFile;
import ee.anet.forexcandlestream.dataprocessing.P;
import ee.anet.forexcandlestream.dataprocessing.TickComparators;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author andreyutkin
 */
public class Main {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        final DataFile inputFile, outputFile;
        final Map<LocalDateTime, TruncTick> openTicks, closeTicks, highTicks, lowTicks;
        final List<Tick> ticks;
        final List<TruncTick> truncTicks;
        Stream open, close, high, low;
        ArrayList<Candle> candles = new ArrayList<Candle>();
        
        
        
        System.out.println("Start: " + LocalDateTime.now());
        
        inputFile = new DataFile("/Users/andreyutkin/Downloads/HISTDATA_COM_ASCII_EURUSD_T201610/test.csv");
        ticks = inputFile.getTicks();
        truncTicks = P.ticksToTruncatedTicks(ticks, ChronoUnit.MINUTES);
        System.out.println("Read from file: " + LocalDateTime.now());
        
        open = P.ticksToMinTicksStream(truncTicks, TickComparators.openClose);
        close = P.ticksToMaxTicksStream(truncTicks, TickComparators.openClose);
        high = P.ticksToMaxTicksStream(truncTicks, TickComparators.lowHigh);
        low = P.ticksToMinTicksStream(truncTicks, TickComparators.lowHigh);
        
        openTicks = P.streamToMap(open);
        closeTicks = P.streamToMap(close);
        highTicks = P.streamToMap(high);
        lowTicks = P.streamToMap(low);
        
        candles = P.ticksToCandles(openTicks, closeTicks, highTicks, lowTicks);
        System.out.println(candles);
        System.out.println("Candle ready: " + LocalDateTime.now());
        
    }
    
}
