package ee.anet.forexcandlestream.dataprocessing;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.GenericCandle;
import ee.anet.forexcandlestream.dataentity.Tick;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.util.Map.Entry;
import static java.util.stream.Collectors.toMap;

/**
 * Created by andreyutkin on 21/12/2016.
 */
public class StepByStepStream {

    List<String> lines;


    public static List<Tick> getTicksFromLines(List<String> lines) {
        List<Tick> ticks = new ArrayList<>();
        lines.stream().forEach((line) -> {
            String[] t = line.split(",");
            ticks.add(new Tick(t[0], t[1]));
        });
        return ticks;
    }

//    public static Map<LocalDateTime, Long> getTruncatedTicks(List<Tick> ticks) {
//        Map<LocalDateTime, Long> groupedTicks =
//            ticks
//                .stream()
//                .collect(Collectors.groupingBy(Tick::getDateTimeStampTruncated, Collectors.counting()));
//        return groupedTicks;
//    }

    public static Map<LocalDateTime, List<Tick>> getTruncatedTicks(List<Tick> ticks) {
        Map<LocalDateTime, List<Tick>> groupedTicks =
            ticks
                .stream()
                .collect(Collectors.groupingBy(Tick::getDateTimeStampTruncated));
        return groupedTicks;
    }

    public static List<Candle> getCandles(Map<LocalDateTime, List<Tick>> truncatedTicks) {
        List<Candle> candles = new ArrayList<>();
        truncatedTicks.entrySet().stream()
                .forEach((e) -> {
                    final Comparator<Tick> highLowComparator = (p1, p2) -> Double.compare( p1.getBidQuote(), p2.bidQuote);
                    final Comparator<Tick> openCloseComparator  = (t1, t2) -> t1.dateTimeStamp.compareTo(t2.dateTimeStamp);
                    Double open = e.getValue().stream().min(openCloseComparator).get().bidQuote;
                    Double high = e.getValue().stream().max(highLowComparator).get().getBidQuote();
                    Double low = e.getValue().stream().min(highLowComparator).get().bidQuote;
                    Double close = e.getValue().stream().max(openCloseComparator).get().bidQuote;
                    candles.add(new GenericCandle(e.getKey(),
                            open, high, low, close));
                });
        return candles;
    }




//    Collectors.counting()

    public static Function<LocalDateTime, LocalDateTime> truncateDate = d -> d.truncatedTo(ChronoUnit.MINUTES);

    public static LocalDateTime getTruncTime(LocalDateTime p) {
        return p.truncatedTo(ChronoUnit.MINUTES);
    }


}
