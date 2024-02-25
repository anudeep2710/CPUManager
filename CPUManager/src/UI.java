import javax.swing.*;
import java.util.List;

public abstract class UI {

    protected JFrame frame;

    // Constructor
    public UI(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Abstract method to initialize UI components
    public abstract void initComponents();

    // Method to display the UI
    public void display() {
        frame.setVisible(true);
    }

    // Method to visualize scheduling results in the UI
    public abstract void visualize(List<Process> processList);

    // Method to display summary statistics in the UI
    public abstract void displaySummary(List<Process> processList);
}
