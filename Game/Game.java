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

        w.startGame();

        Result result = recognizer.recognize();
        String resultText = result.getBestFinalResultNoFiller();
        System.out.println("Got result: "+ resultText);
        while(resultText.equals("")){
            result = recognizer.recognize();
            resultText = result.getBestFinalResultNoFiller();
            System.out.println("Got result: "+ resultText);
        }

        // loop the recognition until the programm exits.
        while (true) {
            int c, t = r.nextInt(8);
            do c = r.nextInt(8);
            while(c == t);
            w.showTextWithColor(t, c);
            result = recognizer.recognize();
            
            if (result != null) {
                resultText = result.getBestFinalResultNoFiller();
                w.showResult(resultText);
                System.out.println("You said: " + resultText + '\n');
            } else {
                System.out.println("I can't hear what you said.\n");
            }
        }
    }
}
