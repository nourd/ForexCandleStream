
package ee.anet.forexcandlestream.dataprocessing;

import ee.anet.forexcandlestream.dataentity.Candle;
import ee.anet.forexcandlestream.dataentity.Tick;
import ee.anet.forexcandlestream.dataentity.TruncTick;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author andreyutkin
 */
public class DataFile {
    private final File dataFile;
    
    public DataFile(String fileName) throws FileNotFoundException {
        this.dataFile = new File(fileName);
    }
    
    /**
     *
     * @return
     */
    public List<Tick> getTicks() throws FileNotFoundException {
        List<Tick> ticksList = new ArrayList<>();
        String[] data;
        Scanner mScanner = null;
        
        try {
            mScanner = new Scanner(this.dataFile);
            while (mScanner.hasNextLine()) {
                data = mScanner.nextLine().split(",");
                ticksList.add(new  Tick(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2])));
            }
        }
        finally {
            if (mScanner != null) {
                mScanner.close();
            }
        }
        
        return ticksList;
    }
    
    public void writeCandle(ArrayList<Candle> candles) {
        
    }
}
