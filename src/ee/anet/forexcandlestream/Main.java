
package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.CandleFunctions;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author andreyutkin
 *
 *       DAT_ASCII_EURUSD_T_201611.csv
 */
public class Main {

//fgsasdfgsdfgsdfg
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Api.calculateCandles("DAT_ASCII_EURUSD_T_201611.csv", "genericOut.csv", CandleFunctions.generic);
            Api.calculateCandles("DAT_ASCII_EURUSD_T_201611.csv", "metaTraderOut.csv", CandleFunctions.metaTrader);
            Api.calculateCandles("DAT_ASCII_EURUSD_T_201611.csv", "ninjaTraderOut.csv", CandleFunctions.ninjaTrader);
        } catch (Exception e) {
            System.out.println("Что-то пошло не так: " + e.getMessage());
        }



    }
}


