
package ee.anet.forexcandlestream.dataentity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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

    public TickLines fileToLines() throws IOException {
        return new TickLines(getLinesList());
    }

    public void linesToFile(List<String> lines) throws IOException {
        Files.write(Paths.get(fileName), lines, StandardCharsets.UTF_8);
    }

    private List<String> getLinesList() throws IOException {
        return readAllLines(Paths.get(fileName));
    }


}
