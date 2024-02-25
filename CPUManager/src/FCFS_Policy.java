import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class FCFS_Policy extends SchedulingPolicy {

    // Method to implement the First-Come, First-Served (FCFS) policy
    @Override
    public void schedule(List<Process> processList, long seedValue) {
        // Sort processes based on arrival time (FCFS)
        processList.sort((p1, p2) -> p1.getArrivalTime() - p2.getArrivalTime());

        // Initialize the current time
        int currentTime = 0;

        // Iterate through the sorted process list
        for (Process process : processList) {
            // Set the start time of the process
            process.setStartTime(Math.max(currentTime, process.getArrivalTime()));

            // Update the current time to account for the process execution
            currentTime = process.getStartTime() + process.getBurstTime();

            // Set the completion time of the process
            process.setCompletionTime(currentTime);

            // Update the status of the process
            process.setStatus("Completed");
        }

        // Display scheduling results using JFrame
        JFrame frame = new JFrame("FCFS Scheduling Results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Append scheduling results to the text area
        StringBuilder sb = new StringBuilder();
        sb.append("FCFS Scheduling Results:\n");
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

}

