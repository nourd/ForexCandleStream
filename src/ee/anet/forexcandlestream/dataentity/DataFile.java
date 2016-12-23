
package ee.anet.forexcandlestream.dataentity;

import ee.anet.forexcandlestream.dataentity.Lines;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.readAllLines;

/**
 * @author andreyutkin
 */
public class DataFile {
    private final String fileName;

    public DataFile(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }

    public Lines getLines() throws IOException {
        return new Lines(getLinesList());
    }

    public void writeLines(List<String> lines) throws IOException {
        Path path = Paths.get(fileName);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    private List<String> getLinesList() throws IOException {
        return readAllLines(Paths.get(fileName));
    }

}
