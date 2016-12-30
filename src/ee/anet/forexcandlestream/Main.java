
package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.CandleFunctions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author andreyutkin
 */
public class Main {

//fgsasdfgsdfgsdfg
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        LocalDateTime start;

//        /Users/andreyutkin/IdeaProjects/forexcandlestream/DAT_ASCII_EURUSD_T_201611.csv





        for (int ii=0; ii<1; ii++) {
            start = LocalDateTime.now();
            Api.calculateCandles("DAT_ASCII_EURUSD_T_201611.csv", "testOut.csv", CandleFunctions.generic);
            System.out.println("Par: " + start.until(LocalDateTime.now(), ChronoUnit.MILLIS));
            System.gc();
        }
    }




}


