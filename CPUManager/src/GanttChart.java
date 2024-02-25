import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GanttChart extends JFrame {
    private List<Process> processList;
    private int totalTime;

    private static final Color[] PROCESS_COLORS = {
            Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.MAGENTA,
            Color.CYAN, Color.YELLOW, Color.PINK, Color.LIGHT_GRAY, Color.DARK_GRAY
    };

    public GanttChart(List<Process> processList, int totalTime) {
        this.processList = processList;
        this.totalTime = totalTime;

        setTitle("Gantt Chart");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        initComponents();
    }

    private void initComponents() {
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (totalTime <= 0) {
                    // Handle invalid totalTime value
                    g.setColor(Color.RED);
                    g.drawString("Invalid totalTime value", 50, 50);
                    return; // Exit early to avoid further processing
                }

                // Calculate the width of each time unit in the chart
                int timeUnitWidth = 2;

                // Draw processes as rectangles on the chart
                for (int i = 0; i < processList.size(); i++) {
                    Process process = processList.get(i);
                    int startTime = process.getStartTime();
                    int endTime = process.getCompletionTime();
                    int processId = process.getProcessId();

                    int x = startTime * timeUnitWidth;
                    int y = 50 + (processId * 30);
                    int width = (endTime - startTime) * timeUnitWidth;
                    int height = 20;

                    // Use a different color for each process
                    g.setColor(PROCESS_COLORS[i % PROCESS_COLORS.length]);
                    g.fillRect(x, y, width, height);

                    g.setColor(Color.BLACK);
                    g.drawString("P" + processId, x + 5, y + 15);
                }

                // Draw labels for start and end times
                g.setColor(Color.BLACK);
                g.drawString("0", 0, 25); // Start time
                g.drawString(String.valueOf(totalTime), getWidth() - 20, 25); // End time
            }
        };

        // Add the chart panel to the frame
        add(chartPanel);
    }


    public void showChart() {
        setVisible(true);
    }
}
