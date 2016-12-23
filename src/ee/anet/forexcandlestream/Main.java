
package ee.anet.forexcandlestream;

import ee.anet.forexcandlestream.dataentity.*;
import ee.anet.forexcandlestream.dataprocessing.DataFile;
import ee.anet.forexcandlestream.dataprocessing.StepByStepStream;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.reducing;

/**
 *
 * @author andreyutkin
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DataFile inputFile;
        Lines lines;
        GroupedTicks groupedTicks;
        LocalDateTime timeStamp, startInterval, start, endClassic, endStream;
        Ticks ticks;
//        Map<LocalDateTime, Map<LocalDateTime, Double>> groupedTicks;
//        Map<LocalDateTime, LocalDateTime> groupedTicks;

        String fileName = "test.csv";

//        Step By Step
        DataFile inpFile = new DataFile(fileName);


//        ticks = inpFile.getLines().getTicks();
//        groupedTicks = inpFile.getLines().getTicks().getGroupedTicks();
//        ticks = StepByStepStream.getTicksFromLines(lines);
//        Map<LocalDateTime, List<Tick>> truncatedTicks = StepByStepStream.getTruncatedTicks(ticks);
        Candles candles = inpFile.getLines().getTicks().getGroupedTicks().getCandles();
        System.out.println(candles);

        inpFile
                .getLines()
                .getTicks()
                .getGroupedTicks()
                .getCandles()
                .printCandles();


//        sortedCandles.stream().forEach(e -> System.out.println(e));










//        Map<LocalDateTime, Double> ticks = new HashMap<LocalDateTime, Double>();
////        Map<LocalDateTime, Double> sortedTicks = new HashMap<LocalDateTime, Double>();
//
//
//        lines.stream().forEach((line) -> {
//            String[] t = line.split(",");
//            ticks.put(LocalDateTime.parse(t[0], DateTimeFormatter.ofPattern("yyyyMMdd HHmmssSSS")), Double.parseDouble(t[1]));
//        });
//
//        System.out.println(ticks);
//
//
//
//        Map<LocalDateTime, Double>  sortedTicks = ticks.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByKey())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e2, LinkedHashMap::new));
//
//        System.out.println(sortedTicks);
//
//
//        Map<LocalDateTime, Double> tickTick = new HashMap<LocalDateTime, Double>();
//
//
//        Map<LocalDateTime, List<Double>>  ss = lines
//                .stream()
//                .collect(Collectors.groupingBy(Main::getTrunc, mapping(Main::getValue, toList())));
//
//        System.out.println(ss);
//
//        List<Double> lst = Arrays.asList(1.1236, 1.12366, 1.12372, 1.1236, 1.12358, 1.12358, 1.12358, 1.12358, 1.12358, 1.12343);

//        Function<List<Double>, Double> high = l -> l.stream().max(Double::compare).get();
//        Function<List<Double>, Double> low = l -> l.stream().min(Double::compare).get();
//        Function<List<Double>, Double> open = l -> l.stream().findFirst().get();
//        Function<List<Double>, Double> close = l -> l.stream().reduce((a, b) -> b).orElse(0.0);
////        Double first = lst.stream().findFirst().get();
////        Double last = lst.stream().reduce((a, b) -> b).orElse(0.0);;
//
////        System.out.println(last);
//
//        List<Candle> candles = new ArrayList<>();
//
//        System.out.println(LocalDateTime.now());
//
//        inputFile.getLinesFromFileStream()
//                .stream()
//                .collect(Collectors.groupingBy(Main::getTrunc, mapping(Main::getValue, toList())))
//
//                .forEach((k,v) -> {
//                    candles.add(new GenericCandle(k, open.apply(v), high.apply(v), low.apply(v), close.apply(v) ));
//                });
//
//        System.out.println(LocalDateTime.now());
//
//        List<Candle> candlesClassic = P.candlesFromFileClassic(fileName);
//
//        System.out.println(LocalDateTime.now());
//
//
//
//
////        ++++++++++++++++++++++++++++
//
//        Map<LocalDateTime, Double> ttt = inputFile.getLinesFromFileStream()
//                .stream()
//                .collect( Collectors.toMap(  l -> LocalDateTime.parse(l.split(",")[0], DateTimeFormatter.ofPattern("yyyyMMdd HHmmssSSS")),
//                        l -> Double.parseDouble(l.split(",")[1])));
//
//        List<Double> sss = ttt.entrySet().stream()
//                .map(p -> p.getValue()).collect(toList());
//
//        System.out.println(sss);
//
//
////                .collect(Collectors.groupingBy(c -> c.getKey(), mapping(Main::getValue, toList())))
////
//
//


    }


}
