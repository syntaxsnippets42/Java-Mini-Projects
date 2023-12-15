import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame {
    private JLabel timeLabel;

    public DigitalClock() {
        // Set up the JFrame
        setTitle("Digital Clock");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the JLabel for displaying time
        timeLabel = new JLabel("", JLabel.CENTER);
        timeLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 60)); // Set the Digital-7 font and font size
        timeLabel.setForeground(Color.GREEN); // Set the font color to green
        timeLabel.setBackground(Color.BLACK); // Set the background color to black
        timeLabel.setOpaque(true); // Make the label opaque to show the background color
        updateTime(); // Initialize the time label

        // Set up a timer to update the time every second
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();

        getContentPane().add(timeLabel);
    }

    private void updateTime() {
        // Format the current time as HH:mm:ss (24-hour format)
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a"); // Use "a" for AM/PM
        String time = dateFormat.format(new Date());
        timeLabel.setText(time);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Create and display the DigitalClock JFrame
            DigitalClock digitalClock = new DigitalClock();
            digitalClock.setVisible(true);
        });
    }
}
