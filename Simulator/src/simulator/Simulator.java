package simulator;

import event.ArrivalEvent;
import event.OutputEvent;
import event.EventsReader;
import event.EventsWriter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author Daniel
 */
public class Simulator {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        //The inner clock to keep track of the simulation of the system
        float clock = 0;

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

        Server server = new Server(numThreads, queueSize);

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
