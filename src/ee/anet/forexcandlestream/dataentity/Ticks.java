package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by andreyutkin on 22/12/2016.
 */
public class Ticks {

    private final List<Tick> ticks;

    public Ticks(List<Tick> ticks) {
        this.ticks = ticks;
    }

    public TicksGrouped getTicksGrouped() {
        Map<LocalDateTime, List<Tick>> ticksGrouped =
                ticks
                        .stream()
                        .collect(Collectors.groupingBy(Tick::getDateTimeStampTruncated));
        return new TicksGrouped(ticksGrouped);
    }
}
