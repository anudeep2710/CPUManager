import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class RoundRobin_Policy extends SchedulingPolicy {
    private List<Process> processList;

    private int timeQuantum;

    // Constructor
    public RoundRobin_Policy(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    // Method to implement the Round Robin scheduling algorithm
    @Override
    public void schedule(List<Process> processList, long seedValue) {
        Queue<Process> readyQueue = new LinkedList<>();
        int currentTime = 0;
        Process currentprocess;

        // Process each job until both the process list and ready queue are empty
        while (!processList.isEmpty() || !readyQueue.isEmpty()) {
            // Add arriving processes to the ready queue
            while (!processList.isEmpty() && processList.get(0).getArrivalTime() <= currentTime) {
                readyQueue.offer(processList.remove(0));
            }


            // If there are processes in the ready queue, execute them
            if (!readyQueue.isEmpty()) {
                Process currentProcess = readyQueue.poll();

                // Set start time if the process is waiting
                if (currentProcess.getStatus().equals("Waiting")) {
                    currentProcess.setStatus("Running");
                    currentProcess.setStartTime(currentTime);
                }

                // Determine the execution time for the current process
                int executionTime = Math.min(timeQuantum, currentProcess.getBurstTime());
                currentTime += executionTime;

                // Update remaining burst time for the process
                currentProcess.setBurstTime(currentProcess.getBurstTime() - executionTime);

                // If the process is not completed, add it back to the ready queue
                if (currentProcess.getBurstTime() > 0) {
                    readyQueue.offer(currentProcess);
                    currentProcess.setStatus("Waiting");
                } else {
                    // Process completed, set completion time and status
                    currentProcess.setCompletionTime(currentTime);
                    currentProcess.setStatus("Completed");
                }
            } else {
                currentTime++; // Increment time if no processes are ready
            }
        }
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;


    }
}
