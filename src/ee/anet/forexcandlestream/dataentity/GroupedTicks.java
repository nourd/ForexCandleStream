package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by andreyutkin on 22/12/2016.
 */
public class GroupedTicks {

    private final Map<LocalDateTime, List<Tick>> groupedTicks;
    private final Comparator<Tick> highLowComparator = (p1, p2) -> Double.compare(p1.getBidQuote(), p2.bidQuote);
    private final Comparator<Tick> openCloseComparator = (t1, t2) -> t1.dateTimeStamp.compareTo(t2.dateTimeStamp);

    public static Comparator<Tick> byTimeStampAsc = (t1, t2) -> t1.dateTimeStamp.compareTo(t2.dateTimeStamp);
    public static Comparator<Tick> byTimeStampDesc = (t1, t2) -> t2.dateTimeStamp.compareTo(t1.dateTimeStamp);

    public static Comparator<Tick> byBidQuoteAsc = (t1, t2) -> Double.compare(t1.bidQuote, t2.bidQuote);
    public static Comparator<Tick> byBidQuoteDesc = (t1, t2) -> Double.compare(t2.bidQuote, t1.bidQuote);


    public GroupedTicks(Map<LocalDateTime, List<Tick>> groupedTicks) {
        this.groupedTicks = groupedTicks;
    }

    public Candles getCandles() {
        List<Candle> candles = new ArrayList<>();
        groupedTicks
                .entrySet()
                .stream()
                .forEach((e) -> {
                    List<Tick> l = e.getValue();
                    candles.add(
                            new Candle(
                                    e.getKey(),
                                    getBidQuote(l, byTimeStampAsc),
                                    getBidQuote(l, byBidQuoteDesc),
                                    getBidQuote(l, byBidQuoteAsc),
                                    getBidQuote(l, byBidQuoteDesc)
                            )
                    );
                });
        return new Candles(candles);
    }

    private Double getBidQuote(List<Tick> ticks, Comparator c) {
        Tick t = (Tick) ticks.stream().sorted(c).findFirst().get();
        return t.bidQuote;
    }
}
