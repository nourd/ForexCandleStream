package ee.anet.forexcandlestream.dataentity;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by andreyutkin on 24/12/2016.
 */
public class CandlesToString {
    public static final Function<Candle, String> generic =
            (c) -> {
                List<String> data = Arrays.asList(
                        c.dateTimeStamp.format(DateTimeFormatter.ofPattern("yyyyMMdd HHmmss")),
                        String.format("%.6f", c.openBidQuote),
                        String.format("%.6f", c.hightBidQuote),
                        String.format("%.6f", c.lowBidQuote),
                        String.format("%.6f", c.closeBidQuote));
                return data.stream().collect(Collectors.joining(";"));
            };
}
