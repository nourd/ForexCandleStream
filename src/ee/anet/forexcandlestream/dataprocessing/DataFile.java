
package ee.anet.forexcandlestream.dataprocessing;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.Lines;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;

/**
 * @author andreyutkin
 */
public class DataFile {
    private final File dataFile;
    private final String fileName;

    public DataFile(String fileName) throws FileNotFoundException {
        this.dataFile = new File(fileName);
        this.fileName = fileName;
    }

    public  List<String> getLinesList() throws  IOException {
        return  readAllLines(Paths.get(fileName));
    }

    public Lines getLines() throws  IOException {
        return new Lines(getLinesList());
    }
    public void writeCandle(ArrayList<Candle> candles) {

    }
}
