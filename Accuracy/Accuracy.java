import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.awt.*; 
import java.util.*;


public class Accuracy {

    public static void main(String[] args) {
        ConfigurationManager cm;
        if (args.length > 0) {
            cm = new ConfigurationManager(args[0]);
        } else {
            cm = new ConfigurationManager(Accuracy.class.getResource("accuracy.config.xml"));
        }

        String[] texts = new String[] {"black", "blue", "pink", "green", "white", "orange", "yellow", "red"};

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


        // loop the recognition until the programm exits.
        while (true) {
            int length = r.nextInt(3)+3;
            String[] words = new String[length];
            for(int i=0; i<length; i++){
                int random = r.nextInt(8);
                words[i] = texts[random];
                System.out.print(texts[random] + " ");
            }
            System.out.println();

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println(resultText);
                checkError(resultText, words);
            } else {
                System.out.println("I can't hear what you said.\n");
            }
            System.out.println();
        }
    }

    public static void checkError(String output, String[] given){
        String[] result = output.split(" ");
        int insertions = result.length>given.length ? result.length-given.length : 0;
        int deletions = result.length<given.length ? given.length-result.length : 0;
        System.out.println("Substitutions: " + substitution(result, given));
        System.out.println("Insertions:    " + insertions);
        System.out.println("Deletions:     " + deletions);
    }

    public static int substitution(String[] result, String[] given){
        int length = result.length>given.length ? given.length : result.length;
        int s = 0;
        for(int i=0; i<length; i++){
            if(!result[i].equals(given[i]))
                s++;
        }
        return s;
    }
}
