package event;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reads arrival events from an external file
 *
 * @author Daniel
 */
public class EventsReader {

    private BufferedReader inputEventsFile;

    /**
     * Constructor
     *
     * @param inputFile the pathname of the file to read the events from
     */
    public EventsReader(String inputFile) {
        try {
            this.inputEventsFile = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EventsReader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error; no se encuentra el fichero de eventos");
        }
    }

    /**
     * Reads the next arrival event and returns it. Automatically closes the
     * file when it reachs the end
     *
     * @return the next arrival event
     * @throws java.io.IOException
     */
    public ArrivalEvent nextArrivalEvent() throws IOException {
        String line = this.inputEventsFile.readLine();
        if (line == null) {
            this.inputEventsFile.close();
            return null;
        } else {
            StringTokenizer tokens = new StringTokenizer(line);
            float time = Float.parseFloat(tokens.nextToken());
            int eventID = Integer.parseInt(tokens.nextToken());
            float duration = Float.parseFloat(tokens.nextToken());

            return new ArrivalEvent(eventID, time, duration);
        }
    }
}
