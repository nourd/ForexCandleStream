
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

    public Candle(
                LocalDateTime dateTimeStamp,
                double openBidQuote,
                double hightBidQuote,
                double lowBidQuote,
                double closeBidQuote) {

        this.dateTimeStamp = dateTimeStamp;
        this.openBidQuote = openBidQuote;
        this.hightBidQuote = hightBidQuote;
        this.lowBidQuote = lowBidQuote;
        this.closeBidQuote = closeBidQuote;
    }

    public String toLine(Function<Candle, String> toLineFunc) {
        return toLineFunc.apply(this);
    }
}
