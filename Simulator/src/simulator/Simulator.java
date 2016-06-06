package simulator;

import event.ArrivalEvent;
import event.EventsReader;
import event.EventsWriter;

import java.io.File;
import java.util.Random;
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
        //Simulator.jar num_servers rand/rr num_threads queue_size path filename
        int numServers = Integer.parseInt(args[0]); //the number of servers
        String serverSelection = args[1]; //the selection method of the server random (rand) or round-robin (rr)
        int numThreads = Integer.parseInt(args[2]); //the number of threads in each server
        int queueSize = Integer.parseInt(args[3]); //the queue size of each server
        String inputFilename = args[4]; //the filename (with path) of the input events file
        String outputPath = args[5]; //the path (without filename) for the output events file

        if (numServers < 1 || numThreads < 1 || queueSize < 0 || inputFilename == null || outputPath == null) {
            System.out.println("Error; at least one parameter is incorrect");
            System.exit(-1);
        }

        System.out.format("Starting the simulator with %d server(s), selecting the server by %s, with %d threads on each server and queues of %d client(s)\n\n\n", numServers, serverSelection, numThreads, queueSize);

        //the events reader is prepared
        EventsReader eventsReader = new EventsReader(inputFilename);

        //the events writer is prepared
        EventsWriter eventsWriter = new EventsWriter(
                outputPath + File.separator + "output " + numServers + " " + numThreads + " " + queueSize + ".txt");

        Server[] servers = new Server[numServers];
        for (int i = 0; i < numServers; i++) {
            servers[i] = new Server(numThreads, queueSize, eventsWriter);
        }

        //we process and send the incoming petitions to the servers
        switch (serverSelection.toLowerCase()) {
            case "rand": {
                Random rand = new Random();
                int x;

                //we read the next events while there are still some left
                ArrivalEvent lastArrivalEvent = eventsReader.nextArrivalEvent();
                while (lastArrivalEvent != null) {
                    for (int j = 0; j < numServers; j++) {
                        servers[j].advanceClock(lastArrivalEvent.getArrivalTime());
                    }

                    x = rand.nextInt(numServers);
                    //System.out.println("Assigning petition to server: " + x);
                    servers[x].petitionArrival(lastArrivalEvent);
                    lastArrivalEvent = eventsReader.nextArrivalEvent();
                }
                break;
            }
            case "rr": {
                int rr = 0;
                //we read the next events while there are still some left
                ArrivalEvent lastArrivalEvent = eventsReader.nextArrivalEvent();
                while (lastArrivalEvent != null) {
                    for (int j = 0; j < numServers; j++) {
                        servers[j].advanceClock(lastArrivalEvent.getArrivalTime());
                    }
                    //System.out.println("Assigning petition to server: " + rr % numServers);
                    servers[rr++ % numServers].petitionArrival(lastArrivalEvent);
                    lastArrivalEvent = eventsReader.nextArrivalEvent();
                }
                break;
            }
            default:
                System.out.println("ERROR; the server selection method is invalid");
                System.exit(-1);
        }

        //when there are no more incoming events, we finish processing the petitions in the server
        for (int i = 0; i < numServers; i++) {
            while (servers[i].petitionsInServer() > 0) {
                servers[i].advanceClock(servers[i].nextOutTime());
            }
        }

        //we close things
        eventsWriter.closeWriter();
    }//Simulator.main()
}//Simulator
