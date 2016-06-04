package simulator;

import event.ArrivalEvent;
import event.OutputEvent;
import event.EventsReader;
import event.EventsWriter;

import java.io.File;
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
        //we read the arguments
        int numThreads = Integer.parseInt(args[0]);
        int queueSize = Integer.parseInt(args[1]);
        String path = args[2];
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

        Server server = new Server(numThreads, queueSize, eventsWriter);

        //we read the next events while there are still some left
        ArrivalEvent lastArrivalEvent = eventsReader.nextArrivalEvent();
        while (lastArrivalEvent != null && server.petitionsInServer() > 0) {
            server.advanceClock(lastArrivalEvent.getArrivalTime());
            server.petitionArrival(lastArrivalEvent);
            lastArrivalEvent = eventsReader.nextArrivalEvent();
        }

        //when there are no more incoming events, we finish processing the petitions in the server
        while (server.petitionsInServer() > 0) {
            server.advanceClock(server.nextOutTime());
        }

        //we close things
        eventsWriter.closeWriter();
    }//Simulator.main()
}//Simulator
