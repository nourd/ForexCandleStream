
package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 
 * 
 *
 * @author andreyutkin
 */
public class TruncTick extends Tick {
    public final LocalDateTime dateTimeStampTruncated;
    
    
    public TruncTick(String dateTimeStampStr, double bidQuote, double askQuote, ChronoUnit chronoUnit) {
        super(dateTimeStampStr,  bidQuote,  askQuote);
        this.dateTimeStampTruncated = this.dateTimeStamp.truncatedTo(chronoUnit);
    }
    
    public TruncTick(Tick tick, ChronoUnit chronoUnit) {
        super(tick.dateTimeStamp,  tick.bidQuote,  tick.askQuote);
        this.dateTimeStampTruncated = this.dateTimeStamp.truncatedTo(chronoUnit);
    }
    
    public TruncTick(String dateTimeStampStr, String dateTimeStampTruncStr,double bidQuote, double askQuote, ChronoUnit chronoUnit) {
        super(dateTimeStampStr,   bidQuote,  askQuote);
        this.dateTimeStampTruncated = LocalDateTime.parse(dateTimeStampTruncStr, DateTimeFormatter.ofPattern("yyyyMMdd HHmmssSSS"));
    }
    
}
