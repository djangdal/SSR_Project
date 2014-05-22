import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.awt.*; 
import java.util.*;


public class Game {

    public static void main(String[] args) {
        ConfigurationManager cm;
        if (args.length > 0) {
            cm = new ConfigurationManager(args[0]);
        } else {
            cm = new ConfigurationManager(Game.class.getResource("game.config.xml"));
        }

        Window w = new Window();
        
        Random r = new Random();
        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit if the programm if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }

        System.out.println("Say: (Blue | Black | Yellow | Green | Orange | Purple)");

        // loop the recognition until the programm exits.
        while (true) {
            System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();

            int t = r.nextInt(8);
            int c;
            do{
                c = r.nextInt(8);
            }
            while(c == t);

            w.showTextWithColor(t, c);

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println("You said: " + resultText + '\n');
            } else {
                System.out.println("I can't hear what you said.\n");
            }
        }
    }
}
