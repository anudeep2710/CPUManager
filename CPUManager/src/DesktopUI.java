import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DesktopUI extends UI {

    // Constructor
    public DesktopUI() {
        super("Scheduling Application", 800, 600);
    }

    public DesktopUI(String schedulerVisualization, int i, int i1) {
        super(schedulerVisualization, i, i1);
    }

    // Implementation of initComponents method
    @Override
    public void initComponents() {
        // Initialize desktop UI components using Swing
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Welcome to the Scheduling Application!");
        panel.add(label);

        frame.getContentPane().add(panel);
    }

    // Method to visualize scheduling results in the desktop UI
    public void visualize(List<Process> processList) {
        // Implement logic to visualize scheduling results in the desktop UI
        // For example, you can draw a Gantt chart or other graphical representation

        // Create a JPanel for visualization
        JPanel visualizationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Example: Draw a simple Gantt chart
                int x = 50;
                int y = 50;
                int barHeight = 20;
                int barSpacing = 5;

                for (Process process : processList) {
                    int startTime = process.getStartTime();
                    int endTime = process.getCompletionTime();
                    int barWidth = (endTime - startTime) * 10; // Adjust scale as needed

                    // Draw a rectangle representing the process
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, barWidth, barHeight);

                    // Draw process ID text
                    g.setColor(Color.BLACK);
                    g.drawString("P" + process.getProcessId(), x, y + barHeight + 15);

                    // Adjust coordinates for the next process
                    x += barWidth + barSpacing;
                }
            }
        };

        frame.getContentPane().removeAll(); // Clear previous components
        frame.getContentPane().add(visualizationPanel); // Add visualization panel
        frame.revalidate(); // Refresh the frame to reflect changes
    }

    // Method to display scheduling results in the desktop UI
    public void displaySchedulingResults(List<Process> processList) {
        // Implement logic to display scheduling results in the desktop UI
        // For example, you can use JTextArea to display each process's details
        JTextArea textArea = new JTextArea();
        StringBuilder sb = new StringBuilder();
        sb.append("Scheduling Results:\n");
        for (Process process : processList) {
            sb.append("Process ID: ").append(process.getProcessId())
                    .append(", Arrival Time: ").append(process.getArrivalTime())
                    .append(", Burst Time: ").append(process.getBurstTime())
                    .append(", Start Time: ").append(process.getStartTime())
                    .append(", Completion Time: ").append(process.getCompletionTime())
                    .append(", Status: ").append(process.getStatus()).append("\n");
        }
        textArea.setText(sb.toString());

        frame.getContentPane().removeAll(); // Clear previous components
        frame.getContentPane().add(new JScrollPane(textArea)); // Add JTextArea with scroll pane
        frame.revalidate(); // Refresh the frame to reflect changes
    }

    // Method to display summary statistics in the desktop UI
    public void displaySummary(List<Process> processList) {
        // Implement logic to display summary statistics in the desktop UI
        // For example, you can use JTextArea to display average turnaround time, average waiting time, etc.
        JTextArea textArea = new JTextArea();
        StringBuilder sb = new StringBuilder();
        sb.append("Summary Statistics:\n");
        // Calculate and append statistics here...
        textArea.setText(sb.toString());

        frame.getContentPane().removeAll(); // Clear previous components
        frame.getContentPane().add(new JScrollPane(textArea)); // Add JTextArea with scroll pane
        frame.revalidate(); // Refresh the frame to reflect changes
    }
}
