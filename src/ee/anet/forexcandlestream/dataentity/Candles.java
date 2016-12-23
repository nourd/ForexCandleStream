package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by andreyutkin on 22/12/2016.
 */
public class Candles {

    private final List<Candle> candles;
    final Comparator<Candle> byTimeComp  = (t1, t2) -> t1.dateTimeStamp.compareTo(t2.dateTimeStamp);

    public Candles(List<Candle> candles) {
        this.candles = candles;
    }

    public void printCandles() {
        candles.stream().sorted(byTimeComp).forEach(e -> System.out.println(e));
    }


}
