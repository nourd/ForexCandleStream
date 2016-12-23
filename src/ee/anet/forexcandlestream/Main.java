
package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.DataFile;

import java.io.IOException;

/**
 *
 * @author andreyutkin
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        new DataFile("test.csv")
                .getLines()
                .getTicks()
                .getTicksGrouped()
                .getCandles()
                .writeToFile("testOut1.csv");
    }


}
