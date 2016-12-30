package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.CandleLines;
import ee.anet.forexcandlestream.dataentity.DataFile;
import ee.anet.forexcandlestream.dataentity.TickLines;

import java.io.IOException;
import java.util.function.Function;

/**
 * Created by andreyutkin on 25/12/2016.
 */
public interface Api {

     static void calculateCandles(String inputFileName, String ouputFileName, Function<Candle, String> toLineFunc) throws IOException {

        TickLines tickLines = new DataFile(inputFileName).fileToLines();

        CandleLines candleLines =
         tickLines
                .linesToTicks()
                .ticksToTicksGrouped()
                .ticksGroupedToCandles()
                .getLines(toLineFunc);

         new DataFile(ouputFileName).linesToFile(candleLines.lines);
    }

}
