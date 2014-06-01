import java.awt.*; 
import java.awt.event.*;
import javax.swing.*; 

public class Window {
	JLabel colorLabel;
	JLabel scoreLabel;
	JLabel resultLabel;
	JButton restartButton;
	Dimension size;
	String correctText;
	int corrects;

    private String[] texts = new String[] {"Black", "Blue", "Pink", "Green", "White", "Orange", "Yellow", "Red"};
    private Color[] colors = new Color[] {Color.black, Color.blue, Color.pink, Color.green, Color.white, Color.orange, Color.yellow, Color.red};
	public Window(){
		// Create the frame
		JFrame frame = new JFrame("Simple GUI");
		frame.setSize(800,600);
		frame.setLocation(100,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		//set layout on pane to null
		Container pane = frame.getContentPane();
		pane.setLayout(null);

		//add the restart button
		restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	startGame();
            }
        }); 
		pane.add(restartButton);
        size = restartButton.getPreferredSize();
        restartButton.setBounds(10, 600-size.height-30, size.width, size.height);

        //add color label
		colorLabel = new JLabel("Please wait..."); 
		colorLabel.setFont(colorLabel.getFont().deriveFont(60.0f));
		colorLabel.setForeground(Color.black);
		pane.add(colorLabel);
        size = colorLabel.getPreferredSize();
        colorLabel.setBounds(400-size.width/2, 70, size.width, size.height);

        //add result label
		resultLabel = new JLabel(""); 
		resultLabel.setFont(resultLabel.getFont().deriveFont(45.0f));
		resultLabel.setForeground(Color.black);
		pane.add(resultLabel);
        size = resultLabel.getPreferredSize();
        resultLabel.setBounds(400-size.width/2, 200, size.width, size.height);

        //add score label
		scoreLabel = new JLabel("Score: 0"); 
		scoreLabel.setFont(scoreLabel.getFont().deriveFont(30.0f));
		scoreLabel.setForeground(Color.black);
		pane.add(scoreLabel);
        size = scoreLabel.getPreferredSize();
        scoreLabel.setBounds(800-size.width-30, 600-size.height-30, size.width, size.height);

		//Display the window. 
		frame.setVisible(true); 
	}

	public void startGame(){
        corrects = 0;
		colorLabel.setText("Say something to begin!");
		resultLabel.setText("");
		scoreLabel.setText("Score: 0");
		colorLabel.setFont(colorLabel.getFont().deriveFont(45.0f));
		colorLabel.setForeground(Color.black);
        size = colorLabel.getPreferredSize();
        colorLabel.setBounds(400-size.width/2, 70, size.width, size.height);
	}

	public void showTextWithColor(int t, int c){
		correctText = texts[c];
		colorLabel.setText(texts[t]);
		colorLabel.setFont(colorLabel.getFont().deriveFont(100.0f));
		colorLabel.setForeground(colors[c]);
        size = colorLabel.getPreferredSize();
        colorLabel.setBounds(400-size.width/2, 70, size.width, size.height);
	}

	public void showResult(String res){
		String s = "incorrect!";
		String s1 = correctText.toLowerCase();
		String s2 = res.toLowerCase();
        if(s1.equals(s2)){
        	scoreLabel.setText("Score: " + ++corrects);
        	s = "correct!";
        }
		resultLabel.setText("<html>You said: " + res + "<br>" + s + "</html>");
        size = resultLabel.getPreferredSize();
        resultLabel.setBounds(400-size.width/2, 300, size.width, size.height);
	}

} 