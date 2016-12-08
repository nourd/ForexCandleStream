
package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.SemiCandle;
import ee.anet.forexcandlestream.dataentity.Tick;
import ee.anet.forexcandlestream.dataentity.TruncTick;
import ee.anet.forexcandlestream.dataprocessing.DataFile;
import ee.anet.forexcandlestream.dataprocessing.P;
import ee.anet.forexcandlestream.dataprocessing.TickComparators;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.sun.xml.internal.xsom.impl.UName.comparator;

/**
 *
 * @author andreyutkin
 */
public class Main {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        final DataFile inputFile, outputFile;
//        final Map<LocalDateTime, TruncTick> openTicks, closeTicks, highTicks, lowTicks;
        final List<Tick> ticks;
        final List<TruncTick> truncTicks;
        Stream open, close, high, low;
        ArrayList<Candle> candles = new ArrayList<Candle>();
        
        
        
        System.out.println("Start: " + LocalDateTime.now());
        
        inputFile = new DataFile("/Users/andreyutkin/Downloads/HISTDATA_COM_ASCII_EURUSD_T201610/test.csv");
        ticks = inputFile.getTicks();

        truncTicks = P.ticksToTruncatedTicks(ticks, ChronoUnit.MINUTES);

        System.out.println(ticks.get(0).bidQuote);
        Map<LocalDateTime, Double> maxTicks = new HashMap<>();
        Map<LocalDateTime, Double> maxTicksSorted = new HashMap<>();
        Map<LocalDateTime, Double> openTicks = new HashMap<>();
        Map<LocalDateTime, Double> openTicksSorted = new HashMap<>();
        Map<LocalDateTime, Double> closeTicks = new HashMap<>();
        Map<LocalDateTime, Double> closeTicksSorted = new HashMap<>();

//        maxTicks = truncTicks.stream().collect(Collectors.toMap(t -> t.dateTimeStamp, t -> t.bidQuote));
//        maxTicks = truncTicks.stream()
//                .collect(Collectors.groupingBy(c -> c.dateTimeStampTruncated, Collectors.minBy(TickComparators.openClose)))
//                .values()
//                .stream()
//                .map(Optional::get)
//                .collect(Collectors.toMap(t -> t.dateTimeStampTruncated, t -> t.bidQuote));
//
//        System.out.println(maxTicks);

//        maxTicks = truncTicks.stream()
//                .collect(Collectors.groupingBy(c -> c.dateTimeStampTruncated, Collectors.minBy(TickComparators.openClose)))
//                .values()
//                .stream()
//                .map(Optional::get)
//                .collect(Collectors.toMap(t -> t.dateTimeStampTruncated, t -> t.bidQuote));
//
//        System.out.println(maxTicks);

        Comparator<TruncTick> byTimeStampAsc = (t1, t2) -> t1.dateTimeStamp.compareTo(t2.dateTimeStamp);
        Comparator<TruncTick> byTimeStampDesc = (t1, t2) -> t2.dateTimeStamp.compareTo(t1.dateTimeStamp);

//        openTicks = truncTicks.stream()
//                .sorted(byTimeStampAsc)
//                .collect(Collectors.groupingBy(c -> c.dateTimeStampTruncated, Collectors.minBy(TickComparators.openClose)))
//                .values()
//                .stream()
//                .map(Optional::get)
//                .collect(Collectors.toMap(t -> t.dateTimeStampTruncated, t -> t.bidQuote));
//
//        openTicksSorted = crunchifySortByKey(openTicks);
//
//        closeTicks = truncTicks.stream()
//                .sorted(byTimeStampDesc)
//                .collect(Collectors.groupingBy(c -> c.dateTimeStampTruncated, Collectors.maxBy(TickComparators.openClose)))
//                .values()
//                .stream()
//                .map(Optional::get)
//                .collect(Collectors.toMap(t -> t.dateTimeStampTruncated, t -> t.bidQuote));
//
//        closeTicksSorted = crunchifySortByKey(closeTicks);
//
//
//
//        openTicksSorted.forEach((k,v)->System.out.println("Time : " + k + " bid : " + v));
//        closeTicksSorted.forEach((k,v)->System.out.println("Time : " + k + " bid : " + v));

        Collection<List<TruncTick>> openTicksList = truncTicks.stream()
                .sorted(byTimeStampAsc)
                .collect(Collectors.groupingBy(c -> c.dateTimeStampTruncated))
                .values();

        System.out.println("\n==> collection stream() util....");
        openTicksList.forEach((temp) -> {
            temp.forEach(((t) -> {System.out.println(t.dateTimeStampTruncated + " - " + t.dateTimeStamp);}));

        });

        BinaryOperator<TruncTick> openBi = BinaryOperator.minBy(TickComparators.openClose);
        BinaryOperator<TruncTick> closeBi = BinaryOperator.maxBy(TickComparators.openClose);

        Collection<Optional<TruncTick>> openTicksListTrunc = truncTicks.stream()
                .sorted(byTimeStampAsc)
                .collect(Collectors.groupingBy(c -> c.dateTimeStampTruncated, Collectors.reducing(closeBi)))
                .values();

        System.out.println("\n==> collection stream() util....");
        openTicksListTrunc.forEach((temp) -> {
            System.out.println(temp.get().dateTimeStampTruncated + " - " + temp.get().dateTimeStamp);

        });




//                collect(Collectors.toMap(t -> t.dateTimeStampTruncated, t -> t.bidQuote));
        
//        open = P.ticksToMinTicksStream(truncTicks, TickComparators.openClose);
//        close = P.ticksToMaxTicksStream(truncTicks, TickComparators.openClose);
//        high = P.ticksToMaxTicksStream(truncTicks, TickComparators.lowHigh);
//        low = P.ticksToMinTicksStream(truncTicks, TickComparators.lowHigh);
//
//        openTicks = P.streamToMap(open);
//        closeTicks = P.streamToMap(close);
//        highTicks = P.streamToMap(high);
//        lowTicks = P.streamToMap(low);
//
//        candles = P.ticksToCandles(openTicks, closeTicks, highTicks, lowTicks);
//        System.out.println(candles);
//        System.out.println("Candle ready: " + LocalDateTime.now());
        
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    // Let's sort HashMap by Key
    public static <K extends Comparable<? super K>, V> Map<K, V> crunchifySortByKey(Map<K, V> crunchifyMap) {

        Map<K, V> crunchifyResult = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> sequentialStream = crunchifyMap.entrySet().stream();

        // comparingByKey() returns a comparator that compares Map.Entry in natural order on key.
        sequentialStream.sorted(Map.Entry.comparingByKey()).forEachOrdered(c -> crunchifyResult.put(c.getKey(), c.getValue()));
        return crunchifyResult;
    }

    // Let's sort HashMap by Value
    public static <K, V extends Comparable<? super V>> Map<K, V> crunchifySortByValue(Map<K, V> crunchifyMap) {

        Map<K, V> crunchifyResult = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> sequentialStream = crunchifyMap.entrySet().stream();

        // comparingByValue() returns a comparator that compares Map.Entry in natural order on value.
        sequentialStream.sorted(Map.Entry.comparingByValue()).forEachOrdered(c -> crunchifyResult.put(c.getKey(), c.getValue()));
        return crunchifyResult;
    }


}
