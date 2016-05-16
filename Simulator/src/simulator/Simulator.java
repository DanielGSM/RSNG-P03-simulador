package simulator;

import event.ArrivalEvent;
import event.EventsReader;
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
        EventsReader eventsReader = new EventsReader(
                File.separator + "opt3"
                + File.separator + "rsng"
                + File.separator + "rsng06"
                + File.separator + "Desktop"
                + File.separator + "entradas.txt");
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
