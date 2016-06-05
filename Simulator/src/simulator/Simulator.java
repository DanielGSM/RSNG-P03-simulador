package simulator;

import event.ArrivalEvent;
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
        //Simulator.jar num_servers num_threads queue_size path filename
        int numServers = Integer.parseInt(args[0]);
        int numThreads = Integer.parseInt(args[1]);
        int queueSize = Integer.parseInt(args[2]);
        String inputFilename = args[3];
        String outputPath = args[4];
        System.out.println("Starting the simulator with " + numThreads
                + " threads and a queue of " + queueSize + " clients\n\n\n");

        /*
        //We generate the events file
        Process p = Runtime.getRuntime().exec("python .." + File.separator + ".." + File.separator + "Generator" + File.separator + "gen.py "
                + numServers + " "
                + numThreads + " "
                + queueSize + " "
                + path
                + " > inputEvents.txt"
        );
         */
        //the events reader is prepared
        EventsReader eventsReader = new EventsReader(inputFilename);

        //the events writer is prepared
        EventsWriter eventsWriter = new EventsWriter(
                outputPath + File.separator + "output " + numServers + " " + numThreads + " " + queueSize + ".txt");

        Server server = new Server(numThreads, queueSize, eventsWriter);

        //we read the next events while there are still some left
        ArrivalEvent lastArrivalEvent = eventsReader.nextArrivalEvent();
        while (lastArrivalEvent != null) {
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
