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
    /**
     * Determines the number of the first x petitions that won't be written in
     * the output file (used to avoid outliers until the system stabilizes)
     */
    private int offset;

    public EventsWriter(String outputFile, int offset) {
        try {
            this.outputEventsFile = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException ex) {
            Logger.getLogger(EventsWriter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error; no se puede abrir el archivo");
        }
        this.offset = offset;
    }

    public void writeEvent(OutputEvent outputEvent) {
        if (outputEvent.getId() >= this.offset) {
            try {
                String line = outputEvent.getArrivalTime()
                        + " " + outputEvent.getId()
                        + " " + outputEvent.getServiceTime();

                if (outputEvent.isServed()) {
                    line += " " + "1"
                            + " " + outputEvent.getThreadTime()
                            + " " + outputEvent.getOutTime();
                } else {
                    line += " " + "0";
                }
                this.outputEventsFile.write(line);
                this.outputEventsFile.newLine();
            } catch (IOException ex) {
                Logger.getLogger(EventsWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error; no se puede escribir en el archivo");
            }
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
