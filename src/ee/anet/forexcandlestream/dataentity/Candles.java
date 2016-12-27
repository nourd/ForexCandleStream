package ee.anet.forexcandlestream.dataentity;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andreyutkin on 22/12/2016.
 */
public class Candles {

    private final List<Candle> candles;
    private final Comparator<Candle> sortByTimeComp = (t1, t2) -> t1.dateTimeStamp.compareTo(t2.dateTimeStamp);

    public Candles(List<Candle> candles) {
        this.candles = candles;
    }

    public void writeCandlesToFile(String fileName) throws IOException {
        new DataFile(fileName).writeLines(getLines());
    }

    private List<String> getLines() {
        return  candles
                .stream()
                .sorted(sortByTimeComp)
                .map(e -> e.toString()).collect(Collectors.toList());
    }


}
