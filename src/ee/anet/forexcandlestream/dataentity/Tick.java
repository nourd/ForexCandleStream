
package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author andreyutkin
 */
public class Tick {
    public final LocalDateTime dateTimeStamp;
    public final double bidQuote;
    public final double askQuote;
    
    public Tick(String dateTimeStampStr, double bidQuote, double askQuote) {
        this.dateTimeStamp = LocalDateTime.parse(dateTimeStampStr, DateTimeFormatter.ofPattern("yyyyMMdd HHmmssSSS"));
        this.bidQuote = bidQuote;
        this.askQuote = askQuote;
    }
    
    public Tick(LocalDateTime dateTimeStamp, double bidQuote, double askQuote) {
        this.dateTimeStamp = dateTimeStamp;
        this.bidQuote = bidQuote;
        this.askQuote = askQuote;
    }
    
}
