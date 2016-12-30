
package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author andreyutkin
 */
public class Tick {
    public final LocalDateTime dateTimeStamp;
    public final LocalDateTime dateTimeStampTruncated;
    public final double bidQuote;

    public Tick(String dateTimeStampStr, String bidQuoteStr) {
        this.dateTimeStamp          = LocalDateTime.parse(dateTimeStampStr, DateTimeFormatter.ofPattern("yyyyMMdd HHmmssSSS"));
        this.dateTimeStampTruncated = this.dateTimeStamp.truncatedTo(ChronoUnit.MINUTES);
        this.bidQuote               = Double.parseDouble(bidQuoteStr);
    }

    public LocalDateTime getDateTimeStampTruncated() {
        return this.dateTimeStampTruncated;
    }

}
