
package ee.anet.forexcandlestream.dataentity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/*
 * @author andreyutkin
 */
public class Candle {
    public final LocalDateTime dateTimeStamp;
    public final double openBidQuote;
    public final double hightBidQuote;
    public final double lowBidQuote;
    public final double closeBidQuote;

    public static Comparator<Tick> byTimeStampAsc = (t1, t2) -> t1.dateTimeStamp.compareTo(t2.dateTimeStamp);
    public static Comparator<Tick> byTimeStampDesc = (t1, t2) -> t2.dateTimeStamp.compareTo(t1.dateTimeStamp);

    public static Comparator<Tick> byBidQuoteAsc = (t1, t2) -> Double.compare(t1.bidQuote, t2.bidQuote);
    public static Comparator<Tick> byBidQuoteDesc = (t1, t2) -> Double.compare(t2.bidQuote, t1.bidQuote);


    public Candle(LocalDateTime dateTimeStamp, List<Tick> ticks) {
        this.dateTimeStamp = dateTimeStamp;
        this.openBidQuote = getBidQuote(ticks, byTimeStampAsc);
        this.hightBidQuote = getBidQuote(ticks, byBidQuoteDesc);
        this.lowBidQuote = getBidQuote(ticks, byBidQuoteAsc);
        this.closeBidQuote = getBidQuote(ticks, byTimeStampDesc);
    }

    @Override
    public String toString() {
        return dateTimeStamp.format(DateTimeFormatter.ofPattern("yyyyMMdd HHmmss")) +
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

    private Double getBidQuote(List<Tick> ticks, Comparator c) {
        Tick t = (Tick) ticks.stream().sorted(c).findFirst().get();
        return t.bidQuote;
    }

}
