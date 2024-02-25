
import javax.swing.*;
import java.awt.*;
import java.util.List;

class GUIVisualizer extends SchedulerVisualizer {
    // Method to display scheduling results using JFrame
    @Override
    public void visualize(List<Process> processList) {
        if (processList == null || processList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Process list is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFrame frame = new JFrame("Scheduling Results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Append scheduling results to the text area
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

        frame.setVisible(true);
    }

    @Override
    public void displaySummary(List<Process> processList) {
        if (processList == null || processList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Process list is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFrame frame = new JFrame("Scheduling Summary");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Calculate and append scheduling summary to the text area
        StringBuilder sb = new StringBuilder();
        sb.append("Scheduling Summary:\n");

        // Calculate average turnaround time and average waiting time
        double totalTurnaroundTime = 0;
        double totalWaitingTime = 0;
        for (Process process : processList) {
            totalTurnaroundTime += process.calculateTurnaroundTime(process.getCompletionTime());
            totalWaitingTime += process.calculateWaitingTime(process.getStartTime());
        }
        double avgTurnaroundTime = totalTurnaroundTime / processList.size();
        double avgWaitingTime = totalWaitingTime / processList.size();

        sb.append("Average Turnaround Time: ").append(avgTurnaroundTime).append("\n");
        sb.append("Average Waiting Time: ").append(avgWaitingTime).append("\n");

        textArea.setText(sb.toString());

        frame.setVisible(true);
    }

}
