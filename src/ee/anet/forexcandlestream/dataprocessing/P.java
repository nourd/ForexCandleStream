package ee.anet.forexcandlestream.dataprocessing;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.GenericCandle;
import ee.anet.forexcandlestream.dataentity.Tick;
import ee.anet.forexcandlestream.dataentity.TruncTick;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static  Comparator<Candle> candleSortComparator = (p1, p2) -> p1.dateTimeStamp.compareTo(p2.dateTimeStamp);



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

    public static List<Candle> candlesFromFile(String fileName) throws IOException {
        DataFile inputFile, outputFile;
        List<Tick> ticks, ticksParall;
        List<TruncTick> truncTicks;
        Map<LocalDateTime, Double> openSemiCandle, highSemiCandle, lowSemiCandle, closeSemiCandle;
        List<Candle> candles, oderedCandles;
        List<String> lines;

        inputFile = new DataFile(fileName);
        lines = inputFile.getLinesFromFileStream();
        ticks = inputFile.linesToTicksSeq(lines);

        truncTicks = P.ticksToTruncatedTicks(ticks, ChronoUnit.MINUTES);

        openSemiCandle = P.ticksToSemiCandleMap(truncTicks, P.byTimeStampAsc, P.openBi);
        highSemiCandle = P.ticksToSemiCandleMap(truncTicks, P.byBidQuoteAsc, P.highBi);
        lowSemiCandle = P.ticksToSemiCandleMap(truncTicks, P.byBidQuoteDesc, P.lowBi);
        closeSemiCandle = P.ticksToSemiCandleMap(truncTicks, P.byTimeStampDesc, P.closeBi);

        candles = P.semiCandlesToCandles(openSemiCandle, highSemiCandle, lowSemiCandle, closeSemiCandle);

        return  candles.stream().sorted(candleSortComparator).collect(Collectors.toList());

    }

    public static List<Candle> candlesFromFileClassic(String fileName) throws IOException {
        DataFile inputFile;
        List<String> lines;
        LocalDateTime timeStamp, startInterval;
        List<Candle> orderedCandles;


        inputFile = new DataFile(fileName);
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
        return orderedCandles;
    }

}
