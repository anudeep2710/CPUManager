import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Simulator {
    private final List<Process> processList;
    private final SchedulingPolicy scheduler;
    private final int totalTime;
    private final UI visualizer;

    public Simulator(List<Process> processList, SchedulingPolicy scheduler, int totalTime, UI visualizer) {
        this.processList = processList;
        this.scheduler = scheduler;
        this.totalTime = totalTime;
        this.visualizer = visualizer;

    }

    public void runSimulation(int numberOfRuns, long seedValue) {
        for (int i = 0; i < numberOfRuns; i++) {
            // Run the scheduler
            scheduler.schedule(processList, seedValue);

            // Display scheduling results and Gantt chart
            displayResults();

            // Calculate statistics for scheduling results
            Statistics.calculateStatistics(processList);

            // Print turnaround time and waiting time for each process

            // Display scheduling results using the GUI visualizer
            visualizer.visualize(processList);
        }
    }

    private void displayResults() {
        if (visualizer != null) {
            visualizer.visualize(processList);
            visualizer.displaySummary(processList);
        } else {
            System.out.println("No visualizer available.");
        }

        // Create a GanttChart object and display it
        showGanttChart();
    }

    private void showGanttChart() {
        JFrame frame = new JFrame("Gantt Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (totalTime <= 0) {
                    g.setColor(Color.RED);
                    g.drawString("Invalid total time value", 50, 50);
                    return;
                }

                int timeUnitWidth = getWidth() / totalTime;

                for (Process process : processList) {
                    int startTime = process.getStartTime();
                    int endTime = process.getCompletionTime();
                    int processId = process.getProcessId();

                    int x = startTime * timeUnitWidth;
                    int y = 50 + (processId * 30);
                    int width = (endTime - startTime) * timeUnitWidth;
                    int height = 20;

                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, width, height);

                    g.setColor(Color.BLACK);
                    g.drawString("P" + processId, x + 5, y + 15);
                }
            }
        };

        frame.add(chartPanel);
        frame.setVisible(true);
    }


}
