package ee.anet.forexcandlestream.dataprocessing;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.Tick;
import ee.anet.forexcandlestream.dataentity.TruncTick;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author andreyutkin
 */
public class P {

    public final static List<TruncTick> ticksToTruncatedTicks(List<Tick> ticks, ChronoUnit chronoUnit) {
        return ticks.stream().map(c -> new TruncTick(c, chronoUnit)).collect(Collectors.toList());
    }

    public final static Stream<TruncTick> ticksToMinTicksStream(List<TruncTick> ticks, Comparator<TruncTick> comparator) {
        return ticks.stream()
                .collect(
                        Collectors.groupingBy(c -> c.dateTimeStampTruncated, Collectors.minBy(comparator)))
                .values().stream().map(Optional::get);
    }

    public final static Stream<TruncTick> ticksToMaxTicksStream(List<TruncTick> ticks, Comparator<TruncTick> comparator) {
        return ticks.stream()
                .collect(
                        Collectors.groupingBy(c -> c.dateTimeStampTruncated, Collectors.maxBy(comparator)))
                .values().stream().map(Optional::get);
    }

    public final static Map<LocalDateTime, TruncTick> streamToMap(Stream<TruncTick> inStream) {
        return inStream
                .collect(
                        Collectors.toMap(t -> t.dateTimeStampTruncated, Function.identity()));
    }

    public final static ArrayList<Candle> ticksToCandles(
            Map<LocalDateTime, TruncTick> openTicks,
            Map<LocalDateTime, TruncTick> closeTicks,
            Map<LocalDateTime, TruncTick> highTicks,
            Map<LocalDateTime, TruncTick> lowTicks) {
        
        ArrayList<Candle> candles = new ArrayList<Candle>();
        for (LocalDateTime key : openTicks.keySet()) {
            candles.add(new Candle(openTicks.get(key).dateTimeStampTruncated,
                    openTicks.get(key).bidQuote,
                    highTicks.get(key).bidQuote,
                    lowTicks.get(key).bidQuote,
                    closeTicks.get(key).bidQuote));
        }
        return candles;
    }

    public static ArrayList<Candle> semiCandlesToCandles(
            Map<LocalDateTime, Double> openTicks,
            Map<LocalDateTime, Double> closeTicks,
            Map<LocalDateTime, Double> highTicks,
            Map<LocalDateTime, Double> lowTicks) {

        ArrayList<Candle> candles = new ArrayList<>();

        for (LocalDateTime key : openTicks.keySet()) {
            candles.add(
                    new Candle(key,openTicks.get(key).doubleValue(), highTicks.get(key).doubleValue(), lowTicks.get(key).doubleValue(), closeTicks.get(key).doubleValue())
            );
        }

        return candles;

    }
}
