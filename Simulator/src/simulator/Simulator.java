package simulator;

import event.ArrivalEvent;
import event.OutputEvent;
import event.EventsReader;
import event.EventsWriter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Simulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //we read the arguments
        int numThreads = Integer.parseInt(args[0]);
        int queueSize = Integer.parseInt(args[1]);
        System.out.println("Starting the simulator with " + numThreads
                + " threads and a queue of " + queueSize + " clients\n\n\n");

        //the events reader is prepared
        EventsReader eventsReader = new EventsReader(
                File.separator + "opt3"
                + File.separator + "rsng"
                + File.separator + "rsng06"
                + File.separator + "Desktop"
                + File.separator + "entradas.txt");

        //the events writer is prepared
        EventsWriter eventsWriter = new EventsWriter(
                File.separator + "opt3"
                + File.separator + "rsng"
                + File.separator + "rsng06"
                + File.separator + "Desktop"
                + File.separator + "salidas.txt");

        //we read the next events while there are still some left
        try {
            ArrivalEvent lastArrivalEvent = eventsReader.nextArrivalEvent();
            while (lastArrivalEvent != null) {
                System.out.println(lastArrivalEvent);
                lastArrivalEvent = eventsReader.nextArrivalEvent();
            }//try
        } catch (IOException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }//catch

        //we close things
        eventsWriter.closeWriter();
    }//Simulator.main()
}//Simulator
