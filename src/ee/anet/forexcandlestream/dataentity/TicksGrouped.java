package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andreyutkin on 22/12/2016.
 */
public class TicksGrouped {

    private final Map<LocalDateTime, List<Tick>> groupedTicks;

    public TicksGrouped(Map<LocalDateTime, List<Tick>> groupedTicks) {
        this.groupedTicks = groupedTicks;
    }

    public Candles getCandles() {
        List<Candle> candles = new ArrayList<>();
        groupedTicks
                .entrySet()
                .stream()
                .forEach((e) -> {
                    candles.add(new Candle(e.getKey(), e.getValue()));
                });
        return new Candles(candles);
    }
}
