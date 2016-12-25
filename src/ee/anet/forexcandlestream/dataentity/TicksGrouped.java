package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by andreyutkin on 22/12/2016.
 */
public class TicksGrouped {

    private final Map<LocalDateTime, List<Tick>> groupedTicks;

    private static Comparator<Tick> byTimeStampAsc = (t1, t2) -> t1.dateTimeStamp.compareTo(t2.dateTimeStamp);
    private static Comparator<Tick> byTimeStampDesc = (t1, t2) -> t2.dateTimeStamp.compareTo(t1.dateTimeStamp);
    private static Comparator<Tick> byBidQuoteAsc = (t1, t2) -> Double.compare(t1.bidQuote, t2.bidQuote);
    private static Comparator<Tick> byBidQuoteDesc = (t1, t2) -> Double.compare(t2.bidQuote, t1.bidQuote);

    public TicksGrouped(Map<LocalDateTime, List<Tick>> groupedTicks) {
        this.groupedTicks = groupedTicks;
    }


    public Candles ticksGroupedToCandles(Function toStringFunc) {
        List<Candle> candles = new ArrayList<>();
        groupedTicks
                .entrySet()
                .stream()
                .forEach((e) -> {
                    List<Tick> ticks = e.getValue();
                        candles.add(new Candle(
                                e.getKey(),
                                getBidQuote(ticks, byTimeStampAsc),
                                getBidQuote(ticks, byBidQuoteDesc),
                                getBidQuote(ticks, byBidQuoteAsc),
                                getBidQuote(ticks, byTimeStampDesc),
                                toStringFunc));
                });
        return new Candles(candles);
    }


    private Double getBidQuote(List<Tick> ticks, Comparator c) {
        Tick t = (Tick) ticks.stream().sorted(c).findFirst().get();
        return t.bidQuote;
    }

}
