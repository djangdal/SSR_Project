import java.awt.*; 
import javax.swing.*; 

public class Window {
	JLabel label;

    private String[] texts = new String[] {"Black", "Blue", "Pink", "Green", "White", "Orange", "Yellow", "Red"};
    private Color[] colors = new Color[] {Color.black, Color.blue, Color.pink, Color.green, Color.white, Color.orange, Color.yellow, Color.red};
	public Window(){
		// Create the frame
		JFrame frame = new JFrame("Simple GUI");
		frame.setSize(800,600);
		frame.setLocation(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		label = new JLabel("Say 'Start' to begin",SwingConstants.CENTER); 
		label.setFont(label.getFont().deriveFont(52.0f));
		label.setForeground(Color.black);
		label.setPreferredSize(new Dimension(500, 500)); 
		frame.getContentPane().add(label, BorderLayout.CENTER); 

		//Display the window. 
		frame.setVisible(true); 
	}

	public void showTextWithColor(int t, int c){
		label.setText(texts[t]);
		label.setForeground(colors[c]);
	}

} 