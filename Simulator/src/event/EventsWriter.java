package event;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Writes output events to an external file
 *
 * @author Daniel
 */
public class EventsWriter {

    private BufferedWriter outputEventsFile;

    public EventsWriter(String outputFile) {
        try {
            this.outputEventsFile = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException ex) {
            Logger.getLogger(EventsWriter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error; no se puede abrir el archivo");
        }
    }

    public void writeEvent(OutputEvent outputEvent) {
        try {
            String line = outputEvent.getArrivalTime()
                    + " " + outputEvent.getId()
                    + " " + outputEvent.getServiceTime()
                    + " " + outputEvent.isServed();
            if (outputEvent.isServed()) {
                line += " " + outputEvent.getServerTime()
                        + " " + outputEvent.getOutTime();
            }
            this.outputEventsFile.write(line);
        } catch (IOException ex) {
            Logger.getLogger(EventsWriter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error; no se puede escribir en el archivo");
        }
    }

    public void closeWriter() {
        try {
            this.outputEventsFile.close();
        } catch (IOException ex) {
            Logger.getLogger(EventsWriter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error; al cerrar el archivo");
        }
    }
}
