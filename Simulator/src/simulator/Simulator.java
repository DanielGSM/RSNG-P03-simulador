package simulator;

import event.EventsReader;
import java.io.File;

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
            while (true) {
                System.out.println(eventsReader.nextEvent());
            }
        } catch (Exception e) {
        }
    }//Simulator.main()
}//Simulator
