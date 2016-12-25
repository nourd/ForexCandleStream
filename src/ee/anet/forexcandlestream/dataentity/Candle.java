
package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.util.function.Function;

/*
 * @author andreyutkin
 */
public class Candle {

    public final LocalDateTime dateTimeStamp;

    public final double openBidQuote;
    public final double hightBidQuote;
    public final double lowBidQuote;
    public final double closeBidQuote;

    private final Function<Candle, String> toStringFunc;

    public Candle(
                LocalDateTime dateTimeStamp,
                double openBidQuote,
                double hightBidQuote,
                double lowBidQuote,
                double closeBidQuote, Function<Candle, String> toStringFunc) {

        this.dateTimeStamp = dateTimeStamp;
        this.openBidQuote = openBidQuote;
        this.hightBidQuote = hightBidQuote;
        this.lowBidQuote = lowBidQuote;
        this.closeBidQuote = closeBidQuote;
        this.toStringFunc = toStringFunc;
    }

    @Override
    public String toString() {
        return toStringFunc.apply(this);
    }
}
