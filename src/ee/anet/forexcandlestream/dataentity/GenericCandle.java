
package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author andreyutkin
 */
public class GenericCandle extends Candle {
    
    public GenericCandle(
                    LocalDateTime dateTimeStamp, 
                    double openBidQuote, 
                    double hightBidQuote, 
                    double lowBidQuote,
                    double closeBidQuote) {
        super(dateTimeStamp, openBidQuote, hightBidQuote, lowBidQuote, closeBidQuote);
    }

    
    @Override
    public String toString() {
        return  dateTimeStamp.format(DateTimeFormatter.ofPattern("yyyyMMdd HHmmss")) +
                ";" +
                String.format("%.6f", openBidQuote) +
                ";" +
                String.format("%.6f", hightBidQuote) +
                ";" +
                String.format("%.6f", lowBidQuote) +
                ";" +
                String.format("%.6f", closeBidQuote) +
                ";0";
    }
    
}
