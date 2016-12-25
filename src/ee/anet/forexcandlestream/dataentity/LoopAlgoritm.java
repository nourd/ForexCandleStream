package ee.anet.forexcandlestream.dataentity;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

/**
 * Created by andreyutkin on 24/12/2016.
 */
public class LoopAlgoritm {


    public static Candles candlesFromFile(String fileName) throws IOException {
        DataFile inputFile;
        List<String> lines;
        LocalDateTime timeStamp, startInterval;
        List<Candle> orderedCandles;


//        inputFile = new DataFile(fileName);
        String line;
//        lines = inputFile.getLinesList();
        lines = readAllLines(Paths.get(fileName));
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
                orderedCandles.add(new Candle(startInterval, open, high, low, close, CandlesToString.generic));
                open = 0.0;
                close = 0.0;
                high = 0.0;
                low = 100000000.0;
                startInterval = startInterval.plusMinutes(1);
            }
        }
        return new Candles(orderedCandles);
    }



}
