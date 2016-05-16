package event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reads arrival events from an external file
 * 
 * @author Daniel
 */
public class EventsReader {

    private BufferedReader eventsFile;

    /**
     * Constructor
     * @param pathname the pathname of the file to read the events from
     */
    public EventsReader(String eventsFile) {
        try {
            this.eventsFile = new BufferedReader(new FileReader(eventsFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EventsReader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error; no se encuentra el fichero de eventos");
        }
    }

    /**
     * Reads the next arrival event and returns it
     * @return the next arrival event
     */
    public ArrivalEvent nextArrivalEvent() throws IOException {
        String line = eventsFile.readLine();
        if (line == null) {
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
