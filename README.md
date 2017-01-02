# ForexCandleStream
Java Stream API, DateTime API

### Статические методы в интерфейсах
Позволяют не плодить лишних сущностей, иногда можно ограничиться интерфейса с набором статических методов, например, интерфейс API.

### Использование функций вместо переопределения методов
Вместо наследования можно использовать передачу функции в качестве параметра метода или использовать поле типа Function<>, например:

public class Candle {

...

public String toLine(Function<Candle, String> toLineFunc) {
        
        return toLineFunc.apply(this);
    }
...

}

### Обобщённые методы

Функции позволяют нам писать один метод вместо нескольких, например, ниже приведённый метод используется для вычисления четырёх различных параметров: OPEN, HIGH, LOW и CLOSE


    private Double getBidQuote(List<Tick> ticks, Comparator c) {
        Tick t = (Tick) ticks.stream().sorted(c).findFirst().get();
        return t.bidQuote;
    }

в зависимости от того, какой компаратор мы передали в качестве параметра:

    private static Comparator<Tick> byTimeStampAsc = (t1, t2) -> t1.dateTimeStamp.compareTo(t2.dateTimeStamp);
    private static Comparator<Tick> byTimeStampDesc = (t1, t2) -> t2.dateTimeStamp.compareTo(t1.dateTimeStamp);
    private static Comparator<Tick> byBidQuoteAsc = (t1, t2) -> Double.compare(t1.bidQuote, t2.bidQuote);
    private static Comparator<Tick> byBidQuoteDesc = (t1, t2) -> Double.compare(t2.bidQuote, t1.bidQuote);


	public Candles ticksGroupedToCandles() {
			List<Candle> candles = new ArrayList<>();
			groupedTicks
					.entrySet()
					.stream()
					.forEach((e) -> {
						List<Tick> ticks = e.getValue();
							candles.add(new Candle(
									e.getKey(),
									getBidQuote(ticks, byTimeStampAsc),
									getBidQuote(ticks, byBidQuoteDesc),
									getBidQuote(ticks, byBidQuoteAsc),
									getBidQuote(ticks, byTimeStampDesc)));
					});
			return new Candles(candles);
		}
            
### Параллельная обработка




