package ee.anet.forexcandlestream.dataprocessing;

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

    public static Map<LocalDateTime, Long> getTruncatedTicks(List<Tick> ticks) {
        Map<LocalDateTime, Long> groupedTicks =
            ticks
                .stream()
                .collect(Collectors.groupingBy(Tick::getDateTimeStampTruncated, Collectors.counting()));


        return groupedTicks;
    }




//    Collectors.counting()

    public static Function<LocalDateTime, LocalDateTime> truncateDate = d -> d.truncatedTo(ChronoUnit.MINUTES);

    public static LocalDateTime getTruncTime(LocalDateTime p) {
        return p.truncatedTo(ChronoUnit.MINUTES);
    }


}
