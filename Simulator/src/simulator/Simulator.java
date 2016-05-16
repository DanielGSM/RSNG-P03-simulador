package simulator;

import event.ArrivalEvent;
import event.EventsReader;
import event.EventsWriter;
import event.OutputEvent;
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
        
        //we prepare the queues
        EventsWriter prueba = new EventsWriter(
                
                File.separator + "opt3"
                + File.separator + "rsng"
                + File.separator + "rsng06"
                + File.separator + "Desktop"
                + File.separator + "salidas.txt");
        OutputEvent e = new OutputEvent(1, (float) 2.3,  (float)3.4, true,  (float)5.6,  (float)8.7);
        System.out.println(e.toString());
        prueba.writeEvent(e);
        prueba.closeWriter();
        
        
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
    }//Simulator.main()
}//Simulator
