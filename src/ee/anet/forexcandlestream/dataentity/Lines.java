package ee.anet.forexcandlestream.dataentity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreyutkin on 22/12/2016.
 */
public class Lines {
    private final List<String> lines;

    public Lines(List<String> lines) {
        this.lines = lines;
    }


    public Ticks linesToTicks() {
        List<Tick> ticks = new ArrayList<>();
        lines.stream().forEach((line) -> {
            String[] t = line.split(",");
            ticks.add(new Tick(t[0], t[1]));
        });
        return new Ticks(ticks);
    }
}
