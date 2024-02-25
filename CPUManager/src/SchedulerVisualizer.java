import java.util.List;

public abstract class SchedulerVisualizer {

    // Method to visualize scheduling results
    public abstract void  visualize(List<Process> processList);

    // Method to display scheduling summary
    public abstract void displaySummary(List<Process> processList);
}
