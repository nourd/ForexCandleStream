
package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;

/**
 *
 * @author andreyutkin
 */

public class MetaTraderCandle extends Candle {
    
    public MetaTraderCandle(
                    LocalDateTime dateTimeStamp, 
                    double openBidQuote, 
                    double hightBidQuote, 
                    double lowBidQuote,
                    double closeBidQuote) {
        super(dateTimeStamp, openBidQuote, hightBidQuote, lowBidQuote, closeBidQuote);
    }

    
    @Override
    public String toLine() {
        return  "";
    }
    
}
