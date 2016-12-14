
package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.GenericCandle;
import ee.anet.forexcandlestream.dataprocessing.DataFile;
import ee.anet.forexcandlestream.dataprocessing.P;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreyutkin
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DataFile inputFile;
        List<Candle> orderedCandles, orderedCandlesStream;

        List<String> lines;
        LocalDateTime timeStamp, startInterval, start, endClassic, endStream;

        for (int i = 0; i <= 0; i++) {
            System.out.println("Start: " + LocalDateTime.now());

                inputFile = new DataFile("/Users/andreyutkin/Downloads/HISTDATA_COM_ASCII_EURUSD_T201611/DAT_ASCII_EURUSD_T_201611.csv");
                String line;
                lines = inputFile.getLinesFromFileStream();
                String[] data = lines.get(0).split(",");
                startInterval = LocalDateTime.parse(data[0], DateTimeFormatter.ofPattern("yyyyMMdd HHmmssSSS")).truncatedTo(ChronoUnit.MINUTES);
                Double open = 0.0;
                Double close = 0.0;
                Double high = 0.0;
                Double low = 100000000.0;
                Double currentBid;
                orderedCandles = new ArrayList<Candle>();


                for (int j = 0; j < lines.size(); j++) {

                    line = lines.get(j);
                    data = line.split(",");
                    currentBid = Double.parseDouble(data[1]);
                    if (open.equals(0.0)) {
                        open = currentBid;
                    }
                    timeStamp = LocalDateTime.parse(data[0], DateTimeFormatter.ofPattern("yyyyMMdd HHmmssSSS"));
                    if (timeStamp.truncatedTo(ChronoUnit.MINUTES).equals(startInterval)) {
                        high = (high < currentBid) ? currentBid : high;
                        low = (low > currentBid) ? currentBid : low;
                        close = currentBid;
                    } else {
                        orderedCandles.add(new GenericCandle(startInterval, open, high, low, close));
//                System.out.println(new GenericCandle(startInterval,open,high,low,close).toString());
//                System.out.println(orderedCandles.size());
                        open = 0.0;
                        close = 0.0;
                        high = 0.0;
                        low = 100000000.0;
                        startInterval = startInterval.plusMinutes(1);
//                System.out.println(startInterval);
                    }
                }

                System.out.println("Classic: " + LocalDateTime.now());
                orderedCandlesStream = P.candlesFromFile("/Users/andreyutkin/Downloads/HISTDATA_COM_ASCII_EURUSD_T201611/DAT_ASCII_EURUSD_T_201611.csv");
                System.out.println("Stream: " + LocalDateTime.now());
//                orderedCandlesStream.forEach((c) -> System.out.println(c.toString()));
//        System.out.println(Period.between(start,endClassic,ChronoUnit.MILLIS) + " - " + Period.between(start,endClassic,ChronoUnit.MILLIS));

        }
    }
}
