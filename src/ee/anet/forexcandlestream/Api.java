package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.CandleToString;
import ee.anet.forexcandlestream.dataentity.DataFile;

import java.io.IOException;

/**
 * Created by andreyutkin on 25/12/2016.
 */
public interface Api {

     static void calculateCandles(String inputFileName, String ouputFileName) throws IOException {
        new DataFile(inputFileName)
                .fileToLines()
                .linesToTicks()
                .ticksToTicksGrouped()
                .ticksGroupedToCandles(CandleToString.generic)
                .writeCandlesToFile(ouputFileName);
    }

}
