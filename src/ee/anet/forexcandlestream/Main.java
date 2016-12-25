
package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.Candles;
import ee.anet.forexcandlestream.dataentity.CandlesToString;
import ee.anet.forexcandlestream.dataentity.DataFile;
import ee.anet.forexcandlestream.dataentity.LoopAlgoritm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author andreyutkin
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        LocalDateTime start;

//        /Users/andreyutkin/IdeaProjects/forexcandlestream/DAT_ASCII_EURUSD_T_201611.csv


        for (int ii=0; ii<1; ii++) {
            start = LocalDateTime.now();
//            Candles candles =
            new DataFile("DAT_ASCII_EURUSD_T_201611.csv")
                    .fileToLines()
                    .linesToTicks()
                    .ticksToTicksGrouped()
                    .ticksGroupedToCandles(CandlesToString.generic)
                    .candlesToFile("testOut.csv");

            System.out.println("Result: " + start.until(LocalDateTime.now(), ChronoUnit.MILLIS));


            start = LocalDateTime.now();
            Candles candles = LoopAlgoritm.candlesFromFile("DAT_ASCII_EURUSD_T_201611.csv");
            candles.candlesToFile("testOutLoop.csv");
            System.out.println("Loop: " + start.until(LocalDateTime.now(), ChronoUnit.MILLIS));


//
        }
    }
}
