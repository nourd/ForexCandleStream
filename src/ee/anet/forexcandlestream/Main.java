
package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.Tick;
import ee.anet.forexcandlestream.dataentity.TruncTick;
import ee.anet.forexcandlestream.dataprocessing.DataFile;
import ee.anet.forexcandlestream.dataprocessing.P;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andreyutkin
 */
public class Main {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        DataFile inputFile, outputFile;
        List<Tick> ticks;
        List<TruncTick> truncTicks;
        Map<LocalDateTime, Double> openSemiCandle, highSemiCandle, lowSemiCandle, closeSemiCandle;
        List<Candle> candles;
        

        inputFile = new DataFile("/Users/andreyutkin/Downloads/HISTDATA_COM_ASCII_EURUSD_T201610/test.csv");

        ticks = inputFile.getTicks();
        truncTicks = P.ticksToTruncatedTicks(ticks, ChronoUnit.MINUTES);

        openSemiCandle = P.ticksToSemiCandleMap(truncTicks, P.byTimeStampAsc, P.openBi);
        highSemiCandle = P.ticksToSemiCandleMap(truncTicks, P.byBidQuoteAsc, P.highBi);
        lowSemiCandle = P.ticksToSemiCandleMap(truncTicks, P.byBidQuoteDesc, P.lowBi);
        closeSemiCandle = P.ticksToSemiCandleMap(truncTicks, P.byTimeStampDesc, P.closeBi);

        candles = P.semiCandlesToCandles(openSemiCandle, highSemiCandle, lowSemiCandle, closeSemiCandle);

        candles.forEach((c) -> System.out.println(c.toLine()));
    }
}
