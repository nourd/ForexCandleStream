package ee.anet.forexcandlestream.dataprocessing;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.GenericCandle;
import ee.anet.forexcandlestream.dataentity.Tick;
import ee.anet.forexcandlestream.dataentity.TruncTick;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 *
 * @author andreyutkin
 */
public class P {
    public static  Comparator<TruncTick> openClose = (p1, p2) -> p1.dateTimeStamp.compareTo(p2.dateTimeStamp);
    public static  Comparator<TruncTick> lowHigh = (p1, p2) -> Double.compare( p1.bidQuote, p2.bidQuote);

    public static  Comparator<TruncTick> byTimeStampAsc = (t1, t2) -> t1.dateTimeStamp.compareTo(t2.dateTimeStamp);
    public static  Comparator<TruncTick> byTimeStampDesc = (t1, t2) -> t2.dateTimeStamp.compareTo(t1.dateTimeStamp);

    public static  Comparator<TruncTick> byBidQuoteAsc = (t1, t2) -> Double.compare( t1.bidQuote, t2.bidQuote);
    public static  Comparator<TruncTick> byBidQuoteDesc = (t1, t2) -> Double.compare( t2.bidQuote, t1.bidQuote);

    public static BinaryOperator<TruncTick> openBi = BinaryOperator.minBy(openClose);
    public static BinaryOperator<TruncTick> closeBi = BinaryOperator.maxBy(openClose);

    public static BinaryOperator<TruncTick> lowBi = BinaryOperator.minBy(lowHigh);
    public static BinaryOperator<TruncTick> highBi = BinaryOperator.maxBy(lowHigh);



    public static List<TruncTick> ticksToTruncatedTicks(List<Tick> ticks, ChronoUnit chronoUnit) {
        return ticks.stream().map(c -> new TruncTick(c, chronoUnit)).collect(Collectors.toList());
    }



    public static Map<LocalDateTime, Double> ticksToSemiCandleMap(List<TruncTick>truncTicks,
                                                                 Comparator<TruncTick> sortComparator,
                                                                 BinaryOperator<TruncTick> reducingOperator) {
        Map<LocalDateTime, Double> semiCandle = new HashMap<>();

        truncTicks.stream()
                .sorted(sortComparator)
                .collect(Collectors.groupingBy(c -> c.dateTimeStampTruncated, Collectors.reducing(reducingOperator)))
                .values()
                .forEach((t) -> { semiCandle.put(t.get().dateTimeStampTruncated, t.get().bidQuote);});

        return semiCandle;
    }

    public final static List<Candle> semiCandlesToCandles(
            Map<LocalDateTime, Double> openSemiCandle,
            Map<LocalDateTime, Double> highSemiCandle,
            Map<LocalDateTime, Double> lowSemiCandle,
            Map<LocalDateTime, Double> closeSemiCandle) {

        List<Candle> candles = new ArrayList<>();
        openSemiCandle.forEach((k,v) -> {
            candles.add(new GenericCandle(k, v, highSemiCandle.get(k), lowSemiCandle.get(k), closeSemiCandle.get(k)));
        });
        return candles;
    }

}
